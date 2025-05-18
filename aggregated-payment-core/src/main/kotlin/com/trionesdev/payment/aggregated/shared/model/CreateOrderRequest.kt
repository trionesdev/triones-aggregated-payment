package com.trionesdev.payment.aggregated.shared.model

import com.trionesdev.payment.aggregated.shared.enums.Channel
import com.trionesdev.payment.aggregated.shared.enums.Scene
import java.time.Instant

/**
 * 创建订单请求
 */
class CreateOrderRequest {
    var channel: Channel? = null
    var scene: Scene? = null

    /**
     * 商户自定义订单ID
     */
    var outTradeNo: String? = null

    /**
     * 订单标题
     */
    var subject: String? = null
    var timeExpire: Instant? = null
    var attach: String? = null
    var extra: Map<String, Any>? = null
}