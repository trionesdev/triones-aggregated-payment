package com.trionesdev.payment.aggregated.spring.boot.starter.rest

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/payment/wx")
class WxPayResource {

    @PostMapping(value = ["transaction-notify"])
    fun notif(
        @RequestHeader("Wechatpay-Nonce") nonce: String,
        @RequestHeader("Wechatpay-Signature") signature: String,
        @RequestHeader("Wechatpay-Timestamp") timestamp: String,
        @RequestHeader("Wechatpay-Serial") serial: String,
        @RequestBody body: String
    ) {

    }
}