package fr.softwaymedical.resquest;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

public class PatientDTO {

    @NotBlank(message = "Social security is required.")
    @Schema(example = "123456789")
    private String socialSecurity;

    @Schema(example = "sofia.mitchell@hotmail.fr")
    private String email;

    @Schema(example = "Sofia")
    private String firstname;

    @Schema(example = "Mitchell")
    private String lastname;

    @Schema(example = "38")
    private int age;

    @Schema(example = "female")
    private String gender;

    @Schema(example = "+1 (800) 403-3710")
    private String phone;

    @Schema(example = "232 Cheever Place, Ruckersville, Pennsylvania, 4623")
    private String address;

    public PatientDTO(String socialSecurity, String email, String firstname, String lastname, int age, String gender, String phone, String address) {
        super();
        this.socialSecurity = socialSecurity;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PatientDTO [socialSecurity=" + socialSecurity + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", age="
                + age + ", gender=" + gender + ", phone=" + phone + ", address=" + address + "]";
    }

}
