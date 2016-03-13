package net.dzikoysk.server.util.io;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeoutException;

public class IOUtils {
	
	public static File initizalize(File file, boolean b){
		if(!file.exists()){
			try {
				file.getParentFile().mkdirs();
				if(b) file.createNewFile();
				else file.mkdir();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public static String getContent(String s){
		String body = null;
		try {
			URL url = new URL(s);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			body = IOUtils.toString(in, encoding);
			in.close();
		} catch (TimeoutException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return body;
	}
	
	public static String getContent(File file){
		StringBuilder sb = new StringBuilder();
	    try{
	    	if(!file.exists()){
	    		file.getParentFile().mkdirs();
				file.createNewFile();
	    	}	
	    	BufferedReader br = new BufferedReader(new FileReader(file));
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        br.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
		}
	    return sb.toString();
	}
	
	public static void delete(File f) {
		if(!f.exists()) return;
		if (f.isDirectory())
			for (File c : f.listFiles()) delete(c);
		if (!f.delete())
			try {
				throw new FileNotFoundException("Failed to delete file: " + f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}

	public static String toString(InputStream in, String encoding) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[8192];
		int len = 0;
		while ((len = in.read(buf)) != -1)
		    baos.write(buf, 0, len);
		return new String(baos.toByteArray(), encoding);
	}

}
