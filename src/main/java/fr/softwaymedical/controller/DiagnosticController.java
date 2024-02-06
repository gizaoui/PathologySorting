package fr.softwaymedical.controller;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.softwaymedical.exception.HealthIdException;
import fr.softwaymedical.model.DiagnosticEntity;
import fr.softwaymedical.model.PatientEntity;
import fr.softwaymedical.repository.DiagnosticRepository;
import fr.softwaymedical.repository.PatientRepository;
import fr.softwaymedical.resquest.DiagnosticDTO;
import fr.softwaymedical.service.DiagnosticService;
import fr.softwaymedical.service.PlanningService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DiagnosticController {

    private static final Logger logger = LogManager.getLogger(DiagnosticController.class);

    @Autowired
    private DiagnosticRepository diagnosticRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DiagnosticService diagnosticService;

    @Autowired
    private PlanningService planningService;

    @PostMapping(value = "/allDiagnostic")
    public ResponseEntity<Iterable<DiagnosticEntity>> allPatient() {
        Iterable<DiagnosticEntity> patientEntity = this.diagnosticRepository.findAll();
        return ResponseEntity.ok(patientEntity);
    }

    @Operation(operationId = "addDiagnostic", description = "New patient")
    @RequestMapping(value = "/addDiagnostic", method = RequestMethod.POST)
    public ResponseEntity<Object> addDiagnostic(@Valid @RequestBody DiagnosticDTO diagnosticDTO) throws HealthIdException {

        try {
            PatientEntity patientEntity = this.patientRepository.findById(diagnosticDTO.getSocialSecurity())
                    .orElseThrow(() -> new Exception("Social security " + diagnosticDTO.getSocialSecurity() + " not found"));

            // Remove the old recordings in planning table for update
            this.planningService.AllPlanningByDiagnostic(diagnosticDTO, patientEntity).forEach((p) -> {
                this.planningService.deletePlanning(p.getId());
            });

            DiagnosticEntity diagnosticEntity = this.diagnosticService.save(diagnosticDTO, patientEntity);

            return ResponseEntity.ok(diagnosticEntity);
        } catch (HealthIdException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
