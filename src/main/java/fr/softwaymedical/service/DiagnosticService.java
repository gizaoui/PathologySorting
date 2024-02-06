package fr.softwaymedical.service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.softwaymedical.exception.HealthIdException;
import fr.softwaymedical.model.DiagnosticEntity;
import fr.softwaymedical.model.PatientEntity;
import fr.softwaymedical.model.PlanningEntity;
import fr.softwaymedical.repository.DiagnosticRepository;
import fr.softwaymedical.repository.PatientRepository;
import fr.softwaymedical.resquest.DiagnosticDTO;

@Service
public class DiagnosticService {

    private static final Logger logger = LogManager.getLogger(DiagnosticService.class);

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DiagnosticRepository diagnosticRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public DiagnosticEntity save(DiagnosticDTO diagnosticDTO, PatientEntity patientEntity) throws HealthIdException {

        DiagnosticEntity diagnosticEntity = new DiagnosticEntity(patientEntity, diagnosticDTO.getRegistered(), diagnosticDTO.getHealthId());

        // Update DiagnosticEntity in PatientEntity
        Set<DiagnosticEntity> diagnosticEntities = new HashSet<DiagnosticEntity>();
        diagnosticEntities.add(diagnosticEntity);
        patientEntity.setDiagnostic(diagnosticEntities);

        Set<PlanningEntity> planningEntities = new HashSet<PlanningEntity>();
        if (diagnosticDTO.getHealthId() % 3 == 0 && diagnosticDTO.getHealthId() % 5 == 0) {
            planningEntities.add(new PlanningEntity(Pathology.CARDIOLOGY.getValue(), diagnosticEntity));
            planningEntities.add(new PlanningEntity(Pathology.TRAUMATOLOGY.getValue(), diagnosticEntity));
        } else if (diagnosticDTO.getHealthId() % 3 == 0) {
            planningEntities.add(new PlanningEntity(Pathology.CARDIOLOGY.getValue(), diagnosticEntity));
        } else if (diagnosticDTO.getHealthId() % 5 == 0) {
            planningEntities.add(new PlanningEntity(Pathology.TRAUMATOLOGY.getValue(), diagnosticEntity));
        } else {
            throw new HealthIdException("Health Id is invalid. It must be superior at zero and to be a multiple of 3 or 5.");
        }

        diagnosticEntity.setPlanning(planningEntities);

        patientRepository.save(patientEntity);

        return diagnosticEntity;
    }
}
