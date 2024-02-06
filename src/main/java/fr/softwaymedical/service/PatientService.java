package fr.softwaymedical.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.softwaymedical.model.PatientEntity;
import fr.softwaymedical.repository.DiagnosticRepository;
import fr.softwaymedical.repository.PatientRepository;
import fr.softwaymedical.resquest.PatientDTO;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DiagnosticRepository diagnosticRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public PatientEntity save(PatientDTO patientDTO) {

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setSocialSecurity(patientDTO.getSocialSecurity());
        patientEntity.setEmail(patientDTO.getEmail());
        patientEntity.setFirstname(patientDTO.getFirstname());
        patientEntity.setLastname(patientDTO.getLastname());
        patientEntity.setGender(patientDTO.getGender());
        patientEntity.setAge(patientDTO.getAge());
        patientEntity.setPhone(patientDTO.getPhone());
        patientEntity.setAddress(patientDTO.getAddress());
        return patientRepository.save(patientEntity);
    }

}
