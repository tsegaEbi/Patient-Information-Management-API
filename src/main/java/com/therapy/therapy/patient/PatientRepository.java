package com.therapy.therapy.patient;

import com.therapy.therapy.common.Nationality;
import com.therapy.therapy.common.Regions;
import com.therapy.therapy.common.Sex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findBySex(Sex sex, Pageable pageable);

    @Query("FROM Patient p where lower(p.firstName) like lower(concat('%',:key,'%')) or lower(p.fatherName) like lower(concat('%',:key,'%')) or lower(p.lastName) like lower(concat('%',:key,'%')) or lower(p.mobile) like lower(concat('%',:key,'%'))  or lower(p.email) like lower(concat('%',:key,'%'))")
    Page<Patient> searchKey(String key, Pageable pageable);

    @Query("FROM Patient p WHERE " +
            "(:sex is null or p.sex= :sex) " +
            " and (:regions is null  or p.regions = :regions)" +
            " and (:nationality is null or p.nationality=:nationality)"  )
     Page<Patient> searchByQuery(Sex sex, Regions regions, Nationality nationality, Pageable pageable);

     Page<Patient> findById(Long id, Pageable pageable);


     @Query(value="Select count(*) from patient",nativeQuery = true)
     int countAll();
}
