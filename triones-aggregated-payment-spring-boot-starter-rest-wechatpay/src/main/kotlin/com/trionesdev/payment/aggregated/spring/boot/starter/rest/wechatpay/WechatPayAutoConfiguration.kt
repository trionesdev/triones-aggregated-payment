package com.trionesdev.payment.aggregated.spring.boot.starter.rest.wechatpay

import com.trionesdev.payment.aggregated.AggregatedPaymentNotifyCallback
import com.trionesdev.payment.aggregated.wechatpay.WechatPayAggregatedPaymentChannel
import com.trionesdev.payment.wechatpay.v3.WechatPay
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [WechatPayResource::class])
class WechatPayAutoConfiguration {

    @Bean
    fun wechatPayService(
        wechatpay: ObjectProvider<WechatPay>,
        aggregatedPaymentNotify: ObjectProvider<AggregatedPaymentNotifyCallback>,
    ): WechatPayAggregatedPaymentChannel {
        return WechatPayAggregatedPaymentChannel(wechatpay.ifAvailable, aggregatedPaymentNotify.ifAvailable)
    }
}