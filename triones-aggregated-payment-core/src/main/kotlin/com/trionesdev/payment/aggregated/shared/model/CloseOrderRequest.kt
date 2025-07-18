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
    var operatorId: String? = null

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        var request: CloseOrderRequest = CloseOrderRequest()
        fun channel(channel: String) = apply { request.channel = channel }

        fun tradeNo(tradeNo: String) = apply { request.tradeNo = tradeNo }

        fun outTradeNo(outTradeNo: String) = apply { request.merchantId = outTradeNo }

        fun merchantId(merchantId: String)=apply { request.merchantId = merchantId }

        fun operatorId(operatorId: String) = apply { request.operatorId = operatorId }
        fun build(): CloseOrderRequest {
            return request;
        }
    }
}