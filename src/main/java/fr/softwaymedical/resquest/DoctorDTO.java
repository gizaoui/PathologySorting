package fr.softwaymedical.resquest;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

public class DoctorDTO {

    @NotBlank(message = "email is required.")
    @Schema(example = "macdonald.gaines@softwaymedical.fr")
    private String email;

    @Schema(example = "Macdonald")
    private String firstname;

    @Schema(example = "Gaines")
    private String lastname;

    @NotBlank(message = "specialty is required.")
    @Schema(example = "urgentiste")
    private String specialty;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "DoctorDTO [email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", specialty=" + specialty + "]";
    }
}
