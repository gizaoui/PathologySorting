package fr.softwaymedical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.softwaymedical.model.PlanningEntity;
import fr.softwaymedical.repository.PlanningRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlanningController {

    @Autowired
    private PlanningRepository planningRepository;

    @PostMapping(value = "/allPlanning")
    public ResponseEntity<Iterable<PlanningEntity>> allPatient() {
        Iterable<PlanningEntity> planningEntity = this.planningRepository.findAll();
        return ResponseEntity.ok(planningEntity);
    }

}
