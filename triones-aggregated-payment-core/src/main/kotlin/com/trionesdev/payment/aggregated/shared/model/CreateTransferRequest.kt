package com.trionesdev.payment.aggregated.shared.model

/**
 * 转账请求
 */
class CreateTransferRequest {
    var appId: String? = null
    var outBillNo: String? = null
    var notifyUrl: String? = null
}