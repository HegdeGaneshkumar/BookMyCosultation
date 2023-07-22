package com.upgrad.appointmentservice.dao;

import com.upgrad.appointmentservice.entities.PrescriptionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends MongoRepository<PrescriptionEntity, String> {
}
