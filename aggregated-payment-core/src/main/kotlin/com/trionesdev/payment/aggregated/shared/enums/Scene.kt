package com.trionesdev.payment.aggregated.shared.enums

enum class Scene {
    M_WEB, //h5 跳转到支付确认页面
    JSAPI, //通过内置浏览器
    APP, //APP
    NATIVE, //native,生成二维码
    MINI_PROGRAM, //小程序
    PAYMENT_CODE, //付款码
    FACE_PAY, //刷脸支付
}