package com.constant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.google.gson.Gson;

public class Constant {
	public static void flush(Object dataMap) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			Gson gson = new Gson();
			String jsonString = gson.toJson(dataMap);
			out.println(jsonString);
			out.flush();
			out.close();
			System.out.println(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Query setParameters(Query arg, Map<String, Object> parameters) {
		Query query = arg;
		if (parameters == null)
			return query;
		Iterator<Map.Entry<String, Object>> iter = parameters.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Object> mEntry = (Map.Entry<String, Object>) iter
					.next();
			Object obj = (Object) mEntry.getValue();
			String key = (String) mEntry.getKey();
			query.setParameter(key, obj);
		}
		return query;
	}
	public static void writeFileToDir(String path,byte data) throws IOException{
		FileWriter fw = null; 
		File file = new File(path);
		
		file.createNewFile();
		fw = new FileWriter(path); 
		fw.write(data);
		fw.close();
	}
	public static String getCurrentDataString(String format){
		DateFormat df = new SimpleDateFormat(format);
		final Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		return df.format(date);	}
}
