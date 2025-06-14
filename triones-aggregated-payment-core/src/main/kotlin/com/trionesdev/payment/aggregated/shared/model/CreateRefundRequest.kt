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
    var reason: String? = null
    var notifyUrl: String? = null

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

        fun reason(reason: String): Builder {
            request.reason = reason
            return this
        }

        fun notifyUrl(notifyUrl: String): Builder {
            request.notifyUrl = notifyUrl
            return this
        }

        fun build(): CreateRefundRequest {
            return request
        }
    }
}