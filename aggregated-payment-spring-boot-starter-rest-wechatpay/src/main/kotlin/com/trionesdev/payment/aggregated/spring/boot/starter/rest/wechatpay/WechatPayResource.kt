package com.trionesdev.payment.aggregated.spring.boot.starter.rest.wechatpay

import com.trionesdev.payment.aggregated.wechatpay.WechatPayService
import com.trionesdev.payment.util.JsonUtils
import com.trionesdev.payment.wechatpay.v3.model.notify.WechatPayNotifyRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/payment/wechatpay")
class WechatPayResource(
    var wechatPayService: WechatPayService
) {

    /**
     * 微信支付回调
     */
    @PostMapping(value = ["transaction-notify"])
    fun notif(
        response: HttpServletResponse,
        @RequestHeader("Wechatpay-Nonce") nonce: String,
        @RequestHeader("Wechatpay-Signature") signature: String,
        @RequestHeader("Wechatpay-Timestamp") timestamp: String,
        @RequestHeader("Wechatpay-Serial") serial: String,
        @RequestBody body: String
    ): TransactionNotifyVO {
        try {
            wechatPayService.transactionNotify(WechatPayNotifyRequest(nonce, signature, timestamp, serial, body))
        } catch (e: Exception) {
            response.sendError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                JsonUtils.writeValueAsString(
                    TransactionNotifyVO(
                        code = "FAIL", message = e.message
                    )
                )
            )
        }
        return TransactionNotifyVO(code = "SUCCESS", message = "Transaction notify success")
    }
}