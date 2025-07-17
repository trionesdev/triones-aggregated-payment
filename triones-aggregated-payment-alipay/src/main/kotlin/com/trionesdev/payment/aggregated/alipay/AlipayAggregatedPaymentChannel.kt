package com.trionesdev.payment.aggregated.alipay


import com.alipay.v3.model.AlipayFundTransUniTransferModel
import com.alipay.v3.model.AlipayTradeCloseModel
import com.alipay.v3.model.AlipayTradeRefundModel
import com.alipay.v3.model.Participant
import com.trionesdev.payment.aggregated.AggregatedPaymentChannel
import com.trionesdev.payment.aggregated.AggregatedPaymentNotifyCallback
import com.trionesdev.payment.aggregated.PaymentComponent
import com.trionesdev.payment.aggregated.shared.enums.Currency
import com.trionesdev.payment.aggregated.shared.enums.Scene
import com.trionesdev.payment.aggregated.shared.model.*
import com.trionesdev.payment.alipay.v3.Alipay
import java.math.BigDecimal

@PaymentComponent(channel = "ALIPAY")
class AlipayAggregatedPaymentChannel(
    var alipay: Alipay?, var aggregatedPaymentNotify: AggregatedPaymentNotifyCallback?
) : AggregatedPaymentChannel() {
    override fun createOrder(request: CreateOrderRequest): CreateOrderResponse {
        var response: Map<String, Any?>? = null;
        when (request.scene) {
            Scene.PC_WEB -> {
                val pageRes =
                    alipay!!.payment.page.createOrderWithRequestPayment(CreateOrderRequestConvert.page(request))
                response = mapOf("pageRedirectionData" to pageRes.pageRedirectionData)
            }

            Scene.WAP -> TODO()
            Scene.JSAPI -> TODO()
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
        return CloseOrderResponse().apply {}
    }

    override fun createRefund(request: CreateRefundRequest): CreateRefundResponse {
        val req = AlipayTradeRefundModel().apply {
            outTradeNo = request.outTradeNo
            tradeNo = request.tradeNo
            refundReason = request.reason
            outRequestNo = request.outRefundNo
        }
        val res = alipay!!.payment.createRefund(req)
        aggregatedPaymentNotify?.refundNotifyProcess(RefundNotifyArgs().apply {
            channel = request.channel
            tradeNo = request.tradeNo
            outTradeNo = request.outTradeNo
            refundAmount = Money.builder().amount(BigDecimal(res.refundFee)).currency(Currency.CNY).build()
            refundNo = request.outRefundNo
        })
        return CreateRefundResponse().apply {
            refundNo = request.outRefundNo
            refundAmount = Money.builder().amount(BigDecimal(res.refundFee)).currency(Currency.CNY).build()
        }
    }

    override fun createTransfer(request: CreateTransferRequest): CreateTransferResponse {
        val req = AlipayFundTransUniTransferModel().apply {
            outBizNo = request.outBillNo
            transAmount = request.amount!!.amount!!.toPlainString()
            bizScene = request.sceneId
            orderTitle = request.title
            payeeInfo = Participant().apply {
                identity = request.payee!!.identity
                identityType = request.payee!!.identityType
                name = request.payee!!.name
            }
            remark = request.remark
            businessParams = request.businessParams
        }
        val res = alipay!!.fund.merchantTransfer.createTransfer(req)
        if (res.status.equals("SUCCESS")) {
            aggregatedPaymentNotify?.transferNotifyProcess(TransferNotifyArgs().apply {
                channel = request.channel
                billNo = res.orderId
                outBillNo = request.outBillNo
                raw = res
            })
        }
        return CreateTransferResponse().apply {
            billNo = res.orderId
            outBillNo = request.outBillNo
        }
    }

    override fun cancelTransfer(request: CancelTransferRequest): CancelTransferResponse {
        return CancelTransferResponse().apply {}
    }

}