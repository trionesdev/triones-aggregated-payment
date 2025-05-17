package com.trionesdev.payment.aggregated

abstract class AggregatedPaymentNotify {
    abstract fun transactionNotifyProcess()
    abstract fun refundNotifyProcess()
}