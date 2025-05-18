package com.trionesdev.payment.aggregated.wechatpay

import com.trionesdev.payment.aggregated.AggregatedPayment
import com.trionesdev.payment.aggregated.AggregatedPaymentNotify
import com.trionesdev.payment.aggregated.PaymentComponent
import com.trionesdev.payment.aggregated.shared.enums.Channel
import com.trionesdev.payment.aggregated.shared.enums.Currency
import com.trionesdev.payment.aggregated.shared.enums.Scene
import com.trionesdev.payment.aggregated.shared.model.CloseOrderRequest
import com.trionesdev.payment.aggregated.shared.model.CreateOrderRequest
import com.trionesdev.payment.aggregated.shared.model.CreateOrderResponse
import com.trionesdev.payment.aggregated.shared.model.Money
import com.trionesdev.payment.aggregated.shared.model.RefundNotifyArgs
import com.trionesdev.payment.aggregated.shared.model.TransactionAmount
import com.trionesdev.payment.aggregated.shared.model.TransactionNotifyArgs
import com.trionesdev.payment.wechatpay.v3.WechatPayTemplate
import com.trionesdev.payment.wechatpay.v3.model.notify.WechatPayNotifyParseRequest
import java.math.BigDecimal
import java.time.Instant

@PaymentComponent(channel = "WECHAT_PAY")
class WechatPayService(
    var wechatTemplate: WechatPayTemplate?,
    var aggregatedPaymentNotify: AggregatedPaymentNotify?
) : AggregatedPayment() {


    override fun createOrder(request: CreateOrderRequest): CreateOrderResponse {
        var response: Any? = null;
        when (request.scene) {
            Scene.M_WEB -> {
                response = wechatTemplate!!.h5.createOrder(CreateOrderRequestConvert.h5(request))
            }

            Scene.JSAPI -> {
                response =
                    wechatTemplate?.jsApi?.createOrderWithRequestPayment(CreateOrderRequestConvert.jsapi(request))
            }

            Scene.APP -> {
                wechatTemplate?.app?.createOrderWithRequestPayment(CreateOrderRequestConvert.app(request))
            }

            Scene.NATIVE -> {
                response = wechatTemplate?.native?.createOrder(CreateOrderRequestConvert.native(request))
            }

            Scene.MINI_PROGRAM -> {
                response =
                    wechatTemplate!!.jsApi.createOrderWithRequestPayment(CreateOrderRequestConvert.jsapi(request))
            }

            Scene.PAYMENT_CODE,
            Scene.FACE_PAY,
            null -> {
            }
        }
        return CreateOrderResponse(response)
    }

    override fun closeOrder(request: CloseOrderRequest) {

    }

    override fun createRefund() {
        TODO("Not yet implemented")
    }

    override fun applyAbnormalRefund() {
        TODO("Not yet implemented")
    }


    fun transactionNotify(request: WechatPayNotifyParseRequest) {
        val response = wechatTemplate!!.transactionNotify(request)
        val processArgs = TransactionNotifyArgs()
        processArgs.channel = Channel.WECHAT_PAY.name
        processArgs.tradeNo = response.transactionId
        processArgs.outTradeNo = response.outTradeNo
        processArgs.attach = response.attach
        processArgs.amount = TransactionAmount(
            Money(BigDecimal(response.amount.total), Currency.fromString(response.amount.currency, Currency.CNY)),
            Money(
                BigDecimal(response.amount.currency),
                Currency.fromString(response.amount.payerCurrency, Currency.CNY)
            )
        )
        processArgs.successTime = Instant.parse(response.successTime)
        processArgs.transaction = response
        aggregatedPaymentNotify?.transactionNotifyProcess(processArgs)
    }

    fun refundNotify(request: WechatPayNotifyParseRequest) {
        val response = wechatTemplate!!.refundNotify(request)
        val processArgs = RefundNotifyArgs()
        aggregatedPaymentNotify?.refundNotifyProcess(processArgs)
    }

}