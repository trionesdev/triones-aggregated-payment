package com.trionesdev.payment.aggregated.shared.model

/**
 * 创建订单返回信息
 */
class CreateOrderResponse(raw: Map<String, Any?>?) {
    /**
     * 支付渠道原始返回报文
     */
    var raw: Map<String, Any?>? = null

    init {
        this.raw = raw
    }
}