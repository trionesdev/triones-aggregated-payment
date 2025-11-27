package com.trionesdev.payment.aggregated.shared.model

import com.trionesdev.payment.aggregated.shared.enums.RefundStatus
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
     * 本地系统交易号
     */
    var outTradeNo: String? = null

    /**
     * 渠道退款单号
     */
    var refundNo: String? = null

    /**
     * 本地退款单号
     */
    var outRefundNo: String? = null

    /**
     * 退款金额
     */
    var refundAmount: Money? = null
    /**
     * 实际退款金额
     */
    var payerRefundAmount: Money? = null

    /**
     * 成功时间
     */
    var successTime: Instant? = null
    var status:RefundStatus? = null
    var raw: MutableMap<String, Any?>? = null

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

        fun refundAmount(refundAmount: Money?): Builder {
            refundNotifyArgs.refundAmount = refundAmount
            return this
        }

        fun payerRefundAmount(payerRefundAmount: Money?): Builder {
            refundNotifyArgs.payerRefundAmount = payerRefundAmount
            return this
        }

        fun successTime(successTime: Instant?): Builder {
            refundNotifyArgs.successTime = successTime
            return this
        }

        fun status(status: RefundStatus?): Builder {
            refundNotifyArgs.status = status
            return this
        }
        fun raw(raw: MutableMap<String, Any?>?): Builder {
            refundNotifyArgs.raw = raw
            return this
        }

        fun build(): RefundNotifyArgs {
            return refundNotifyArgs
        }
    }
}