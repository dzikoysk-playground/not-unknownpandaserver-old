package net.dzikoysk.panda.util.configuration.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class ConfigurationUtils {
	
	public static String getContent(File file){
		StringBuilder sb = new StringBuilder();
	    try{
	    	if(!file.exists()){
	    		file.getParentFile().mkdirs();
				file.createNewFile();
	    	}
	    	List<String> list = Arrays.asList(getLines(file));
	    	for(String s : list){
	    		sb.append(s);
	    		sb.append(System.lineSeparator());
	    	}
	    } catch (IOException e) {
	    	e.printStackTrace();
		}
	    return sb.toString();
	}

	public static String[] getLines(File file){
		try{
			if(!file.exists()){
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
	    	String[] ss = new String[countLines(file.getPath()) + 1];
	    	BufferedReader br = new BufferedReader(new FileReader(file));
	        String line = br.readLine();
	        int i = 0;
	        while (line != null) {
	            ss[i] = line;
	            line = br.readLine();
	            i++;
	        }
	        br.close();
	        return ss;
	    } catch (IOException e) {
	    	e.printStackTrace();
		}
		return null;
	}
	
	public static int countLines(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}
	
	public static String getPath(Stack<String> stack){
		StringBuilder sb = new StringBuilder();
		if(stack == null || stack.isEmpty()) return null;
		Iterator<String> it = stack.iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key == null || key.isEmpty()) continue;
			sb.append(".");
			sb.append(key);
		}
		String path = sb.toString();
		if(path.length() > 0) path = path.substring(1);
		return path;
	}
	
	public static int getTabs(String s){
		 if (s.isEmpty())  return 0;
		 String t = "\t";
		 int count = 0;
		 int idx = 0;
		 while ((idx = s.indexOf(t, idx)) != -1) {
			 count++;
			 idx += t.length();
		 }
		 return count;
	}
}
