package com.therapy.therapy.examination.labOrder;

import com.therapy.therapy.common.ORDER_STATUS;
import com.therapy.therapy.common.POSITIVE_NEGATIVE;
import com.therapy.therapy.examination.ExaminationDTO;
import com.therapy.therapy.examination.labOrder.Image.LabOrderResultImage;
import com.therapy.therapy.examination.labOrder.Image.LabOrderResultImageDTO;
import com.therapy.therapy.laboratory.LaboratoryDTO;
import com.therapy.therapy.patient.checkup.PatientVisitDTO;
import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class LabOrderDTO {
    private Long id;
    private LaboratoryDTO laboratory;

    private POSITIVE_NEGATIVE findingStatus;

    private ExaminationDTO examination;

    private String finding;
    private StaffDTO technician;

    private ORDER_STATUS orderStatus;

    private Boolean active;

    private Date dateCreated;

    private PatientVisitDTO visit;

    private Long examinationId;

    private List<LabOrderResultImageDTO> resultImages;

    public static LabOrderDTO toDTOWithOutStaff(LabOrder order ){
        if(order==null)
            return null;

        LabOrderDTO dto= new LabOrderDTO();

        dto.setId(order.getId());
        dto.setLaboratory(LaboratoryDTO.toDto(order.getLaboratory()));
        dto.setFinding(order.getFinding());
        dto.setFindingStatus(order.getFindingStatus());

        dto.setOrderStatus(order.getOrderStatus());

        dto.setActive(order.getActive());

        dto.setDateCreated(order.getDateCreated());

        dto.setVisit(PatientVisitDTO.toLabOrderDTO(order.getExamination().getPatientVisit()));

        dto.setExaminationId(order.getExamination().getId());


        return dto;
    }

    public static LabOrderDTO toDTO(LabOrder order, Staff staff){
        if(order==null)
            return null;

        LabOrderDTO dto= new LabOrderDTO();

        dto.setId(order.getId());
        dto.setLaboratory(LaboratoryDTO.toDto(order.getLaboratory()));
        dto.setFinding(order.getFinding());
        dto.setFindingStatus(order.getFindingStatus());

        dto.setOrderStatus(order.getOrderStatus());

        dto.setActive(order.getActive());

        dto.setDateCreated(order.getDateCreated());

        dto.setVisit(PatientVisitDTO.toLabOrderDTO(order.getExamination().getPatientVisit()));

        dto.setExaminationId(order.getExamination().getId());

        if(staff!=null)
            dto.setTechnician(StaffDTO.toDTO(staff));

        return dto;
    }
    public static LabOrderDTO toDetailDTO(LabOrder order, Staff staff){
        if(order==null)
            return null;

        LabOrderDTO dto= new LabOrderDTO();

        dto.setId(order.getId());
        dto.setLaboratory(LaboratoryDTO.toDto(order.getLaboratory()));
        dto.setFinding(order.getFinding());
        dto.setFindingStatus(order.getFindingStatus());

        dto.setOrderStatus(order.getOrderStatus());

        dto.setActive(order.getActive());

        dto.setDateCreated(order.getDateCreated());


        dto.setExaminationId(order.getExamination().getId());

        dto.setExamination(ExaminationDTO.toDTO(order.getExamination(),null));

        if(staff!=null)
            dto.setTechnician(StaffDTO.toDTO(staff));

        return dto;
    }
    public static LabOrderDTO toDetailDTO(LabOrder order, List<LabOrderResultImage> images, Staff staff){
        if(order==null)
            return null;

        LabOrderDTO dto= new LabOrderDTO();

        dto.setId(order.getId());
        dto.setLaboratory(LaboratoryDTO.toDto(order.getLaboratory()));
        dto.setFinding(order.getFinding());
        dto.setFindingStatus(order.getFindingStatus());

        dto.setOrderStatus(order.getOrderStatus());

        dto.setActive(order.getActive());

        dto.setDateCreated(order.getDateCreated());


        dto.setExaminationId(order.getExamination().getId());

        dto.setExamination(ExaminationDTO.toDTO(order.getExamination(),null));

        if(staff!=null)
            dto.setTechnician(StaffDTO.toDTO(staff));

        if(images!=null && images.size()>0){
            dto.setResultImages(images.stream().map(i->LabOrderResultImageDTO.toDTO(i)).collect(Collectors.toList()));
        }
        return dto;
    }
}
