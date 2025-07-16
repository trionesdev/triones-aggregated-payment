package com.trionesdev.payment.aggregated.alipay

import com.alipay.api.domain.AlipayTradePagePayModel
import com.alipay.api.domain.GoodsDetail
import com.trionesdev.payment.aggregated.shared.model.CreateOrderRequest

object CreateOrderRequestConvert {
    fun page(request: CreateOrderRequest): AlipayTradePagePayModel {
        return AlipayTradePagePayModel().apply {
            outTradeNo = request.outTradeNo
            totalAmount = request.amount?.amount?.toPlainString()
            subject = request.subject
            goodsDetail = request.goodsDetail?.map {
                GoodsDetail().apply {
                    goodsId = it.goodsId
                    goodsName = it.goodsName
                    quantity = it.quantity?.toLong()
                    price = it.price?.toPlainString()
                    showUrl = it.showUrl
                }
            }
        }
    }
}