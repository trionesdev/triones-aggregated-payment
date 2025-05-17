package com.trionesdev.payment.aggregated

import com.trionesdev.payment.aggregated.shared.model.RefundNotifyArgs
import com.trionesdev.payment.aggregated.shared.model.TransactionNotifyArgs

abstract class AggregatedPaymentNotify {
    abstract fun transactionNotifyProcess(args: TransactionNotifyArgs)
    abstract fun refundNotifyProcess(args: RefundNotifyArgs)
}