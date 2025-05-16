package com.trionesdev.payment.aggregated.spring.boot.starter.rest

import com.trionesdev.payment.wxpay.v3.WxPayTemplate
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class WxPayService(
    private val wxpayTemplate: WxPayTemplate
) {
    private val log = LoggerFactory.getLogger(WxPayService::class.java)


    fun createJsapiOrder(){}

}