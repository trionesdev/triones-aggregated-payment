package com.trionesdev.payment.aggregated.spring.boot.starter.rest.wechatpay

import com.trionesdev.payment.aggregated.wechatpay.WechatPayAggregatedPayment
import com.trionesdev.payment.util.JsonUtils
import com.trionesdev.payment.wechatpay.v3.model.notify.WechatPayNotifyParseRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("rest-api/payment/wechatpay")
class WechatPayResource(
    var wechatPayAggregatedPayment: WechatPayAggregatedPayment
) {

    /**
     * 微信支付回调
     */
    @PostMapping(value = ["transaction-notify"])
    fun transactionNotify(
        response: HttpServletResponse,
        @RequestHeader("Wechatpay-Nonce") nonce: String,
        @RequestHeader("Wechatpay-Signature") signature: String,
        @RequestHeader("Wechatpay-Timestamp") timestamp: String,
        @RequestHeader("Wechatpay-Serial") serial: String,
        @RequestBody body: String
    ): TransactionNotifyVO {
        try {
            wechatPayAggregatedPayment.transactionNotify(WechatPayNotifyParseRequest(nonce, signature, timestamp, serial, body))
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

    /**
     * 退款回调
     */
    @PostMapping(value = ["refund-notify"])
    fun refundNotify(
        @RequestHeader("Wechatpay-Nonce") nonce: String,
        @RequestHeader("Wechatpay-Signature") signature: String,
        @RequestHeader("Wechatpay-Timestamp") timestamp: String,
        @RequestHeader("Wechatpay-Serial") serial: String,
        @RequestBody body: String,
        response: HttpServletResponse,
    ): TransactionNotifyVO {
        try {
            wechatPayAggregatedPayment.refundNotify(WechatPayNotifyParseRequest(nonce, signature, timestamp, serial, body))
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
        return TransactionNotifyVO(code = "SUCCESS", message = "Refund notify success")
    }


    /**
     * 商家转账回调
     */
    @PostMapping(value = ["transfer-notify"])
    fun transferNotify(
        @RequestHeader("Wechatpay-Nonce") nonce: String,
        @RequestHeader("Wechatpay-Signature") signature: String,
        @RequestHeader("Wechatpay-Timestamp") timestamp: String,
        @RequestHeader("Wechatpay-Serial") serial: String,
        @RequestBody body: String,
        response: HttpServletResponse,
    ): TransactionNotifyVO {
        try {
            wechatPayAggregatedPayment.transferNotify(WechatPayNotifyParseRequest(nonce, signature, timestamp, serial, body))
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
        return TransactionNotifyVO(code = "SUCCESS", message = "Transfer notify success")
    }

}