<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String filename = request.getParameter("filename");//"tzhsymd.txt";    
	String filepath = request.getParameter("filepath");//"路径";    
	response.setContentType("application/octet-stream;charset=utf-8");
	String tempName = URLEncoder.encode(filename,"UTF-8");
	response.setHeader("Content-Disposition", "attachment;filename = "
			+ tempName);		
	DataInputStream reader = new DataInputStream(new FileInputStream(
			filepath + filename));
	DataOutputStream output = new DataOutputStream(response
			.getOutputStream());
	//BufferedWriter fos =new BufferedWriter(new OutputStreamWriter(response.getOutputStream(),"utf-8"));
	int length = 0;
	byte[] inputByte = new byte[1024];
	while ((length = reader.read(inputByte, 0, inputByte.length)) > 0) {
		output.write(inputByte, 0, length);
	}
	output.flush();
	output.close();
	out.clear();
	out = pageContext.pushBody();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	</head>
	<body>
	</body>
</html>
