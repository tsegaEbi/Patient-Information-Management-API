package com.therapy.therapy.laboratory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/laboratory")
public class LaboratoryController {

    @Autowired
    private LaboratoryService labService;


    @PostMapping("/create")
    public List<LaboratoryDTO> create(@RequestBody LaboratoryDTO dto){
        if(dto.getName()!=null){
            return labService.create(dto.getName(),dto.getDescription(),dto.getCategory())
                    .stream()
                    .map(t->LaboratoryDTO.toDto(t))
                    .collect(Collectors.toList());
        }
        return labService.listAll()
                .stream()
                .map(t->LaboratoryDTO.toDto(t))
                .collect(Collectors.toList());
    }
    @GetMapping("/list")
    public List<LaboratoryDTO> listAll(){
        return labService.listAll()
                .stream()
                .map(t->LaboratoryDTO.toDto(t))
                .collect(Collectors.toList());
    }
}
