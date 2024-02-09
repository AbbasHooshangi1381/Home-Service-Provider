package com.example.springbootfinal.dto.Expert;

public class ExpertSearchCriteriaDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String specialistField;
    private String averageScoresOrderBy;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialistField() {
        return specialistField;
    }

    public void setSpecialistField(String specialistField) {
        this.specialistField = specialistField;
    }

    public String getAverageScoresOrderBy() {
        return averageScoresOrderBy;
    }

    public void setAverageScoresOrderBy(String averageScoresOrderBy) {
        this.averageScoresOrderBy = averageScoresOrderBy;
    }

}
