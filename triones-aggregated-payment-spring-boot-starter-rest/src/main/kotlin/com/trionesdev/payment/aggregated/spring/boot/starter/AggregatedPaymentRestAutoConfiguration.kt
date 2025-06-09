package com.trionesdev.payment.aggregated.spring.boot.starter

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.trionesdev.payment.aggregated.spring"])
class AggregatedPaymentRestAutoConfiguration {
}