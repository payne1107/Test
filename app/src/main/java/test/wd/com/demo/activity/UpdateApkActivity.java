package test.wd.com.demo.activity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import test.wd.com.demo.R;
import util.UpdateAppUtils;

/**
 * Created by 92457 on 2018/3/29.
 * 更新版本
 */

public class UpdateApkActivity extends FragmentActivity implements View.OnClickListener {
    private static final int UPDATE_APK_CODE = 200;
    private Context mContext;

    String apkPath = "http://m.lieshoujianzhi.com/mobile/download";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_apk);
        mContext = this;
        TextView tvCheckVersion = (TextView) findViewById(R.id.tv_check_version);
        tvCheckVersion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_check_version:
                PermissionGen.with(UpdateApkActivity.this).addRequestCode(UPDATE_APK_CODE)
                        .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE).request();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = UPDATE_APK_CODE)
    public void applyUpdatePermissionSucess() {
        //申请权限成功
        //更新apk
        UpdateAppUtils.from(this)
                .serverVersionCode(2)
                .serverVersionName("2.0")
                .apkPath(apkPath)
                .updateInfo("1.修复若干bug\n2.美化部分页面\n3.增加微信支付方式")
//                .showNotification(false)
//                .needFitAndroidN(false)
                .isForce(false)
                .update();
    }

    @PermissionFail(requestCode = UPDATE_APK_CODE)
    public void applyUpdatePermissionFailed() {
        //申请权限失败
        Log.d("Dong", "呵呵呵 失败了吗");
    }
}
