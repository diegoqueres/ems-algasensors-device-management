package com.algaworks.algasensors.device.management.api.controller

import com.algaworks.algasensors.device.management.api.model.SensorInput
import com.algaworks.algasensors.device.management.common.IDGenerator
import com.algaworks.algasensors.device.management.domain.model.Sensor
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/sensors")
class SensorController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody sensorInput: SensorInput): Sensor {
        return Sensor(
            id = IDGenerator.generateTSID(),
            name = sensorInput.name,
            ip = sensorInput.ip,
            location = sensorInput.location,
            protocol = sensorInput.protocol,
            model = sensorInput.model,
            enabled = false
        )
    }

}