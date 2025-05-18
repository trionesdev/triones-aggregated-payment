package com.trionesdev.payment.aggregated

import com.trionesdev.payment.aggregated.shared.model.*

abstract class AggregatedPayment {
    /**
     * 创建支付订单
     */
    abstract fun createOrder(request: CreateOrderRequest): CreateOrderResponse

    /**
     * 关闭支付订单
     */
    abstract fun closeOrder(request: CloseOrderRequest)

    /**
     * 申请退款
     */
    abstract fun createRefund(request: CreateRefundRequest): CreateRefundResponse

    /**
     * 转账(商户对用户转账)
     */
    abstract fun createTransfer(request: CreateTransferRequest): CreateTransferResponse

}