package com.therapy.therapy.examination.labOrder.Image;

import com.therapy.therapy.examination.labOrder.LabOrderDTO;
import com.therapy.therapy.staff.StaffDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class LabOrderResultImageDTO {
    private Long id;
    private LabOrderDTO labOrder;
    private StaffDTO staff;
    private Date dateCreated;

    public static LabOrderResultImageDTO toDTO(LabOrderResultImage image){
        LabOrderResultImageDTO dto= new LabOrderResultImageDTO();

        dto.setDateCreated(image.getDateCreated());
        dto.setId(image.getId());
        dto.setLabOrder(LabOrderDTO.toDTOWithOutStaff(image.getLabOrder()));
        dto.setStaff(StaffDTO.toDTO(image.getStaff()));
        return dto;
    }
}
