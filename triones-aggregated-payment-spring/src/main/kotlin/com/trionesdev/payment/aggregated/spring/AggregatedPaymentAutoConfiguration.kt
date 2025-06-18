package com.trionesdev.payment.aggregated.spring

import com.trionesdev.payment.aggregated.AggregatedPaymentChannel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AggregatedPaymentAutoConfiguration {

    @Bean
    fun aggregatedPaymentFactory(aggregatedPaymentChannels: MutableList<AggregatedPaymentChannel>): AggregatedPaymentFactory {
        return AggregatedPaymentFactory(aggregatedPaymentChannels)
    }

}