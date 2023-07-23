package com.upgrad.appointmentservice.dto;

import com.upgrad.appointmentservice.entities.MedicineEntity;

import java.util.List;
import java.util.Map;

public class PrescriptionDto {

    String appointmentId;
    String doctorId;
    String userId;
    String diagnosis;
    List<Map<String, String>> medicineList;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Map<String,String>> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Map<String,String>> medicineList) {
        this.medicineList = medicineList;
    }

    @Override
    public String toString() {
        return "PrescriptionDto{" +
                "appointmentId='" + appointmentId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", userId='" + userId + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", medicineList=" + medicineList +
                '}';
    }
}
