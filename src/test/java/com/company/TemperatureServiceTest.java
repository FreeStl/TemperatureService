package com.company;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TemperatureServiceTest {
    Storage storage = mock(Storage.class);
    TemperatureService tempService = new TemperatureServiceImpl(storage);

    @Test
    public void TemperatureServiceShouldSetTemperature() {
        TemperatureData tempData = mock(TemperatureData.class);

        when(storage.saveTemperature(tempData)).thenReturn(true);
        assertTrue(tempService.saveTemperature(tempData), "temperature data is set");
        verify(storage).saveTemperature(tempData);
    }

    @Test
    public void TemperatureServiceShouldGetAverageTemperature() {
        LocalDateTime testTime = LocalDateTime.MIN;
        TemperatureData tempStat1 = new TemperatureData(21.3, LocalDateTime.now());
        TemperatureData tempStat2 = new TemperatureData(36.6, LocalDateTime.now());
        List<TemperatureData> tempList = new ArrayList<>();
        tempList.add(tempStat1);
        tempList.add(tempStat2);

        when(storage.getTemperatureByTime(testTime, testTime)).thenReturn(tempList);
        assertEquals(28.950000000000006, tempService.averageTemperature(testTime, testTime),
                "average temp is calculated");
        verify(storage).getTemperatureByTime(testTime, testTime);

    }
}
