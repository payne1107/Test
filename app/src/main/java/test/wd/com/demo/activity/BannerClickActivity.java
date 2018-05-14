package test.wd.com.demo.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import test.wd.com.demo.R;

/**
 * Created by 92457 on 2018/5/4.
 */

public class BannerClickActivity extends FragmentActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private String bannerUrl = "http://m.lieshoujianzhi.com/ceshi.html";
//    private String info = "711" + "," + "Rabbit-eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNTg1NjUwNTQ0MSIsImV4cCI6MTUyNjM3NzU3MCwiaWF0IjoxNTI1NzcyNzcwfQ.-E3zuku8ahqh0N0ClPb9rOU7oXKZ0JZLu8WAihX72vzUXGNwp-dP8McUGF00nakEyWkdrQ5AdNEZkIP0XK25ag" +
//            "," + "15856505441" + "," + getAppVersionName();

    private String info = "" + "," + "" +
            "," + "" + "," + getAppVersionName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
    }


    @Override
    protected void onResume() {
        super.onResume();
        initWebView();
    }

    public void initWebView() {
        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JSHook(), "hello");

        settings.setSupportZoom(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDefaultTextEncodingName("GBK");
        settings.setLoadsImagesAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // settings.set
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                Log.d("Dong", "shouldOverrideUrlLoading_url=" + url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
                                       @Override
                                       public void onReceivedIcon(WebView view, Bitmap icon) {
                                           super.onReceivedIcon(view, icon);
                                       }

                                       @Override
                                       public void onGeolocationPermissionsHidePrompt() {
                                           super.onGeolocationPermissionsHidePrompt();
                                       }

                                       @Override
                                       public void onGeolocationPermissionsShowPrompt(final String origin, final GeolocationPermissions.Callback callback) {
                                           callback.invoke(origin, true, false);
                                           super.onGeolocationPermissionsShowPrompt(origin, callback);
                                       }

                                       @Override
                                       public void onProgressChanged(WebView view, int newProgress) {
                                           if (newProgress == 100) {
                                               progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                                           } else {
                                               progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                                               progressBar.setProgress(newProgress);//设置进度值
                                           }
                                       }
                                   }
        );
        webView.loadUrl(bannerUrl);
    }

    public class JSHook{
        @JavascriptInterface
        public void javaMethod(String p){
            Log.d("Dong" , "JSHook.JavaMethod() called! + " + p);
        }
        @JavascriptInterface
        public void showAndroid(int clickType){
            Log.d("Dong" , "JshowAndroid + ---->" +clickType);
            runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    webView.loadUrl("javascript:show('"+info+"')");
                }
            });
        }
        @JavascriptInterface
        public void showAndroid(){
            Log.d("Dong" , "JshowAndroid + ----> showAndroid" );
            runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    webView.loadUrl("javascript:show('"+info+"')");
                }
            });
        }
        public String getInfo(){
            return "获取手机内的信息！！";
        }

        @JavascriptInterface
        public void share(final String id) {
            Log.d("Dong", "id ===" + id);
        }
    }




    /**
     * 获取软件当前版本信息
     * @return
     */
    public String getAppVersionName() {
        try {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager().getPackageInfo(pkName, 0).versionName;
            return versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1.0";
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
