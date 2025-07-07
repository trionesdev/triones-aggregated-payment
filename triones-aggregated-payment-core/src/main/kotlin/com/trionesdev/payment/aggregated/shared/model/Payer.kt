package com.trionesdev.payment.aggregated.shared.model

class Payer {
    /**
     * 标识
     */
    var identity: String? = null

    /**
     * 标识类型
     */
    var identityType: String? = null

    constructor()



    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private var player: Payer = Payer()

        fun identity(identity: String?) = apply {
            player.identity = identity
        }
        fun identityType(identityType: String?) = apply {
            player.identityType = identityType
        }

        fun build(): Payer {
            return player
        }
    }
}