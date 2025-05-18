package com.trionesdev.payment.aggregated.shared.model

class TransactionAmount {
    var total: Money? = null
    var payerTotal: Money? = null
    constructor(total: Money, payerTotal: Money) {
        this.total = total
        this.payerTotal = payerTotal
    }
}