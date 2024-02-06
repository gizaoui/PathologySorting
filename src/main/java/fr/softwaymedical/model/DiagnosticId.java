package fr.softwaymedical.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class DiagnosticId implements Serializable {

    private static final long serialVersionUID = 1L;

    private PatientEntity patient;

    private Date registered;

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

    @Override
    public int hashCode() {
        return Objects.hash(patient.getSocialSecurity(), registered);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DiagnosticId other = (DiagnosticId) obj;
        return Objects.equals(patient.getSocialSecurity(), other.patient.getSocialSecurity()) && Objects.equals(registered, other.registered);
    }

}
