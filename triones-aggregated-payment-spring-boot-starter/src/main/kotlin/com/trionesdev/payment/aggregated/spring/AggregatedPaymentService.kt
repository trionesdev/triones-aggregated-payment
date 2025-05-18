package com.trionesdev.payment.aggregated.spring

import com.trionesdev.payment.aggregated.AggregatedPayment
import com.trionesdev.payment.aggregated.PaymentComponent
import jakarta.annotation.PostConstruct
import org.apache.commons.collections4.CollectionUtils
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.stereotype.Component

@Component
class AggregatedPaymentService(var aggregatedPayments: List<AggregatedPayment>) {

    var aggregatedPaymentsMap: MutableMap<String, AggregatedPayment> = HashMap()

    @PostConstruct
    fun init() {
        if (CollectionUtils.isNotEmpty(aggregatedPayments)) {
            aggregatedPayments.forEach { payment ->
                {
                    val paymentComponent =
                        AnnotationUtils.getAnnotation(payment.javaClass, PaymentComponent::class.java)
                    paymentComponent?.let { component ->
                        {
                            aggregatedPaymentsMap.put(component.channel, payment)
                        }
                    }
                }
            }
        }
    }
}