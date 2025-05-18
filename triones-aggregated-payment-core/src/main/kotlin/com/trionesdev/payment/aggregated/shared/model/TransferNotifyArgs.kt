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
}