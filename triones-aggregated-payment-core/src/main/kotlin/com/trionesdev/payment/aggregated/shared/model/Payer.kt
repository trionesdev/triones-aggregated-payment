package com.trionesdev.payment.aggregated.shared.model

class Payer {
    var openId: String? = null

    constructor()

    constructor(openId: String?) {
        this.openId = openId
    }

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private var player: Payer = Payer()
        fun openId(openId: String?) = apply {
            player.openId = openId
        }

        fun build(): Payer {
            return player
        }
    }
}