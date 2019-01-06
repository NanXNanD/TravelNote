package com.nxnd.travelnote.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;
import com.nxnd.travelnote.R;

import org.json.JSONObject;
import org.xutils.image.ImageOptions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.R.attr.radius;

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
}
