package com.fangstar.network;

import android.app.Application;

import com.fangstar.network.volley.Network;

/**
 * Created at 2016/7/5.
 *
 * @author YinLanShan
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Network.init(this);
    }
}
