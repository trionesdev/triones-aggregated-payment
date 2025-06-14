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

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private var goodsDetail: GoodsDetail = GoodsDetail()

        fun goodsId(goodsId: String?): Builder {
            goodsDetail.goodsId = goodsId
            return this
        }

        fun goodsName(goodsName: String?): Builder {
            goodsDetail.goodsName = goodsName
            return this
        }

        fun quantity(quantity: Int?): Builder {
            goodsDetail.quantity = quantity
            return this
        }

        fun price(price: BigDecimal?): Builder {
            goodsDetail.price = price
            return this
        }

        fun build(): GoodsDetail {
            return goodsDetail
        }
    }
}