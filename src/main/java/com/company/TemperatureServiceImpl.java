package com.company;

import java.time.LocalDateTime;
import java.util.List;


public class TemperatureServiceImpl implements TemperatureService {
    private final Storage storage;

    public TemperatureServiceImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public double averageTemperature(LocalDateTime from, LocalDateTime to) {
        List<TemperatureData> temperatures = storage.getTemperatureByTime(from, to);
         return temperatures.stream()
                .mapToDouble(TemperatureData::getTemperature)
                .average()
                 .orElseThrow();
    }

    @Override
    public boolean saveTemperature(TemperatureData temperature) {
        return storage.saveTemperature(temperature);
    }
}




