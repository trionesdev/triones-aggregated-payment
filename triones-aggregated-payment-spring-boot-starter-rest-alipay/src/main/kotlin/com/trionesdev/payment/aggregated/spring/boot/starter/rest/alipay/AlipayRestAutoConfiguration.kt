package com.trionesdev.payment.aggregated.spring.boot.starter.rest.alipay

import com.trionesdev.payment.aggregated.AggregatedPaymentNotifyCallback
import com.trionesdev.payment.aggregated.alipay.AlipayAggregatedPaymentChannel
import com.trionesdev.payment.alipay.v3.Alipay
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [AlipayResource::class])
class AlipayRestAutoConfiguration {
    @Bean
    fun alipayService(
        alipay: ObjectProvider<Alipay>,
        aggregatedPaymentNotify: ObjectProvider<AggregatedPaymentNotifyCallback>,
    ): AlipayAggregatedPaymentChannel {
        return AlipayAggregatedPaymentChannel(alipay.getIfAvailable(), aggregatedPaymentNotify.getIfAvailable())
    }
}