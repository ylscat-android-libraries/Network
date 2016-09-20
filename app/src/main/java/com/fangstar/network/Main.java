package com.fangstar.network;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.fangstar.network.volley.Callback;
import com.fangstar.network.volley.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * Created at 2016/7/5.
 *
 * @author YinLanShan
 */
public class Main extends Activity implements View.OnClickListener, Callback {
    private static final String TAG = "Network Demo";
    private Server mServer;
    private Dialog mWaitingDialog;
    private final String URL = "http://127.0.0.1:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mServer = new Server(8080);
        findViewById(R.id.request).setOnClickListener(this);
        TextView tv = (TextView) findViewById(R.id.url);
        tv.setText(URL);
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.waiting);
        dialog.setCancelable(false);
        mWaitingDialog = dialog;
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            mServer.start();
        } catch (IOException e) {
            Log.e(TAG, "Server failed to start", e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mServer.stop();
    }

    @Override
    public void onClick(View v) {
        mWaitingDialog.show();
        Network.request(URL, null, this);
    }

    @Override
    public void onResponse(JSONObject json, Map<String, String> headers, VolleyError error) {
        mWaitingDialog.dismiss();
        TextView tv = (TextView) findViewById(R.id.text);
        if(error == null) {
            String text;
            try {
                text = json.toString(2);
            } catch (JSONException e) {
                text = "JSON format failed";
                Log.e(TAG, text, e);
            }
            tv.setText(text);
        }
        else {
            tv.setText(error.toString());
        }
    }
}
