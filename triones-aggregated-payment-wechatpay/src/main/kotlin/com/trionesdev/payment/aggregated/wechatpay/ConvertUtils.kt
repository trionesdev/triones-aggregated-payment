package com.trionesdev.payment.aggregated.wechatpay

import com.trionesdev.payment.aggregated.shared.enums.Currency
import com.trionesdev.payment.aggregated.shared.model.*
import com.trionesdev.payment.wechatpay.v3.operation.model.TransferSceneReportInfo
import com.trionesdev.payment.wechatpay.v3.operation.model.WechatPayCreateTransferRequest
import com.trionesdev.payment.wechatpay.v3.operation.model.WechatPayCreateTransferResponse
import com.trionesdev.payment.wechatpay.v3.payment.model.ReqRefundAmount
import com.trionesdev.payment.wechatpay.v3.payment.model.WechatPayRefund
import com.trionesdev.payment.wechatpay.v3.payment.model.WechatPayRefundCreateRequest
import java.math.BigDecimal

object ConvertUtils {
    fun createRefundRequestToWechatPay(request: CreateRefundRequest): WechatPayRefundCreateRequest {
        val wechatpayRequest = WechatPayRefundCreateRequest()
        wechatpayRequest.transactionId = request.tradeNo
        wechatpayRequest.outTradeNo = request.outTradeNo
        wechatpayRequest.outRefundNo = request.outRefundNo
        wechatpayRequest.notifyUrl = request.notifyUrl
        wechatpayRequest.amount = ReqRefundAmount().apply {
            this.refund = request.refundAmount?.amount?.multiply(BigDecimal.valueOf(100))?.toInt()
            this.total = request.totalAmount?.amount?.multiply(BigDecimal.valueOf(100))?.toInt()
            this.currency = request.refundAmount?.currency?.name
        }
        return wechatpayRequest
    }

    fun createRefundResponseFromWechatPay(response: WechatPayRefund): CreateRefundResponse {
        val refundResponse = CreateRefundResponse()
        refundResponse.refundNo = response.refundId
        refundResponse.outRefundNo = response.outRefundNo
        refundResponse.tradeNo = response.transactionId
        refundResponse.outTradeNo = response.outTradeNo
        refundResponse.refundAmount = response.amount?.refund?.let {
            Money().apply {
                this.amount = BigDecimal(it).divide(BigDecimal.valueOf(100))
                this.currency = Currency.fromString(response.amount?.currency, Currency.CNY)
            }
        }
        refundResponse.totalAmount = response.amount?.total?.let {
            Money().apply {
                this.amount = BigDecimal(it).divide(BigDecimal.valueOf(100))
                this.currency = Currency.fromString(response.amount?.currency, Currency.CNY)
            }
        }
        refundResponse.payerRefundAmount = response.amount?.payerRefund?.let {
            Money().apply {
                this.amount = BigDecimal(it).divide(BigDecimal.valueOf(100))
                this.currency = Currency.fromString(response.amount?.currency, Currency.CNY)
            }
        }
        refundResponse.payerTotalAmount = response.amount?.payerTotal?.let {
            Money().apply {
                this.amount = BigDecimal(it).divide(BigDecimal.valueOf(100))
                this.currency = Currency.fromString(response.amount?.currency, Currency.CNY)
            }
        }
        return refundResponse
    }

    fun createTransferRequestToWechatPay(request: CreateTransferRequest): WechatPayCreateTransferRequest {
        val wechatPayRequest = WechatPayCreateTransferRequest()
        wechatPayRequest.appId = request.appId
        wechatPayRequest.outBillNo = request.outBillNo
        wechatPayRequest.transferSceneId = request.sceneId
        wechatPayRequest.openId = request.payee?.identity
        wechatPayRequest.userName = request.payee?.name
        wechatPayRequest.transferAmount = request.amount?.amount?.multiply(BigDecimal.valueOf(100))?.toInt()
        wechatPayRequest.transferRemark = request.remark
        wechatPayRequest.notifyUrl = request.notifyUrl
        wechatPayRequest.userRecvPerception = request.title
        wechatPayRequest.transferSceneReportInfos = request.sceneReportInfos?.map {
            TransferSceneReportInfo().apply {
                this.infoType = it.type
                this.infoContent = it.content
            }
        }
        return wechatPayRequest
    }

    fun createTransferResponseFromWechatPay(response: WechatPayCreateTransferResponse): CreateTransferResponse {
        val createTransferResponse = CreateTransferResponse()
        createTransferResponse.billNo = response.transferBillNo
        createTransferResponse.outBillNo = response.outBillNo
        createTransferResponse.extra = mapOf("packageInfo" to response.packageInfo)
        createTransferResponse.raw = response
        return createTransferResponse
    }
}