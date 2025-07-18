package com.trionesdev.payment.aggregated.spring.boot.starter.rest.alipay

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("rest-api/payment/alipay")
class AlipayResource {
    val logger: Logger? = LoggerFactory.getLogger(AlipayResource::class.java)

    /**
     * 支付宝异步通知
     */
    @PostMapping(value = ["notify"])
    fun transactionNotify(
        request: HttpServletRequest,
        @RequestHeader("alipay-signature") signature: String?,
        @RequestHeader("alipay-sn") sn: String?,
        @RequestHeader("alipay-timestamp") timestamp: String?,
        @RequestHeader("alipay-nonce") nonce: String?
    ) {
    }

    /**
     * 支付宝应用网关
     */
    @PostMapping(value = ["app-gateway"])
    fun appGateway() {
    }
}