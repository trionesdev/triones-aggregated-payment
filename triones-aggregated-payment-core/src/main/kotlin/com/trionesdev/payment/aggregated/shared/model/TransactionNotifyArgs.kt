package com.trionesdev.payment.aggregated.shared.model

import java.time.Instant

class TransactionNotifyArgs {
    /**
     * 支付渠道
     */
    var channel: String? = null

    /**
     * 支付渠道交易号
     */
    var tradeNo: String? = null

    /**
     * 商户系统交易号
     */
    var outTradeNo: String? = null

    /**
     * 附加信息(提交订单时带的附加信息，原样返回)
     */
    var attach: String? = null

    /**
     * 金额
     */
    var totalAmount: Money? = null

    /**
     * 实际支付金额
     */
    var payerAmount: Money? = null

    /**
     * 成功时间
     */
    var successTime: Instant? = null

    /**
     * 交易信息(第三方支付的交易信息)
     */
    var original: Any? = null

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private var transactionNotifyArgs: TransactionNotifyArgs = TransactionNotifyArgs()

        fun channel(channel: String?): Builder {
            transactionNotifyArgs.channel = channel
            return this
        }

        fun tradeNo(tradeNo: String?): Builder {
            transactionNotifyArgs.tradeNo = tradeNo
            return this
        }

        fun outTradeNo(outTradeNo: String?): Builder {
            transactionNotifyArgs.outTradeNo = outTradeNo
            return this
        }

        fun attach(attach: String?): Builder {
            transactionNotifyArgs.attach = attach
            return this
        }

        fun totalAmount(totalAmount: Money?): Builder {
            transactionNotifyArgs.totalAmount = totalAmount
            return this
        }

        fun payerAmount(payerAmount: Money?): Builder {
            transactionNotifyArgs.payerAmount = payerAmount
            return this
        }

        fun successTime(successTime: Instant?): Builder {
            transactionNotifyArgs.successTime = successTime
            return this
        }

        fun original(original: Any?): Builder {
            transactionNotifyArgs.original = original
            return this
        }

        fun build(): TransactionNotifyArgs {
            return transactionNotifyArgs
        }
    }
}