package com.trionesdev.payment.aggregated.shared.model

import com.trionesdev.payment.aggregated.shared.enums.Currency
import java.math.BigDecimal

class Money {
    var amount: BigDecimal? = null
    var currency: Currency? = null

    constructor()

    constructor(amount: BigDecimal) {
        this.amount = amount
        this.currency = Currency.CNY
    }

    constructor(amount: BigDecimal, currency: Currency) {
        this.amount = amount
        this.currency = currency
    }

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        var money: Money = Money()
        fun amount(amount: BigDecimal) = apply {
            money.amount = amount
        }

        fun currency(currency: Currency) = apply {
            money.currency = currency
        }

        fun build(): Money {
            return money
        }
    }
}