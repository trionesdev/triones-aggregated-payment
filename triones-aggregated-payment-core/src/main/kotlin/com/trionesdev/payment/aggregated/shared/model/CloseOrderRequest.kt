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
}