package com.invessence.broker.util;

import java.io.*;
import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.jcraft.jsch.*;

public class CommonUtil {

	static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
	
	public static boolean todaysDateCompare(String lbDate){
		try {
			
			Date tDate =new Date();
			String td=sdf.format(tDate);
			
			System.out.println(lbDate+" : "+td);
			if(lbDate.equals(td)){
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	public void copyFiles(){
		try
		{
			JSch jsch = new JSch();

			Session session = null;
			session = jsch.getSession("abhangp", "207.97.205.90", 22);
			session.setPassword("T35t123");
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			ChannelSftp channel = null;
			channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();

			channel.cd("/data/ltam");

			InputStream in = channel.get("NewAccounts_20160107.csv");
			// set local file
			String lf = "D:\\\\Project\\\\Abhang\\\\temp\\\\OBJECT_FILE";
			FileOutputStream tergetFile = new FileOutputStream(lf);

			// read containts of remote file to local
			int c;
			while ( (c= in.read()) != -1 ) {
				tergetFile.write(c);
			}

			in.close();
		tergetFile.close();
//
//			String localfilepath="D:\\Project\\Abhang\\temp\\test.txt";
//			File localFile = new File(localfilepath);
//			//If you want you can change the directory using the following line.
//			String RemoteDirectoryPath="/data/ltam";
//			channel.cd(RemoteDirectoryPath);
//			channel.put(new FileInputStream(localFile), localFile.getName());
			channel.disconnect();
			session.disconnect();
		}catch (JSchException e)
		{
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SftpException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new CommonUtil().copyFiles();
}



	/*  To print strackTrace in log file	 */

	public static String stackTraceToString(StackTraceElement[] stackTrace) {
		StringWriter sw = new StringWriter();
		printStackTrace(stackTrace, new PrintWriter(sw));
		return sw.toString();
	}
	public static void printStackTrace(StackTraceElement[] stackTrace, PrintWriter pw) {
		for(StackTraceElement stackTraceEl : stackTrace) {
			pw.println(stackTraceEl);
		}
	}


	/*  To print all class object in console */
	public static String objectToString(Object o) {
		ArrayList<String> list = new ArrayList<String>();
		CommonUtil.objectToString(o, o.getClass(), list);
		return o.getClass().getName().concat(list.toString());
	}

	private static void objectToString(Object o, Class<?> clazz, List<String> list) {
		Field f[] = clazz.getDeclaredFields();
		AccessibleObject.setAccessible(f, true);
		for (int i = 0; i < f.length; i++) {
			try {
				list.add(f[i].getName() + "=" + f[i].get(o));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if (clazz.getSuperclass().getSuperclass() != null) {
			objectToString(o, clazz.getSuperclass(), list);
		}
	}
}
