package com.trionesdev.payment.aggregated.shared.model

import com.trionesdev.payment.aggregated.shared.enums.Currency
import java.math.BigDecimal

class Money {
    var amount: BigDecimal? = null
    var currency: Currency? = null

    constructor(amount: BigDecimal) {
        this.amount = amount
        this.currency = Currency.CNY
    }

    constructor(amount: BigDecimal, currency: Currency) {
        this.amount = amount
        this.currency = currency
    }
}