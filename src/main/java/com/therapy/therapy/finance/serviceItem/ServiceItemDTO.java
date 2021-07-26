package com.therapy.therapy.finance.serviceItem;

import com.therapy.therapy.finance.serviceItem.servicePackage.ServicePackageDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter

public class ServiceItemDTO {
    private Long id;
    private String name;
    private String description;
    private SERVICE_CATEGORY category;

    private List<ServicePackageDTO> packages;

    public static ServiceItemDTO toDTO(ServiceItem item,List<ServicePackageDTO>pacs){

        ServiceItemDTO dto = new ServiceItemDTO();
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setId(item.getId());
        dto.setCategory(item.getCategory());

        if(pacs!=null)
          dto.setPackages(pacs);

        return dto;
    }

    public static ServiceItemDTO toDTO(String name, String description,SERVICE_CATEGORY category){

        ServiceItemDTO dto = new ServiceItemDTO();
        dto.setName(name.toUpperCase());
        dto.setDescription(description.toUpperCase());
        dto.setCategory(category);

        return dto;
    }
    public static ServiceItem  toItem(String name, String description,SERVICE_CATEGORY category){

        ServiceItem  dto = new ServiceItem ();
        dto.setName(name.toUpperCase());
        dto.setDescription(description.toUpperCase());
        dto.setCategory(category);

        return dto;
    }
    public static ServiceItemDTO toDTO(ServiceItem item){

        ServiceItemDTO dto = new ServiceItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName().toUpperCase());
        dto.setDescription(item.getDescription().toUpperCase());
        dto.setCategory(item.getCategory());

        return dto;
    }
    public static ServiceItem  toItemForUpdate(ServiceItemDTO dto){

        ServiceItem item = new ServiceItem();
        item.setId(dto.getId());
        item.setName(dto.getName().toUpperCase());
        item.setDescription(dto.getDescription().toUpperCase());
        item.setCategory(dto.getCategory());

        return item;
    }

    public static List<ServiceItemDTO> toList(List<ServiceItem> service){

        return service.stream().map(t->toDTO(t)).collect(Collectors.toList());
    }
}
