package com.trionesdev.payment.aggregated.shared.model

/**
 * 创建订单返回信息
 */
class CreateOrderResponse(response: Any?) {
    var info:Any? = null
    init {
        this.info = response
    }
}