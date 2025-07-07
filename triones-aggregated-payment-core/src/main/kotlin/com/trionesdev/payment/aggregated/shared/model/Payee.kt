package com.trionesdev.payment.aggregated.shared.model

class Payee {
    /**
     * 标识
     */
    var identity: String? = null

    /**
     * 标识类型
     */
    var identityType: String? = null
    var certNo: String? = null
    var certType: String? = null
    var name: String? = null


    class Builder {
        private val payee = Payee()

        fun identity(identity: String?): Builder {
            payee.identity = identity
            return this
        }

        fun identityType(identityType: String?): Builder {
            payee.identityType = identityType
            return this
        }

        fun certNo(certNo: String?): Builder {
            payee.certNo = certNo
            return this
        }

        fun certType(certType: String?): Builder {
            payee.certType = certType
            return this;
        }

        fun name(name: String?): Builder {
            payee.name = name
            return this
        }

        fun build(): Payee {
            return payee
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