package com.therapy.therapy.finance.serviceItem.servicePackage;

import com.therapy.therapy.finance.serviceItem.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicePackageRepository extends JpaRepository<ServicePackage,Long > {

    List<ServicePackage> findByService(ServiceItem Service);

    ServicePackage findByPackageNameAndPrice(String name, Double price);

    List<ServicePackage> findByActive(Boolean active);

    List<ServicePackage> findByServiceAndActive(ServiceItem service, Boolean active);

}
