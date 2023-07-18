package com.upgrad.doctorservice.dao;

import com.upgrad.doctorservice.entities.DoctorInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRepository extends MongoRepository<DoctorInfoEntity, Integer> {
}
