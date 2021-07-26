package com.therapy.therapy.staff;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.department.Department;
import com.therapy.therapy.department.DepartmentService;
import com.therapy.therapy.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin

@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DocumentService documentService;


    @GetMapping("/department/{id}")
    public List<StaffDTO> getByDepartment(@PathVariable("id")Long id){

        return StaffDTO.toList(staffService.findByDepartmentActive(id));
    }
    @GetMapping("{id}")
    public StaffDTO get(@PathVariable("id")Long id){

        return StaffDTO.toDTO(staffService.get(id));
    }
    @PostMapping("/update")
    public ActionResponse<StaffDTO> update(@RequestBody StaffDTO dto) throws Exception {
        ActionResponse<StaffDTO> response = new ActionResponse<>();
        ActionResponse<Staff> result= new ActionResponse<>();
        Department dept=null;
        if( dto.getDepartmentId()!=null)
            dept =departmentService.get(dto.getDepartmentId());

        result =staffService.updateStaff(StaffDTO.toStaff(dto,dept));
        response.setMessage(result.getMessage());
        response.setResult(result.getResult());

        if(result.getT()!=null){
            response.setT(StaffDTO.toDTO((Staff)result.getT()));
        }

        return response;
    }
    @PostMapping("/add")
    public ActionResponse<StaffDTO> addNew(@RequestBody StaffCreateDTO dto) throws Exception {
        ActionResponse<StaffDTO> response = new ActionResponse<>();
        ActionResponse<Staff> result= new ActionResponse<>();
        Department dept=null;
        if( dto.getDepartmentId()!=null) {
            dept = departmentService.get(dto.getDepartmentId());

            result = staffService.create(StaffCreateDTO.toStaff(dto, dept));
            response.setMessage(result.getMessage());
            response.setResult(result.getResult());

            if (result.getT() != null) {
                response.setT(StaffDTO.toDTO((Staff) result.getT()));
            }
        }
        else {
            response.setResult(false);
            response.setMessage("Department Unknown");
        }
        return response;
    }
    @GetMapping("/list")
    public Page<StaffDTO> list(@RequestParam("pageNumber")int pageNumber){
        Sort sort = Sort.by(Sort.Direction.ASC,"firstName");
        Pageable pageable = PageRequest.of(pageNumber,Constants.PAGE_SIZE,sort);

        return StaffDTO.toList(staffService.findAll(pageable));

    }
    @PostMapping("/byQuery")
    public Page<StaffDTO> byQuery(@RequestBody StaffSearchQuery query){
        Sort sort = Sort.by(Sort.Direction.ASC,"firstName");
        Pageable pageable = PageRequest.of(query.getPageNumber(),Constants.PAGE_SIZE,sort);

        return StaffDTO.toList(staffService.searchByQuery(query,pageable));

    }
    @GetMapping("/search")
    public Page<StaffDTO> search(@RequestParam("key") String key){
        Sort sort = Sort.by(Sort.Direction.ASC,"firstName");
        Pageable pageable = PageRequest.of(0,Constants.PAGE_SIZE,sort);

        return StaffDTO.toList(staffService.searchByKey(key,pageable));

    }
    @PostMapping("/uploadPhoto/{id}")
    public Boolean uploadPhoto(@RequestParam("image") MultipartFile multipartFile, @PathVariable("id")Long id) throws IOException {

        if(multipartFile==null) {
            throw new IOException("Empty File");
        }
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            long maxSize = 12;
            Long size = multipartFile.getSize();
            size = size / 1023 / 2023;
            if (size > maxSize) {
                throw new IOException("Very huge file Size");
            }

            File file = documentService.uploadProfilePhoto(id, multipartFile);


        return Boolean.TRUE;

    }

    @GetMapping(value = "/profilePhoto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> profilePhoto(@PathVariable("id")Long id) throws IOException {
        ByteArrayResource img =documentService.getProfilePhoto(id);
        if(img==null)
            return null;

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(img.contentLength())
                .body(img);

    }

    /*@GetMapping( "/profilePhoto/{id}")
     public  byte[] getImageWithMediaType(@PathVariable("id")Long id) throws IOException {

         return documentService.getProfilePhoto(id);
    }*/
}
