package com.algaworks.algasensors.device.management.api.config.jackson

import tools.jackson.core.JsonGenerator
import tools.jackson.databind.ValueSerializer
import io.hypersistence.tsid.TSID
import tools.jackson.databind.SerializationContext

class TSIDToStringSerializer : ValueSerializer<TSID>() {

    override fun serialize(
        value: TSID,
        gen: JsonGenerator,
        ctxt: SerializationContext
    ) {
        gen.writeString(value.toString())
    }

}