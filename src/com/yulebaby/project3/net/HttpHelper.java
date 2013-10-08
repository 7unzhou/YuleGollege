package com.yulebaby.project3.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.AndroidHttpClient;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yulebaby.project3.comment.App;

public class HttpHelper {

	public static class NetworkType {
		public static int NotAvailable = -100;
	}

	public static boolean checkNetWorkInfo(Context context) {
		ConnectivityManager cManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cManager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
			return true;
		} else {
			return false;
		}
	}

	

	public static void getInfo(Context context,
			String url, ArrayList<NameValuePair> params
			) throws JSONException,
			ParseException, IOException {
		Log.d(App.TAG, "Httphelper Url = " + url);
	
		
		AndroidHttpClient httpClient = AndroidHttpClient.newInstance(App.Name);
		HttpPost request = new HttpPost(url);
		HttpParams httpParameters = new BasicHttpParams();
		// HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);
		// HttpConnectionParams.setSoTimeout(httpParameters, 10000);
		request.setParams(httpParameters);
		// BasicHttpContext mHttpContext = new BasicHttpContext();
		// CookieStore mCookieStore = new BasicCookieStore();
		// Cookie cookie = new BasicClientCookie("JSESSIONID",
		// UserInfo.getCookie());
		// mCookieStore.addCookie(cookie);
		// mHttpContext.setAttribute(ClientContext.COOKIE_STORE, mCookieStore);
		//request.setHeader("Cookie", "JSESSIONID="+ App.getInstance().getUserInfo().getCookie());

		try {
			if (params != null)
				request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpResponse response = httpClient.execute(request/* , mHttpContext */);
		if (response.getStatusLine().getStatusCode() == 200) {
			String strResult = java.net.URLDecoder.decode(
					EntityUtils.toString(response.getEntity()), "UTF-8");
			System.out.println("strResult------>" + strResult);
			JSONObject json = new JSONObject(strResult);
			//data = analyze.analyzeJson(context, json);
		}
		httpClient.close();
	
	}


	
}
