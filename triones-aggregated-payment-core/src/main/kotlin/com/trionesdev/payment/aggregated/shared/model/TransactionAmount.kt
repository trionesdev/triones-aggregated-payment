package com.trionesdev.payment.aggregated.shared.model

class TransactionAmount {

    var total: Money? = null
    var payerTotal: Money? = null
    constructor()
    constructor(total: Money, payerTotal: Money) {
        this.total = total
        this.payerTotal = payerTotal
    }

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private var transactionAmount: TransactionAmount = TransactionAmount()

        fun total(total: Money?): Builder {
            transactionAmount.total = total
            return this
        }

        fun payerTotal(payerTotal: Money?): Builder {
            transactionAmount.payerTotal = payerTotal
            return this
        }

        fun build(): TransactionAmount {
            return transactionAmount
        }
    }
}