package com.trionesdev.payment.aggregated.shared.model

/**
 * 转账请求
 */
class CreateTransferRequest {
    var channel: String? = null
    var appId: String? = null
    var outBillNo: String? = null
    var notifyUrl: String? = null
    
    /**
     * Builder for CreateTransferRequest
     */
    class Builder {
        private val request = CreateTransferRequest()
        
        /**
         * 设置支付渠道
         */
        fun channel(channel: String?): Builder {
            request.channel = channel
            return this
        }
        
        /**
         * 设置应用ID
         */
        fun appId(appId: String?): Builder {
            request.appId = appId
            return this
        }
        
        /**
         * 设置外部账单号
         */
        fun outBillNo(outBillNo: String?): Builder {
            request.outBillNo = outBillNo
            return this
        }
        
        /**
         * 设置通知URL
         */
        fun notifyUrl(notifyUrl: String?): Builder {
            request.notifyUrl = notifyUrl
            return this
        }
        
        /**
         * 构建CreateTransferRequest实例
         */
        fun build(): CreateTransferRequest {
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