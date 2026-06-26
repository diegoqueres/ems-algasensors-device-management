package com.algaworks.algasensors.device.management.domain.model

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Sensor(
    @Id
    @AttributeOverride(name = "value", column = Column(name = "id", columnDefinition = "BIGINT"))
    val id: SensorId? = null,
    var name: String = "",
    var ip: String = "",
    var location: String = "",
    var protocol: String = "",
    var model: String = "",
    var enabled: Boolean = false
)