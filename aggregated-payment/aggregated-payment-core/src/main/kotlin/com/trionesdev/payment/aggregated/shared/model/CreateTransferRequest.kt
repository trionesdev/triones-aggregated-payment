package com.trionesdev.payment.aggregated.shared.model

/**
 * 转账请求
 */
class CreateTransferRequest {
    /**
     * 通道
     */
    var channel: String? = null

    /**
     * 应用ID（微信支付使用）
     */
    var appId: String? = null

    /**
     * 商户系统单号
     */
    var outBillNo: String? = null

    /**
     * 场景ID
     */
    var sceneId: String? = null

    /**
     * 收款方信息
     */
    var payee: Payee? = null

    /**
     * 转账金额
     */
    var amount: Money? = null

    /**
     * 备注
     */
    var remark: String? = null
    var title: String? = null

    /**
     * 转账场景报备信息
     * 微信支付必填
     */
    var sceneReportInfos: List<SceneReportInfo>? = null

    /**
     * 业务扩展参数
     */
    var businessParams: String? = null
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

        fun sceneId(sceneId: String?): Builder {
            request.sceneId = sceneId
            return this
        }

        fun payee(payee: Payee?): Builder {
            request.payee = payee
            return this
        }

        fun amount(amount: Money?) = apply { request.amount = amount }

        fun remark(remark: String?): Builder {
            request.remark = remark
            return this
        }

        fun title(title: String?) = apply { request.title = title }

        fun sceneReportInfos(sceneReportInfos: List<SceneReportInfo>?) =
            apply { request.sceneReportInfos = sceneReportInfos }

        fun businessParams(businessParams: String?): Builder {
            request.businessParams = businessParams
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