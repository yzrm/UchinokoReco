package com.example.uchinokoreco.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import java.util.Collections;
import java.util.List;

public class PermissionUtil {

//    public static final List<String> GALLERY_REQUIRED_PERMISSIONS =
//            Collections.singletonList(Manifest.permission.)
//    /**
//     * ギャラリーを使用するための権限の有無確認用メソッド
//     * @param context Context
//     * @return 必要権限の強化が全てある場合にはtrue、それ以外はfalse
//     */
//    public static boolean galleryPermissionGranted(Context context){
//        boolean isGranted = true;
//        for (int i = 0; i < GALLERY_REQUIRED_PERMISSIONS.size(); i++ ) {
//            int permission = ContextCompat.checkSelfPermission(context, GALLERY_REQUIRED_PERMISSIONS.get(i));
//            if (permission != PackageManager.PERMISSION_GRANTED) {
//                isGranted = false;
//            }
//        }
//        return isGranted;
//    }
}
