package com.trionesdev.payment.aggregated.shared.model

/**
 * 创建订单返回信息
 */
class CreateOrderResponse(raw: Any?) {
    /**
     * 支付渠道原始返回报文
     */
    var raw: Any? = null

    init {
        this.raw = raw
    }
}