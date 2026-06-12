package com.algaworks.algasensors.device.management

import io.hypersistence.tsid.TSID
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.temporal.ChronoUnit

class TSIDTest {

    @Test
    fun `should generate TSID`() {
        val tsidFast: TSID = TSID.fast()
        println(tsidFast)               // 0QNYX33RXJE8A
        println(tsidFast.toLong())      // 853393753616496906
        println(tsidFast.instant)       // 2026-06-12T22:02:06.151Z

        val tsid: TSID = IDGenerator.generateTSID()
        Assertions.assertThat(tsid.instant)
            .isCloseTo(Instant.now(), Assertions.within(1, ChronoUnit.MINUTES))
    }

}