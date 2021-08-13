package com.therapy.therapy.examination;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.ORDER_STATUS;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.examination.labOrder.*;
import com.therapy.therapy.icdCode.IcdService;
import com.therapy.therapy.laboratory.Laboratory;
import com.therapy.therapy.laboratory.LaboratoryService;
import com.therapy.therapy.patient.checkup.PatientVisit;
import com.therapy.therapy.patient.checkup.PatientVisitService;
import com.therapy.therapy.security.SecurityService;
import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin

@RequestMapping("/examination")
public class ExaminationController {



    @Autowired
    private SecurityService securityService;

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private PatientVisitService patientVisitService;

    @Autowired
    private LabOrderService labService;

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private StaffService staffService;


    @Autowired
    private IcdService iCdService;


    // due to unexpected and unsolved securitservice null problem, removed to this end point
    @PostMapping("/addExamination")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public ActionResponse<ExaminationDTO> addExamination(@RequestBody ExaminationCreateDTO dto) {

        Staff staff =staffService.getByStaffId(securityService.getStaffId());

        ActionResponse<ExaminationDTO> response= new ActionResponse<>();
        response.setResult(false);

        if(staff==null){
            throw new IllegalArgumentException("Unknown Staff");

        }

        PatientVisit visit =patientVisitService.get(dto.getPatientVisitId());

        if(visit==null){
            response.setMessage("Unknown Checkups");
        }
        else {
            Examination examination =ExaminationCreateDTO.toExamination(dto,visit,staff);
            ActionResponse<Examination> result =examinationService.create(examination,dto.getLaboratoryCreates());

            response.setMessage(result.getMessage());
            response.setResult(result.getResult());

            if(result.getResult()) {
                List<LabOrderDTO> labOrders =labService.getByExamination((Examination)response.getT())
                        .stream()
                        .map(t->LabOrderDTO.toDTO(t,null)).collect(Collectors.toList());
                if (result.getT() != null) {
                    response.setT(ExaminationDTO.toDetail((Examination) result.getT(), labOrders));
                }
            }
        }

        return response;

    }

