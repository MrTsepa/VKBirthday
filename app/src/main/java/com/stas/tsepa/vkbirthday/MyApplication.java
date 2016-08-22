package com.stas.tsepa.vkbirthday;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

/**
 * Created by Tsepa Stas
 * Email tsepa.stas@gmail.com
 */
public class MyApplication extends android.app.Application {

    VKAccessToken mVKAccessToken;

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}
