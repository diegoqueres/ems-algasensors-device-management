package com.algaworks.algasensors.device.management.domain.model

import io.hypersistence.tsid.TSID
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
open class SensorId(private val value: TSID?) : Serializable {

    protected constructor() : this(null)

    constructor(value: Long) : this(TSID.from(value))

    constructor(value: String) : this(TSID.from(value))

    override fun toString(): String = value.toString()

}