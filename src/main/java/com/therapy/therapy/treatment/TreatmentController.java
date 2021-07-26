package com.therapy.therapy.treatment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/treatment")
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    @PostMapping("/create")
    public List<TreatmentDTO> create(@RequestBody TreatmentDTO dto) throws Exception {

        treatmentService.add(TreatmentDTO.toTreatment(dto));

        return treatmentService.listAll().stream().map(t->TreatmentDTO.toDTO(t)).collect(Collectors.toList());
    }
    @GetMapping("/list")
    public List<TreatmentDTO> list(){

        return treatmentService.listAll().stream().map(t->TreatmentDTO.toDTO(t)).collect(Collectors.toList());
    }

    @PostMapping("/delete")
    public List<TreatmentDTO> delete(@RequestBody Treatment  tr) throws Exception {

       treatmentService.delete(tr);

        return treatmentService.listAll().stream().map(t->TreatmentDTO.toDTO(t)).collect(Collectors.toList());
    }
}
