package fr.softwaymedical.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Diagnostic")
@IdClass(DiagnosticId.class)
public class DiagnosticEntity {

    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patient", referencedColumnName = "socialSecurity")
    @JsonManagedReference
    private PatientEntity patient;

    @Id
    @Column(name = "registered")
    private Date registered;

    @Column(name = "healthId")
    private int healthId;

    @OneToMany(mappedBy = "diagnostic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<PlanningEntity> planning;

    public DiagnosticEntity() {
        super();
    }

    public DiagnosticEntity(PatientEntity patient, Date registered, int healthId) {
        super();
        this.patient = patient;
        this.registered = registered;
        this.healthId = healthId;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public int getHealthId() {
        return healthId;
    }

    public void setHealthId(int healthId) {
        this.healthId = healthId;
    }

    public Set<PlanningEntity> getPlanning() {
        return planning;
    }

    public void setPlanning(Set<PlanningEntity> planning) {
        this.planning = planning;
    }

    @Override
    public String toString() {
        return "DiagnosticEntity [patient.getSocialSecurity()=" + patient.getSocialSecurity() + ", registered=" + registered + ", healthId="
                + healthId + "]";
    }
}
