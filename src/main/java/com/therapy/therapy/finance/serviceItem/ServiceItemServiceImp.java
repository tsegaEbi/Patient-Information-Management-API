package com.therapy.therapy.finance.serviceItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ServiceItemServiceImp implements ServiceItemService {
    private final ServiceItemRepository repository;

    public ServiceItemServiceImp(ServiceItemRepository repository){
        this.repository =repository;
    }
    @Override
    public List<ServiceItem> getByKey(String key) {
        return null;
    }

    @Override
    public ServiceItem getByName(String name) {
        name=name.toUpperCase();

        return repository.findByName(name);
    }

    @Override
    public List<ServiceItem> listAll() {
        return repository.findAll();
    }

    @Override
    public ServiceItem get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<ServiceItem> list(Pageable pageable) {
        return null;
    }

    @Override
    public ServiceItem add(ServiceItem service) throws Exception {

        if(validateNew(service))
        {
            repository.save(service);
            return service;
        }
        else
            throw new IllegalArgumentException("Service already exists");
    }

     private boolean validateNew(ServiceItem service){
        if(repository.findByName(service.getName())!=null)
        {
            return false;
        }
        return true;
    }
    @Override
    @Transactional
    public ServiceItem update(ServiceItem service) {
      ServiceItem oldService = get(service.getId());

      if(oldService!=null)
      {
         return repository.save(oldService);
      }

        return null;
    }

    @Override
    @Transactional
    public void delete(ServiceItem service) throws Exception {
        try {
            repository.delete(service);
        }
        catch (Exception e)
        {
            throw new Exception("Error");
        }
    }
}
