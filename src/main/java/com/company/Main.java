package com.company;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        DbStorage storage = new DbStorage();
        TemperatureService tempService = new TemperatureServiceImpl(storage);
        TemperatureData tempStat1 = new TemperatureData(21.3, LocalDateTime.now());
        TemperatureData tempStat2 = new TemperatureData(36.6, LocalDateTime.now());
        LocalDateTime from = LocalDateTime.of(2014,11,1,14,11);
        LocalDateTime to = LocalDateTime.of(2024,11,1,14,11);

        System.out.println(tempService.averageTemperature(from, to));

        storage.update("TRUNCATE TABLE TemperatureStatistics;");
    }
}
