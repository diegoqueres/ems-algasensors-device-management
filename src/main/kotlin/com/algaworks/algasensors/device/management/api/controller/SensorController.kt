package com.algaworks.algasensors.device.management.api.controller

import com.algaworks.algasensors.device.management.api.model.SensorInput
import com.algaworks.algasensors.device.management.api.model.SensorOutput
import com.algaworks.algasensors.device.management.common.IDGenerator
import com.algaworks.algasensors.device.management.domain.model.Sensor
import com.algaworks.algasensors.device.management.domain.model.SensorId
import com.algaworks.algasensors.device.management.domain.repository.SensorRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/sensors")
class SensorController(
    private val sensorRepository: SensorRepository
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody sensorInput: SensorInput): SensorOutput {
        val sensor = Sensor(
            id = SensorId(IDGenerator.generateTSID()),
            name = sensorInput.name,
            ip = sensorInput.ip,
            location = sensorInput.location,
            protocol = sensorInput.protocol,
            model = sensorInput.model,
            enabled = false
        )

        val savedSensor = sensorRepository.saveAndFlush(sensor)
        return SensorOutput(
            id = savedSensor.id!!.value!!,
            name = savedSensor.name,
            ip = savedSensor.ip,
            location = savedSensor.location,
            protocol = savedSensor.protocol,
            model = savedSensor.model,
            enabled = savedSensor.enabled
        )
    }

}