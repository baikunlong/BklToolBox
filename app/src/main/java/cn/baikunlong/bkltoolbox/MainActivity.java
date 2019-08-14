package cn.baikunlong.bkltoolbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.baikunlong.bkltoolbox.activity.BaseActivity;
import cn.baikunlong.bkltoolbox.activity.DomainNameActivity;

public class MainActivity extends BaseActivity {

    private static final int[] TOOL_IMAGES = new int[]{
            R.drawable.ic_domain_name,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,};

    private static final String[] TOOL_NAMES = new String[]{
            "查询域名",
            "等待开发",
            "等待开发",
            "等待开发",
            "等待开发"};

    private GridView gv_main;

    protected void initView() {
        gv_main = (GridView) findViewById(R.id.gv_main);
    }

    protected void initData() {
        gv_main.setAdapter(new BaseAdapter() {
            class ViewHolder {
                public View rootView;
                public ImageView iv_tool;
                public TextView tv_name;

                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.iv_tool = (ImageView) rootView.findViewById(R.id.iv_tool);
                    this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
                }

            }

            @Override
            public int getCount() {
                return TOOL_IMAGES.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(MainActivity.this, R.layout.item_main_gv, null);
                }
                ViewHolder holder = new ViewHolder(convertView);
                holder.iv_tool.setImageResource(TOOL_IMAGES[position]);
                holder.tv_name.setText(TOOL_NAMES[position]);
                return convertView;
            }
        });
    }

    protected void initEvent() {
        gv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (TOOL_NAMES[position]) {
                    case "查询域名":
                        startActivity(new Intent(MainActivity.this, DomainNameActivity.class));
                        break;
                    case "查询域名2":

                        break;
                    case "查询域名22":

                        break;
                    case "查询域名23":

                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}
