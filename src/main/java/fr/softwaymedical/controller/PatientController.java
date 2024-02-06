package fr.softwaymedical.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.softwaymedical.model.PatientEntity;
import fr.softwaymedical.repository.PatientRepository;
import fr.softwaymedical.resquest.PatientDTO;
import fr.softwaymedical.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @PostMapping(value = "/allPatient")
    public ResponseEntity<Iterable<PatientEntity>> allPatient() {
        Iterable<PatientEntity> patientEntity = this.patientRepository.findAll();
        return ResponseEntity.ok(patientEntity);
    }

    @Operation(operationId = "addPatient", description = "New patient")
    @RequestMapping(value = "/addPatient", method = RequestMethod.POST)
    public ResponseEntity<PatientEntity> addPatient(@Valid @RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(this.patientService.save(patientDTO));
    }

    @Operation(operationId = "findBySocialNumber", description = "Search patient by a social number")
    @RequestMapping(value = "/findBySocialNumber/{secialNumber}", method = RequestMethod.POST)
    public ResponseEntity<Object> SearchPatient(@PathVariable(value = "secialNumber") String secialNumber) throws Exception {
        try {
            return ResponseEntity.ok(this.patientRepository.findById(secialNumber).orElseThrow(() 
                    -> new Exception("Social number " + secialNumber + " not found")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
