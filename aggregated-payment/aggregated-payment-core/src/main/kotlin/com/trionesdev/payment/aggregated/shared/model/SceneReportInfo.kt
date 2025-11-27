package com.trionesdev.payment.aggregated.shared.model

class SceneReportInfo {
    var type: String? = null
    var content: String? = null

    class Builder {
        var info = SceneReportInfo()

        fun type(type: String?) = apply {
            info.type = type
        }

        fun content(content: String?) = apply { info.content = content }

        fun build(): SceneReportInfo {
            return info
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