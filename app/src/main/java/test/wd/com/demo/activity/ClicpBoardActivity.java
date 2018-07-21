package test.wd.com.demo.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import test.wd.com.demo.R;

/**
 * Created by 92457 on 2018/6/1.
 */

public class ClicpBoardActivity extends FragmentActivity{
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicpboard);
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）
//        ClipData clipData = ClipData.newPlainText(null, "需要复制的文本数据");
//        // 把数据集设置（复制）到剪贴板
//        clipboard.setPrimaryClip(clipData);
        ClipData clipData = clipboard.getPrimaryClip();

        if (clipData != null && clipData.getItemCount() > 0) {
            // 从数据集中获取（粘贴）第一条文本数据
            CharSequence text = clipData.getItemAt(0).getText();
//            if (text.toString().equals("123")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(text.toString());
                builder.setMessage(text.toString());
                builder.create().show();
                Toast.makeText(this,"123",Toast.LENGTH_SHORT).show();
                //清空面板数据
            clipboard.setPrimaryClip(ClipData.newPlainText(null, ""));
//            }
            Log.d("Dong", "----------------------------------->" + text);
            System.out.println("text: " + text);
        }
    }
}
