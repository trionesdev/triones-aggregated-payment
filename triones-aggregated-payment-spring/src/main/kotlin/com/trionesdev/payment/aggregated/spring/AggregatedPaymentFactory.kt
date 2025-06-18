package com.trionesdev.payment.aggregated.spring

import com.trionesdev.payment.aggregated.AggregatedPaymentChannel
import com.trionesdev.payment.aggregated.PaymentComponent
import com.trionesdev.payment.aggregated.shared.model.*
import jakarta.annotation.PostConstruct
import org.apache.commons.collections4.CollectionUtils
import org.springframework.core.annotation.AnnotationUtils


class AggregatedPaymentFactory(var aggregatedPaymentChannels: MutableList<AggregatedPaymentChannel>) {

    var channelsMap: MutableMap<String, AggregatedPaymentChannel> = HashMap()

    @PostConstruct
    fun init() {
        if (CollectionUtils.isNotEmpty(aggregatedPaymentChannels)) {
            for (payment in aggregatedPaymentChannels) {
                val paymentComponent =
                    AnnotationUtils.getAnnotation(payment.javaClass, PaymentComponent::class.java)
                paymentComponent?.let { component ->
                    channelsMap.put(component.channel, payment)
                }
            }
        }
    }

    fun createOrder(request: CreateOrderRequest): CreateOrderResponse {
        return channelsMap[request.channel!!]!!.createOrder(request)
    }

    fun closeOrder(request: CloseOrderRequest) {
        channelsMap[request.channel!!]!!.closeOrder(request)
    }

    fun createRefund(request: CreateRefundRequest): CreateRefundResponse {
        return channelsMap[request.channel!!]!!.createRefund(request)
    }

    fun createTransfer(request: CreateTransferRequest): CreateTransferResponse {
        return channelsMap[request.channel!!]!!.createTransfer(request)
    }
}