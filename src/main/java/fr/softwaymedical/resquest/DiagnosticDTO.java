package fr.softwaymedical.resquest;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class DiagnosticDTO {

    @NotBlank(message = "Social security is required.")
    @Schema(example = "123456789")
    private String socialSecurity;

    @NotNull(message = "Registered date is required.")
    @Schema(example = "2023-01-01T00:00:00.000Z")
    private Date registered;

    @Schema(example = "15")
    private int healthId;

    public DiagnosticDTO(String socialSecurity, Date registered, int healthId) {
        super();
        this.socialSecurity = socialSecurity;
        this.registered = registered;
        this.healthId = healthId;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
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

    @Override
    public String toString() {
        return "DiagnosticDTO [socialSecurity=" + socialSecurity + ", registered=" + registered + ", healthId=" + healthId + "]";
    }
}
