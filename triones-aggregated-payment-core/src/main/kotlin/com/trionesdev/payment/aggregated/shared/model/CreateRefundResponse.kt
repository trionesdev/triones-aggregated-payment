package com.trionesdev.payment.aggregated.shared.model

class CreateRefundResponse {
    var refundNo: String? = null
    var outRefundNo: String? = null
    var tradeNo: String? = null
    var outTradeNo: String? = null

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        var response: CreateRefundResponse = CreateRefundResponse()
        fun refundNo(refundNo: String): Builder {
            response.refundNo = refundNo
            return this
        }

        fun outRefundNo(outRefundNo: String): Builder {
            response.outRefundNo = outRefundNo
            return this
        }

        fun tradeNo(tradeNo: String): Builder {
            response.tradeNo = tradeNo
            return this
        }

        fun outTradeNo(outTradeNo: String): Builder {
            response.outTradeNo = outTradeNo
            return this
        }

        fun build(): CreateRefundResponse {
            return response
        }
    }
}