    @GetMapping("/{id}")
    public ExaminationDTO get(@PathVariable("id")Long id){
        Examination examination =examinationService.get(id);
        if(examination==null)
            return null;

        List<LabOrder> labOrders =labService.getByExamination(examination);

        List<LabOrderDTO>labOrderDTOs =new ArrayList();

        if(labOrders!=null){

            for(LabOrder o:labOrders){
                if(o.getTechnician()!=null){

                    labOrderDTOs.add(LabOrderDTO.toDTO(o,staffService.get(o.getTechnician())));

                }
                else
                {
                    labOrderDTOs.add(LabOrderDTO.toDTO(o,null));
                }
            }
        }
        Staff staff= new Staff();

        return ExaminationDTO.toDetail(examinationService.get(id),labOrderDTOs);
    }
    @GetMapping("/allByPatientId")
    public List<ExaminationDTO> getByPatientId(@RequestParam("id")Long id){

        return examinationService.getAllByPatient(id).stream().map(t->ExaminationDTO.toDTOLab(t,labService.getByExamination(t))).collect(Collectors.toList());
    }
    @GetMapping("/byPatientVisit")
    public  ExaminationDTO  getByPatientVisit(@RequestParam("id")Long id){
        PatientVisit visit =patientVisitService.get(id);
        if(visit==null)
            throw new IllegalArgumentException("Visit not found");
        Examination examination =examinationService.getByPatientVisit(visit);

        if(examination==null)
            return null;

        return ExaminationDTO.toDTOLab(examination,labService.getByExamination(examination));
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','NURSE','CARD')")
    @GetMapping("/list")
    public Page<ExaminationDTO> list(@RequestParam("pageNumber")int pageNumber){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNumber, Constants.PAGE_SIZE, sort);
        return examinationService.list(pageable).map(t->ExaminationDTO.toDTOLab(t,labService.getByExamination(t)));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','FINANCE')")
    @PostMapping("/activate")
    public  boolean activate(Long id){
        examinationService.activate(id);
        return true;
    }


    @PostMapping("/updateLabOrder")
    public LabOrderDTO assign(@RequestBody LabOrderUpdate dto) throws Exception {

        if(dto.getCmd().equalsIgnoreCase("REMOVE")) {
            labService.delete(labService.get(dto.getId()));
            return null;
        }
        if(dto.getCmd().equalsIgnoreCase("ACTIVATE")) {
            labService.activate(dto.getId());
            LabOrder order = labService.get(dto.getId());
            if(order.getTechnician()!=null)
                return LabOrderDTO.toDTO(order,staffService.get(order.getTechnician()));

            return LabOrderDTO.toDTO(order,null);
        }
        if(dto.getCmd().equalsIgnoreCase("AT")) {
            Staff staff = staffService.get(dto.getStaffId());
            LabOrder order = labService.get(dto.getId());
            if (order == null)
                return null;

            if (staff == null) {
                if (order.getTechnician() != null) {
                    staff = staffService.get(order.getTechnician());
                    return LabOrderDTO.toDTO(order, staff);
                }
                return LabOrderDTO.toDTO(order, null);
            } else {
                order = labService.assignTechnician(dto.getId(), dto.getStaffId());
                return LabOrderDTO.toDTO(order, staff);
            }
        }

        return null;
    }

    @PostMapping("/updateChart")
    public ExaminationDTO updateChart(@RequestBody ExaminationUpdateDTO dto) throws Exception {
        Staff staff =staffService.getByStaffId(securityService.getStaffId());

        Examination examination =examinationService.get(dto.getId());
        if(examination==null)
            return null;

        if(staff.getId()==examination.getExaminer().getId()) {
            Examination newExamination = ExaminationUpdateDTO.GetUpdateChart(dto, examination);

            Examination updatedExamination = examinationService.update(newExamination);
            if (updatedExamination != null) {
                List<LabOrder> labOrders = labService.getByExamination(updatedExamination);
                if (labOrders != null)
                    return ExaminationDTO.toDetail(updatedExamination,
                            labOrders.stream().map(o -> LabOrderDTO.toDTO(o, null)).collect(Collectors.toList()));
                else
                    return ExaminationDTO.toDetail(updatedExamination, null);
            }

        }

        return ExaminationDTO.toDetail(examination,null);
    }
    @PostMapping("/updateDiagnosis")
    public ExaminationDTO updateDiagnosis(@RequestBody ExaminationUpdateDTO dto) throws Exception {

        Staff staff =staffService.getByStaffId(securityService.getStaffId());

        Examination examination =examinationService.get(dto.getId());
        if(examination==null)
            return null;

        if(staff.getId()==examination.getExaminer().getId()) {
            Examination newExamination = ExaminationUpdateDTO.GetUpdateDiagnosis(dto, examination);
            Examination updatedExamination = examinationService.update(newExamination);
            if (updatedExamination != null) {
                List<LabOrder> labOrders = labService.getByExamination(updatedExamination);
                if (labOrders != null)
                    return ExaminationDTO.toDetail(updatedExamination,
                            labOrders.stream().map(o -> LabOrderDTO.toDTO(o, null)).collect(Collectors.toList()));
                else
                    return ExaminationDTO.toDetail(updatedExamination, null);
            }
        }

        return ExaminationDTO.toDetail(examination, null);
    }

    @PostMapping("/labOrder/update")
    public List<LabOrderDTO> updateLabOrder(@RequestBody ExaminationUpdateDTO dto) throws Exception {

        Staff  staff =staffService.getByStaffId(securityService.getStaffId());

        Examination examination =examinationService.get(dto.getId());
        if(examination==null)
            return null;



        if(staff.getId()==examination.getExaminer().getId()) { // check if examiner

            if (dto.getLaboratories() != null && dto.getLaboratories().size() > 0) { // check if laboratory is not null

                List<LabOrder> prevOrders = labService.getByExamination(examination); // prev labOrders

                List<Laboratory> filtered = new ArrayList<>();

                if (prevOrders != null) {
                    for (Laboratory l : dto.getLaboratories()) {

                        if(prevOrders.stream().filter(t->t.getLaboratory().getId()==laboratoryService.get(l.getId()).getId())==null)  {

                            filtered.add(laboratoryService.get(l.getId()));
                        }
                    }
                }
                else {

                    filtered = dto.getLaboratories();
                }

                if (filtered != null) {

                    ActionResponse<LabOrder> response = labService.create(examination, filtered);
                }


            }

            List<LabOrder> newOrders = labService.getByExamination(examination);

            List<LabOrderDTO> labOrderDtos = new ArrayList<>();
            if (newOrders != null ) {

                for (LabOrder o : newOrders) {

                    if (o.getTechnician() != null) {
                        labOrderDtos.add(LabOrderDTO.toDTO(o, staffService.get(o.getTechnician())));
                    } else {
                        labOrderDtos.add(LabOrderDTO.toDTO(o, null));
                    }
                }
            }
            return labOrderDtos;
        }

        return null; // no examination
    }
    @PostMapping("/labOrder/byQuery")
    public Page<LabOrderDTO> searchByQuery(@RequestBody LabOrderSearchQuery query) throws Exception {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(query.getPageNumber(), Constants.PAGE_SIZE, sort);
        return labService.getByQuery(query,pageable)
                .map(t-> LabOrderDTO.toDTO(t,null));
    }

    @GetMapping("/labOrder/{id}")
    public LabOrderDTO list(@PathVariable("id")Long id){
        LabOrder labOrder =labService.get(id);
        if(labOrder==null)
            return null;

        if(labOrder.getTechnician()==null) {
            return LabOrderDTO.toDetailDTO(labOrder,null);
        }

        return LabOrderDTO.toDetailDTO(labOrder,staffService.get(labOrder.getTechnician()));
    }
    @PostMapping("/labOrder/addResult")
    @Transactional

    public  LabOrderDTO  updateLabOrder(@RequestBody LabOrderDTO dto) throws Exception {
        LabOrder labOrder =labService.get(dto.getId());

        if(labOrder!=null){
            labOrder.setOrderStatus(ORDER_STATUS.DONE);

            if(labOrder.getTechnician()==null)
                labOrder.setTechnician( staffService.getByStaffId(securityService.getStaffId()).getId());

            labOrder.setFinding(dto.getFinding());
            labOrder.setFindingStatus(dto.getFindingStatus());

            labOrder =labService.update(labOrder);

            if(labOrder.getTechnician()!=null)
                return LabOrderDTO.toDetailDTO(labOrder,staffService.get(labOrder.getTechnician()));
            else
                return LabOrderDTO.toDetailDTO(labOrder, staffService.getByStaffId(securityService.getStaffId()));
        }
        return null;

    }


    //  Health Problem
//    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER')")
//    @PostMapping("/healthProblem/add")
//    private ActionResponse<HealthProblemDTO> addHealthProblem(@RequestBody HealthProblemCreateDTO dto) throws IllegalAccessException{
//
//        String staffId =securityService.getStaffId();
//        Staff staff =staffService.getByStaffId(staffId);
//
//        ActionResponse<HealthProblemDTO> response= new ActionResponse<>();
//        response.setResult(false);
//
//        if(staff==null){
//            throw new IllegalArgumentException("Unknown Staff");
//
//        }
//
//       Examination examination =examinationService.get(dto.getExaminationId());
//                if(examination==null){
//                    response.setMessage("Unknown Examination");
//                }
//
//                // check if the examiner and the problem staff id is the same
//
//                if(examination.getExaminer().getId()!=staff.getId()){
//                    response.setMessage("Sorry,only examiner can add health problem to the examination history");
//                }
//                Icd icd =iCdService.get(dto.getIcdId());
//                if(examination==null){
//                    response.setMessage("Unknown ICD Code");
//                }
//
//                else {
//                    HealthProblem problem =HealthProblemCreateDTO.toHealthProblem(dto, icd, examination.getExaminer(), examination);
//                    ActionResponse<HealthProblem> result =patientHealthProblemService.create(problem);
//
//                    response.setMessage(result.getMessage());
//                    response.setResult(result.getResult());
//                    if(response.getT()!=null)
//                        response.setT(HealthProblemDTO.toDTO((HealthProblem)result.getT()));
//                }
//
//                return response;
//    }
//
//    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','NURSE','MEDICAL')")
//    @GetMapping("/healthProblem/list")
//    private Page<HealthProblemDTO> listProblem(@RequestParam("pageNumber")int pageNumber) {
//        Sort sort = Sort.by(Sort.Direction.DESC,"id");
//        Pageable pageable = PageRequest.of(pageNumber, Constants.PAGE_SIZE, sort);
//        return patientHealthProblemService.list(pageable).map(t->HealthProblemDTO.toDTO(t));
//
//    }
//
//    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','NURSE','MEDICAL')")
//    @PostMapping("/healthProblem/list")
//    private Page<HealthProblemDTO> healthProblemByQuery(@RequestBody HealthProblemSearchQuery query) {
//        Sort sort = Sort.by(Sort.Direction.DESC,"id");
//        Pageable pageable = PageRequest.of(query.getPageNumber(), Constants.PAGE_SIZE, sort);
//        return patientHealthProblemService.getByQuery(query,pageable).map(t->HealthProblemDTO.toDTO(t));
//
//    }
//
//
//    @GetMapping("/healthProblem/examination/{id}")
//    private List<HealthProblemDTO> listProblemByExamination(@PathVariable("id")Long id) {
//        Sort sort = Sort.by(Sort.Direction.DESC,"id");
//        return patientHealthProblemService.getByExamination(id).stream().map(t->HealthProblemDTO.toDTO(t)).collect(Collectors.toList());
//
//    }
//    @GetMapping("/healthProblem/patient/{id}")
//    private List<HealthProblemDTO> listProblemByPatient(@PathVariable("id")Long id) {
//        Sort sort = Sort.by(Sort.Direction.DESC,"id");
//        return patientHealthProblemService.getByPatient(id).stream().map(t->HealthProblemDTO.toDTO(t)).collect(Collectors.toList());
//
//    }
}
