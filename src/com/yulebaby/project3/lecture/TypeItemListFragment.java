package com.yulebaby.project3.lecture;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.devsmart.android.ui.HorizontalListView;
import com.yulebaby.project3.BuildConfig;
import com.yulebaby.project3.R;
import com.yulebaby.project3.comment.Utils;
import com.yulebaby.project3.model.BaseGson;
import com.yulebaby.project3.model.Lecture;
import com.yulebaby.project3.model.LectureListItem;
import com.yulebaby.project3.net.IParseListener;
import com.yulebaby.project3.net.ZhouRestClientUsage;
import com.yulebaby.project3.net.imageload.ImageCache.ImageCacheParams;
import com.yulebaby.project3.net.imageload.ImageFetchFactory;
import com.yulebaby.project3.net.imageload.ImageFetcher;

public class TypeItemListFragment extends Fragment implements
		OnItemClickListener {

	int typeId;

	List<Lecture> typeList;
	List<LectureListItem> itemList;

	GridView mGridView;
	private ImageAdapter mAdapter;

	LectureAdapter mListAdapter;

	public static final String TAG = "TypeItemListFragment";

	public TypeItemListFragment(List<Lecture> dataList, int typeId) {
		this.typeList = dataList;
		this.typeId = typeId;
		itemList = new ArrayList<LectureListItem>();

	}

	private static final String IMAGE_CACHE_DIR = "yulecollege";

	protected static final int MSG_UPDATE_LISTITEM = 200;
	private ImageFetcher mImageFetcher;
	private int mImageThumbSize;
	private int mImageThumbSpacing;

	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_UPDATE_LISTITEM:
				mGridView.setEnabled(true);
				mAdapter.notifyDataSetChanged();
				mGridView.invalidate();
				break;

			default:
				break;
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mListAdapter = new LectureAdapter(getActivity(), typeList);
		mAdapter = new ImageAdapter(getActivity());
		ImageCacheParams cacheParams = new ImageCacheParams(getActivity(),
				IMAGE_CACHE_DIR);
		cacheParams.setMemCacheSizePercent(getActivity(), 0.25f);
		// mImageFetcher = new ImageFetcher(getActivity(),
		// getResources().getDimensionPixelSize(R.dimen.videoavatar));
		mImageFetcher = ImageFetchFactory.getInstance().initImageFetcher(
				IMAGE_CACHE_DIR, getActivity(),
				getResources().getDimensionPixelOffset(R.dimen.videoavatar));

		mImageFetcher.setLoadingImage(R.drawable.empty_photo);
		mImageFetcher.addImageCache(getActivity().getSupportFragmentManager(),
				cacheParams);

		mImageThumbSize = getResources().getDimensionPixelSize(
				R.dimen.image_thumbnail_size);
		mImageThumbSpacing = getResources().getDimensionPixelSize(
				R.dimen.image_thumbnail_spacing);

	}

	@Override
	public void onStart() {
		super.onStart();
		new ZhouRestClientUsage().getLectureListById(typeId, listen);
	}

	IParseListener listen = new IParseListener() {
		@Override
		public void getJsonResult(Object result) {
			BaseGson<LectureListItem> resultBean = (BaseGson<LectureListItem>) result;
			List<LectureListItem> list = resultBean.getData().get("list");

			// if (null != list && list.size() > 0) {
			// dataList = list;
			// Message msg = mHandler.obtainMessage(MSG_UPDATA_LIST);
			// msg.sendToTarget();
			itemList = list;
			mHandler.sendEmptyMessage(MSG_UPDATE_LISTITEM);
			System.out.println("getList size:" + list.size());
			// } else {
			// Utils.showTip(R.string.http_nodata);
			// }

		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.fragment_lecturelist, null);

		HorizontalListView listview = (HorizontalListView) view
				.findViewById(R.id.itemlistview);
		listview.setAdapter(mListAdapter);
		LayoutParams layoutParams = listview.getLayoutParams();
		layoutParams.height = 80;
		listview.setLayoutParams(layoutParams);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				mGridView.setEnabled(false);
				int selectId = typeList.get(position).getId();
				if (selectId != typeId) {
					typeId = selectId;
					new ZhouRestClientUsage()
							.getLectureListById(typeId, listen);
				}
			}
		});

		mGridView = (GridView) view.findViewById(R.id.gridView);

		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(this);
		mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView absListView,
					int scrollState) {
				if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
					mImageFetcher.setPauseWork(true);
				} else {
					mImageFetcher.setPauseWork(false);
				}
			}

			@Override
			public void onScroll(AbsListView absListView, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
			}
		});
		mGridView.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						if (mAdapter.getNumColumns() == 0) {
							final int numColumns = (int) Math.floor(mGridView
									.getWidth()
									/ (mImageThumbSize + mImageThumbSpacing));
							if (numColumns > 0) {
								final int columnWidth = (mGridView.getWidth() / numColumns)
										- mImageThumbSpacing;
								mAdapter.setNumColumns(numColumns);
								mAdapter.setItemHeight((int) (columnWidth * 1.2));
								if (BuildConfig.DEBUG) {
									Log.d(TAG,
											"onCreateView - numColumns set to "
													+ numColumns);
								}
							}
						}
					}
				});

		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		int count = mAdapter.getNumColumns();
		// Utils.showTip("ItemClick:"+itemList.get(position-count).getUrl());
		((IFragmentControl) getActivity()).showFragment(PlayFragment.TAG,
				itemList.get(position - count).getUrl());
	}

	private class ImageAdapter extends BaseAdapter {

		private final Context mContext;
		private int mItemHeight = 0;
		private int mNumColumns = 0;
		private int mActionBarHeight = 0;
		private GridView.LayoutParams mImageViewLayoutParams;

		public ImageAdapter(Context context) {
			super();
			mContext = context;
			mImageViewLayoutParams = new GridView.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		}

		@Override
		public int getCount() {
			return itemList.size() + mNumColumns;
		}

		@Override
		public Object getItem(int position) {
			return position < mNumColumns ? null : itemList.get(
					position - mNumColumns).getPic();
		}

		@Override
		public long getItemId(int position) {
			return position < mNumColumns ? 0 : position - mNumColumns;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		@Override
		public int getItemViewType(int position) {
			return (position < mNumColumns) ? 1 : 0;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		class ViewHolder {
			ImageView avatarImage;
			// ImageView tagImage;
			TextView title;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup container) {
			if (position < mNumColumns) {
				if (convertView == null) {
					convertView = new View(mContext);
				}
				convertView.setLayoutParams(new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT, mActionBarHeight));
				return convertView;
			}

			ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(getActivity()).inflate(
						R.layout.gallery_typeitem, null);
				holder = new ViewHolder();
				holder.avatarImage = (ImageView) convertView
						.findViewById(R.id.gallery_img);
				// holder.tagImage = (ImageView)
				// convertView.findViewById(R.id.gallery_tag);
				holder.title = (TextView) convertView
						.findViewById(R.id.gallery_title);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.title
					.setText(itemList.get(position - mNumColumns).getName());
			convertView.setLayoutParams(mImageViewLayoutParams);
			mImageFetcher.loadImage(itemList.get(position - mNumColumns)
					.getPic(), holder.avatarImage);

			System.out.println("image url:"
					+ itemList.get(position - mNumColumns).getPic());
			return convertView;
		}

		public void setItemHeight(int height) {
			if (height == mItemHeight) {
				return;
			}
			mItemHeight = height;
			mImageViewLayoutParams = new GridView.LayoutParams(
					LayoutParams.MATCH_PARENT, mItemHeight);
			mImageFetcher.setImageSize(height);
			notifyDataSetChanged();
		}

		public void setNumColumns(int numColumns) {
			mNumColumns = numColumns;
		}

		public int getNumColumns() {
			return mNumColumns;
		}
	}

}
