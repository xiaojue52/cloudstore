package com.project;

import java.util.HashMap;
import java.util.Map;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.cloudclient.R;
import com.project.base.BaseActivity;
import com.project.base.Constant;
/**
 * 注册用户
 * @author Administrator
 *
 */
public class SigninActivity extends BaseActivity{
	private Context context;
	private EditText username_et,password_et;
	@Override
	protected void onCreate(Bundle b){
		super.onCreate(b);
		this.setContentView(R.layout.signin);
		this.context = this;
		this.username_et = (EditText)this.findViewById(R.id.signin_username_edit);
		this.password_et = (EditText)this.findViewById(R.id.signin_password_edit);
	}
	private ProgressDialog pd;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message mes){
			String ret = mes.getData().getString("data");
			if(ret==null){
				Toast.makeText(context, "网络错误！", Toast.LENGTH_LONG).show();			
			}else{
				if(ret.equals("-1")){
					Toast.makeText(context, "用户已存在！", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(context, "注册成功！", Toast.LENGTH_LONG).show();
					finish();
				}
			}
			pd.dismiss();
		}
	};
	public void submit(View v){
		final String name = this.username_et.getText().toString();
		final String password = this.password_et.getText().toString();
		if (name.length()==0||password.length()==0){
			Toast.makeText(context, "用户名、密码不能为空！", Toast.LENGTH_LONG).show();
			return;
		}
		pd = ProgressDialog.show(context, "注册", "请稍后。。。。。。");
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String url = "http://"+Constant.addr+"/CloudServer/addUser.action";

				Map<String, String> pars = new HashMap<String, String>();
				pars.put("user.name", name);
				pars.put("user.password", password);
				String ret = Constant.getData(url, pars);
				Message mes = Message.obtain();
				Bundle b = new Bundle();
				b.putString("data", ret);
				mes.setData(b);
				handler.sendMessage(mes);
			}
			
		}).start();
	}

}
