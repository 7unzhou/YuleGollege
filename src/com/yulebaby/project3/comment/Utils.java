package com.yulebaby.project3.comment;

import com.yulebaby.project3.R;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

public class Utils {
	public static void showTip(int resId) {
		showTip(App.getInstance().getResources().getString(resId));
	}

	public static void showTip(String msg) {
		Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT).show();
	}

	public static boolean isNetworkAvailable() {
		ConnectivityManager cm = (ConnectivityManager) App.getInstance()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		// if no network is available networkInfo will be null
		// otherwise check if we are connected
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		showTip(R.string.network_notconnection);
		return false;
	}

	public static void enableStrictMode() {
		// if (Utils.hasGingerbread()) {
		// StrictMode.ThreadPolicy.Builder threadPolicyBuilder =
		// new StrictMode.ThreadPolicy.Builder()
		// .detectAll()
		// .penaltyLog();
		// StrictMode.VmPolicy.Builder vmPolicyBuilder =
		// new StrictMode.VmPolicy.Builder()
		// .detectAll()
		// .penaltyLog();
		//
		// // if (Utils.hasHoneycomb()) {
		// // threadPolicyBuilder.penaltyFlashScreen();
		// // vmPolicyBuilder
		// // .setClassInstanceLimit(ImageGridActivity.class, 1)
		// // .setClassInstanceLimit(ImageDetailActivity.class, 1);
		// // }
		// StrictMode.setThreadPolicy(threadPolicyBuilder.build());
		// StrictMode.setVmPolicy(vmPolicyBuilder.build());
		// }
	}

	public static boolean hasFroyo() {
		// Can use static final constants like FROYO, declared in later versions
		// of the OS since they are inlined at compile time. This is guaranteed
		// behavior.
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	}

	public static boolean hasGingerbread() {
		return false/* Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD */;
	}

	public static boolean hasHoneycomb() {
		return false;/* Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB; */
	}

	public static boolean hasHoneycombMR1() {
		return false;/*
					 * Build.VERSION.SDK_INT >=
					 * Build.VERSION_CODES.HONEYCOMB_MR1;
					 */
	}

	public static boolean hasJellyBean() {
		return false;/* Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN; */
	}
}
