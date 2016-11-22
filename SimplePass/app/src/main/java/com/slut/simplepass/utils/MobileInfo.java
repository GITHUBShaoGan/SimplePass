package com.slut.simplepass.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;

import com.slut.simplepass.App;

import static android.content.Context.FINGERPRINT_SERVICE;

/**
 * Created by 七月在线科技 on 2016/11/22.
 */

public class MobileInfo {

    /**
     * 判断手机是否支持指纹识别
     *
     * @return
     */
    public static boolean isFingerPrintSupport() {
        boolean flag = false;
        try {
            FingerprintManager manager = (FingerprintManager) App.getContext().getSystemService(FINGERPRINT_SERVICE);
            if (ActivityCompat.checkSelfPermission(App.getContext(), Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    flag = manager.hasEnrolledFingerprints();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            return flag;
        }
    }

}
