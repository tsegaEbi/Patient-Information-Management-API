package com.therapy.therapy.finance.serviceItem.servicePackage;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter

public class ServicePackageDTO {
    private Long id;
    private String packageName;
    private Long serviceId;
    private String serviceName;

    private Double price;
    private Boolean active;

    public static ServicePackageDTO toDto(ServicePackage pac){
        ServicePackageDTO dto = new ServicePackageDTO();
        dto.setServiceId(pac.getService().getId());
        dto.setActive(pac.getActive());
        dto.setPackageName(pac.getPackageName());
        dto.setServiceName(pac.getService().getName());
        dto.setPrice(pac.getPrice());

        dto.setId(pac.getId());
        return dto;
    }

    public static List<ServicePackageDTO> toList(List<ServicePackage> pacs){

        return pacs.stream().map(t->toDto(t)).collect(Collectors.toList());
    }
}
