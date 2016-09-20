package com.fangstar.network.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created at 2015/11/18.
 *
 * @author YinLanShan
 */
public class ProtocolError extends VolleyError {
    public final int result;
    public final String msg;
    public final JSONObject json;

    public ProtocolError(JSONObject json, NetworkResponse resp) {
        super(json.optString("msg"));
        result = json.optInt("result");
        msg = json.optString("msg");
        this.json = json;
    }
}
