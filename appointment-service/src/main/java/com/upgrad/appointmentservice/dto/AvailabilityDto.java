package com.upgrad.appointmentservice.dto;

import java.util.List;
import java.util.Map;

public class AvailabilityDto {
    String doctorId;
    Map<String, List<String>> availabilityMap;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Map<String, List<String>> getAvailabilityMap() {
        return availabilityMap;
    }

    public void setAvailabilityMap(Map<String, List<String>> availabilityMap) {
        this.availabilityMap = availabilityMap;
    }

    @Override
    public String toString() {
        return "AvailabilityDto{" +
                "doctorId='" + doctorId + '\'' +
                ", availabilityMap=" + availabilityMap +
                '}';
    }
}
