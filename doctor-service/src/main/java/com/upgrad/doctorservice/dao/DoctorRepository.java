package com.upgrad.doctorservice.dao;

import com.upgrad.doctorservice.entities.DoctorInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.print.Doc;
import java.util.List;

public interface DoctorRepository extends MongoRepository<DoctorInfoEntity, String> {

    public List<DoctorInfoEntity> findDoctorsByStatusAndSpeciality(String status, String speciality);

    public List<DoctorInfoEntity> findDoctorsByStatus(String status);

    public List<DoctorInfoEntity> findDoctorsBySpeciality(String speciality);
}
