package com.therapy.therapy.examination.labOrder.Image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabOrderResultImageRepository extends JpaRepository<LabOrderResultImage,Long> {

}
