package com.trionesdev.payment.aggregated.shared.enums

enum class Scene {
    /**
     * 手机端网页，
     * 微信：H5支付，跳转到对应的支付页面（非微信客户端内部浏览器）
     * 支付宝：手机网站支付，拉起支付宝
     */
    WAP, //h5 跳转到支付确认页面

    /**
     * 电脑网页
     * 微信：Native支付，生成二维码
     * 支付宝：电脑网页支付，跳转到付款页面
     */
    PC_WEB,//跳转到支付页面

    /**
     * 基于内置浏览器的
     * 微信：JSAPI
     * 支付宝：JSAPI
     */
    JSAPI,
    APP, //APP

    /**
     * 小程序
     * 微信：小程序JSAPI
     * 支付宝：小程序JSAPI
     */
    MINI_PROGRAM, //小程序

    /**
     * 商户扫描用户出示的付款码
     *
     */
    PAYMENT_CODE, //付款码

    /**
     * 刷脸付
     */
    FACE_PAY, //刷脸支付
}