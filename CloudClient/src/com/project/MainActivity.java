package com.project;

import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.cloudclient.R;
import com.google.gson.Gson;
import com.project.base.BaseActivity;
import com.project.base.Constant;
import com.project.structure.User;
/**
 * 登陆界面
 * @author Administrator
 *
 */
public class MainActivity extends BaseActivity{
	private Context context;
	private EditText username_et,password_et;
	private MainApplication app;
	

	@Override
	protected void onCreate(Bundle b){
		super.onCreate(b);
		this.setContentView(R.layout.main);
		username_et = (EditText) this.findViewById(R.id.username_edit);
		password_et = (EditText)this.findViewById(R.id.password_edit);
		context =this;
		app = (MainApplication)this.getApplication();
	}
	
	private ProgressDialog pd;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message mes){
			pd.dismiss();
			String ret = mes.getData().getString("data");
			if(ret==null){
				Toast.makeText(context, "网络错误！", Toast.LENGTH_LONG).show();			
			}else{
				if(ret.equals("-1")){
					Toast.makeText(context, "用户名、密码错误！", Toast.LENGTH_LONG).show();
				}else{
					User user = new Gson().fromJson(ret, User.class);
					app.setUser(user);

					Intent it = new Intent(context,OpenFileActivity.class);
					startActivity(it);
				}
			}
		}
	};
	public void signin(View v){
		Intent it = new Intent(this,SigninActivity.class);
		startActivity(it);
	}
	String username;
	String password;
	public void submit(View v){
		username = username_et.getText().toString();
		password = password_et.getText().toString();
		if (username.length()==0||password.length()==0){
			Toast.makeText(context, "用户名、密码不能为空！", Toast.LENGTH_LONG).show();
			return;
		}
		pd = ProgressDialog.show(this, "登录中", "请稍后。。。。。。");
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String url = "http://"+Constant.addr+"/CloudServer/login.action";
				Map<String, String> pars = new HashMap<String, String>();
				pars.put("user.name", username);
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
