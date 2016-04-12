package com.example.grace.travelexperts;

/**
 * Created by grace // agency class
 */
public class Agency {

    // Declaration
    private int AgencyId;
    private String AgncyAddress;
    private String AgncyCity;
    private String AgncyProv;
    private String AgncyPostal;
    private String AgncyCountry;
    private String AgncyPhone;
    private String AgncyFax;

    // Constructor
    public Agency(){}

    public Agency(int agencyId, String agncyAddress, String agncyCity, String agncyProv, String agncyPostal, String agncyCountry, String agncyPhone, String agncyFax) {
        setAgencyId(agencyId);
        setAgncyAddress(agncyAddress);
        setAgncyCity(agncyCity);
        setAgncyProv(agncyProv);
        setAgncyPostal(agncyPostal);
        setAgncyCountry(agncyCountry);
        setAgncyPhone(agncyPhone);
        setAgncyFax(agncyFax);
    }

    // get set
    public int getAgencyId() {
        return AgencyId;
    }

    public void setAgencyId(int agencyId) {
        AgencyId = agencyId;
    }

    public String getAgncyAddress() {
        return AgncyAddress;
    }

    public void setAgncyAddress(String agncyAddress) {
        AgncyAddress = agncyAddress;
    }

    public String getAgncyCity() {
        return AgncyCity;
    }

    public void setAgncyCity(String agncyCity) {
        AgncyCity = agncyCity;
    }

    public String getAgncyProv() {
        return AgncyProv;
    }

    public void setAgncyProv(String agncyProv) {
        AgncyProv = agncyProv;
    }

    public String getAgncyPostal() {
        return AgncyPostal;
    }

    public void setAgncyPostal(String agncyPostal) {
        AgncyPostal = agncyPostal;
    }

    public String getAgncyCountry() {
        return AgncyCountry;
    }

    public void setAgncyCountry(String agncyCountry) {
        AgncyCountry = agncyCountry;
    }

    public String getAgncyPhone() {
        return AgncyPhone;
    }

    public void setAgncyPhone(String agncyPhone) {
        AgncyPhone = agncyPhone;
    }

    public String getAgncyFax() {
        return AgncyFax;
    }

    public void setAgncyFax(String agncyFax) {
        AgncyFax = agncyFax;
    }
}

