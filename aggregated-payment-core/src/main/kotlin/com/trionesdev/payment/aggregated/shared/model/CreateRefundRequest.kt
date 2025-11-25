package com.trionesdev.payment.aggregated.shared.model

class CreateRefundRequest {
    var channel: String? = null

    /**
     * 支付平台订单号
     */
    var tradeNo: String? = null

    /**
     * 商户自定义订单ID
     */
    var outTradeNo: String? = null

    /**
     * 商户内部退款单号
     */
    var outRefundNo: String? = null
    var reason: String? = null
    var notifyUrl: String? = null

    /**
     * 退款金额
     */
    var refundAmount : Money? = null
    /**
     * 原订单总金额
     */
    var totalAmount : Money? = null

    companion object  {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        var request: CreateRefundRequest = CreateRefundRequest()


        fun channel(channel: String): Builder {
            request.channel = channel
            return this
        }

        fun tradeNo(tradeNo: String): Builder {
            request.tradeNo = tradeNo
            return this
        }

        fun outTradeNo(outTradeNo: String): Builder {
            request.outTradeNo = outTradeNo
            return this
        }

        fun outRefundNo(outRefundNo: String): Builder {
            request.outRefundNo = outRefundNo
            return this
        }

        fun reason(reason: String): Builder {
            request.reason = reason
            return this
        }

        fun notifyUrl(notifyUrl: String): Builder {
            request.notifyUrl = notifyUrl
            return this
        }


        fun refundAmount(refundAmount: Money): Builder {
            request.refundAmount = refundAmount
            return this
        }

        fun totalAmount(totalAmount: Money): Builder {
            request.totalAmount = totalAmount
            return this
        }

        fun build(): CreateRefundRequest {
            return request
        }
    }
}