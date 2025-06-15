package com.trionesdev.payment.aggregated.wechatpay

import com.trionesdev.payment.aggregated.AggregatedPaymentChannel
import com.trionesdev.payment.aggregated.AggregatedPaymentNotifyCallback
import com.trionesdev.payment.aggregated.PaymentComponent
import com.trionesdev.payment.aggregated.shared.enums.Channel
import com.trionesdev.payment.aggregated.shared.enums.Currency
import com.trionesdev.payment.aggregated.shared.enums.Scene
import com.trionesdev.payment.aggregated.shared.model.*
import com.trionesdev.payment.wechatpay.v3.WechatPay
import com.trionesdev.payment.wechatpay.v3.model.notify.WechatPayNotifyParseRequest
import com.trionesdev.payment.wechatpay.v3.payment.model.WechatPayCloseOrderRequest
import java.math.BigDecimal
import java.time.Instant

@PaymentComponent(channel = "WECHAT_PAY")
class WechatPayAggregatedPaymentChannel(
    var wechatpay: WechatPay?,
    var aggregatedPaymentNotify: AggregatedPaymentNotifyCallback?
) : AggregatedPaymentChannel() {


    override fun createOrder(request: CreateOrderRequest): CreateOrderResponse {
        var response: Map<String, Any?>? = null;
        when (request.scene) {
            Scene.M_WEB -> {
                val h5Res = wechatpay!!.payment.h5.createOrder(CreateOrderRequestConvert.h5(request))
                response = mapOf("h5Url" to h5Res.h5Url)
            }

            Scene.PC_WEB -> {
                val webRes = wechatpay!!.payment.native.createOrder(CreateOrderRequestConvert.native(request))
                response = mapOf<String, Any?>("codeUrl" to webRes?.codeUrl)
            }


            Scene.APP -> {
                val appRes =
                    wechatpay!!.payment.app.createOrderWithRequestPayment(CreateOrderRequestConvert.app(request))
                response = mapOf(
                    "appId" to appRes.appId,
                    "timeStamp" to appRes.timeStamp,
                    "nonceStr" to appRes.nonceStr,
                    "packageStr" to appRes.packageStr,
                    "paySign" to appRes.paySign,
                    "signType" to appRes.signType
                )
            }

            Scene.WEB_JSAPI,
            Scene.MINI_PROGRAM -> {
                val jsApiRes =
                    wechatpay!!.payment.jsApi.createOrderWithRequestPayment(CreateOrderRequestConvert.jsapi(request))
                response = mapOf(
                    "appId" to jsApiRes.appId,
                    "timeStamp" to jsApiRes.timeStamp,
                    "nonceStr" to jsApiRes.nonceStr,
                    "packageStr" to jsApiRes.packageStr,
                    "paySign" to jsApiRes.paySign,
                    "signType" to jsApiRes.signType
                )
            }

            Scene.PAYMENT_CODE,
            Scene.FACE_PAY,
            null -> {
            }
        }
        return CreateOrderResponse(response)
    }

    override fun closeOrder(request: CloseOrderRequest) {
        val req = WechatPayCloseOrderRequest().apply {
            this.mchId = request.merchantId
            this.outTradeNo = request.outTradeNo
        }
        wechatpay!!.payment.closeOrder(req)
    }

    override fun createRefund(request: CreateRefundRequest): CreateRefundResponse {
        val response = wechatpay!!.payment.createRefund(ConvertUtils.createRefundRequestToWechatPay(request))
        return ConvertUtils.createRefundResponseFromWechatPay(response)
    }

    override fun createTransfer(request: CreateTransferRequest): CreateTransferResponse {
        val response = wechatpay!!.operation.createTransfer(ConvertUtils.createTransferRequestToWechatPay(request))
        return ConvertUtils.createTransferResponseFromWechatPay(response)
    }

    fun transactionNotify(request: WechatPayNotifyParseRequest) {
        val response = wechatpay!!.payment.transactionNotify(request)
        val processArgs = TransactionNotifyArgs().apply {
            this.channel = Channel.WECHAT_PAY.name
            this.tradeNo = response.transactionId
            this.outTradeNo = response.outTradeNo
            this.attach = response.attach
            this.amount = TransactionAmount().apply {
                this.total = Money().apply {
                    this.amount = BigDecimal(response.amount.total).divide(BigDecimal("100"))
                    this.currency = Currency.fromString(response.amount.currency, Currency.CNY)
                }
                this.payerTotal = Money().apply {
                    this.amount = BigDecimal(response.amount.payerTotal).divide(BigDecimal("100"))
                    this.currency = Currency.fromString(response.amount.payerCurrency, Currency.CNY)
                }
            }
            this.successTime = Instant.parse(response.successTime)
            this.original = response
        }
        aggregatedPaymentNotify?.transactionNotifyProcess(processArgs)
    }

    fun refundNotify(request: WechatPayNotifyParseRequest) {
        val response = wechatpay!!.payment.refundNotify(request)
        val processArgs = RefundNotifyArgs().apply {
            this.channel = Channel.WECHAT_PAY.name
            this.tradeNo = response.transactionId
            this.outTradeNo = response.outTradeNo
            this.refundNo = response.refundId
            this.outRefundNo = response.outRefundNo

            this.amount = TransactionAmount(
                Money().apply {
                    this.amount = BigDecimal(response.amount.total)
                    this.currency = Currency.fromString(response.amount.currency, Currency.CNY)
                },
                Money().apply {
                    this.amount = BigDecimal(response.amount.payerTotal)
                    this.currency = Currency.fromString(response.amount.payerCurrency, Currency.CNY)
                }
            )
            this.successTime = Instant.parse(response.successTime)
            this.original = response
        }

        aggregatedPaymentNotify?.refundNotifyProcess(processArgs)
    }

    fun transferNotify(request: WechatPayNotifyParseRequest) {
        val response = wechatpay!!.operation.transferNotify(request)
        val processArgs = TransferNotifyArgs().apply {
            this.channel = Channel.WECHAT_PAY.name
            this.billNo = response.transferBillNo
            this.outBillNo = response.outBillNo
            this.original = response
        }
        aggregatedPaymentNotify?.transferNotifyProcess(processArgs)
    }
}