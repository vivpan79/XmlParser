package com.dev;

/**
 * Created by vivek_000 on 2015-11-28.
 */
public class TimeCarrier {
    private final Integer eco;
    private final String generalTime;
    private final String deviatingTime;

    public TimeCarrier(final Integer eco, final String generalTime, final String deviatingTime) {
        this.eco = eco;
        this.generalTime = generalTime;
        this.deviatingTime = deviatingTime;
    }

    public Integer getEco() {
        return eco;
    }

    public String getGeneralTime() {
        return generalTime;
    }

    public String getDeviatingTime() {
        return deviatingTime;
    }

    @Override
    public String toString() {
        return "TimeCarrier{" +
                "eco='" + eco + '\'' +
                ", generalTime='" + generalTime + '\'' +
                ", deviatingTime='" + deviatingTime + '\'' +
                '}';
    }
}
