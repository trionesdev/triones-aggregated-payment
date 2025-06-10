package com.trionesdev.payment.aggregated.spring

import com.trionesdev.payment.aggregated.AbstractAggregatedPayment
import com.trionesdev.payment.aggregated.PaymentComponent
import com.trionesdev.payment.aggregated.shared.model.CloseOrderRequest
import com.trionesdev.payment.aggregated.shared.model.CreateOrderRequest
import com.trionesdev.payment.aggregated.shared.model.CreateOrderResponse
import com.trionesdev.payment.aggregated.shared.model.CreateRefundRequest
import com.trionesdev.payment.aggregated.shared.model.CreateRefundResponse
import com.trionesdev.payment.aggregated.shared.model.CreateTransferRequest
import com.trionesdev.payment.aggregated.shared.model.CreateTransferResponse
import jakarta.annotation.PostConstruct
import org.apache.commons.collections4.CollectionUtils
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.stereotype.Component

@Component
class AggregatedPaymentFactory(var abstractAggregatedPayments: List<AbstractAggregatedPayment>) {

    var aggregatedPaymentsMap: MutableMap<String, AbstractAggregatedPayment> = HashMap()

    @PostConstruct
    fun init() {
        if (CollectionUtils.isNotEmpty(abstractAggregatedPayments)) {
           for ( payment in abstractAggregatedPayments) {
               val paymentComponent =
                   AnnotationUtils.getAnnotation(payment.javaClass, PaymentComponent::class.java)
               paymentComponent?.let { component ->
                   aggregatedPaymentsMap.put(component.channel, payment)
               }
           }
        }
    }

    fun createOrder(request: CreateOrderRequest): CreateOrderResponse {
        return aggregatedPaymentsMap[request.channel!!]!!.createOrder(request)
    }

    fun closeOrder(request: CloseOrderRequest) {
        aggregatedPaymentsMap[request.channel!!]!!.closeOrder(request)
    }

    fun createRefund(request: CreateRefundRequest): CreateRefundResponse {
        return aggregatedPaymentsMap[request.channel!!]!!.createRefund(request)
    }

    fun createTransfer(request: CreateTransferRequest): CreateTransferResponse {
        return aggregatedPaymentsMap[request.channel!!]!!.createTransfer(request)
    }
}