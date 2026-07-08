package com.algaworks.algasensors.device.management.api.model

import io.hypersistence.tsid.TSID

data class SensorOutput(
    val id: TSID,
    val name: String,
    val ip: String,
    val location: String,
    val protocol: String,
    val model: String,
    val enabled: Boolean
)
