package com.weather.weatherreport.repo;

import com.weather.weatherreport.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorDataRepository extends JpaRepository<SensorData,Long> {
}
