package com.trionesdev.payment.aggregated.shared.model

import com.trionesdev.payment.aggregated.shared.enums.Scene
import java.time.Instant

/**
 * 创建订单请求
 */
class CreateOrderRequest {
    var channel: String? = null
    var scene: Scene? = null

    /**
     * 应用ID
     */
    var appId: String? = null

    /**
     * 商户ID
     */
    var merchantId: String? = null
    /**
     * 商户自定义订单ID
     */
    var outTradeNo: String? = null

    /**
     * 订单标题
     */
    var subject: String? = null

    /**
     * 回调地址
     */
    var notifyUrl: String? = null
    var timeExpire: Instant? = null
    var attach: String? = null
    var amount: Money? = null
    /**
     * 订单包含的商品列表信息
     */
    var goodsDetail:List<GoodsDetail>? = null
    var payer: Payer? = null
    var extra: Map<String, Any>? = null
}