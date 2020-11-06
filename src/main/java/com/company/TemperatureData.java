package com.company;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TemperatureData {
    private final double temperature;
    private final LocalDateTime time;

    public TemperatureData(double temperature, LocalDateTime time) {
        this.temperature = temperature;
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
