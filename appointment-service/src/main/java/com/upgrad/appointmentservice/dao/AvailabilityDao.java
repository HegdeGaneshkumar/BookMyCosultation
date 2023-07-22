package com.upgrad.appointmentservice.dao;

import com.upgrad.appointmentservice.entities.AvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityDao extends JpaRepository<AvailabilityEntity, Long> {

    public List<AvailabilityEntity> findAvailabilitiesByDoctorId(String doctorId);
}
