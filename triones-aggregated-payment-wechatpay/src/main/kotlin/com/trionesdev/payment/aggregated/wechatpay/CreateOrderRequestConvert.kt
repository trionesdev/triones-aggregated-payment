package com.trionesdev.payment.aggregated.wechatpay

import com.trionesdev.payment.aggregated.shared.model.CreateOrderRequest
import com.trionesdev.payment.aggregated.shared.model.Money
import com.trionesdev.payment.aggregated.shared.model.Payer
import com.trionesdev.payment.wechatpay.v3.model.Amount
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

    fun moneyToAmount(money: Money?): Amount? {
        if (money == null) {
            return null
        }
        return Amount(money.amount?.toInt(), money.currency?.name)
    }

    fun payer(payer: Payer?): com.trionesdev.payment.wechatpay.v3.model.Payer {
        var p = com.trionesdev.payment.wechatpay.v3.model.Payer()
        p.openId = payer?.openId
        return p;
    }

    fun h5(request: CreateOrderRequest): WechatPayH5CreateOrderRequest {
        val h5Request = WechatPayH5CreateOrderRequest()
        h5Request.appId = request.appId
        h5Request.mchId = request.merchantId
        h5Request.description = request.subject
        h5Request.outTradeNo = request.outTradeNo
        h5Request.amount = moneyToAmount(request.amount)
        h5Request.timeExpire = timeFormat(request.timeExpire)
        h5Request.attach = request.attach
        h5Request.notifyUrl = request.notifyUrl
        return h5Request
    }

    fun jsapi(request: CreateOrderRequest): WechatPayJsApiCreateOrderRequest {
        val jsapiRequest = WechatPayJsApiCreateOrderRequest()
        jsapiRequest.appId = request.appId
        jsapiRequest.mchId = request.merchantId
        jsapiRequest.description = request.subject
        jsapiRequest.outTradeNo = request.outTradeNo
        jsapiRequest.amount = moneyToAmount(request.amount)
        jsapiRequest.timeExpire = timeFormat(request.timeExpire)
        jsapiRequest.attach = request.attach
        jsapiRequest.payer = payer(request.payer)
        jsapiRequest.notifyUrl = request.notifyUrl
        return jsapiRequest;
    }

    fun app(request: CreateOrderRequest): WechatPayAppCreateOrderRequest {
        val appRequest = WechatPayAppCreateOrderRequest()
        appRequest.appId = request.appId
        appRequest.mchId = request.merchantId
        appRequest.description = request.subject
        appRequest.outTradeNo = request.outTradeNo
        appRequest.amount = moneyToAmount(request.amount)
        appRequest.timeExpire = timeFormat(request.timeExpire)
        appRequest.attach = request.attach
        appRequest.notifyUrl = request.notifyUrl
        return appRequest;
    }

    fun native(request: CreateOrderRequest): WechatPayNativeCreateOrderRequest {
        val nativeRequest = WechatPayNativeCreateOrderRequest()
        nativeRequest.appId = request.appId
        nativeRequest.mchId = request.merchantId
        nativeRequest.description = request.subject
        nativeRequest.outTradeNo = request.outTradeNo
        nativeRequest.amount = moneyToAmount(request.amount)
        nativeRequest.timeExpire = timeFormat(request.timeExpire)
        nativeRequest.attach = request.attach
        nativeRequest.notifyUrl = request.notifyUrl
        return nativeRequest;
    }
}