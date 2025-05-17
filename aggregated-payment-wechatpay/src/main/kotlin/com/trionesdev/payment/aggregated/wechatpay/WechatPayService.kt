package com.trionesdev.payment.aggregated.wechatpay

import com.trionesdev.payment.aggregated.AggregatedPayment
import com.trionesdev.payment.aggregated.AggregatedPaymentNotify
import com.trionesdev.payment.aggregated.PaymentComponent
import com.trionesdev.payment.aggregated.shared.enums.Scene
import com.trionesdev.payment.aggregated.shared.model.CloseOrderRequest
import com.trionesdev.payment.aggregated.shared.model.CloseOrderResponse
import com.trionesdev.payment.aggregated.shared.model.CreateOrderRequest
import com.trionesdev.payment.aggregated.shared.model.CreateOrderResponse
import com.trionesdev.payment.aggregated.shared.model.TransactionNotifyArgs
import com.trionesdev.payment.wechatpay.v3.WechatPayTemplate
import com.trionesdev.payment.wechatpay.v3.model.notify.WechatPayNotifyRequest

@PaymentComponent(channel = "WECHAT_PAY")
class WechatPayService(
    var wechatTemplate: WechatPayTemplate?,
    var aggregatedPaymentNotify: AggregatedPaymentNotify?
) : AggregatedPayment() {


    override fun createOrder(request: CreateOrderRequest): CreateOrderResponse {
        var response: Any? = null;
        when (request.scene) {
            Scene.H5 -> {
                response = wechatTemplate?.h5?.createOrder(CreateOrderRequestConvert.h5(request))
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

            Scene.PAYMENT_CODE -> {}
            null -> {}
        }
        return CreateOrderResponse(response)
    }

    override fun closeOrder(request: CloseOrderRequest): CloseOrderResponse {
        return CloseOrderResponse()
    }

    override fun refundApply() {

    }

    override fun refund() {

    }

    fun transactionNotify(request: WechatPayNotifyRequest) {
        val response = wechatTemplate!!.transactionNotify(request)
        val processArgs = TransactionNotifyArgs()
        processArgs.tradeNo = response.transactionId
        processArgs.outTradeNo = response.outTradeNo
        processArgs.attach = response.attach
        aggregatedPaymentNotify?.transactionNotifyProcess(processArgs)
    }
}