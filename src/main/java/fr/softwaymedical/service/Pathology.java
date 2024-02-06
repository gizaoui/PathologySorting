package fr.softwaymedical.service;

public enum Pathology {

    CARDIOLOGY("cardiology"), TRAUMATOLOGY("traumatology");;

    private String pathology;

    private Pathology(String pathology) {
        this.pathology = pathology;
    }

    public String getValue() {
        return this.pathology;
    }
}
