package com.company;

import java.time.LocalDateTime;
import java.util.List;

interface Storage {
    boolean saveTemperature(TemperatureData temperature);
    List<TemperatureData> getTemperatureByTime(LocalDateTime from, LocalDateTime to);
}
