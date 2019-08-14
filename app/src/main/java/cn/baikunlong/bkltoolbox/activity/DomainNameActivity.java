package cn.baikunlong.bkltoolbox.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import cn.baikunlong.bkltoolbox.R;
import cn.baikunlong.bkltoolbox.bean.DomainName;
import cn.baikunlong.bkltoolbox.utils.NetUtils;
import okhttp3.FormBody;

public class DomainNameActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_domain_name;
    private Button btn_query;
    private Button btn_empty;
    private Button btn_usable;
    private ListView lv_domains;
    private BaseAdapter adapter;

    /**
     * 是否只显示可用
     */
    private boolean isOnlyUsable=false;

    @Override
    protected void initView() {
        et_domain_name = (EditText) findViewById(R.id.et_domain_name);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_query.setOnClickListener(this);
        btn_empty = (Button) findViewById(R.id.btn_empty);
        btn_empty.setOnClickListener(this);
        btn_usable = (Button) findViewById(R.id.btn_usable);
        btn_usable.setOnClickListener(this);
        lv_domains = (ListView) findViewById(R.id.lv_domains);
    }

    private ArrayList<DomainName> domainNames = new ArrayList<>();

    @Override
    protected void initData() {
        adapter = new BaseAdapter() {
            class ViewHolder {
                public View rootView;
                public TextView tv_name;
                public TextView tv_status;

                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
                    this.tv_status = (TextView) rootView.findViewById(R.id.tv_status);
                }

            }

            @Override
            public int getCount() {
                return domainNames.size();
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
                    convertView = View.inflate(DomainNameActivity.this, R.layout.item_domain_name_lv, null);
                }
                ViewHolder holder = new ViewHolder(convertView);
                holder.tv_name.setText(domainNames.get(position).getName());
                switch (domainNames.get(position).getStatus()) {
                    case -1:
                        holder.tv_status.setText("获取失败");
                        holder.tv_status.setTextColor(Color.RED);
                        break;
                    case 0:
                        holder.tv_status.setText("查询中.........");
                        holder.tv_status.setTextColor(Color.GRAY);
                        break;
                    case 1:
                        holder.tv_status.setText("可用");
                        holder.tv_status.setTextColor(Color.parseColor("#FF4CAF50"));
                        break;
                    case 2:
                        holder.tv_status.setText("不可用");
                        holder.tv_status.setTextColor(Color.GRAY);
                        break;
                }
                return convertView;
            }
        };
        lv_domains.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_domain_name;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_query) {
            domainNames.clear();
            submit();
        }
        if (v.getId() == R.id.btn_empty) {
            et_domain_name.setText("");
        }
        if (v.getId() == R.id.btn_usable) {
            isOnlyUsable=!isOnlyUsable;
            for (int i = 0; i < domainNames.size(); i++) {
                if(domainNames.get(i).getStatus()==2){
                    domainNames.remove(domainNames.get(i));
                    i=0;
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    private void submit() {
        // validate
        String name = et_domain_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入域名", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] strings = name.split("\n");
        for (int i = 0; i < strings.length; i++) {
            String trim = strings[i].trim();
            DomainName domainName = new DomainName(trim, 0);
            domainNames.add(domainName);
            queryData(trim, domainName);
        }
        adapter.notifyDataSetChanged();
    }

    private void queryData(final String name, final DomainName domainName) {
        thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String data = "{\"domainName\":\"" + name + "\",\"tldList\":[\".cn\"]}";
                    FormBody body = new FormBody.Builder().add("action", "searchDomain").add("data", data).build();
                    JSONObject jsonObject = NetUtils.post(body);
                    if (jsonObject != null) {
                        if (jsonObject.optInt("code") == 0) {
                            if (jsonObject.optJSONObject("data").optBoolean(name+".cn")) {
                                //可用
                                domainName.setStatus(1);
                            } else {
                                //不可用
                                domainName.setStatus(2);
                                //如果只显示可用则删了
                                if(isOnlyUsable){
                                    domainNames.remove(domainName);
                                }
                            }
                            return;
                        }
                    }
                    //获取出错
                    domainName.setStatus(-1);
                } finally {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }
}
