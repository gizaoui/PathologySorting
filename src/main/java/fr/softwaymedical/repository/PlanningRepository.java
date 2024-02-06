package fr.softwaymedical.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.softwaymedical.model.PlanningEntity;

@Repository
public interface PlanningRepository extends CrudRepository<PlanningEntity, Long> {

}
