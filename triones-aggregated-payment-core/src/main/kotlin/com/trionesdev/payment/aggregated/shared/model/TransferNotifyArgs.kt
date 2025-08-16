package com.trionesdev.payment.aggregated.shared.model

import com.trionesdev.payment.aggregated.shared.enums.TransferStatus

class TransferNotifyArgs {
    /**
     * 支付渠道
     */
    var channel: String? = null

    /**
     * 支付渠道内部转账单号
     */
    var billNo: String? = null

    /**
     * 商家转账单号
     */
    var outBillNo: String? = null
    var status: TransferStatus? = null
    var raw: MutableMap<String, Any?>? = null

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private var transferNotifyArgs: TransferNotifyArgs = TransferNotifyArgs()

        fun channel(channel: String?): Builder {
            transferNotifyArgs.channel = channel
            return this
        }

        fun billNo(billNo: String?): Builder {
            transferNotifyArgs.billNo = billNo
            return this
        }

        fun outBillNo(outBillNo: String?): Builder {
            transferNotifyArgs.outBillNo = outBillNo
            return this
        }

        fun status(state: TransferStatus): Builder {
            transferNotifyArgs.status = state
            return this
        }

        fun raw(raw: MutableMap<String, Any?>?): Builder {
            transferNotifyArgs.raw = raw
            return this
        }

        fun build(): TransferNotifyArgs {
            return transferNotifyArgs
        }
    }
}