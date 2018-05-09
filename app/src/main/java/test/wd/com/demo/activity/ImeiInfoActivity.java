package test.wd.com.demo.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Method;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import test.wd.com.demo.R;

/**
 * Created by 92457 on 2018/4/23.
 */

public class ImeiInfoActivity extends FragmentActivity {

    private TextView tvImeiId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imei_info);
        tvImeiId = (TextView) findViewById(R.id.tv_imei_id);
        PermissionGen.with(ImeiInfoActivity.this)
                .addRequestCode(1200)
                .permissions(
                        Manifest.permission.READ_PHONE_STATE
                )
                .request();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 1200)
    public void openContact() {
        Log.d("Dong", "成功了吗--->" );
        tvImeiId.setText("IMEI:"+getPhoneImsiNum(this));
    }

    @PermissionFail(requestCode = 1200)
    public void failContact() {
        Log.d("Dong", "失败了吗--->" );
        tvImeiId.setText("IMEI: 没有权限");
    }

    public static String getPhoneImsiNum(Context context) {
        int subId1 = -1;
        int subId2 = -1;
        String imsi1 = null;
        String imsi2 = null;
        try {
            TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //大于等于Android 5.0 L版本
                Method getSubscriberId = tManager.getClass().getMethod("getSubscriberId", int.class);

                ContentResolver contentResolver = context.getContentResolver();
                Cursor c = contentResolver.query(Uri.parse("content://telephony/siminfo"), new String[]{"_id"}, "sim_id" + " = ?", new String[]{"0"}, null);
                if (null != c && c.moveToFirst()) {
                    subId1 = c.getInt(c.getColumnIndexOrThrow("_id"));
                    Log.d("PhoneUtil", "subId1:" + subId1);
                    c.close();
                }

                c = contentResolver.query(Uri.parse("content://telephony/siminfo"), new String[]{"_id"}, "sim_id" + " = ?", new String[]{"1"}, null);
                if (null != c && c.moveToFirst()) {
                    subId2 = c.getInt(c.getColumnIndexOrThrow("_id"));
                    Log.d("PhoneUtil", "subId2:" + subId2);
                    c.close();
                }

                if (subId1 > 0) {
                    imsi1 = (String) getSubscriberId.invoke(tManager, subId1);
                }
                if (subId2 > 0) {
                    imsi2 = (String) getSubscriberId.invoke(tManager, subId2);
                }

                if (!TextUtils.isEmpty(imsi1) && !TextUtils.isEmpty(imsi2)) {
                    return imsi1 + "," + imsi2;
                } else {
                    if (!TextUtils.isEmpty(imsi1)) {
                        return imsi1;
                    } else {
                        return imsi2;
                    }
                }
            } else { //Android 5.0以下的api获取ismi方法 sdk < 21
                return tManager.getDeviceId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "获取串号失败";
    }
}
