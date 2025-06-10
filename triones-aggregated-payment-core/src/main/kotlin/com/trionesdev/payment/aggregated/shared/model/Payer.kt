package com.trionesdev.payment.aggregated.shared.model

class Payer {
    var openId: String? = null

    constructor(openId: String?) {
        this.openId = openId
    }
}