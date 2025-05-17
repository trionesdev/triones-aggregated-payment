package com.trionesdev.payment.aggregated

import com.trionesdev.payment.aggregated.shared.model.CloseOrderRequest
import com.trionesdev.payment.aggregated.shared.model.CloseOrderResponse
import com.trionesdev.payment.aggregated.shared.model.CreateOrderRequest
import com.trionesdev.payment.aggregated.shared.model.CreateOrderResponse

abstract class AggregatedPayment {
    /**
     * 创建支付订单
     */
    abstract fun createOrder(request: CreateOrderRequest): CreateOrderResponse

    /**
     * 关闭支付订单
     */
    abstract fun closeOrder(request: CloseOrderRequest): CloseOrderResponse

    /**
     * 退款申请
     */
    abstract fun refundApply()

    /**
     * 退款
     */
    abstract fun refund()
}