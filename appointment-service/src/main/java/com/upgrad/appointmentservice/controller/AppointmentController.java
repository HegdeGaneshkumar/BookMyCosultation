package com.upgrad.appointmentservice.controller;

import com.upgrad.appointmentservice.dto.AvailabilityDto;
import com.upgrad.appointmentservice.dto.PrescriptionDto;
import com.upgrad.appointmentservice.entities.AppointmentEntity;
import com.upgrad.appointmentservice.entities.PrescriptionEntity;
import com.upgrad.appointmentservice.service.AppointmentService;
import com.upgrad.appointmentservice.util.POJOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @PostMapping(value = "/doctor/{doctorId}/availability", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AvailabilityDto> updateDoctorAvailability(@RequestBody Map<String, Map<String, List<String>>> availabilityMap, @PathVariable String doctorId){

        appointmentService.saveDoctorAvailability(availabilityMap.get("availabilityMap"), doctorId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/doctor/{doctorId}/availability", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AvailabilityDto> getDoctorAvailability(@PathVariable String doctorId){

        AvailabilityDto availabilityDto = appointmentService.getDoctorAvailabilityById(doctorId);
        return new ResponseEntity<>(availabilityDto, HttpStatus.OK);
    }

    @PostMapping(value = "/appointments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAppointment(@RequestBody AppointmentEntity appointmentEntity){
        String appointmentId = appointmentService.saveAppointment(appointmentEntity);
        return new ResponseEntity(appointmentId, HttpStatus.OK);
    }

    @GetMapping(value = "/appointments/{appointmentId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AppointmentEntity> getAppointmentDetails(@PathVariable String appointmentId){
        AppointmentEntity appointmentEntity = appointmentService.getAppointmentById(appointmentId);
        return new ResponseEntity<>(appointmentEntity, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}/appointments", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity getAllAppointmentsOfUser(@PathVariable String userId){
        List<AppointmentEntity> appointmentEntities = appointmentService.getAppointmentsByUserId(userId);
        return new ResponseEntity(appointmentEntities, HttpStatus.OK);
    }

    @PostMapping(value = "/prescriptions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrescriptionEntity> createPrescription(@RequestBody PrescriptionDto prescriptionDto){

        PrescriptionEntity savedPrescription = appointmentService.savePrescriptionDetails(POJOConverter.convertPrescriptionDtoToEntity(prescriptionDto));
        return new ResponseEntity<>(savedPrescription, HttpStatus.OK);
    }
}
