package com.algaworks.algasensors.device.management.api.controller

import com.algaworks.algasensors.device.management.api.model.SensorInput
import com.algaworks.algasensors.device.management.api.model.SensorOutput
import com.algaworks.algasensors.device.management.common.IDGenerator
import com.algaworks.algasensors.device.management.domain.model.Sensor
import com.algaworks.algasensors.device.management.domain.model.SensorId
import com.algaworks.algasensors.device.management.domain.repository.SensorRepository
import io.hypersistence.tsid.TSID
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/sensors")
class SensorController(
    private val sensorRepository: SensorRepository
) {

    @GetMapping
    fun search(@PageableDefault pageable: Pageable) : Page<SensorOutput> {
        val sensors: Page<Sensor> = sensorRepository.findAll(pageable)

        return sensors.map(this::convertToModel)
    }

    @GetMapping("{sensorId}")
    fun getOne(@PathVariable sensorId: TSID) : SensorOutput {
        val sensor = sensorRepository.findById(SensorId(sensorId))
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        return convertToModel(sensor)
    }

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
        return convertToModel(savedSensor)
    }

    @PutMapping("/{sensorId}")
    fun update(@PathVariable sensorId: TSID, @RequestBody input: SensorInput): SensorOutput {
        val sensor = sensorRepository.findById(SensorId(sensorId))
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        sensor.name = input.name
        sensor.location = input.location
        sensor.ip = input.ip
        sensor.model = input.model
        sensor.protocol = input.protocol

        return convertToModel(sensorRepository.save(sensor))
    }

    @DeleteMapping("/{sensorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable sensorId: TSID) {
        val sensor = sensorRepository.findById(SensorId(sensorId))
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        sensorRepository.delete(sensor)
    }

    private fun convertToModel(sensor: Sensor): SensorOutput {
        return SensorOutput(
            id = sensor.id!!.value!!,
            name = sensor.name,
            ip = sensor.ip,
            location = sensor.location,
            protocol = sensor.protocol,
            model = sensor.model,
            enabled = sensor.enabled
        )
    }

}