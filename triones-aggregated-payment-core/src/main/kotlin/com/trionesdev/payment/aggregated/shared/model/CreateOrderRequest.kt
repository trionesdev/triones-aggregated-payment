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
     * 超时时间
     */
    var timeExpire: Instant? = null

    /**
     * 金额
     */
    var amount: Money? = null

    /**
     * 订单包含的商品列表信息
     */
    var goodsDetail: List<GoodsDetail>? = null

    /**
     * 支付人信息
     */
    var payer: Payer? = null

    /**
     * 附件信息，原路返回
     */
    var attach: String? = null

    /**
     * 回调地址
     */
    var notifyUrl: String? = null

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private var createOrderRequest: CreateOrderRequest = CreateOrderRequest()

        fun channel(channel: String) = apply {
            createOrderRequest.channel = channel
        }

        fun scene(scene: Scene) = apply {
            createOrderRequest.scene = scene
        }

        fun appId(appId: String) = apply {
            createOrderRequest.appId = appId
        }

        fun merchantId(merchantId: String): Builder {
            createOrderRequest.merchantId = merchantId
            return this
        }

        fun outTradeNo(outTradeNo: String): Builder {
            createOrderRequest.outTradeNo = outTradeNo
            return this
        }


        fun subject(subject: String): Builder {
            createOrderRequest.subject = subject
            return this
        }

        fun timeExpire(timeExpire: Instant): Builder {
            createOrderRequest.timeExpire = timeExpire
            return this
        }

        fun amount(amount: Money): Builder {
            createOrderRequest.amount = amount
            return this
        }

        fun goodsDetail(goodsDetail: List<GoodsDetail>): Builder {
            createOrderRequest.goodsDetail = goodsDetail
            return this
        }

        fun payer(payer: Payer): Builder {
            createOrderRequest.payer = payer
            return this
        }

        fun attach(attach: String): Builder {
            createOrderRequest.attach = attach
            return this
        }

        fun notifyUrl(notifyUrl: String): Builder {
            createOrderRequest.notifyUrl = notifyUrl
            return this
        }

        fun build(): CreateOrderRequest {
            return createOrderRequest
        }
    }

}