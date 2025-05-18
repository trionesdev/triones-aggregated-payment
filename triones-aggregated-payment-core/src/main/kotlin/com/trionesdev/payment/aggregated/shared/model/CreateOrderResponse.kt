package com.trionesdev.payment.aggregated.shared.model

/**
 * 创建订单返回信息
 */
class CreateOrderResponse(response: Any?) {
    /**
     * 支付渠道原始返回信息
     */
    var original: Any? = null

    init {
        this.original = response
    }
}