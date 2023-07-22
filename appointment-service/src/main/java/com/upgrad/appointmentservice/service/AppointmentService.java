package com.upgrad.appointmentservice.service;

import com.upgrad.appointmentservice.dto.AvailabilityDto;
import com.upgrad.appointmentservice.entities.AppointmentEntity;
import com.upgrad.appointmentservice.entities.AvailabilityEntity;
import com.upgrad.appointmentservice.entities.PrescriptionEntity;

import java.util.List;
import java.util.Map;

public interface AppointmentService {

    public void saveDoctorAvailability(Map<String, List<String>> availabilityMap, String doctorId);

    public AvailabilityDto getDoctorAvailabilityById(String doctorId);

    public String saveAppointment(AppointmentEntity appointmentEntity);

    public AppointmentEntity getAppointmentById(String appointmentId);

    public List<AppointmentEntity> getAppointmentsByUserId(String userId);

    public PrescriptionEntity savePrescriptionDetails(PrescriptionEntity prescriptionEntity);

}
