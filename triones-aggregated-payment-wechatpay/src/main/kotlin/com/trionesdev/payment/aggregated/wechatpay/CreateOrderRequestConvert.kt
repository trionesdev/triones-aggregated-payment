package com.trionesdev.payment.aggregated.wechatpay

import com.trionesdev.payment.aggregated.shared.model.CreateOrderRequest
import com.trionesdev.payment.wechatpay.v3.payment.app.model.WechatPayAppCreateOrderRequest
import com.trionesdev.payment.wechatpay.v3.payment.h5.model.WechatPayH5CreateOrderRequest
import com.trionesdev.payment.wechatpay.v3.payment.jsapi.model.WechatPayJsApiCreateOrderRequest
import com.trionesdev.payment.wechatpay.v3.payment.nativepay.model.WechatPayNativeCreateOrderRequest
import java.time.Instant
import java.time.format.DateTimeFormatter

object CreateOrderRequestConvert {

    fun timeFormat(time: Instant?): String? {
        return time?.let { return DateTimeFormatter.ISO_INSTANT.format(time) }
    }

    fun h5(request: CreateOrderRequest): WechatPayH5CreateOrderRequest {
        val h5Request = WechatPayH5CreateOrderRequest()
        h5Request.appId = request.appId
        h5Request.mchId = request.merchantId
        h5Request.description = request.subject
        h5Request.outTradeNo = request.outTradeNo
        h5Request.timeExpire = timeFormat(request.timeExpire)
        h5Request.attach = request.attach
        h5Request.notifyUrl = request.notifyUrl
        return h5Request;
    }

    fun jsapi(request: CreateOrderRequest): WechatPayJsApiCreateOrderRequest {
        val jsapiRequest = WechatPayJsApiCreateOrderRequest()
        jsapiRequest.outTradeNo = request.outTradeNo
        return jsapiRequest;
    }

    fun app(request: CreateOrderRequest): WechatPayAppCreateOrderRequest {
        val appRequest = WechatPayAppCreateOrderRequest()
        appRequest.outTradeNo = request.outTradeNo
        return appRequest;
    }

    fun native(request: CreateOrderRequest): WechatPayNativeCreateOrderRequest {
        val nativeRequest = WechatPayNativeCreateOrderRequest()
        nativeRequest.outTradeNo = request.outTradeNo
        return nativeRequest;
    }
}