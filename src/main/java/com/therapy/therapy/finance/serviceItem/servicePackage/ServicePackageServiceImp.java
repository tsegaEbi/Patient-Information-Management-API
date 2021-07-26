package com.therapy.therapy.finance.serviceItem.servicePackage;

import com.therapy.therapy.finance.serviceItem.ServiceItem;
import com.therapy.therapy.finance.serviceItem.ServiceItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ServicePackageServiceImp implements ServicePackageService{

    private final ServicePackageRepository repository;

    private final ServiceItemRepository itemRepository;

    public ServicePackageServiceImp(ServicePackageRepository repository, ServiceItemRepository itemRepository){

        this.repository =repository;

        this.itemRepository =itemRepository;
    }
    @Override
    public ServicePackage get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<ServicePackage> list(Pageable pageable) {
        return repository.findAll(pageable);
    }
    @Override
    public List<ServicePackage> getAll(){

        return repository.findAll();
    }

    @Override
    public List<ServicePackage> getAllActive() {
        return repository.findByActive(true);
    }

    @Override
    public ServicePackage addServicePackage(String packageName, Double price, ServiceItem item){
        if(getByPackageNameAndPrice(packageName,price)==null)
            throw new IllegalArgumentException("Package name and price already exists");

        ServicePackage servicePackage = new ServicePackage();

        servicePackage.setService(item);
        servicePackage.setActive(Boolean.TRUE);
        servicePackage.setPrice(price);
        servicePackage.setPackageName(packageName);


        return repository.save(servicePackage);
    }
    @Override
    public ServicePackage add(ServicePackage servicePackage) throws Exception {

        return repository.save(servicePackage);
    }

    @Override
    public ServicePackage update(ServicePackage servicePackage) {
        return repository.save(servicePackage);
    }

    @Override
    @Transactional
    public void delete(ServicePackage servicePackage) {
            repository.delete(servicePackage);
    }

    @Override
    public List<ServicePackage> getByService(ServiceItem item) {


        if(item!=null)
         return  repository.findByService(item);

        return null;
    }

    @Override
    public List<ServicePackage> getByServiceActives(ServiceItem item) {
         if(item!=null)
            return  repository.findByServiceAndActive(item,true);

        return null;
    }

    @Override
    public ServicePackage getByPackageNameAndPrice(String packageName, Double price) {
        return repository.findByPackageNameAndPrice(packageName,price);
    }
}
