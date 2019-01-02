package com.nxnd.travelnote.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nxnd.travelnote.R;
import com.nxnd.travelnote.model.TravelNotesModel;
import com.nxnd.travelnote.util.CommonUtil;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huchuan
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<TravelNotesModel> mItems;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    private Context context;

    public NoteAdapter(List<TravelNotesModel> mItems,Context context) {
        this.mItems = mItems;
        this.context = context;
    }


    public void addItem(int position,TravelNotesModel travelNotesModel) {
        if (position > mItems.size()) return;

        mItems.add(position, travelNotesModel);
        notifyItemInserted(position);
    }

//    public void removeItem(int position) {
//        if (position >= mItems.size()) return;
//
//        mItems.remove(position);
//        notifyItemRemoved(position);
//    }

    public void removeAll(){
        int s = mItems.size();
        mItems.clear();
        notifyItemRangeRemoved(0,s);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.note_list_item, viewGroup, false);
        return new ViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        TravelNotesModel travelNotesModel = mItems.get(i);
        viewHolder.setData(travelNotesModel);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public TravelNotesModel getItemByIndex(int i){
        return mItems.get(i);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private void onItemHolderClick(RecyclerView.ViewHolder itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private QMUIRadiusImageView img ;
        private TextView time ;
        private TextView countview;
        private TextView address ;
        private TextView title;
        private TextView username ;
        private ImageView avator;
        private NoteAdapter mAdapter;

        public ViewHolder(View view, NoteAdapter adapter) {
            super(view);
            itemView.setOnClickListener(this);
            mAdapter = adapter;

            img = view.findViewById(R.id.note_photo);
            time = (TextView) view.findViewById(R.id.tv_time);
            countview = (TextView) view.findViewById(R.id.tv_count_view);
            address = (TextView) view.findViewById(R.id.tv_address);
            title = (TextView) view.findViewById(R.id.tv_title);
            avator = (ImageView) view.findViewById(R.id.img_avator);
            username = (TextView) view.findViewById(R.id.tv_user_name);
        }

        public void setData(TravelNotesModel model) {
            time.setText(model.getStartDate());//TODO 时间转换
            countview.setText(model.getViewNum()+"");
            address.setText(model.getLocation());
            username.setText(model.getUserName());
            title.setText(model.getTitle());
            Glide.with(mAdapter.context)
                    .load(CommonUtil.getImageUrl(model.getCoverUrl())).asBitmap()
                    .into(img);
            x.image().bind(avator, CommonUtil.getImageUrl(model.getUserImage()),CommonUtil.options);

        }


        @Override
        public void onClick(View v) {
            mAdapter.onItemHolderClick(this);
        }
    }
}
