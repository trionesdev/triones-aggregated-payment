package com.trionesdev.payment.aggregated.wechatpay

import com.trionesdev.payment.aggregated.shared.model.CreateRefundRequest
import com.trionesdev.payment.aggregated.shared.model.CreateRefundResponse
import com.trionesdev.payment.aggregated.shared.model.CreateTransferRequest
import com.trionesdev.payment.aggregated.shared.model.CreateTransferResponse
import com.trionesdev.payment.wechatpay.v3.operation.model.WechatPayCreateTransferRequest
import com.trionesdev.payment.wechatpay.v3.operation.model.WechatPayCreateTransferResponse
import com.trionesdev.payment.wechatpay.v3.payment.model.ReqRefundAmount
import com.trionesdev.payment.wechatpay.v3.payment.model.WechatPayRefund
import com.trionesdev.payment.wechatpay.v3.payment.model.WechatPayRefundCreateRequest

object ConvertUtils {
    fun createRefundRequestToWechatPay(request: CreateRefundRequest): WechatPayRefundCreateRequest {
        val wechatpayRequest = WechatPayRefundCreateRequest()
        wechatpayRequest.transactionId = request.tradeNo
        wechatpayRequest.outTradeNo = request.outTradeNo
        wechatpayRequest.notifyUrl = request.notifyUrl
        wechatpayRequest.amount = ReqRefundAmount()
        return wechatpayRequest
    }

    fun createRefundResponseFromWechatPay(response: WechatPayRefund): CreateRefundResponse {
        val refundResponse = CreateRefundResponse()
        refundResponse.refundNo = response.refundId
        refundResponse.outRefundNo = response.outRefundNo
        refundResponse.tradeNo = response.transactionId
        refundResponse.outTradeNo = response.outTradeNo
        return refundResponse
    }

    fun createTransferRequestToWechatPay(request: CreateTransferRequest): WechatPayCreateTransferRequest {
        val wechatPayRequest = WechatPayCreateTransferRequest()
        wechatPayRequest.appId = request.appId
        wechatPayRequest.outBillNo = request.outBillNo
        wechatPayRequest.notifyUrl = request.notifyUrl
        return wechatPayRequest
    }

    fun createTransferResponseFromWechatPay(response: WechatPayCreateTransferResponse): CreateTransferResponse {
        val createTransferResponse = CreateTransferResponse()
        createTransferResponse.billNo = response.transferBillNo
        createTransferResponse.outBillNo = response.outBillNo
        createTransferResponse.extra = mapOf("packageInfo" to response.packageInfo)
        createTransferResponse.original = response
        return createTransferResponse
    }
}