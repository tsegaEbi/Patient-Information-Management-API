package com.therapy.therapy.finance.serviceItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {

    ServiceItem findByName(String name);

}
