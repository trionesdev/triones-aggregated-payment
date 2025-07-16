package com.trionesdev.payment.aggregated.alipay

import com.alipay.api.domain.AlipayTradeCloseModel
import com.alipay.api.domain.AlipayTradeRefundModel
import com.trionesdev.payment.aggregated.AggregatedPaymentChannel
import com.trionesdev.payment.aggregated.AggregatedPaymentNotifyCallback
import com.trionesdev.payment.aggregated.PaymentComponent
import com.trionesdev.payment.aggregated.shared.enums.Currency
import com.trionesdev.payment.aggregated.shared.enums.Scene
import com.trionesdev.payment.aggregated.shared.model.*
import com.trionesdev.payment.alipay.Alipay
import java.math.BigDecimal

@PaymentComponent(channel = "ALIPAY")
class AlipayAggregatedPaymentChannel(
    var alipay: Alipay?,
    var aggregatedPaymentNotify: AggregatedPaymentNotifyCallback?
) : AggregatedPaymentChannel() {
    override fun createOrder(request: CreateOrderRequest): CreateOrderResponse {
        var response: Map<String, Any?>? = null;
        when (request.scene) {
            Scene.PC_WEB -> {
                val pageRes =
                    alipay!!.payment.page.createOrderWithRequestPayment(CreateOrderRequestConvert.page(request))
                response = mapOf("pageRedirectionData" to pageRes.body)
            }

            Scene.M_WEB -> TODO()
            Scene.WEB_JSAPI -> TODO()
            Scene.APP -> TODO()
            Scene.MINI_PROGRAM -> TODO()
            Scene.PAYMENT_CODE -> TODO()
            Scene.FACE_PAY -> TODO()
            null -> TODO()
        }
        return CreateOrderResponse(response)
    }

    override fun closeOrder(request: CloseOrderRequest): CloseOrderResponse {
        val req = AlipayTradeCloseModel().apply {
            outTradeNo = request.outTradeNo
            tradeNo = request.tradeNo
            operatorId = request.operatorId
        }
        val res = alipay!!.payment.closeOrder(req)
        return CloseOrderResponse().apply {

        }
    }

    override fun createRefund(request: CreateRefundRequest): CreateRefundResponse {
       val req = AlipayTradeRefundModel().apply {
           outTradeNo = request.outTradeNo
           tradeNo = request.tradeNo
           refundReason = request.reason
           outRequestNo = request.outRefundNo
       }
        val res = alipay!!.payment.createRefund(req)
        return CreateRefundResponse().apply {
            refundNo = request.outRefundNo
            refundAmount = Money.builder().amount(BigDecimal(res.refundFee)).currency(Currency.CNY).build()
        }
    }

    override fun createTransfer(request: CreateTransferRequest): CreateTransferResponse {
        TODO("Not yet implemented")
    }

    override fun cancelTransfer(request: CancelTransferRequest): CancelTransferResponse {
        TODO("Not yet implemented")
    }

}