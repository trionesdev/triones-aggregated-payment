package com.trionesdev.payment.aggregated.shared.model

import java.time.Instant

class RefundNotifyArgs {
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
     * 渠道退款单号
     */
    var refundNo: String? = null

    /**
     * 商家退款单号
     */
    var outRefundNo: String? = null

    /**
     * 金额
     */
    var amount: TransactionAmount? = null

    /**
     * 成功时间
     */
    var successTime: Instant? = null
    var original: Any? = null

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private var refundNotifyArgs: RefundNotifyArgs = RefundNotifyArgs()

        fun channel(channel: String?): Builder {
            refundNotifyArgs.channel = channel
            return this
        }

        fun tradeNo(tradeNo: String?): Builder {
            refundNotifyArgs.tradeNo = tradeNo
            return this
        }

        fun outTradeNo(outTradeNo: String?): Builder {
            refundNotifyArgs.outTradeNo = outTradeNo
            return this
        }

        fun refundNo(refundNo: String?): Builder {
            refundNotifyArgs.refundNo = refundNo
            return this
        }

        fun outRefundNo(outRefundNo: String?): Builder {
            refundNotifyArgs.outRefundNo = outRefundNo
            return this
        }

        fun amount(amount: TransactionAmount?): Builder {
            refundNotifyArgs.amount = amount
            return this
        }

        fun successTime(successTime: Instant?): Builder {
            refundNotifyArgs.successTime = successTime
            return this
        }

        fun original(original: Any?): Builder {
            refundNotifyArgs.original = original
            return this
        }

        fun build(): RefundNotifyArgs {
            return refundNotifyArgs
        }
    }
}