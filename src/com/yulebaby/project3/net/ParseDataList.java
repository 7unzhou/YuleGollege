/**
 * 
 */
package com.yulebaby.project3.net;

import java.lang.reflect.Type;
import java.util.List;

import android.test.UiThreadTest;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yulebaby.project3.R;
import com.yulebaby.project3.comment.Utils;
import com.yulebaby.project3.model.BaseGson;
import com.yulebaby.project3.model.Lecture;

/**
 * @author : zhoujunzhou
 * @date : 2013-9-25
 * @Description :
 */
public class ParseDataList<T> extends AsyncHttpResponseHandler {

	
	// com.yulebaby.project3.model.BaseGson<Lecture> resultBean;
	TypeToken typeToken;
	IParseListener listen;

	public ParseDataList(String url, RequestParams params, TypeToken typeToken,
			IParseListener listen) {
		// ZhouRestClient.get("collegeLectureTypeList", null,this);
		ZhouRestClient.post(url, params, this);
		this.typeToken = typeToken;
		this.listen = listen;

	}

	public enum ParseType {  
		  DATALIST,DATAMODEL  
		}  
	private ParseType mType;
	public void setParseType(ParseType type){
		this.mType = type;
	}
	
	@Override
	public void onSuccess(String response) {
		System.out.println("Response:" + response);

		try {

			setBean(response, typeToken.getType());
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("ParseModel", "解析response to BaseGson 错误。");
		}

	}

	@Override
	public void onFailure(Throwable arg0, String arg1) {
		// TODO Auto-generated method stub
		super.onFailure(arg0, arg1);
		System.out.println("onFailure:" + arg1);
		Utils.showTip(R.string.no_network_connection);
	}

	void setBean(String response, Type typeToken) {
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
				.create();
		
		if(mType == ParseType.DATAMODEL){
			
		}
		BaseGson resultBean = (BaseGson) gson.fromJson(response, typeToken);

		if (resultBean.getResult() == 1) {
			Utils.showTip(R.string.http_nologin);
			return;
		} else if (resultBean.getResult() == -1) {
			Utils.showTip(R.string.http_error);
			return;
		}
		System.out.println("http return code:" + resultBean.getResult());
		listen.getJsonResult(resultBean);
	}

}
