package com.nxnd.travelnote.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nxnd.travelnote.activity.EditActivity;
import com.nxnd.travelnote.activity.LocationActivity;
import com.nxnd.travelnote.activity.MainActivity;
import com.nxnd.travelnote.activity.StepActivity;
import com.nxnd.travelnote.model.TravelNotesModel;
import com.nxnd.travelnote.service.TravelNotesService;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.nxnd.travelnote.R;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.nxnd.travelnote.activity.StepActivity.REQUEST_CODE_LOCATION;

/**
 * Created by huchuan
 */
public class EditFragment extends Fragment {

    private static final int REQUEST_CODE_EDIT = 866;
    @BindView(R.id.mynote_topbar)
    QMUITopBar mTopBar;

    @BindView(R.id.newnote)
    QMUIRoundButton newNote;

    @BindView(R.id.nonote)
    LinearLayout noNote;

    @BindView(R.id.hasnote)
    LinearLayout hasNote;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        ButterKnife.bind(this,view);
        mTopBar.setTitle("我的日记");
        this.refresh();
        return view;
    }


    @OnClick(R.id.newnote)
    public void onClickNewNote() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getActivity());
        builder.setTitle("日记标题")
                .setPlaceholder("在此输入日记标题")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        String text = builder.getEditText().getText().toString();
                        TravelNotesService.createNote(text,getActivity());
                        refresh();
                        enterEdit();
                        dialog.dismiss();
                    }
                })
                .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();
    }

    @OnClick(R.id.hasnote)
    public void onClickHasNote(){
        enterEdit();
    }

    private void enterEdit(){
        //进入编辑
        Intent i=new Intent(getActivity(),EditActivity.class);
        startActivity(i);
        startActivityForResult(i, REQUEST_CODE_EDIT);//打开编辑页面
    }

    //刷新页面
    private void refresh(){
        TravelNotesModel travelNotesModel = TravelNotesService.getNoteInfo(getActivity());
        if (travelNotesModel.getTitle()!=null){
            Log.d("editF","有日记，名为"+travelNotesModel.getTitle());
            noNote.setVisibility(View.GONE);
            hasNote.setVisibility(View.VISIBLE);
        }else {
            Log.d("editF","没有日记");
            hasNote.setVisibility(View.GONE);
            noNote.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_EDIT:
                refresh();
                break;
        }
    }
}
