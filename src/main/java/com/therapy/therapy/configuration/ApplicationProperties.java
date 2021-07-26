package com.therapy.therapy.configuration;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApplicationProperties {

    private final Security security = new Security();

    private String projectFileStorageLocation;

    private String employeePhotoStorageLocation;

    @Data
    public static class Security {

        private final ClientAuthorization clientAuthorization = new ClientAuthorization();

        private final Authentication authentication = new Authentication();

        private final RememberMe rememberMe = new RememberMe();

        private final OAuth2 oauth2 = new OAuth2();

        @Data
        public static class ClientAuthorization {

            private String accessTokenUri;

            private String tokenServiceId;

            private String clientId;

            private String clientSecret;
        }

        @Data
        public static class Authentication {

            private final Jwt jwt = new Jwt();

            private String tempSecret;

            @Setter
            @Getter
            public static class Jwt {

                private String secret;

                private String base64Secret;

                private long tokenValidityInSeconds = 300; // 5 minutes;

                private long tokenValidityInSecondsForRememberMe = 300; // 5 minutes;
            }
        }

        @Data
        public static class RememberMe {
            private String key;
        }

        @Data
        public static class OAuth2 {
            private List<String> audience = new ArrayList<>();
        }
    }
}
