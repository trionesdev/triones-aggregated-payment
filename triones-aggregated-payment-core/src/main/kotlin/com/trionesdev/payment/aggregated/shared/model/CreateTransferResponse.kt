package com.trionesdev.payment.aggregated.shared.model

/**
 * 转账结果
 */
class CreateTransferResponse {
    var billNo: String? = null
    var outBillNo: String? = null
    var extra: Map<String, Any>? = null
    var raw: MutableMap<String, Any?>? = null

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private var response: CreateTransferResponse = CreateTransferResponse()

        fun billNo(billNo: String?): Builder {
            response.billNo = billNo
            return this
        }

        fun outBillNo(outBillNo: String?): Builder {
            response.outBillNo = outBillNo
            return this
        }

        fun extra(extra: Map<String, Any>?): Builder {
            response.extra = extra
            return this
        }

        fun raw(raw: MutableMap<String, Any?>?): Builder {
            response.raw = raw
            return this
        }

        fun build(): CreateTransferResponse {
            return response
        }
    }
}