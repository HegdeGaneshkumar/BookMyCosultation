package com.upgrad.appointmentservice.service;

import com.upgrad.appointmentservice.dao.AppointmentDao;
import com.upgrad.appointmentservice.dao.AvailabilityDao;
import com.upgrad.appointmentservice.dao.PrescriptionRepository;
import com.upgrad.appointmentservice.dto.AvailabilityDto;
import com.upgrad.appointmentservice.entities.AppointmentEntity;
import com.upgrad.appointmentservice.entities.AvailabilityEntity;
import com.upgrad.appointmentservice.entities.PrescriptionEntity;
import com.upgrad.appointmentservice.exception.PaymentPendingException;
import com.upgrad.appointmentservice.exception.RequestedResourceUnavailable;
import com.upgrad.appointmentservice.producer.KafkaMessageProducer;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AvailabilityDao _availabilityDao;

    @Autowired
    private AppointmentDao _appointmentDao;

    private static final String TOPIC_NAME = "appointment-service";

    @Autowired
    private KafkaMessageProducer kafkaMessageProducer ;


    @Autowired
    private PrescriptionRepository _prescriptionRepo;
    @Override
    public void saveDoctorAvailability(Map<String, List<String>> availabilityMap, String doctorId) {
        for (Map.Entry<String,List<String>> entry : availabilityMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            List<String> timeslots = entry.getValue();

            for(String timeslot: timeslots) {
                AvailabilityEntity availabilityEntity = new AvailabilityEntity();
                availabilityEntity.setDoctorId(doctorId);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                availabilityEntity.setAvailabilityDate(LocalDate.parse(entry.getKey(), formatter));
                availabilityEntity.setTimeslot(timeslot);
                availabilityEntity.setBooked(false);
                AvailabilityEntity savedAvailabilityEntity = _availabilityDao.save(availabilityEntity);
                System.out.println(savedAvailabilityEntity.toString());
            }
        }

    }

    @Override
    public AvailabilityDto getDoctorAvailabilityById(String doctorId) {
        List<AvailabilityEntity> availabilityEntities = _availabilityDao.findAvailabilitiesByDoctorId(doctorId);
        Map<String, List<String>> availabiltyMap = new HashMap<>();
        for(AvailabilityEntity availabilityEntity: availabilityEntities){
            String availabilityDate = availabilityEntity.getAvailabilityDate().toString();
            String timeslot = availabilityEntity.getTimeslot();
            if(availabilityEntity.isBooked() == false) {
                if (availabiltyMap.containsKey(availabilityDate)) {
                    List<String> timeslots = availabiltyMap.get(availabilityDate);
                    timeslots.add(timeslot);
                    availabiltyMap.put(availabilityDate, timeslots);
                } else {
                    List<String> timeslots = new ArrayList<>();
                    timeslots.add(timeslot);
                    availabiltyMap.put(availabilityDate, timeslots);
                }
            }
        }
        AvailabilityDto availabilityDto = new AvailabilityDto();
        availabilityDto.setDoctorId(doctorId);
        availabilityDto.setAvailabilityMap(availabiltyMap);

        return availabilityDto;
    }

    @Override
    public String saveAppointment(AppointmentEntity appointmentEntity) {

        appointmentEntity.setStatus("PendingPayment");
        appointmentEntity.setCreatedDate(LocalDateTime.now());
        AppointmentEntity savedAppointmentEntity = _appointmentDao.save(appointmentEntity);
        try {
            kafkaMessageProducer.publish(TOPIC_NAME, savedAppointmentEntity.getUserEmailId(), "Your appointment has been confirmed with Appontment ID:" + savedAppointmentEntity.getAppointmentId());
        }
        catch (IOException exception){
            System.out.println(exception);
        }
        return savedAppointmentEntity.getAppointmentId();
    }

    @Override
    public AppointmentEntity getAppointmentById(String appointmentId) {
        AppointmentEntity appointmentEntity = _appointmentDao.findById(appointmentId).get();
        return appointmentEntity;
    }

    @Override
    public List<AppointmentEntity> getAppointmentsByUserId(String userId) {
        List<AppointmentEntity> appointmentEntities = _appointmentDao.findAppointmentsByUserId(userId);
        return appointmentEntities;
    }

    @Override
    public PrescriptionEntity savePrescriptionDetails(PrescriptionEntity prescriptionEntity) {
        AppointmentEntity appointmentEntity;
        try {
            appointmentEntity = _appointmentDao.findAppointmentByAppointmentId(prescriptionEntity.getAppointmentId());
        }catch (RuntimeException e){
            throw new RequestedResourceUnavailable();
        }
        if (appointmentEntity.getStatus().equals("Confirmed")) {
            PrescriptionEntity savedPrescription = _prescriptionRepo.save(prescriptionEntity);
            try {
                kafkaMessageProducer.publish(TOPIC_NAME, appointmentEntity.getUserEmailId(), savedPrescription.toString());
            }
            catch (IOException exception){
                System.out.println(exception);
            }
            return savedPrescription;
        } else {
            throw new PaymentPendingException();
        }
    }
}
