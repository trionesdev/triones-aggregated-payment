package com.trionesdev.payment.aggregated.alipay


import com.alipay.v3.model.AlipayTradePrecreateModel
import com.alipay.v3.model.GoodsDetail
import com.trionesdev.payment.aggregated.shared.model.CreateOrderRequest
import com.trionesdev.payment.alipay.v3.payment.page.AlipayTradePagePayModel

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
                    quantity = it.quantity
                    price = it.price?.toPlainString()
                    showUrl = it.showUrl
                }
            }
            notifyUrl = request.notifyUrl
            request.extra?.let { t ->
                t["qrPayMode"]?.let { t -> qrPayMode = t as String? }
                t["returnUrl"]?.let { t -> returnUrl = t as String? }
            }
        }
    }

    fun orderCode(request: CreateOrderRequest): AlipayTradePrecreateModel {
        return AlipayTradePrecreateModel().apply {
            outTradeNo = request.outTradeNo
            totalAmount = request.amount?.amount?.toPlainString()
            subject = request.subject
            goodsDetail = request.goodsDetail?.map {
                GoodsDetail().apply {
                    goodsId = it.goodsId
                    goodsName = it.goodsName
                    quantity = it.quantity
                    price = it.price?.toPlainString()
                    showUrl = it.showUrl
                }
            }
        }
    }
}