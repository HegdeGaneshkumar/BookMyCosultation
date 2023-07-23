package com.upgrad.appointmentservice.util;

import com.upgrad.appointmentservice.dto.PrescriptionDto;
import com.upgrad.appointmentservice.entities.MedicineEntity;
import com.upgrad.appointmentservice.entities.PrescriptionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class POJOConverter {

    public static PrescriptionEntity convertPrescriptionDtoToEntity(PrescriptionDto prescriptionDto){
        PrescriptionEntity prescriptionEntity = new PrescriptionEntity();
        prescriptionEntity.setDoctorId(prescriptionDto.getDoctorId());
        prescriptionEntity.setAppointmentId(prescriptionDto.getAppointmentId());
        prescriptionEntity.setUserId(prescriptionDto.getUserId());
        prescriptionEntity.setDiagnosis(prescriptionDto.getDiagnosis());
        List<MedicineEntity> medicineEntityList = new ArrayList<>();

        for(Map<String, String> map: prescriptionDto.getMedicineList()){
            MedicineEntity medicineEntity = new MedicineEntity();
            medicineEntity.setDosage(map.get("dosage"));
            medicineEntity.setFrequency(map.get("frequency"));
            medicineEntity.setName(map.get("name"));
            medicineEntity.setRemarks(map.get("remarks"));
            medicineEntityList.add(medicineEntity);
        }

        prescriptionEntity.setMedicineList(medicineEntityList);

        return prescriptionEntity;
    }
}
