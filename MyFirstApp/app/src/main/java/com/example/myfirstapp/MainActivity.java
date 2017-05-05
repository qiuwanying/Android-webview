package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private WebView webView;

    private Button back;
    private Button refresh;

    private TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView1);
        back = (Button) findViewById(R.id.back);
        refresh = (Button) findViewById(R.id.fresh);
        titleView = (TextView) findViewById(R.id.title);


        webView.loadUrl("http://www.baidu.com");

        webView.setWebViewClient(new WebViewClient(){
            // 通过webview 自己的方式去加载网页，否则会调用系统浏览器去加载

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });

        // 获取title
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                titleView.setText(title);
                super.onReceivedTitle(view, title);
            }
        });

        refresh.setOnClickListener(new MyListener());
        back.setOnClickListener(new MyListener());
    }

    // 给这两个按钮添加监听方法
    class MyListener  implements  View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.fresh:
                    webView.reload();
                    break;
                case R.id.back:
                    finish();
                    break;
                default:break;
            }
        }
    }

    // /** called when user click the send button */
    //    public void sendMessage(View view) {
    //        // do something
    //        Intent intent  = new Intent(this, DisplayMessageActivity.class);
    //        EditText editText = (EditText) findViewById(R.id.edit_message);
    //        String message = editText.getText().toString();
    //        intent.putExtra(EXTRA_MESSAGE, message);
    //        startActivity(intent);
    //    }
}



