package xyz.renhono.aapp1;

import android.app.Application;

import org.xutils.x;

/**
 * Created by GT70 on 2016/10/26 0026.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(true);


    }
}
