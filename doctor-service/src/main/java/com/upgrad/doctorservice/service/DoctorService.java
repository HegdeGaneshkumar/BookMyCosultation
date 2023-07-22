package com.upgrad.doctorservice.service;

import com.upgrad.doctorservice.entities.DoctorInfoEntity;

import javax.print.Doc;
import java.util.List;
import java.util.Map;

public interface DoctorService {

    public DoctorInfoEntity saveDoctorDetails(DoctorInfoEntity doctorInfoEntity);

    public DoctorInfoEntity verifyDoctor(Map<String, String> approverDetails, String doctorId);

    public DoctorInfoEntity getDoctorList();

    public List<DoctorInfoEntity> getDoctorsBasedOnParams(String status, String speciality);

    public DoctorInfoEntity getDoctorById(String id);
}
