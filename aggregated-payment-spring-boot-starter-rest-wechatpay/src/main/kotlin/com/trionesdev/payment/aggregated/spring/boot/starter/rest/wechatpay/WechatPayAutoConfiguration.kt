package com.trionesdev.payment.aggregated.spring.boot.starter.rest.wechatpay

import com.trionesdev.payment.aggregated.AggregatedPaymentNotify
import com.trionesdev.payment.aggregated.wechatpay.WechatPayService
import com.trionesdev.payment.wechatpay.v3.WechatPayTemplate
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WechatPayAutoConfiguration {

    @Bean
    fun wechatPayService(
        wechatPayTemplate: ObjectProvider<WechatPayTemplate>,
        aggregatedPaymentNotify: ObjectProvider<AggregatedPaymentNotify>,
    ): WechatPayService {
        return WechatPayService(wechatPayTemplate.ifAvailable, aggregatedPaymentNotify.ifAvailable)
    }
}