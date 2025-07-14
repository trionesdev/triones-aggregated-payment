package com.trionesdev.payment.aggregated.shared.model

class CancelTransferRequest {
    /**
     * 通道
     */
    var channel: String? = null

    var billNo: String? = null

    /**
     * 商户系统单号
     */
    var outBillNo: String? = null

    class Builder {
        private val request = CancelTransferRequest()

        fun channel(channel: String?) = apply { request.channel = channel }

        fun billNo(billNo: String?) = apply { request.billNo = billNo }

        fun build(): CancelTransferRequest {
            return request
        }
    }

    companion object {
        /**
         * 创建Builder实例
         */
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

}