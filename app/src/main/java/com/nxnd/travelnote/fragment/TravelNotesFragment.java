package com.nxnd.travelnote.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.nxnd.travelnote.R;
import com.nxnd.travelnote.Url;
import com.nxnd.travelnote.activity.TravelNotesActivity;
//import com.nxnd.travelnote.adapter.TravelNotesAdapter;
//import com.nxnd.travelnote.listener.RecyclerItemClickListener;
import com.nxnd.travelnote.adapter.NoteAdapter;
import com.nxnd.travelnote.model.TravelNotesModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huchuan 游记
 */
public class TravelNotesFragment extends Fragment {

    private ListView listView;
    private LinearLayoutManager linearLayoutManager;
    private List<TravelNotesModel> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travel_notes, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        //TODO 测试用
        mList = new ArrayList<TravelNotesModel>();
        mList.add(new TravelNotesModel("123","345","测试标题","https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=85690711,3884201894&fm=26&gp=0.jpg","2018-11-22","中国 北京市","川大宝","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542865972201&di=74aa9bd230408814fcb346dbe7ef5e37&imgtype=0&src=http%3A%2F%2Fwww.jituwang.com%2Fuploads%2Fallimg%2F151003%2F258203-1510030RP894.jpg",123));
//        NoteAdapter adapter = new NoteAdapter(getActivity(),mList);
//        List<TravelNotesModel> lists = new ArrayList<TravelNotesModel>();
//        linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
//        listView.setAdapter(adapter);
//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), onItemClickListener));
//        adapter.refreshList(lists);


//        String url = Url.url + "get_all_travel_notes";
//        OkHttpUtils
//                .get()
//                .url(url)
//                .addParams("null", "null")
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//                        Toast.makeText(getActivity(), "获取游记失败", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//
//                        List<TravelNotesModel> list = new ArrayList<TravelNotesModel>();
//
//                        try {
//                            JSONArray jsonArray = new JSONArray(response);
//                            for (int i = 0; i < jsonArray.length(); i++) {
//
//                                TravelNotesModel travelNotesModel = new TravelNotesModel(
//                                        jsonArray.getJSONObject(i).optString("title"),
//                                        jsonArray.getJSONObject(i).optString("date"),
//                                        jsonArray.getJSONObject(i).optString("background"),
//                                        jsonArray.getJSONObject(i).optString("text1"),
//                                        jsonArray.getJSONObject(i).optString("img1"),
//                                        jsonArray.getJSONObject(i).optString("text2")
//                                );
//
//                                list.add(travelNotesModel);
//
//                            }
//
//                            adapter.refreshList(list);
//                            mList = list;
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                });
//
//
        return view;
    }


//    private RecyclerItemClickListener.OnItemClickListener onItemClickListener = new RecyclerItemClickListener.OnItemClickListener() {
//        @Override
//        public void onItemClick(View view, int position) {
//
//            if (position == mList.size()){
//                return;
//            }
//
//            Intent intent = new Intent(getActivity(), TravelNotesActivity.class);
//            intent.putExtra("title", mList.get(position).getTitle());
//            intent.putExtra("date",mList.get(position).getDate());
//            intent.putExtra("background",mList.get(position).getBackground());
//            intent.putExtra("text1",mList.get(position).getText1());
//            intent.putExtra("img1",mList.get(position).getImg1());
//            intent.putExtra("text2",mList.get(position).getText2());
//
//            startActivity(intent);
//        }
//    };
}
