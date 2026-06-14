package com.algaworks.algasensors.device.management.common

import io.hypersistence.tsid.TSID
import java.util.Optional

object IDGenerator {
    private val tsidFactory: TSID.Factory

    init {
        Optional.ofNullable(System.getenv("tsid.node"))
            .ifPresent { tsidNode -> System.setProperty("tsid.node", tsidNode) }
        Optional.ofNullable(System.getenv("tsid.node.count"))
            .ifPresent { tsidNodeCount -> System.setProperty("tsid.node.count", tsidNodeCount) }

        this.tsidFactory = TSID.Factory.builder().build()
    }

    fun generateTSID(): TSID {
        return tsidFactory.generate()
    }
}