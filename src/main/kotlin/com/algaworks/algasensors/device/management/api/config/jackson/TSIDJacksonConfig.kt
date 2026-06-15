package com.algaworks.algasensors.device.management.api.config.jackson

import tools.jackson.databind.JacksonModule
import tools.jackson.databind.module.SimpleModule
import io.hypersistence.tsid.TSID
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TSIDJacksonConfig {

    @Bean
    fun tsidModule(): JacksonModule {
        val module = SimpleModule()
        module.addSerializer(TSID::class.java, TSIDToStringSerializer())
        return module
    }

}