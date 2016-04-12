package com.example.grace.travelexperts;

/**
 * Created by Mary Grace // agent class
 */

public class Agent {

    // Declaration
    private int AgentId;
    private String AgtFirstName;
   // private String AgtMiddleInitial;
    private String AgtLastName;
    private String AgtBusPhone;
    private String AgtEmail;
    private String AgtPosition;
    private int AgencyId;

    // Constructor
    public Agent(){}

    public Agent(int agentId, String agtFirstName, String agtLastName, String agtBusPhone, String agtEmail, String agtPosition, int agencyId) {
        setAgentId(agentId);
        setAgtFirstName(agtFirstName);

        setAgtLastName(agtLastName);
        setAgtBusPhone(agtBusPhone);
        setAgtEmail(agtEmail);
        setAgtPosition(agtPosition);
        setAgencyId(agencyId);
    }

    // Getter and Setter
    public int getAgentId() {
        return AgentId;
    }

    public void setAgentId(int agentId) {
        AgentId = agentId;
    }

    public String getAgtFirstName() {
        return AgtFirstName;
    }

    public void setAgtFirstName(String agtFirstName) {
        AgtFirstName = agtFirstName;
    }



    public String getAgtLastName() {
        return AgtLastName;
    }

    public void setAgtLastName(String agtLastName) {
        AgtLastName = agtLastName;
    }

    public String getAgtBusPhone() {
        return AgtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone) {
        AgtBusPhone = agtBusPhone;
    }

    public String getAgtEmail() {
        return AgtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        AgtEmail = agtEmail;
    }

    public String getAgtPosition() {
        return AgtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        AgtPosition = agtPosition;
    }

    public int getAgencyId() {
        return AgencyId;
    }

    public void setAgencyId(int agencyId) {
        AgencyId = agencyId;
    }
}

