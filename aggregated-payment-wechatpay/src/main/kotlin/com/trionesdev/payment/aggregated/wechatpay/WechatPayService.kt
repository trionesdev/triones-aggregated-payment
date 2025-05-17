package com.trionesdev.payment.aggregated.wechatpay

import com.trionesdev.payment.aggregated.AggregatedPayment
import com.trionesdev.payment.aggregated.AggregatedPaymentNotify
import com.trionesdev.payment.aggregated.PaymentComponent
import com.trionesdev.payment.aggregated.shared.model.CreateOrderRequest
import com.trionesdev.payment.aggregated.shared.model.CreateOrderResponse
import com.trionesdev.payment.aggregated.shared.enums.Scene
import com.trionesdev.payment.wechatpay.v3.WechatPayTemplate
import com.trionesdev.payment.wechatpay.v3.model.notify.WechatPayNotifyRequest
import com.trionesdev.payment.wechatpay.v3.payment.app.model.WechatPayAppCreateOrderRequest
import com.trionesdev.payment.wechatpay.v3.payment.h5.model.WechatPayH5CreateOrderRequest
import com.trionesdev.payment.wechatpay.v3.payment.jsapi.model.WechatPayJsApiCreateOrderRequest
import com.trionesdev.payment.wechatpay.v3.payment.nativepay.model.WechatPayNativeCreateOrderRequest

@PaymentComponent(channel = "WECHAT_PAY")
class WechatPayService(
    var wechatTemplate: WechatPayTemplate?,
    var aggregatedPaymentNotify: AggregatedPaymentNotify?
) : AggregatedPayment() {
    override fun createOrder(request: CreateOrderRequest): CreateOrderResponse {
        var response: Any? = null;
        when (request.scene) {
            Scene.H5 -> {
                val h5Req = WechatPayH5CreateOrderRequest()
                response = wechatTemplate?.h5?.createOrder(h5Req)
            }

            Scene.JSAPI -> {
                val jsapiReq = WechatPayJsApiCreateOrderRequest()
                response = wechatTemplate?.jsApi?.createOrder(jsapiReq)
            }

            Scene.APP -> {
                val appReq = WechatPayAppCreateOrderRequest()
                wechatTemplate?.app?.createOrder(appReq)
            }

            Scene.NATIVE -> {
                val nativeReq = WechatPayNativeCreateOrderRequest()
                response = wechatTemplate?.native?.createOrder(nativeReq)
            }

            Scene.MINI_PROGRAM -> {
                val miniReq = WechatPayJsApiCreateOrderRequest()
                response = wechatTemplate!!.jsApi.createOrder(miniReq)
            }

            Scene.PAYMENT_CODE -> {}
            null -> {}
        }
        return CreateOrderResponse(response)
    }

    override fun closeOrder() {

    }

    override fun refundApply() {

    }

    override fun refund() {

    }

    fun transactionNotify(request: WechatPayNotifyRequest) {
        val response = wechatTemplate!!.transactionNotify(request)
        aggregatedPaymentNotify?.transactionNotifyProcess()
    }
}