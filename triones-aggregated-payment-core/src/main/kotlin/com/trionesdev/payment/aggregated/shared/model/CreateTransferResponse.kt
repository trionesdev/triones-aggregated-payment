package com.trionesdev.payment.aggregated.shared.model

/**
 * 转账结果
 */
class CreateTransferResponse {
    var billNo: String? = null
    var outBillNo: String? = null
    var extra: Map<String, Any>? = null
    var raw:Any? = null
}