package com.cary.activity.timecat.fragment.index.wechatpay;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;

/**
 * Created by Cary on 2018/4/15.
 */

public class WeChatPayClass {
    private IWXAPI api;
    private Context mContext;

    public WeChatPayClass(Context mContext) {
        this.mContext = mContext;
        api = WXAPIFactory.createWXAPI(mContext, "wxb4ba3c02aa476ea1");

    }

    public void WXPay(String content){
        String url = "http://wxpay.wxutil.com/pub_v2/app/app_pay.php";
        Toast.makeText(mContext, "获取订单中...", Toast.LENGTH_SHORT).show();
        try{
//            byte[] buf = new byte[]{};//WXUtil.httpGet(url);
//            if (buf != null && buf.length > 0) {
//                String content = new String(buf);
                Log.e("get server pay params:",content);
                JSONObject json = new JSONObject(content);
                if(null != json){
//                    && !json.has("appid") ){
                    PayReq req = new PayReq();
                    //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                    req.appId			= json.getString("appid");
                    req.partnerId		= json.getString("partnerid");
                    req.prepayId		= json.getString("prepayid");
                    req.nonceStr		= json.getString("noncestr");
                    req.timeStamp		= json.getString("timestamp");
                    req.packageValue	= json.getString("package");
                    req.sign			= json.getString("sign");
                    req.extData			= "app data"; // optional
                    Toast.makeText(mContext, "正常调起支付", Toast.LENGTH_SHORT).show();
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    api.sendReq(req);
                }else{
                    Log.d("PAY_GET", "返回错误"+json.getString("retmsg"));
                    Toast.makeText(mContext, "返回错误", Toast.LENGTH_SHORT).show();
                }
//            }else{
//                Log.d("PAY_GET", "服务器请求错误");
//                Toast.makeText(mContext, "服务器请求错误", Toast.LENGTH_SHORT).show();
//            }
        }catch(Exception e){
            Log.e("PAY_GET", "异常："+e.getMessage());
            Toast.makeText(mContext, "异常："+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
