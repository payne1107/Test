package test.wd.com.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import test.wd.com.demo.R;

/**
 * Created by 92457 on 2018/5/28.
 * 播放视频
 */

public class PlayVideoActivity extends FragmentActivity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        init();
    }


    private void init() {
        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSetting = webView.getSettings();
        webSetting.setDefaultTextEncodingName("utf-8") ;//这句话去掉也没事。。只是设置了编码格式
        webSetting.setJavaScriptEnabled(true);  //这句话必须保留。。不解释
        webSetting.setDomStorageEnabled(true);//这句话必须保留。。否则无法播放优酷视频网页。。其他的可以
        webView.setWebChromeClient(new WebChromeClient());//重写一下。有的时候可能会出现问题
        webView.setWebViewClient(new WebViewClient(){//不写的话自动跳到默认浏览器了。。跳出APP了。。怎么能不写？
            public boolean shouldOverrideUrlLoading(WebView view, String url) {//这个方法必须重写。否则会出现优酷视频周末无法播放。周一-周五可以播放的问题
                Log.d("Dong", "url ------<>" + url);
                if(url.startsWith("intent")|| url.startsWith("youku")){
                    return true;
                }else{
                    return super.shouldOverrideUrlLoading(view, url);
                }
            }
        });
        webView.loadUrl("http://www.jmzsjy.com/UploadFile/微课/地方风味小吃——宫廷香酥牛肉饼.mp4");
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (webView != null) {
                webView.onResume();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (webView != null) {
                webView.onPause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
