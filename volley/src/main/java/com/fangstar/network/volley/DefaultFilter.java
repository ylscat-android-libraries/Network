package com.fangstar.network.volley;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;


import org.json.JSONObject;

/**
 * 网络返回数据后，进一步判断业务上的正确与否
 * Created at 2015/11/18.
 *
 * @author YinLanShan
 */
public class DefaultFilter implements Filter<JSONObject> {
    private static Dialog sUpgradeDialog;

    @Override
    public Response<JSONObject> filter(JSONObject json, NetworkResponse response) {
        //举例子如下注释
        /*switch (json.optInt("errorcode")) {
            case 1: //黑名单
            case 2: //离职
            case 4: //密码被修改
                Utils.clearForLogout();

                Intent intent = new Intent(FangstarBrokerApplication.sApplication,
                        UserLoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("type", "Error");
                intent.putExtra("msg", json.optString("msg"));
                FangstarBrokerApplication.sApplication.startActivity(intent);
                return Response.error(new ProtocolError(json, response));
            case 3: //强制升级
                Response<JSONObject> error = Response.error(new ProtocolError(json, response));
                if(sUpgradeDialog != null && sUpgradeDialog.isShowing()){
                    if(sUpgradeDialog.getContext() == BaseActivity.sCurrentActivity){
                        return error;
                    }
                }
                JSONObject data = json.optJSONObject(Constants.JSON_TAG_DATA);
                if(BaseActivity.sCurrentActivity != null && data != null) {
                    promptUpgradeDialog(BaseActivity.sCurrentActivity, data);
                }
                return  error;
        }

        if(json.optInt("result") != 1){//此处因为案场助理，客户查询中心，在result=0的时候还需要取数据，所有特作修改。姚飞  2016年1月15日11:11:50
        	if(json.optInt("msg_type")==0){
        		 return Response.error(new ProtocolError(json, response));
        	}else{
        		return Response.success(json,
                        HttpHeaderParser.parseCacheHeaders(response));
        	}
        }else{
        	 return Response.success(json,
                     HttpHeaderParser.parseCacheHeaders(response));
        }*/
        return Response.success(json,
                HttpHeaderParser.parseCacheHeaders(response));
    }
}
