package com.upgrad.appointmentservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "prescription")
public class PrescriptionEntity {
    @Id
    String id;
    String userId;
    String doctorId;
    String doctorName;
    String appointmentId;

    String diagnosis;
    List<MedicineEntity> medicineList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public List<MedicineEntity> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<MedicineEntity> medicineList) {
        this.medicineList = medicineList;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "PrescriptionEntity{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", medicineList=" + medicineList +
                '}';
    }
}
