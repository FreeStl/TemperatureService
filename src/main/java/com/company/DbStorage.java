package com.company;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SqlNoDataSourceInspection")
class DbStorage implements Storage {
    private static final String url = "jdbc:h2:~/test";
    private static final String username = "";
    private static final String password = "";

    public DbStorage() {
        String prepareTable = "CREATE TABLE IF NOT EXISTS TemperatureStatistics(dateTime TIMESTAMP, temperature Double);";
        update(prepareTable);
    }

    @Override
    public boolean saveTemperature(TemperatureData tempStats) {
        String query = "insert into TemperatureStatistics(dateTime, temperature) values(?, ?);";
        boolean isUpdated = false;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(tempStats.getTime()));
            preparedStatement.setDouble(2, tempStats.getTemperature());
            isUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public List<TemperatureData> getTemperatureByTime(LocalDateTime from, LocalDateTime to) {
        String query = "select * from TemperatureStatistics where dateTime >= ? and dateTime <= ?";
        List<TemperatureData> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(from));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(to));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TemperatureData temperatureStatistics = new TemperatureData(
                        resultSet.getDouble("temperature"),
                        resultSet.getTimestamp("dateTime").toLocalDateTime()
                );
                result.add(temperatureStatistics);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    public void update(String query) {
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
