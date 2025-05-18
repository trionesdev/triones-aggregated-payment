package com.trionesdev.payment.aggregated.wechatpay

import com.trionesdev.payment.aggregated.AggregatedPayment
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
class WechatPayService(
    var wechatpay: WechatPay?,
    var aggregatedPaymentNotify: AggregatedPaymentNotifyCallback?
) : AggregatedPayment() {


    override fun createOrder(request: CreateOrderRequest): CreateOrderResponse {
        var response: Any? = null;
        when (request.scene) {
            Scene.M_WEB -> {
                response = wechatpay!!.payment.h5.createOrder(CreateOrderRequestConvert.h5(request))
            }

            Scene.PC_WEB -> {
                response = wechatpay!!.payment?.native?.createOrder(CreateOrderRequestConvert.native(request))
            }

            Scene.WEB_JSAPI -> {
                response =
                    wechatpay!!.payment.jsApi.createOrderWithRequestPayment(CreateOrderRequestConvert.jsapi(request))
            }

            Scene.APP -> {
                wechatpay!!.payment?.app?.createOrderWithRequestPayment(CreateOrderRequestConvert.app(request))
            }

            Scene.MINI_PROGRAM -> {
                response =
                    wechatpay!!.payment.jsApi.createOrderWithRequestPayment(CreateOrderRequestConvert.jsapi(request))
            }

            Scene.PAYMENT_CODE,
            Scene.FACE_PAY,
            null -> {
            }
        }
        return CreateOrderResponse(response)
    }

    override fun closeOrder(request: CloseOrderRequest) {
        val req = WechatPayCloseOrderRequest()
        req.mchId = request.merchantId
        req.outTradeNo = request.outTradeNo
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
        processArgs.original = response
        aggregatedPaymentNotify?.transactionNotifyProcess(processArgs)
    }

    fun refundNotify(request: WechatPayNotifyParseRequest) {
        val response = wechatpay!!.payment.refundNotify(request)
        val processArgs = RefundNotifyArgs()
        processArgs.channel = Channel.WECHAT_PAY.name
        processArgs.tradeNo = response.transactionId
        processArgs.outTradeNo = response.outTradeNo
        processArgs.refundNo = response.refundId
        processArgs.outRefundNo = response.outRefundNo

        processArgs.amount = TransactionAmount(
            Money(BigDecimal(response.amount.total), Currency.fromString(response.amount.currency, Currency.CNY)),
            Money(
                BigDecimal(response.amount.currency),
                Currency.fromString(response.amount.payerCurrency, Currency.CNY)
            )
        )
        processArgs.successTime = Instant.parse(response.successTime)
        processArgs.original = response
        aggregatedPaymentNotify?.refundNotifyProcess(processArgs)
    }

    fun transferNotify(request: WechatPayNotifyParseRequest) {
        val response = wechatpay!!.operation.transferNotify(request)
        val processArgs = TransferNotifyArgs()
        processArgs.channel = Channel.WECHAT_PAY.name
        processArgs.billNo = response.transferBillNo
        processArgs.outBillNo = response.outBillNo
        processArgs.original = response
        aggregatedPaymentNotify?.transferNotifyProcess(processArgs)
    }
}