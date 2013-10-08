package com.yulebaby.project3.comment;

import android.app.Application;

public class App extends Application {
	private static App instance;
	public static final String TAG = "YuleCollege";
	public static final String Name = "com.yulebaby.college";

	public static App getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

}
