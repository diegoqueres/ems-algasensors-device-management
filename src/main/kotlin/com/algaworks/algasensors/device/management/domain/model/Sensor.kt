package com.algaworks.algasensors.device.management.domain.model

import io.hypersistence.tsid.TSID

data class Sensor(
    val id: TSID? = null,
    var name: String = "",
    var ip: String = "",
    var location: String = "",
    var protocol: String = "",
    var model: String = "",
    var enabled: Boolean = false
)