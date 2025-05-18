package com.trionesdev.payment.aggregated.shared.model

import java.time.Instant

class TransactionNotifyArgs {
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
     * 附加信息(提交订单时带的附加信息，原样返回)
     */
    var attach: String? = null

    /**
     * 金额
     */
    var amount: TransactionAmount? = null

    /**
     * 成功时间
     */
    var successTime: Instant? = null

    /**
     * 交易信息(第三方支付的交易信息)
     */
    var original: Any? = null
}