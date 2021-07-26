package com.therapy.therapy.finance.serviceItem;
import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.finance.serviceItem.servicePackage.ServicePackage;
import com.therapy.therapy.finance.serviceItem.servicePackage.ServicePackageCreateDTO;
import com.therapy.therapy.finance.serviceItem.servicePackage.ServicePackageDTO;
import com.therapy.therapy.finance.serviceItem.servicePackage.ServicePackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin

@RequestMapping("/service")
public class ServiceItemController {

    @Autowired
    private ServiceItemService serviceService;

    @Autowired
    private ServicePackageService packageService;


    @PostMapping("/create")
    public ActionResponse<ServiceItemDTO> create(@RequestBody ServiceItemDTO dto) throws Exception {
        ActionResponse<ServiceItemDTO> response =new ActionResponse<>();
        response.setResult(false);

        if(dto.getName()==null && dto.getName().length()==0)
        {
            response.setMessage("Service name can't be empty");
            return response;
        }

        try {
            ServiceItem item = serviceService.add(ServiceItemDTO.toItem(dto.getName(), dto.getDescription(),dto.getCategory()));
            response.setMessage("valid");
            response.setResult(true);
            response.setT(ServiceItemDTO.toDTO(item));

        }
        catch (Exception e){
            response.setMessage(e.getMessage());
         }

        return response;
    }

    @GetMapping("/list")
    public List<ServiceItemDTO> list() {
        List<ServiceItem> services =serviceService.listAll();
        List<ServiceItemDTO> serviceDtos =new ArrayList<>();

        if(services!=null)
        {
            for (ServiceItem item:services
                 ) {
                 serviceDtos.add(ServiceItemDTO.toDTO(item,ServicePackageDTO.toList(packageService.getByService(item))));
            }
        }
          return serviceDtos;
    }

    @PostMapping("/addPackage")
    public List<ServicePackageDTO> addPackage(@RequestBody ServicePackageCreateDTO dto) throws Exception {

        if(dto.getPackageName()==null && dto.getPrice()==null)
            throw new IllegalArgumentException("Package name and price must be valid");

        if(dto.getServiceId()==null)
            throw new IllegalArgumentException("Service is unknown");

        ServiceItem item =serviceService.get(dto.getServiceId());

        if(item==null)
            throw new IllegalArgumentException("Service is unknown");

        ServicePackage pac= packageService.add(ServicePackageCreateDTO.toPackage(dto.getPackageName(),dto.getPrice(),item));

        return ServicePackageDTO.toList(packageService.getAll());
    }

    @GetMapping("/{id}")
    public ServiceItemDTO get(@PathVariable("id")Long id){

        ServiceItem item =serviceService.get(id);

        if(item!=null){

            return ServiceItemDTO.toDTO(item,ServicePackageDTO.toList(packageService.getByService(item)));

        }
        return null;
    }
    @GetMapping("/active/{id}")
    public ServiceItemDTO getWithActivePackage(@PathVariable("id")Long id){

        ServiceItem item =serviceService.get(id);

        if(item!=null){

            return ServiceItemDTO.toDTO(item,ServicePackageDTO.toList(packageService.getByServiceActives(item)));
         }
        return null;
    }

    @PostMapping("/package/update/active/{id}")
    public ServicePackageDTO updatePackageActive(@PathVariable("id")Long id) throws Exception {
        ServicePackage pac=packageService.get(id);

        if(pac!=null){
            pac.setActive(!pac.getActive());
            packageService.update(pac);
        }
        return ServicePackageDTO.toDto(pac);
    }
    @PostMapping("/update")
    public ServiceItemDTO updateService(@RequestBody ServiceItemDTO dto) throws Exception {
        ServiceItem item = serviceService.update(ServiceItemDTO.toItemForUpdate(dto));

        if(item!=null)
             return  ServiceItemDTO.toDTO(item,ServicePackageDTO.toList(packageService.getByService(item)));

        return null;
    }
    @PostMapping("/removeService")
    public Boolean removeService(@RequestBody Long id)  {
        try
        {
            serviceService.delete(serviceService.get(id));
            return true;
        } catch (Exception e) {
             return false;
        }
    }
    @PostMapping("/removePackage")
    public boolean removePackage(@RequestBody Long id)  {
         try{
             ServicePackage pac =packageService.get(id);
             packageService.delete(packageService.get(id));
             return true;
         }
         catch (Exception e) {
            return false;
         }
    }
}
