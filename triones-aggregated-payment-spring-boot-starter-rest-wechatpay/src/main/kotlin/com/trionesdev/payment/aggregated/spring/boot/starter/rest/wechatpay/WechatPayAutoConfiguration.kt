package com.trionesdev.payment.aggregated.spring.boot.starter.rest.wechatpay

import com.trionesdev.payment.aggregated.AggregatedPaymentNotifyCallback
import com.trionesdev.payment.aggregated.wechatpay.WechatPayService
import com.trionesdev.payment.wechatpay.v3.WechatPay
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WechatPayAutoConfiguration {

    @Bean
    fun wechatPayService(
        wechatpay: ObjectProvider<WechatPay>,
        aggregatedPaymentNotify: ObjectProvider<AggregatedPaymentNotifyCallback>,
    ): WechatPayService {
        return WechatPayService(wechatpay.ifAvailable, aggregatedPaymentNotify.ifAvailable)
    }
}