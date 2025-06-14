package com.trionesdev.payment.aggregated.shared.model

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
    var original: Any? = null

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

        fun original(original: Any?): Builder {
            transferNotifyArgs.original = original
            return this
        }

        fun build(): TransferNotifyArgs {
            return transferNotifyArgs
        }
    }
}