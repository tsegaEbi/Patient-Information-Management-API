package com.therapy.therapy.document;

import com.therapy.therapy.examination.labOrder.Image.LabOrderResultImage;
import com.therapy.therapy.examination.labOrder.LabOrder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface DocumentService {

    File uploadProfilePhoto(Long id, MultipartFile file) throws IOException;

    File uploadLabResultPhoto(LabOrder labOrder,Long resultImageId, MultipartFile file) throws IOException;

    ByteArrayResource getLabResultPhoto(LabOrderResultImage image) throws IOException;

    ByteArrayResource getProfilePhoto(Long id) throws IOException;

}
