package com.algaworks.algasensors.device.management.domain.repository

import com.algaworks.algasensors.device.management.domain.model.Sensor
import com.algaworks.algasensors.device.management.domain.model.SensorId
import org.springframework.data.jpa.repository.JpaRepository

interface SensorRepository : JpaRepository<Sensor, SensorId>