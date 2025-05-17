package com.trionesdev.payment.aggregated

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class PaymentComponent(val channel: String)