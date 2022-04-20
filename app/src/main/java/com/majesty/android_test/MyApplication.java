package com.majesty.android_test;

import android.app.Application;

import com.majesty.android_test.di.AppComponent;
import com.majesty.android_test.di.DaggerAppComponent;
import com.majesty.android_test.di.DatabaseModule;
import com.majesty.android_test.di.NetworkModule;

public class MyApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().networkModule(new NetworkModule()).databaseModule(new DatabaseModule(this)).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
