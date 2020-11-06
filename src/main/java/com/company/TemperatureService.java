package com.company;

import java.time.LocalDateTime;

interface TemperatureService {
    double averageTemperature(LocalDateTime from, LocalDateTime to);
    boolean saveTemperature(TemperatureData temperature);
}
