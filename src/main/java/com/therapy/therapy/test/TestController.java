package com.therapy.therapy.test;

import org.apache.catalina.core.ApplicationContext;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/test")



public class TestController {

    @RequestMapping
    public List<String> GetHeaders(@RequestHeader Map<String,String> headers, @RequestAttribute("body")String body){
        List<String>result=new ArrayList<>();
        for(String k: headers.keySet()){
            result.add(k+"=>"+headers.get(k));
        }
        result.add("Modified Request="+body);
        return result;
    }
   @GetMapping("/image")
    public @ResponseBody byte[] getImage() throws IOException {
       URL url = new URL("https://images.freeimages.com/images/large-previews/636/holding-a-dot-com-iii-1411477.jpg");
       InputStream input = url.openStream();
       return input.readAllBytes();
   }
}
