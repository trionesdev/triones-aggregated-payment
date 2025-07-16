package com.trionesdev.payment.aggregated.shared.model

import java.time.Instant

class CancelTransferResponse {
    /**
     * 商户转账单号
     */
    var outBillNo: String? = null

    /**
     * 微信转账单号
     */
    var transBillNo: String? = null

    var updateTime: Instant? = null

    /**
     * 支付渠道原始返回报文
     */
    var raw: Map<String, Any?>? = null

    class Builder {
        private var response = CancelTransferResponse()

        fun outBillNo(outBillNo: String?) = apply { response.outBillNo = outBillNo }
        fun transBillNo(transBillNo: String?) = apply { response.transBillNo = transBillNo }
        fun updateTime(updateTime: Instant?) = apply { response.updateTime = updateTime }
        fun raw(raw: Map<String, Any?>?) = apply { response.raw = raw }
        fun build(): CancelTransferResponse {
            return response
        }
    }

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }
}