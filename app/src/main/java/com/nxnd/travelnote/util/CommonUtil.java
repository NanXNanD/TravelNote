package com.nxnd.travelnote.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nxnd.travelnote.R;

import org.xutils.image.ImageOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by huchuan on 2018/12/29.
 */

public class CommonUtil {

    //图片加载Option
    public  static ImageOptions options = new ImageOptions.Builder().
            setUseMemCache(true).
            setSize(1920,1080).
            setConfig(Bitmap.Config.RGB_565).
            setIgnoreGif(false).
            setPlaceholderScaleType(ImageView.ScaleType.CENTER_CROP).
            setImageScaleType(ImageView.ScaleType.CENTER_CROP).
            setLoadingDrawableId(R.drawable.image_loading).
            setFailureDrawableId(R.drawable.image_fail).
            build();

    //图片url转换
    public static String getImageUrl(String originUrl){
        if(originUrl.length()>10&&originUrl.substring(1,10).equals("NxndDiary")){
            return "http://39.105.175.150:8080"+originUrl;
        }else {
            return originUrl;
        }
    }

    //获取当前时间
    public static String getCurrTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
        String date = format.format(calendar.getTime());
        return date;
    }

    //获取当前日期
    public static String getCurrDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String date = format.format(calendar.getTime());
        return date;
    }

//    //转换时间格式
//    public static String changeTimeFormat(String oldS){
//        String newS;
//
//        return newS;
//    }
    //转换日期格式

}
