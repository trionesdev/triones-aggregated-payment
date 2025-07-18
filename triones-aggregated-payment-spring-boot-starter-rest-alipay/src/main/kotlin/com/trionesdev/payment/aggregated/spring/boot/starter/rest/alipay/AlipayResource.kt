package com.trionesdev.payment.aggregated.spring.boot.starter.rest.alipay

import com.trionesdev.payment.aggregated.alipay.AlipayAggregatedPaymentChannel
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("rest-api/payment/alipay")
class AlipayResource(var alipayAggregatedPaymentChannel: AlipayAggregatedPaymentChannel) {
    val logger: Logger? = LoggerFactory.getLogger(AlipayResource::class.java)

    /**
     * 支付宝异步通知
     */
    @PostMapping(value = ["notify"])
    fun transactionNotify(
        request: HttpServletRequest
    ): String {
        val tradeStatus = request.getParameter("trade_status")
        if ("TRADE_SUCCESS" == tradeStatus) {
            alipayAggregatedPaymentChannel.transactionNotify(request.parameterMap)
            return "success"
        }
        return "fail"
    }

    /**
     * 支付宝应用网关
     */
    @PostMapping(value = ["app-gateway"])
    fun appGateway() {
    }
}