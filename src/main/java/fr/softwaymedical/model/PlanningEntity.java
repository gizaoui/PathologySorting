package fr.softwaymedical.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Planning")
public class PlanningEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "service", length = 30)
    private String service;

    // @formatter:off
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumns({ 
        @JoinColumn(name = "registered", referencedColumnName = "registered"), 
        @JoinColumn(name = "patient", referencedColumnName = "patient") })
    private DiagnosticEntity diagnostic;
    // @formatter:on

    public PlanningEntity() {
        super();
    }

    public PlanningEntity(String service, DiagnosticEntity diagnostic) {
        super();
        this.service = service;
        this.diagnostic = diagnostic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public DiagnosticEntity getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(DiagnosticEntity diagnostic) {
        this.diagnostic = diagnostic;
    }

    @Override
    public String toString() {
        return "PlanningEntity [id=" + id + ", service=" + service + ", diagnostic=" + diagnostic + "]";
    }

}
