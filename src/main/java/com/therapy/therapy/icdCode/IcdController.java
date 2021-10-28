package com.therapy.therapy.icdCode;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.examination.HelathProblem.HealthProblemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/icd")
public class IcdController {

    @Autowired
    private IcdService icdService;

   @Autowired
   private IcdSubCategoryService subCategoryService;

  @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL')")
   @PostMapping("/add")
   public ActionResponse<IcdDTO> add(@RequestBody IcdCreateDTO dto) throws Exception {
       ActionResponse<IcdDTO> response =new ActionResponse<>();
       if(dto.getSubCategoryId()==null){
           response.setResult(false);
           response.setMessage("No Sub-Category Selected");
           return response;
       }
       IcdSubCategory subCategory =subCategoryService.get(dto.getSubCategoryId());
       if(subCategory==null){
           response.setResult(false);
           response.setMessage("Unknown ICD SubCategory Selected");
           return response;
       }
       Icd icd =IcdCreateDTO.toIcd(dto,subCategoryService.get(dto.getSubCategoryId()));
       try {
           Icd newIcd = icdService.add(icd);
           response.setMessage("Successfully Added");
           response.setResult(true);
           response.setT(IcdDTO.toDTO(newIcd));

       }
       catch (Exception e){
           response.setMessage("Unexpected Exception="+e.getMessage());
           response.setResult(false);

       }

            return  response;
   }
    @GetMapping("/list/{pageNumber}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public Page<IcdDTO>  getList( @PathVariable("pageNumber") int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.ASC, "code");
        Pageable pageable = PageRequest.of(pageNumber, Constants.PAGE_SIZE,sort);
        return icdService.list(pageable).map(s->IcdDTO.toDTO(s));
    }

    @GetMapping("/subCategory/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public List<IcdDTO> getByCategory(@PathVariable("id")Long id) {
         IcdSubCategory subCategory =subCategoryService.get(id);
         if(subCategory==null) return null;

         return icdService.getBySubCategory(subCategory).stream().map(i -> IcdDTO.toDTO(i)).collect(Collectors.toList());
    }
    @GetMapping("/category/{category}/{pageNumber}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public List<IcdDTO> getByCategory(@PathVariable("category")ICD_CATEGORY category, @PathVariable("pageNumber") int pageNumber) {
       Pageable pageable = PageRequest.of(pageNumber, Constants.PAGE_SIZE);
       return icdService.getByCategory(category,pageable).stream().map(i -> IcdDTO.toDTO(i)).collect(Collectors.toList());
    }
    @GetMapping("/category/{subCategory}/{pageNumber}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public List<IcdDTO> getBySubCategory(@PathVariable("subCategory")Long subCategory, @PathVariable("pageNumber") int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, Constants.PAGE_SIZE);
        IcdSubCategory sub = subCategoryService.get(subCategory);
        if(sub==null)
            return null;

         return icdService.getBySubCategory(sub).stream().map(i -> IcdDTO.toDTO(i)).collect(Collectors.toList());
    }
    @GetMapping("/subCategories")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public List<IcdSubCategoryDTO> getSubCategory() {

        //if(order.equalsIgnoreCase("CODE"))
             return subCategoryService.getAllByCode().stream().map(i -> IcdSubCategoryDTO.toDTO(i)).collect(Collectors.toList());
       // if(order.equalsIgnoreCase("NAME"))
         //   return subCategoryService.getAllByName().stream().map(i -> IcdSubCategoryDTO.toDTO(i)).collect(Collectors.toList());

       // return null;
    }
    @GetMapping("/subCategoriesByCategory")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public List<IcdSubCategoryDTO> getSubCategoryByCategory(@RequestParam("category")ICD_CATEGORY category) {

          return subCategoryService.getByCategory(category).stream().map(i -> IcdSubCategoryDTO.toDTO(i)).collect(Collectors.toList());


    }
    @GetMapping("/subCategories/list/{pageNumber}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public Page<IcdSubCategoryDTO> getSubCategory( @PathVariable("pageNumber") int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.ASC, "code");
        Pageable pageable = PageRequest.of(pageNumber, Constants.PAGE_SIZE,sort);
         return subCategoryService.list(pageable).map(s->IcdSubCategoryDTO.toDTO(s));
    }
    @GetMapping("/searchByKey/{key}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public Page<IcdDTO> searchByKey(@PathVariable("key")String key) {
        Pageable pageable = PageRequest.of(0, Constants.PAGE_SIZE);
        return icdService.getByKey(key,pageable).map(i -> IcdDTO.toDTO(i));
    }
}
