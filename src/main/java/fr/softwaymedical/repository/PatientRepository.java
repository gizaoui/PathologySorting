package fr.softwaymedical.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.softwaymedical.model.PatientEntity;

@Repository
public interface PatientRepository extends CrudRepository<PatientEntity, String> {

}
