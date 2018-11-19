package com.sis.model;

public class Satellite {


    private String satname;
    private String country;
    private String apogee;
    private String perigee;
    private String purpose;
    private String orbit;

    public Satellite(){
        this.satname = "";
        this.country = "";
        this.apogee = "";
        this.perigee = "";
        this.purpose = "";
        this.orbit = "";
    }
    public String getOrbit() {
        return orbit;
    }

    public void setOrbit(String orbit) {
        this.orbit = orbit;
    }

    public String getSatname() {
        return satname;
    }

    public void setSatname(String satname) {
        this.satname = satname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getApogee() {
        return apogee;
    }

    public void setApogee(String apogee) {
        this.apogee = apogee;
    }

    public String getPerigee() {
        return perigee;
    }

    public void setPerigee(String perigee) {
        this.perigee = perigee;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
