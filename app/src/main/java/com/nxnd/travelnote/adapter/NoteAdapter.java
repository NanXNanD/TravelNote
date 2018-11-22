package com.nxnd.travelnote.adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nxnd.travelnote.R;
import com.nxnd.travelnote.model.TravelNotesModel;

import java.util.List;

/**
 * Created by huchuan
 */

public class NoteAdapter extends ArrayAdapter<TravelNotesModel> {
    /**
     * Constructor
     *
     * @param context  listView所在的上下文，也就是ListView所在的Activity
     * @param datas  Cell上要显示的数据list，也就是实体类集合
     */
    private Context context;
    public NoteAdapter(Context context, List<TravelNotesModel> datas) {
        super(context, R.layout.note_list_item, datas);
        this.context = context;
    }

    @Override
    /**
     * @param position 当前设置的Cell行数，类似于iOS开发中的indexPath.row
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        TravelNotesModel item = getItem(position);
        Log.d("note item info", "getView: "+item.getTitle()+item.getCoverUrl());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.note_list_item, null);
        Glide.with(context)
                .load(item.getCoverUrl()).asBitmap()
                .into((ImageView)view.findViewById(R.id.photo));
        TextView time = (TextView) view.findViewById(R.id.tv_time);
        TextView countday = (TextView) view.findViewById(R.id.tv_count_day);
        TextView countview = (TextView) view.findViewById(R.id.tv_count_view);
        TextView address = (TextView) view.findViewById(R.id.tv_address);
        TextView username = (TextView) view.findViewById(R.id.tv_user_name);
        time.setText(item.getStartDate());
        countday.setText("12");
        countview.setText(item.getViewNum());
        address.setText(item.getLocation());
        username.setText(item.getUserName());
        Glide.with(context)
                .load(item.getUserImage())
                .asBitmap()
                .into((ImageView)view.findViewById(R.id.img_avator));

        return view;
    }

//    @Override
//    protected void convert(BaseViewHolder viewHoder, DetailBean item) {
//        Glide.with(viewHoder.itemView.getContext())
//                .load(item.getCover_image()).asBitmap()
//        .into((RoundedImageView)viewHoder.getView(R.id.photo));
//        viewHoder.getTextView(R.id.tv_time).setText(item.getFirst_day());
//        viewHoder.getTextView(R.id.tv_count_day).setText(item.getDay_count()+"");
//        viewHoder.getTextView(R.id.tv_count_view).setText(item.getView_count()+"");
//        viewHoder.getTextView(R.id.tv_address).setText(item.getPopular_place_str());
//        viewHoder.getTextView(R.id.tv_user_name).setText(item.getUser().getName());
//        Glide.with(viewHoder.itemView.getContext())
//                .load(item.getUser().getAvatar_s())
//                .asBitmap().transform(new GlideCircleTransform(viewHoder.itemView.getContext()))
//                .into(viewHoder.getImageView(R.id.img_avator));
//    }
}
