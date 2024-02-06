package fr.softwaymedical.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.softwaymedical.model.DiagnosticEntity;
import fr.softwaymedical.model.PatientEntity;
import fr.softwaymedical.model.PlanningEntity;
import fr.softwaymedical.repository.DiagnosticRepository;
import fr.softwaymedical.resquest.DiagnosticDTO;

@Service
public class PlanningService {

    private static final Logger logger = LogManager.getLogger(PlanningService.class);

    @Autowired
    DiagnosticRepository diagnosticRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<PlanningEntity> AllPlanningByDiagnostic(DiagnosticDTO diagnosticDTO, PatientEntity patientEntity) {
        logger.info(diagnosticDTO);
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<PlanningEntity> query = builder.createQuery(PlanningEntity.class);
        Root<PlanningEntity> planning = query.from(PlanningEntity.class);
        Join<PlanningEntity, DiagnosticEntity> diagnostic = planning.join("diagnostic", JoinType.INNER);
        query.where(builder.and(builder.equal(diagnostic.get("patient"), patientEntity), builder.equal(diagnostic.get("registered"), diagnosticDTO.getRegistered())));
        query.select(planning);
        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    public int deletePlanning(long id) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaDelete<PlanningEntity> query = builder.createCriteriaDelete(PlanningEntity.class);
        Root<PlanningEntity> planning = query.from(PlanningEntity.class);
        query.where(builder.equal(planning.get("id"), id));
        return entityManager.createQuery(query).executeUpdate();
    }

}
