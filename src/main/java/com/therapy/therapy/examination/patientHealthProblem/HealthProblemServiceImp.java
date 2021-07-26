package com.therapy.therapy.examination.patientHealthProblem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthProblemServiceImp implements HealthProblemService {
    @Override
    public HealthProblem get(Long id) {
        return null;
    }

    @Override
    public Page<HealthProblem> list(Pageable pageable) {
        return null;
    }

    @Override
    public HealthProblem add(HealthProblem healthProblem) throws Exception {
        return null;
    }

    @Override
    public HealthProblem update(HealthProblem healthProblem) throws Exception {
        return null;
    }

    @Override
    public void delete(HealthProblem healthProblem) throws Exception {

    }

    @Override
    public List<HealthProblem> getByExamination(Long examinationId) {
        return null;
    }
}
