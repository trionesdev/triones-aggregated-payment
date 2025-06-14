package com.trionesdev.payment.aggregated.shared.model

/**
 * 关闭订单请求
 */
class CloseOrderRequest {
    var channel: String? = null

    /**
     * 支付平台订单号
     */
    var tradeNo: String? = null

    /**
     * 商户自定义订单ID
     */
    var outTradeNo: String? = null
    var merchantId: String? = null

    companion object  {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        var request: CloseOrderRequest = CloseOrderRequest()
        fun channel(channel: String): Builder {
            request.channel = channel
            return this
        }

        fun tradeNo(tradeNo: String): Builder {
            request.tradeNo = tradeNo
            return this
        }

        fun outTradeNo(outTradeNo: String): Builder {
            request.merchantId = outTradeNo
            return this
        }

        fun merchantId(merchantId: String): Builder {
            request.merchantId = merchantId
            return this
        }

        fun build(): CloseOrderRequest {
            return request;
        }
    }
}