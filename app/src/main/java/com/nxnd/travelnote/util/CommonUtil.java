package com.nxnd.travelnote.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import org.xutils.image.ImageOptions;

/**
 * Created by huchuan on 2018/12/29.
 */

public class CommonUtil {

    //图片加载Option
    public  static ImageOptions options = new ImageOptions.Builder().
            setUseMemCache(true).
            setConfig(Bitmap.Config.RGB_565).
            setIgnoreGif(false).
            setPlaceholderScaleType(ImageView.ScaleType.CENTER_CROP).
            setImageScaleType(ImageView.ScaleType.CENTER_CROP).
            build();

    //图片url转换
    public static String getImageUrl(String originUrl){
        if(originUrl.length()>10&&originUrl.substring(1,10).equals("NxndDiary")){
            return "http://39.105.175.150:8080"+originUrl;
        }else {
            return originUrl;
        }
    }


}
