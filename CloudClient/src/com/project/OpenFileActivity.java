// filename: OpenFileDemo.java
package com.project;

import java.util.HashMap;
import java.util.Map;
import com.cloudclient.R;
import com.project.base.BaseActivity;
import com.project.base.Constant;
import com.project.socket.SocketClient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 上传界面
 * @author Administrator
 *
 */
public class OpenFileActivity extends BaseActivity {

	static private int openfileDialogId = 0;
	private Context context;
	private MainApplication app;
	private String name;
	private TextView path_tv;
	private String path;
	private Spinner spinner;
	private TextView rebuy_tv;
	private Button rebuy_btn;

	private void setText(String arg1,String arg2){
		this.rebuy_tv.setText(arg1);
		this.rebuy_btn.setText(arg2);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_open_file);
		this.context = this;
		this.app = (MainApplication) this.getApplication();
		this.path_tv = (TextView) this.findViewById(R.id.path);
		this.rebuy_tv = (TextView)this.findViewById(R.id.rebuy_vip_details);
		this.rebuy_btn = (Button)this.findViewById(R.id.open_buy);
		if(app.getUser().vip!=null&&app.getUser().vip.equals("1"))
			this.setText("您是vip", "续费");
		else
			this.setText("您还不是vip", "购买");
		// 设置单击按钮时打开文件对话框
		findViewById(R.id.button_openfile).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						showDialog(openfileDialogId);
					}
				});

		this.checkVip();

	}
    /**
     * 检查是否是VIp
     */
	private void checkVip() {
		SharedPreferences settings = context.getSharedPreferences("info", 0);
		String loged = settings.getString(app.getUser().name, "0");
		if (!(loged.equals("1") || ((app.getUser().vip != null
				&& app.getUser().vip.equals("1"))))) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
						//Thread.sleep(1000);

						Message mes = Message.obtain();
						mes.what = 0;
						handler.sendMessage(mes);

				}

			}).start();
			SharedPreferences.Editor editor = settings.edit();
			editor.putString(app.getUser().name, "1");
		      // Commit the edits!
		    editor.commit();
		}
		
	}

	//private PopupWindow mPopupWindow;
    /**
     * 显示购买VIP界面
     * @param v
     */
	public void showPop(View v) {
		//AlertDialog.Builder builder = new AlertDialog.Builder(this); 
		/*View popupView = getLayoutInflater().inflate(R.layout.buyvip, null);
		mPopupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, true);
		mPopupWindow.setTouchable(true);
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		//mPopupWindow.showAsDropDown(path_tv);
		mPopupWindow.showAtLocation(this.findViewById(R.id.open_file_div), Gravity.CENTER|Gravity.CENTER, 0, 0);
		Button buyvip_btn = (Button) popupView.findViewById(R.id.buyvip_btn);
		spinner = (Spinner)popupView.findViewById(R.id.spinner_vip);
		String[] mItems = {"1个月","6个月","1年"};
		ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
		spinner.setAdapter(_Adapter);
		buyvip_btn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				buyVip();
			}

		});*/
		AlertDialog.Builder builder = new AlertDialog.Builder(this); 
		View popupView = getLayoutInflater().inflate(R.layout.buyvip, null);
		spinner = (Spinner)popupView.findViewById(R.id.spinner_vip);
		String[] mItems = {"1个月","6个月","1年"};
		ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
		spinner.setAdapter(_Adapter);
		builder.setTitle("购买VIP");
		builder.setView(popupView);
		/*builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) { 
				buyVip();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			} 
			
		});*/
		final AlertDialog dialog= builder.create();
		dialog.show();
		Button buyvip_btn = (Button) popupView.findViewById(R.id.buyvip_btn);
		buyvip_btn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				buyVip();
				dialog.dismiss();
			}

		});
		Button cancel_buyvip_btn = (Button)popupView.findViewById(R.id.cancel_buyvip_btn);
		cancel_buyvip_btn.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
			
		});
		
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == openfileDialogId) {
			Map<String, Integer> images = new HashMap<String, Integer>();
			// 下面几句设置各文件类型的图标， 需要你先把图标添加到资源文件夹
			images.put(OpenFileDialog.sRoot, R.drawable.filedialog_root); // 根目录图标
			images.put(OpenFileDialog.sParent, R.drawable.filedialog_folder_up); // 返回上一层的图标
			images.put(OpenFileDialog.sFolder, R.drawable.filedialog_folder); // 文件夹图标
			images.put("wav", R.drawable.filedialog_wavfile); // wav文件图标
			images.put(OpenFileDialog.sEmpty, R.drawable.filedialog_root);
			images.put("default", R.drawable.fileimage);
			Dialog dialog = OpenFileDialog.createDialog(id, this, "打开文件",
					new CallbackBundle() {
						@Override
						public void callback(Bundle bundle) {
							path = bundle.getString("path");
							path_tv.setText(path);
							name = bundle.getString("name");
						}
					}, ".wav;", images);
			return dialog;
		}
		return null;
	}

	private ProgressDialog pd;
	/**
	 * 次handle用来处理线程发送过来得数据，更新UI
	 */
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message mes) {
			switch(mes.what){
			case 0:
				showPop(null);
				break;
			case 1:
				pd.dismiss();
				String ret1 = mes.getData().getString("data");
				if (ret1 == null) {
					Toast.makeText(context, "网络错误！", Toast.LENGTH_LONG).show();
				} else if (ret1.equals("-1")) {
					Toast.makeText(context, "更新成功！", Toast.LENGTH_LONG).show();
				} else if (ret1.equals("0")) {
					Toast.makeText(context, "写文件失败，网络是否断开，文件是否可读！",
							Toast.LENGTH_LONG).show();
				} else
					Toast.makeText(context, "上传成功！", Toast.LENGTH_LONG).show();
				break;
			case 2:
				pd.dismiss();
				String ret2 = mes.getData().getString("data");
				if (ret2 == null) {
					Toast.makeText(context, "网络错误！", Toast.LENGTH_LONG).show();
				} else {
					if (ret2.equals("-1")) {
						Toast.makeText(context, "操作失败！", Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(context, "购买成功！", Toast.LENGTH_LONG).show();
						//mPopupWindow.dismiss();
						setText("您是vip", "续费");
					}
				}
				break;
				default:
					break;
			}
		}
	};

	public void submit(View v) {
		pd = ProgressDialog.show(context, "上传中", "请稍后。。。。。。");
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SocketClient so = new SocketClient(name, path);
				String ret = null;
				if (so.send() == -1) {
					ret = "0";
				} else {
					try {
						String url = "http://" + Constant.addr
								+ "/CloudServer/addCFile.action";
						Map<String, String> pars = new HashMap<String, String>();
						pars.put("doc.user.id", app.getUser().id.toString());
						pars.put("doc.name", name);
						ret = Constant.getData(url, pars);
					} catch (Exception e) {
						e.printStackTrace();
						ret = null;
					}
				}
				Message mes = Message.obtain();
				Bundle b = new Bundle();
				b.putString("data", ret);
				
				mes.setData(b);
				mes.what = 1;
				handler.sendMessage(mes);
			}

		}).start();
	}

	private void buyVip() {
		pd = ProgressDialog.show(context, "购买VIP", "请稍后。。。。。。");
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String url = "http://" + Constant.addr
						+ "/CloudServer/getVip.action";

				Map<String, String> pars = new HashMap<String, String>();
				pars.put("user.id", app.getUser().id.toString());
				String ret = Constant.getData(url, pars);
				Message mes = Message.obtain();
				Bundle b = new Bundle();
				b.putString("data", ret);
				mes.setData(b);
				mes.what=2;
				handler.sendMessage(mes);
			}

		}).start();
	}
}
