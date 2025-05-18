package com.trionesdev.payment.aggregated.shared.model

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
}