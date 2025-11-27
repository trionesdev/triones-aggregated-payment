package com.trionesdev.payment.aggregated.shared.model

class CreateRefundResponse {
    /**
     * 渠道退款单号
     */
    var refundNo: String? = null
    /**
     * 本地退款单号
     */
    var outRefundNo: String? = null
    /**
     * 渠道支付交易号
     */
    var tradeNo: String? = null
    /**
     * 本地交易号
     */
    var outTradeNo: String? = null
    /**
     * 退款金额
     */
    var refundAmount: Money? = null

    /**
     * 原订单总金额
     */
    var totalAmount: Money? = null

    /**
     * 实际支付金额
     */
    var payerTotalAmount: Money? = null
    /**
     * 实际退款金额
     */
    var payerRefundAmount: Money? = null

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        var response: CreateRefundResponse = CreateRefundResponse()
        fun refundNo(refundNo: String): Builder {
            response.refundNo = refundNo
            return this
        }

        fun outRefundNo(outRefundNo: String): Builder {
            response.outRefundNo = outRefundNo
            return this
        }

        fun tradeNo(tradeNo: String): Builder {
            response.tradeNo = tradeNo
            return this
        }

        fun outTradeNo(outTradeNo: String): Builder {
            response.outTradeNo = outTradeNo
            return this
        }

        fun refundAmount(refundAmount: Money): Builder {
            response.refundAmount = refundAmount
            return this
        }

        fun totalAmount(totalAmount: Money): Builder {
            response.totalAmount = totalAmount
            return this
        }

        fun payerTotalAmount(payerTotalAmount: Money): Builder {
            response.payerTotalAmount = payerTotalAmount
            return this
        }

        fun payerRefundAmount(payerRefundAmount: Money): Builder {
            response.payerRefundAmount = payerRefundAmount
            return this
        }

        fun build(): CreateRefundResponse {
            return response
        }
    }
}