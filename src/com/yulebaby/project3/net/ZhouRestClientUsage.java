package com.yulebaby.project3.net;

import java.util.HashMap;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.yulebaby.project3.comment.App;
import com.yulebaby.project3.model.BaseGson;
import com.yulebaby.project3.model.BaseModelGson;
import com.yulebaby.project3.model.ExamDetail;
import com.yulebaby.project3.model.Lecture;
import com.yulebaby.project3.model.LectureListItem;

public class ZhouRestClientUsage {

	/**
	 * 教授讲堂分类列表
	 */
	// http://college.yulebaby.com/s/collegeLectureTypeList
	public void getLectureTypeList(IParseListener listen) {
		new ParseDataList<Lecture>("collegeLectureTypeList", null,
				new TypeToken<BaseGson<Lecture>>() {
				}, listen);
	}

	/**
	 * 
	 * 教授讲堂视频列表接口
	 */
	// http://college.yulebaby.com/s/collegeLectureList?typeId=1
	public void getLectureListById(int typeId, IParseListener listen) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("typeId", typeId + "");
		// paramMap.put("typeId", 1 + "");
		RequestParams params = new RequestParams(paramMap);

		new ParseDataList<LectureListItem>("collegeLectureList", params,
				new TypeToken<BaseGson<LectureListItem>>() {
				}, listen);
	}

	/**
	 * 
	 * 专家在线接口列表
	 * 
	 */
	public void getExamTypeList(IParseListener listen) {
		new ParseDataList<Lecture>("collegeExamTypeList", null,
				new TypeToken<BaseGson<Lecture>>() {
				}, listen);
	}

	// http://college.yulebaby.com/s/collegeExamDetail?id=1
	public void getExamDetail(IParseListener listen, int id) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id + "");
		// paramMap.put("typeId", 1 + "");
		RequestParams params = new RequestParams(paramMap);

		new ParseDataModel<ExamDetail>("collegeExamDetail", params,
				new TypeToken<BaseModelGson<ExamDetail>>() {
				}, listen);
	}

	// http://college.yulebaby.com/s/collegeExamList?typeId=1
	public void getExamList(IParseListener listen, int id) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("typeId", id);
		// paramMap.put("typeId", 1 + "");
		RequestParams params = new RequestParams(paramMap);

		new ParseDataList<Lecture>("collegeExamList", params,
				new TypeToken<BaseGson<Lecture>>() {
				}, listen);
	}

}
