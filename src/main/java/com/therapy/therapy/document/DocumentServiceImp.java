package com.therapy.therapy.document;

import com.therapy.therapy.examination.labOrder.LabOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

@Configuration
@ConfigurationProperties(prefix = "document")
@Setter
@Getter

public class DocumentServiceImp implements DocumentService {
    private String profilePhotoPath;
    private String labResultPhotoPath;

    @Autowired
    ServletContext context;
    @Override
    public  File uploadLabResultPhoto(LabOrder order, MultipartFile file) throws IOException {

        int year = Calendar.getInstance().get(Calendar.YEAR);

        File dir =new File(this.labResultPhotoPath);
        if(!dir.exists()){
            dir.mkdir();


        }

        byte[] bytes = file.getBytes();

        String filePath=this.profilePhotoPath+"/"+String.valueOf(order.getId()).concat(".jpg");
        File newFile = new File(filePath);
        file.transferTo(newFile);
        return newFile;
    }
    @Override
    public  File uploadProfilePhoto(Long id, MultipartFile file) throws IOException {


        File dir =new File(this.profilePhotoPath);
        if(!dir.exists()){
            dir.mkdir();
        }

        byte[] bytes = file.getBytes();

        String filePath=this.profilePhotoPath+"/"+String.valueOf(id).concat(".jpg");
        File newFile = new File(filePath);
        file.transferTo(newFile);
        return newFile;
    }
    @Override
    public ByteArrayResource getProfilePhoto(Long id) throws IOException {
//        String filePath=this.profilePhotoPath+"/"+String.valueOf(id).concat(".jpg");
//        File file = new File(filePath);
//        byte[] fileContent = Files.readAllBytes(file.toPath());
//        return fileContent;
        String filePath=this.profilePhotoPath+"/"+String.valueOf(id).concat(".jpg");
        final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(filePath )));
        return inputStream;
    }
}
