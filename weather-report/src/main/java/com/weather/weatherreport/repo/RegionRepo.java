package com.weather.weatherreport.repo;

import com.weather.weatherreport.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepo extends JpaRepository<Region,Long> {
}
