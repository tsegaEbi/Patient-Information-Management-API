package com.therapy.therapy.finance.serviceItem;

import com.therapy.therapy.common.CRUDService;

import java.util.List;

public interface ServiceItemService extends CRUDService<ServiceItem> {

    List<ServiceItem> getByKey(String key);

    ServiceItem getByName(String name);

    List<ServiceItem> listAll();



}
