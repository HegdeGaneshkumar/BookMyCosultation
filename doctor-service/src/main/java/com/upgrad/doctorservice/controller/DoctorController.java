package com.upgrad.doctorservice.controller;

import com.upgrad.doctorservice.dto.DoctorInfoDTO;
import com.upgrad.doctorservice.entities.DoctorInfoEntity;
import com.upgrad.doctorservice.service.DoctorService;
import com.upgrad.doctorservice.service.S3Repository;
import com.upgrad.doctorservice.util.POJOConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class DoctorController {
    private DoctorService doctorService;

    @Autowired
    private S3Repository s3Repository;
    @Autowired
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    //Todo -  Role: USER, ADMIN. If the token has the role of either USER or ADMIN, then the endpoint can be accessed.
    //Validation
    @PostMapping(value = "/doctors", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorInfoDTO> registerDoctor(@Valid @RequestBody DoctorInfoDTO doctorInfoDTO){
        //call service layer
        DoctorInfoEntity doctorInfoEntity = POJOConverter.convertDtoToEntity(doctorInfoDTO);
        DoctorInfoEntity savedDoctorEntity = doctorService.saveDoctorDetails(doctorInfoEntity);
        DoctorInfoDTO savedDoctorDto = POJOConverter.convertEntityToDto(savedDoctorEntity);
        return new ResponseEntity<>( savedDoctorDto, HttpStatus.OK);
    }

    @PostMapping(value = "/doctors/{doctorId}/document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadDocuments(@PathVariable String doctorId, @RequestParam(value = "files", required = true) List<MultipartFile> files){

        //call service layer and upload the documents to s3
        try {
            for(MultipartFile file: files)
                s3Repository.uploadFiles(doctorId, file);
        }catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity("File(s) uploaded Successfully",HttpStatus.OK);
    }

    @PutMapping(value = "/doctors/{doctorId}/approve")
    public ResponseEntity<DoctorInfoEntity> approveDoctor(@RequestBody Map<String, String> approverDetails, @PathVariable String doctorId){
        approverDetails.put("status", "Approved");
        DoctorInfoEntity updatedDoctorDetails = doctorService.verifyDoctor(approverDetails, doctorId);
        return new ResponseEntity<>(updatedDoctorDetails, HttpStatus.OK);
    }

    @PutMapping(value = "/doctors/{doctorId}/reject")
    public ResponseEntity<DoctorInfoEntity> rejectDoctor(@RequestBody Map<String, String> approverDetails, @PathVariable String doctorId){

        approverDetails.put("status", "Rejected");
        DoctorInfoEntity updatedDoctorDetails = doctorService.verifyDoctor(approverDetails, doctorId);
        return new ResponseEntity<>(updatedDoctorDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/doctors")
    public ResponseEntity<List<DoctorInfoEntity>> getDoctors(@RequestParam(required = false) String status, @RequestParam(required = false) String speciality ){

        List<DoctorInfoEntity> doctorInfoEntities = doctorService.getDoctorsBasedOnParams(status, speciality);
        return new ResponseEntity<>(doctorInfoEntities, HttpStatus.OK);
    }

    @GetMapping(value = "/doctors/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorInfoEntity> getDoctorDetails(@PathVariable String doctorId){
        DoctorInfoEntity doctorInfoEntity = doctorService.getDoctorById(doctorId);
        return new ResponseEntity<>(doctorInfoEntity, HttpStatus.OK);
    }

    @GetMapping(value = "/doctors/{doctorId}/documents/metadata")
    public ResponseEntity<DoctorInfoEntity> getUploadedDocs(@PathVariable String doctorId){
        String uploadedFiles = s3Repository.getUploadedDocuments(doctorId);
        return new ResponseEntity(uploadedFiles, HttpStatus.OK);
    }

    @GetMapping(value = "/doctors/{doctorId}/documents/{documentName}")
    public ResponseEntity<DoctorInfoEntity> getDocument(@PathVariable String doctorId, @PathVariable String docName){

        return new ResponseEntity(HttpStatus.OK);
    }

}
