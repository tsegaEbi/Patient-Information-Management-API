package com.therapy.therapy.finance.serviceItem.servicePackage;

import com.therapy.therapy.common.CRUDService;
import com.therapy.therapy.finance.serviceItem.ServiceItem;

import java.util.List;

public interface ServicePackageService extends CRUDService<ServicePackage> {

    List<ServicePackage> getByService(ServiceItem service);

    List<ServicePackage>getByServiceActives(ServiceItem service);

    ServicePackage getByPackageNameAndPrice(String packageName, Double price);

    ServicePackage addServicePackage(String packageName, Double price, ServiceItem item);

    List<ServicePackage> getAll();

    List<ServicePackage> getAllActive();
}
