package com.trionesdev.payment.aggregated.wechatpay

import com.trionesdev.payment.aggregated.shared.model.CreateOrderRequest
import com.trionesdev.payment.wechatpay.v3.payment.app.model.WechatPayAppCreateOrderRequest
import com.trionesdev.payment.wechatpay.v3.payment.h5.model.WechatPayH5CreateOrderRequest
import com.trionesdev.payment.wechatpay.v3.payment.jsapi.model.WechatPayJsApiCreateOrderRequest
import com.trionesdev.payment.wechatpay.v3.payment.nativepay.model.WechatPayNativeCreateOrderRequest

object CreateOrderRequestConvert {
    fun h5(request: CreateOrderRequest): WechatPayH5CreateOrderRequest {
        val h5Request = WechatPayH5CreateOrderRequest()
        h5Request.outTradeNo = request.outTradeNo
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