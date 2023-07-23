package com.upgrad.paymentservice.dao;

import com.upgrad.paymentservice.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDao extends JpaRepository<AppointmentEntity, String> {
}
