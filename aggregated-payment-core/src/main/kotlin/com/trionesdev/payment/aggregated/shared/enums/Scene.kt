package com.trionesdev.payment.aggregated.shared.enums

enum class Scene {
    H5, //h5 跳转到支付确认页面
    JSAPI, //通过内置浏览器
    APP, //APP
    NATIVE, //native,生成二维码
    MINI_PROGRAM, //小程序
    PAYMENT_CODE //付款码
}