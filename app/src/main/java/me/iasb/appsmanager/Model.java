package me.iasb.appsmanager;

import android.graphics.drawable.Drawable;

public class Model {
    private Drawable appIcon;
    private String appName;
    private String appPackageName;

    public Model(Drawable appIcon, String appName, String appDesc) {
        this.appIcon = appIcon;
        this.appName = appName;
        this.appPackageName = appDesc;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public String getappPackageName() {
        return appPackageName;
    }
}
