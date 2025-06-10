package com.trionesdev.payment.aggregated.spring

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.trionesdev.payment.aggregated.spring"])
class AggregatedPaymentAutoConfiguration {
}