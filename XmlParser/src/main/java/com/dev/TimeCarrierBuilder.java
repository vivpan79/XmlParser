package com.dev;

/**
 * Created by vivek_000 on 2015-11-28.
 */
public class TimeCarrierBuilder {
    private Integer eco;
    private String generalTime;
    private String deviatingTime;

    public void setEco(String eco) {
        this.eco = Integer.parseInt(eco);
    }

    public void setEco(Integer eco) {
        this.eco = eco;
    }

    public void setGeneralTime(String generalTime) {
        this.generalTime = generalTime;
    }

    public void setDeviatingTime(String deviatingTime) {
        this.deviatingTime = deviatingTime;
    }

    public TimeCarrier getTimeCarrier() {
        return new TimeCarrier(eco, generalTime, deviatingTime);
    }
}
