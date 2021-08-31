package com.therapy.therapy.document;

import com.therapy.therapy.examination.labOrder.Image.LabOrderResultImage;
import com.therapy.therapy.examination.labOrder.LabOrder;
import com.therapy.therapy.patient.checkup.PatientVisitServiceImp;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(DocumentServiceImp.class);

    @Autowired
    ServletContext context;

    @Override
    public  File uploadLabResultPhoto(LabOrder order,Long fileId, MultipartFile file) throws IOException {

        String path=this.labResultPhotoPath+"/"+order.getLaboratory().getCategory()+"/"+order.getLaboratory().getName();

        File dir =new File(path);
        if(!dir.exists()){
            if(dir.mkdirs()){
                byte[] bytes = file.getBytes();

                String filePath=path+"/"+String.valueOf(fileId).concat(".jpg");
                File newFile = new File(filePath);
                file.transferTo(newFile);
                return newFile;
            }
            else
            {
                throw new IOException("Directory can't be created");
            }
        }
        else{
            byte[] bytes = file.getBytes();

            String filePath=path+"/"+String.valueOf(fileId).concat(".jpg");
            File newFile = new File(filePath);
            file.transferTo(newFile);
            return newFile;
        }

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
    @Override
    public ByteArrayResource getLabResultPhoto(LabOrderResultImage image) throws IOException {

        String filePath=this.labResultPhotoPath+"/"+image.getLabOrder().getLaboratory().getCategory()+"/"+image.getLabOrder().getLaboratory().getName()+"/"+String.valueOf(image.getId()).concat(".jpg");
        final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(filePath )));
        return inputStream;
    }
}
