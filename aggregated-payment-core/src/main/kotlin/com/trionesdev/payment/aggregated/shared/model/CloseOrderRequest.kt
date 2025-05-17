package com.trionesdev.payment.aggregated.shared.model

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
}