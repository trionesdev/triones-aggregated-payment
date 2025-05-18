package com.trionesdev.payment.aggregated.shared.model

import java.math.BigDecimal

class GoodsDetail {
    /**
     * 商品Id
     */
    var goodsId: String? = null

    /**
     * 商品名称
     */
    var goodsName: String? = null

    /**
     * 数量
     */
    var quantity: Int? = null

    /**
     * 单价(单位元)
     */
    var price: BigDecimal? = null
}