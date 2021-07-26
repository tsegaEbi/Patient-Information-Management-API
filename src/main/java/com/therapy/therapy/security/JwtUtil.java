package com.therapy.therapy.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffService;
import com.therapy.therapy.user.UserEntity;
import com.therapy.therapy.user.UserEntityService;
import com.therapy.therapy.user.roles.userRole.UserRole;
import com.therapy.therapy.user.roles.userRole.UserRoleService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtUtil {
    private final String SECRET_KEY="secretKeyForTherapySolutionInDenbelUsingsecretKeyForTherapySolutionInDenbelUsingdfgsdgsdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfg";
    private static final String CLAIMS_USER = "user";
    private static final String AUTHORITIES_KEY = "auth";


    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private StaffService staffService;

    Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public Date extractExpiration(String token){

            return extractClaim(token, Claims::getExpiration);
    }
    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){

        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String createToken(Map<String,Object> claims, String subject) {
        byte[] keyBytes;
        UserEntity userEntity =userEntityService.getByStaffId(subject);
        Staff staff = staffService.getByStaffId(subject);


        keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        Key key = Keys.hmacShaKeyFor(keyBytes);
        HashMap<String, Object> userClaim = new HashMap<>();
        userClaim.put("staffId", subject);
        userClaim.put("id", staff.getId());
        if(staff!=null)
        userClaim.put("name",staff.getFirstName()+" "+staff.getFatherName() );

        if(userEntity!=null)
        {
            List<UserRole> roles =userRoleService.getByUserId(userEntity.getId());
            logger.info("found ROle="+roles.get(0).getRole().getName());
            if(roles!=null && roles.size()>0){
                userClaim.put("Roles", roles);
            }
        }

        return Jwts.builder()
                .claim(CLAIMS_USER, new ObjectMapper().convertValue(userClaim, JsonNode.class).toString())
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 30 * 30 * 10))
                .signWith(key)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String userName=extractUserName(token);
        return (userName.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token){

        return extractExpiration(token).before(new Date());
    }
    public String generateToken(UserDetails userDetails){
        Map<String,Object>claims =new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }
    public void expireToken(String token){

    }
    public Authentication getAuthentication(String token) {
        byte[] keyBytes;

        keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        Key key = Keys.hmacShaKeyFor(keyBytes);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String userString = claims.get(CLAIMS_USER).toString();

        String subject = "";
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (null != userString && !userString.isEmpty()) {
            try {
                JsonNode userJson = new ObjectMapper().readTree(userString);
                subject = userJson.get("Email").asText();
                userJson.get("Roles").elements()
                        .forEachRemaining(c -> {
                            authorities.add(new SimpleGrantedAuthority(c.asText()));
                        });

            } catch (JsonProcessingException e) {
                //log.error("Error parsing jwt json.", e);
            }
        }
        User principal = new User(subject, "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public static String getUserId()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String staffId=null;
        if (principal instanceof UserDetails) {
              staffId = ((UserDetails)principal).getUsername();
        } else {
            staffId = principal.toString();
        }
        return staffId;
    }

}
