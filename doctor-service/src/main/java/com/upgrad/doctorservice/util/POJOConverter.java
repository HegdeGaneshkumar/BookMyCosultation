package com.upgrad.doctorservice.util;

import com.upgrad.doctorservice.dto.DoctorInfoDTO;
import com.upgrad.doctorservice.entities.DoctorInfoEntity;

public class POJOConverter {
    public static DoctorInfoEntity convertDtoToEntity(DoctorInfoDTO doctorInfoDTO){
        DoctorInfoEntity doctorInfoEntity = new DoctorInfoEntity();
        doctorInfoEntity.setFirstName(doctorInfoDTO.getFirstName());
        doctorInfoEntity.setLastName(doctorInfoDTO.getLastName());
        doctorInfoEntity.setDob(doctorInfoDTO.getDob());
        doctorInfoEntity.setMobile(doctorInfoDTO.getMobile());
        doctorInfoEntity.setEmailId(doctorInfoDTO.getEmailId());
        doctorInfoEntity.setPan(doctorInfoDTO.getPan());
        doctorInfoEntity.setSpeciality(doctorInfoDTO.getSpeciality());
        doctorInfoEntity.setRegistrationDate(doctorInfoDTO.getRegistrationDate());
        doctorInfoEntity.setStatus(doctorInfoDTO.getStatus());
        return doctorInfoEntity;
    }

    public static DoctorInfoDTO convertEntityToDto(DoctorInfoEntity doctorInfoEntity)
    {
        DoctorInfoDTO doctorInfoDto = new DoctorInfoDTO();
        doctorInfoDto.setFirstName(doctorInfoEntity.getFirstName());
        doctorInfoDto.setLastName(doctorInfoEntity.getLastName());
        doctorInfoDto.setDob(doctorInfoEntity.getDob());
        doctorInfoDto.setMobile(doctorInfoEntity.getMobile());
        doctorInfoDto.setEmailId(doctorInfoEntity.getEmailId());
        doctorInfoDto.setPan(doctorInfoEntity.getPan());
        doctorInfoDto.setSpeciality(doctorInfoEntity.getSpeciality());
        doctorInfoDto.setRegistrationDate(doctorInfoEntity.getRegistrationDate());
        doctorInfoDto.setStatus(doctorInfoEntity.getStatus());
        return doctorInfoDto;
    }
}
