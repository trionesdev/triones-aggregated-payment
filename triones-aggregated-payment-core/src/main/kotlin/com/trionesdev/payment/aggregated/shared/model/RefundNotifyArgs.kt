package com.trionesdev.payment.aggregated.shared.model

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
     * 商户系统交易号
     */
    var outTradeNo: String? = null

    /**
     * 渠道退款单号
     */
    var refundNo: String? = null

    /**
     * 商家退款单号
     */
    var outRefundNo: String? = null

    /**
     * 金额
     */
    var amount: TransactionAmount? = null

    /**
     * 成功时间
     */
    var successTime: Instant? = null
    var original: Any? = null
}