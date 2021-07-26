package com.therapy.therapy.finance.serviceItem.servicePackage;

import com.therapy.therapy.finance.serviceItem.ServiceItem;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ServicePackageCreateDTO {
    private Long serviceId;
    private Double price;
    private Boolean active=true;
    private String packageName;

    public static ServicePackage toPackage(String packageName, Double price, ServiceItem item){
        ServicePackage pac = new ServicePackage();
        pac.setPackageName(packageName);
        pac.setPrice(price);
        pac.setActive(true);

        if(item!=null)
         pac.setService(item);

        return pac;
    }

}
