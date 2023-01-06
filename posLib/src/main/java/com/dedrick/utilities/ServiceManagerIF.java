package com.dedrick.utilities;

import android.os.IBinder;

/**
 * Created by Simon on 2019/3/5.
 */

public interface ServiceManagerIF {

    public void onBindSuccess();
    public void onConnect(IBinder service);
}
