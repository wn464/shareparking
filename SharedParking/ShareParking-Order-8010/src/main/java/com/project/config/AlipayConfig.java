package com.project.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092900620733";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCR2askIdEr7brWuc1sJ2A7+Nb0A+lbrp8ui/asqtTLaJ4qK6yrJF2yd3NiieTjHpvWW32xm/+d1ZBLyfLvU5G4DD+wUmsludPVZA0JZpPN3wcxf3Fg/Cn8hXCYcVx5uHlygmDqQLnIZm2ZWsci87N6+iYdlXQDtCxGDMyPEwCXFvCxz7YjbeNa3Fm5YfeLqgN4/BvK2a6g+HwbIxxYLKfW/9OscIfwq2FAkTchVEYt7n5ZGfQgaE6OUELq49bT4eeVkzvXDt02ue3HT324L+J5Z4bzgb0ghLkY/Xu4jZkJ/eujQLCq1Oqh1xcDl2odm9glk35Yv/bpk2arJa5ej8HvAgMBAAECggEAS/da65PtlfrZ4TOfqmCMAFCo7SoIZud6I81k6ySDd3sDPqXG5tEnOGh9Ier+28k6lBjNTDAboML2yHLkZQFbGcjPd3BGPL0gd7oIDEhwXb93yLE/hatx1wRN1CjfaMrL/vensPuLiLTJCyI8LJlQd5JJPgl1BuTWVpoeBbt5CDhxQefSVpUtZq4kCoS3pVfmYCMTeFrliiE2Z5S9Ad8dLvhUiRU/97UyBUHgC5m3BkgvKjkLU+MuhtqllmQc0dCvcVzzS++lX+D0XsCizw468EW952Qz6zXSrg4BGmZ6MaEG9LZBN+GuySSWXP+ZEWW8kd6bxgiAGTE7mkdKh0+/CQKBgQDWRHTPbepmECpeOyA/MQhrLb0CjXKegmj6baf+igGcJunhVbztXNc3qevm6tLTyW9rv4EoHRPxtP+nY/7H+3YQLn3JviSPRIm8WUpvQ1V09Fr7aIzjSEHX1fXONRF8+TSq3woazoJswEEFzTJwsEF4a1YQYiodcLNrJB3NqzWf7QKBgQCuQeENhiwNIsVH8m9zoH5kyy3pkaaV7XoyMrX6BR/iwv72xn4jd/V6VWg/wVFztmfjT85wC6Z7BvLnXtV+oq6+ZBp87XpRTzf/kwufN3nXDuy3H0ah07A7oDceiZ16gfYiSEuR5G4ZseSqxzCtyxt/S+GqrOoVm0X04u6c6CmVywKBgGDC8W1rWdtVmYyMbveakQ61zRGGBykGA5p0QtV0RV3bROt56KPYtWhx2BKkISl7YlHqXc1btFIHRxy0z65IJP0RwcSFlcUNUpY4TKEKa2ExqJS4lkddrj5mZWcGGCuz7tDrgIiykbvC5ppUHF8/eZGEB43GhvZcmunbmYnzs3fRAoGBAKq3NdE3Cj8QOnAC7fc8W7dJakByBd7KGbXGAovfq15PpNOEoJ0E78QamhgjvzV6lQqX6+nPMBlFNIFBYi0/GazCi0SqrvOrhXPnzjH8emwbXgMFLABsUDIKAxXywgn7+nOZmhdi0A5yPlCk9ma9HcI3R+0am/Xw+bcw/41O0TbDAoGBAJziZHiX3agyWvIjhRqVvE0e2kOFXye65ihtfvmhu3j7klNiwsa/WkVCvOSiqBSvwvVJJh7pQFnxzuiPluQ1PaeHxrUPrR5ZdughEyhxIYi0/xkZJ9bxpN2GKWSWEnGUc8zH5yUX/Rx7asv1EdUi+9189xhFmFjrza+x79qSB2JG";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmClpgFHgrWHYSJJUevA4CeEjBgIS4deuFw2ZbIl7kCw8vQGjMeKxoUmNug4S2mHjX0ZrPYEU8Ejo6Y/1cRuGtewzWYKQ2VhBGjKFdUs8i/3XHTuK7KihG6KidYj1lKgaCG7PjoisXh50ZwHcUT5V1+X5MD/nn+M8G6wKdg5MYwZdNm0EyVxuk1O6MhB3s53Y68adApHAiQEHpOsvA74obMku4mJhx3wgFED71F5dO7aBy2geXgWv3iplh81Hquji+/wJaSIEyKFgLb/nkiwh1S1rZQ4k9YvKuc0RNLva8VHvWBsMEnhCvPOX3jpTPjV4FG8eVTRtwwYmbNzZCyQpdQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://k2481770p3.zicp.vip:53365/ret1";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://k2481770p3.zicp.vip:53365/ret";
	/*
	 * http://2485201h3o.zicp.vip:59366 http://k2481770p3.zicp.vip:53365
	 */
	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

