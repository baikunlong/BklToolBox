
package cn.baikunlong.bkltoolbox.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/**
 * BaseActivity 所有Activity都是继承此类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        initView();
        initData();
        initEvent();
    }

    protected Handler handler=new Handler();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化事件
     */
    protected abstract void initEvent();

    /**
     * @return 对应的布局文件id
     */
    protected abstract int getLayoutId();


    private ProgressDialog dialog;

    public void showDialog() {
        if (dialog == null) {
            dialog = ProgressDialog.show(this, "请稍后", "加载中...", true, false);
        } else {
            dialog.show();
        }
    }

    public void cancelDialog() {
        if (dialog != null) {
            dialog.cancel();
        }
    }

    public void thread(Runnable runnable){
        new Thread(runnable).start();
    }

}
