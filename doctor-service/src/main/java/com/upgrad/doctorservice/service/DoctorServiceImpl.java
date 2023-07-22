package com.upgrad.doctorservice.service;

import com.upgrad.doctorservice.dao.DoctorRepository;
import com.upgrad.doctorservice.entities.DoctorInfoEntity;
import com.upgrad.doctorservice.exception.RequestedResourceUnavailable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public DoctorInfoEntity saveDoctorDetails(DoctorInfoEntity doctorInfoEntity) {
        doctorInfoEntity.setId(getAlphaNumericString(10));
        doctorInfoEntity.setStatus("Pending");
        doctorInfoEntity.setRegistrationDate(LocalDate.now());
        if(doctorInfoEntity.getSpeciality() == null)
        {
            doctorInfoEntity.setSpeciality("General Physician");
        }
        DoctorInfoEntity savedDoctorEntity = doctorRepository.save(doctorInfoEntity);
        return savedDoctorEntity;
    }

    @Override
    public DoctorInfoEntity verifyDoctor(Map<String, String> approverDetails, String doctorId) {
        try {
            DoctorInfoEntity doctorDetails = doctorRepository.findById(doctorId).get();
            doctorDetails.setApprovedBy(approverDetails.get("approvedBy"));
            doctorDetails.setApproverComments(approverDetails.get("approverComments"));
            if (approverDetails.get("status").equals("Approved"))
                doctorDetails.setStatus("Active");
            else
                doctorDetails.setStatus("Rejected");
            doctorDetails.setVerificationDate(LocalDate.now());
            DoctorInfoEntity updatedDoctorEntity = doctorRepository.save(doctorDetails);
            return updatedDoctorEntity;
        }
        catch (RuntimeException exception){
            throw new RequestedResourceUnavailable();
        }
    }

    @Override
    public DoctorInfoEntity getDoctorList() {
        return null;
    }

    @Override
    public List<DoctorInfoEntity> getDoctorsBasedOnParams(String status, String speciality) {
        List<DoctorInfoEntity> doctorInfoEntities = null;
        if(status != null && speciality!= null)
            doctorInfoEntities = doctorRepository.findDoctorsByStatusAndSpeciality(status, speciality);
        else if(speciality!= null)
            doctorInfoEntities = doctorRepository.findDoctorsBySpeciality(speciality);
        else if(status!= null)
            doctorInfoEntities = doctorRepository.findDoctorsByStatus(status);

        return doctorInfoEntities;
    }

    @Override
    public DoctorInfoEntity getDoctorById(String id) {
        try {
            return doctorRepository.findById(id).get();
        }
        catch (RuntimeException e){
            throw new RequestedResourceUnavailable();
        }
    }

    static String getAlphaNumericString(int n)
    {

        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
