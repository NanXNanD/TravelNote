package com.nxnd.travelnote.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nxnd.travelnote.R;
import com.nxnd.travelnote.Url;
import com.nxnd.travelnote.model.StepModel;
import com.nxnd.travelnote.util.CommonUtil;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by huchuan on 2018/12/28.
 */

public class StepAdapter extends ArrayAdapter<StepModel> {

    private int newResourceId;

    public StepAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.newResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        StepModel stepModel = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(newResourceId, parent, false);

        //控件
        TextView date = view.findViewById(R.id.step_item_date);
        TextView location = view.findViewById(R.id.step_item_location);
        TextView content = view.findViewById(R.id.step_item_content);
        ImageView img = view.findViewById(R.id.step_item_img);

        //操作
        date.setText(stepModel.getDatetime());//TODO 时间格式转换
        location.setText(stepModel.getLocation());
        content.setText(stepModel.getContent());
        x.image().bind(img, CommonUtil.getImageUrl(stepModel.getImageurl()),CommonUtil.options);
        Log.d("xImageBind", "加载step图片: "+CommonUtil.getImageUrl(stepModel.getImageurl()));
        return view;
    }
}
