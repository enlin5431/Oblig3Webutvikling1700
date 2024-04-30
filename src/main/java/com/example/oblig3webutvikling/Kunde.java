package com.example.oblig3webutvikling;

public class Kunde {
    private String film;
    private String fornavn;
    private String etternavn;
    private int antall;
    private int telefonnr;

    private String epost;

    public Kunde(String film,String fornavn,
                 String etternavn,int antall,
                 int telefonnr,String epost){
        this.fornavn=fornavn;
        this.etternavn=etternavn;
        this.antall=antall;
        this.telefonnr=telefonnr;
        this.epost=epost;
        this.film=film;
    }
    public Kunde(){}
    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public int getAntall() {
        return antall;
    }

    public void setAntall(int antall) {
        this.antall = antall;
    }

    public int getTelefonnr() {
        return telefonnr;
    }

    public void setTelefonnr(int telefonnr) {
        this.telefonnr = telefonnr;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }
}

