package za.co.workpool.CleanPropertiesFile;

import java.io.BufferedReader;  
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    
    public static Map<String,Integer> duplicateEntriesMatchCase(List<String> properties) {
    	Set<String> keyList = new HashSet<String>();
    	Map<String,Integer> dups= new HashMap<String,Integer>();
    	
    	for(String property:properties) {
    			if(!keyList.contains(property)) {
    				keyList.add(property);
    			} else if(!dups.containsKey(property)){
    				dups.put(property, 2);
    			} else 
    				dups.merge(property, 1, Integer::sum);
    	}
    	return dups;
    }
    
    public static Map<String,Integer> duplicateEntriesIgnoreCase(List<String> properties) {
    	Set<String> keyList = new HashSet<String>();
    	Map<String,Integer> dups = new HashMap<String,Integer>();
    	
    	for(String property:properties) {
    		property=property.toLowerCase();
    			if(!keyList.contains(property)) {
    				keyList.add(property);
    			} else if(!dups.containsKey(property)){
    				dups.put(property, 2);
    			} else 
    				dups.merge(property, 1, Integer::sum);
    	}
    	return dups;
    }
    
    public static Map<String,Integer> duplicateKeysMatchCase(List<String> properties) {
    	Set<String> keyList = new HashSet<>();
    	Map<String, Integer> dups = new HashMap<String, Integer>();
    	
    	for(String property:properties) {
    		if(property.contains("=")) {
    			String[] key = property.split("=");
    			if(!keyList.contains(key[0])) {
    				keyList.add(key[0]);
    			} else if(!dups.containsKey(key[0])){
    				dups.put(key[0], 2);
    			} else 
    				dups.merge(key[0], 1, Integer::sum);
    		}
    	}
    	return dups;
    }
    
    public static Map<String,Integer> duplicateKeysIgnoreCase(List<String> properties) {
    	Set<String> keyList = new HashSet<String>();
    	Map<String,Integer> dups = new HashMap<>();
    	
    	for(String property:properties) {
    		property=property.toLowerCase();
    		if(property.contains("=")) {
    			String[] key = property.split("=");
    			if(!keyList.contains(key[0])) {
    				keyList.add(key[0]);
    			} else if(!dups.containsKey(key[0])){
    				dups.put(key[0], 2);
    			} else 
    				dups.merge(key[0], 1, Integer::sum);
    		}
    	}
    	return dups;
    }
    
    public static Map<String,Integer> duplicateValuesMatchCase(List<String> properties) {
    	Set<String> keyList = new HashSet<String>();
    	Map<String,Integer> dups = new HashMap<String,Integer>();
    	
    	for(String property:properties) {
    		if(property.contains("=")) {
    			String[] key = property.split("=");
	    		if(key.length==2) {
	    			if(!keyList.contains(key[1])) {
	    				keyList.add(key[1]);
	    			} else if(!dups.containsKey(key[0])){
	    				dups.put(key[1], 2);
	    			} else 
	    				dups.merge(key[1], 1, Integer::sum);
    			}
    		}
    	}
    	return dups;
    }
    
    public static Map<String,Integer> duplicateValuesIgnoreCase(List<String> properties) {
    	Set<String> keyList = new HashSet<String>();
    	Map<String,Integer> dups = new HashMap<String,Integer>();
    	
    	for(String property:properties) {
    		property=property.toLowerCase();
    		if(property.contains("=")) {
    			String[] key = property.split("=");
	    		if(key.length==2) {
	    			if(!keyList.contains(key[1])) {
	    				keyList.add(key[1]);
	    			} else if(!dups.containsKey(key[0])){
	    				dups.put(key[1], 2);
	    			} else 
	    				dups.merge(key[1], 1, Integer::sum);
    			}
    		}
    	}
    	return dups;
    }
    
    public static List<String> casesOnValidKeys(List<String> properties) {
    	List<String> valFails = new ArrayList<String>();
    	Pattern p = Pattern.compile("[A-Z]");
        Matcher m;
    	for(String property:properties) {
    		if(property.contains("=")) {
    			String[] key = property.split("=");
    			m = p.matcher(key[0]);
    			if(m.find()) {
    				valFails.add(property);
    			}
    		}
    	}
    	return valFails;
    }
    
    public static List<String> casesOnInvalidKeys(List<String> properties) {
    	List<String> valFails = new ArrayList<String>();
    	Pattern p = Pattern.compile("[A-Z]");
        Matcher m;
    	for(String property:properties) {
    		if(property.contains("=")) {
    			String[] key = property.split("=");
    			m = p.matcher(key[0]);
    			if(m.find()) {
    				valFails.add(property);
    			}
    		}
    		else if(property.contains(":")) {
    			String[] key = property.split("=");
    			m = p.matcher(key[0]);
    			if(m.find()) {
    				valFails.add(property);
    			}
    		}
    	}
    	return valFails;
    }
    
	public static List<String> specialChars(List<String> properties) {
		List<String> valFails = new ArrayList<String>();
		Pattern p = Pattern.compile("[^a-zA-Z0-9. ]");
		Matcher m;
		for (String property : properties) {
			if (property.contains("=")) {
				String[] key = property.split("=");
				m = p.matcher(key[0]);
				if (m.find()) {
					valFails.add(property);
				}
			}
		}
		return valFails;
	}
    
	public static List<String> noOrMultipleEqualSign(List<String> properties) {
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
    
	public static List<String> whiteSpacesOnKey(List<String> properties) {
		List<String> valFails = new ArrayList<String>();
		Pattern p = Pattern.compile("[ ]");
		Matcher m;
		for (String property : properties) {
			if (property.contains("=")) {
				String[] key = property.split("=");
				m = p.matcher(key[0]);
				if (m.find()) {
					valFails.add(property);
				}
			}
		}
		return valFails;
	}
    
	public static List<String> whiteSpacesOnBeginOrEndOfValue(List<String> properties) {
		List<String> valFail = new ArrayList<String>();
    	for(String property:properties) {
    		if(property.contains("=")) {
    			String[] key = property.split("=");
    			if(key.length==2) {
    				if(Character.isWhitespace(key[1].charAt(0)) || Character.isWhitespace(key[1].charAt(key[1].length()-1))) {
    					valFail.add(property);
    				}
    			} 
    		}
    	}
    	return valFail;
    }
    
	public static List<String> noKey(List<String> properties) {
		List<String> valFails = new ArrayList<String>();
		for (String property : properties) {
			if (property.startsWith("=")) {
				valFails.add(property);
			}
		}
		return valFails;
	}
	
	public static List<String> noValue(List<String> properties) {
		List<String> valFails = new ArrayList<String>();
		for (String property : properties) {
			if (property.trim().endsWith("=")) {
				valFails.add(property);
			}
		}
		return valFails;
	}
    
	public static List<String> fullStops(List<String> properties) {
		List<String> valFails = new ArrayList<String>();
		for (String property : properties) {
			if (property.contains("=")) {
				String[] key = property.split("=");
				if(key[0].endsWith(".") || key[0].startsWith(".") || key[0].contains("..")) {
    				valFails.add(property);
    			}
			}
		}
		return valFails;
	}
	
	public static List<Integer> EmptyLines() {
		List<Integer> emptyLines = new ArrayList<>();
		int currentLine = 0;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("application.properties"));) {
			String line = bufferedReader.readLine();
			while (line != null) {
				currentLine++;
				if (line.trim().length() == 0) {
					emptyLines.add(currentLine);
				}
				line = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.getStackTrace();
		}

		return emptyLines;
	}
	
	public static int totalEntries() {
		List<Integer> emptyLines = new ArrayList<>();
		int totalEntries = 0;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("application.properties"));) {
			String line = bufferedReader.readLine();
			while (line != null) {
				if (line.trim().length() != 0) {
					totalEntries++;
				}
				line = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.getStackTrace();
		}

		return totalEntries;
	}
    
    //OLD METHODS
    
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