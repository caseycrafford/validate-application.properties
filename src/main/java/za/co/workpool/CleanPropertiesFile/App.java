package za.co.workpool.CleanPropertiesFile;

import java.io.BufferedReader;  
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class App 
{
    public static void main( String[] args ) {
    	List<String> testing = getListOfProperties();
    	List<String> test2 = new ArrayList<String>(Arrays.asList("test=","te st=","te$t=","te.st=","tes,st=","Test="));
    	System.out.println("Duplicates:\t    " + checkForDuplicates(testing));
    	System.out.println("Formatting problems:" + checkForFormatting(test2));
    	System.out.println(checkEqualSign(testing));
    }
    
    public static List<String> getListOfProperties() {
		List<String> properties = new ArrayList<String>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("application.properties"));) {
			String line = bufferedReader.readLine();
			while (line != null) {
				if (line.length() > 0) {
					properties.add(line);
				}
				line = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.getStackTrace();
		}

		return properties;
	}

    public static List<String> checkForDuplicates(List<String> properties) {
    	Set<String> keyList = new HashSet<String>();
    	List<String> dups = new ArrayList<String>();
    	
    	for(String property:properties) {
    		if(property.contains("=")) {
    			String[] key = property.split("=");
    			if(!keyList.contains(key[0])) {
    				keyList.add(key[0]);
    			} else {
    				dups.add(property);
    			}
    		}
    	}
    	return dups;
    }
    
    public static List<String> checkForFormatting(List<String> properties) {
    	List<String> valFails = new ArrayList<String>();
    	Pattern p = Pattern.compile("[^a-z0-9.]");
        Matcher m;
    	for(String property:properties) {
    		if(property.contains("=")) {
    			String[] key = property.split("=");
    			m = p.matcher(key[0]);
    			if(m.find()) {
    				valFails.add(property);
    			}
    			if(key[0].endsWith(".") || key[0].startsWith(".")) {
    				valFails.add(property);
    			}
    			if(key[0].contains("..")) {
    				valFails.add(property);
    			}
    		}
    	}
    	return valFails;
    }
    
    public static List<String> checkEqualSign(List<String> properties) {
    	Set<String> keyList = new HashSet<String>();
    	List<String> valFail = new ArrayList<String>();
    	
    	for(String property:properties) {
    		if(property.contains("=")) {
    			String[] key = property.split("=");
    			if(key.length>2) {
    				valFail.add(property);
    			} 
    		} else valFail.add(property);
    	}
    	return valFail;
    }
    
    public static List<String> checkValue(List<String> properties){
    	List<String> valFail = new ArrayList<String>();
    	for(String property:properties) {
    		if(property.contains("=")) {
    			String[] key = property.split("=");
    			if(key.length==2) {
    				if(Character.isWhitespace(key[1].charAt(0))) {
    					valFail.add(property);
    				}
    			} else if (key.length<2) {
    				valFail.add(property);
    			}
    		} else valFail.add(property);
    	}
    	
    	
    	return valFail;
    }
}