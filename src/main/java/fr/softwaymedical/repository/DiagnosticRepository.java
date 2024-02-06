package fr.softwaymedical.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.softwaymedical.model.DiagnosticEntity;
import fr.softwaymedical.model.DiagnosticId;

@Repository
public interface DiagnosticRepository extends CrudRepository<DiagnosticEntity, DiagnosticId> {

}
