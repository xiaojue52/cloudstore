package com.project.base;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class Constant {
	public static String host = "192.168.1.108";
	public static String addr = host+":8080";

	/**
	 * 采用http post方式访问服务器
	 * @param url
	 * @param pars
	 * @return
	 */
	public static String getData(String url, Map<String, String> pars) {
		// 创建请求对象
		HttpPost post;
		// 创建客户端对象
		HttpClient client;
		// 创建发送请求的对象
		HttpResponse response;
		// 创建接收返回数据的对象
		HttpEntity entity;
		// 创建流对象
		InputStream is;
		UrlEncodedFormEntity urlEntity;
		{
			HttpParams params = new BasicHttpParams();			
			/* 连接超时 */
			HttpConnectionParams.setConnectionTimeout(params, 2000);
			/* 请求超时 */	
			HttpConnectionParams.setSoTimeout(params, 4000);
			post = new HttpPost(url);
			client = new DefaultHttpClient(params);
			// 参数设置
			List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
			if (pars != null) {
				Iterator<Map.Entry<String, String>> iter = pars.entrySet()
						.iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> mEntry = (Map.Entry<String, String>) iter
							.next();
					pairs.add(new BasicNameValuePair(mEntry.getKey(), mEntry
							.getValue()));
				}
			}
			try {
				// 用UrlEncodedFormEntity来封装List对象
				urlEntity = new UrlEncodedFormEntity(pairs, "utf-8");
				// 设置使用的Entity
				post.setEntity(urlEntity);

				try {
					// 客户端开始向指定的网址发送请求
					response = client.execute(post);
					// 获得请求的Entity
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						entity = response.getEntity();
						is = entity.getContent();
						// 下面是读取数据的过程
						BufferedReader br = new BufferedReader(
								new InputStreamReader(is));
						String line = null;
						StringBuffer sb = new StringBuffer();
						while ((line = br.readLine()) != null) {
							sb.append(line);
						}
						System.out.println(sb.toString());
						// Toast.makeText(context, sb.toString(),
						// Toast.LENGTH_SHORT).show();
						return sb.toString();
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 暂时未用到，用来分析文件编码格式
	 * @param str_filepath
	 * @return
	 * @throws Exception
	 */
	public static String convertCodeAndGetText(String str_filepath) throws Exception {// 转码

		File file = new File(str_filepath);
		BufferedReader reader;
		String text = "";
		// FileReader f_reader = new FileReader(file);
		// BufferedReader reader = new BufferedReader(f_reader);
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream in = new BufferedInputStream(fis);
		in.mark(4);
		byte[] first3bytes = new byte[3];
		in.read(first3bytes);// 找到文档的前三个字节并自动判断文档类型。
		in.reset();
		if (first3bytes[0] == (byte) 0xEF && first3bytes[1] == (byte) 0xBB
				&& first3bytes[2] == (byte) 0xBF) {// utf-8

			reader = new BufferedReader(new InputStreamReader(in, "utf-8"));

		} else if (first3bytes[0] == (byte) 0xFF
				&& first3bytes[1] == (byte) 0xFE) {

			reader = new BufferedReader(new InputStreamReader(in, "unicode"));
		} else if (first3bytes[0] == (byte) 0xFE
				&& first3bytes[1] == (byte) 0xFF) {

			reader = new BufferedReader(new InputStreamReader(in, "utf-16be"));
		} else if (first3bytes[0] == (byte) 0xFF
				&& first3bytes[1] == (byte) 0xFF) {

			reader = new BufferedReader(new InputStreamReader(in, "utf-16le"));
		} else {

			reader = new BufferedReader(new InputStreamReader(in, "GBK"));
		}
		String str = reader.readLine();

		while (str != null) {
			text = text + str + "/n";
			str = reader.readLine();

		}
		reader.close();

		return text;
	}
}
