package com.upgrad.appointmentservice.dao;

import com.upgrad.appointmentservice.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDao extends JpaRepository<AppointmentEntity, String> {

    public List<AppointmentEntity> findAppointmentsByUserId(String userId);

    public AppointmentEntity findAppointmentByAppointmentId(String appointmentId);
}
