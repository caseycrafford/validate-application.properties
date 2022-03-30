package za.co.workpool.CleanPropertiesFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class AppTest 
{
	List<String> properties = App.getListOfProperties();
	
	@Test
    public void testFileStuff() {
		List<String> dupEqualSigns =  App.checkEqualSign(properties);
		System.out.println(dupEqualSigns);
    }
	
	@Test
	void testStringSplit() {
		String toSplit = "dob.on.cannot.be.in.the.future.=Date of Birth Cannot be in the Future";
		String[] keys = toSplit.split("=");
		for(int i =0; i<keys.length;i++) {
			System.out.println(keys[i]);
		}
		System.out.println(keys[0].endsWith("."));
		System.out.println(keys.length);
	}
	
	@Test
	void testWhiteSpace() {
		System.out.print(App.checkForFormatting(properties));
	}
	
	@Test
	void testAll() {
		Set<String> allValidationFails = new HashSet<>();
		allValidationFails.addAll(App.checkForFormatting(properties));
		allValidationFails.addAll(App.checkEqualSign(properties));
		allValidationFails.addAll(App.checkForDuplicates(properties));
		allValidationFails.addAll(App.checkValue(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	@Test
	void testDup() {
		Set<String> allValidationFails = new HashSet<>();
		allValidationFails.addAll(App.checkForDuplicates(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	
	//NEW TEST CASES
	
	@Test
	void testDupEntriesMatchCase() {
		Set<String> allValidationFails = new HashSet<>();
		allValidationFails.addAll(App.duplicateEntriesMatchCase(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	@Test
	void testDupEntriesIgnoreCase() {
		Set<String> allValidationFails = new HashSet<>();
		allValidationFails.addAll(App.duplicateEntriesIgnoreCase(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	@Test
	void testDupKeysMatchCase() {
		Map<String,Integer> dups = App.duplicateKeysMatchCase(properties);
		System.out.println(dups);
	}
	
	@Test
	void testDupKeysIgnoreCase() {
		Map<String,Integer> dups = App.duplicateKeysIgnoreCase(properties);
		System.out.println(dups);
	}
	
	@Test
	void testDupValuesMatchCase() {
		Set<String> allValidationFails = new HashSet<>();
		allValidationFails.addAll(App.duplicateValuesMatchCase(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	@Test
	void testDupValuesIgnoreCase() {
		List<String> allValidationFails = new ArrayList<>();
		allValidationFails.addAll(App.duplicateValuesIgnoreCase(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	@Test
	void testEq() {
		Set<String> allValidationFails = new HashSet<>();
		allValidationFails.addAll(App.checkEqualSign(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	@Test
	void testCasesOnKeys() {
		List<String> allValidationFails = new ArrayList<>();
		allValidationFails.addAll(App.casesOnKeys(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	@Test
	void testSpecialChars() {
		List<String> allValidationFails = new ArrayList<>();
		allValidationFails.addAll(App.specialChars(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	@Test
	void testEqualSign() {
		List<String> allValidationFails = new ArrayList<>();
		allValidationFails.addAll(App.noOrMultipleEqualSign(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	@Test
	void testWhiteSpacesOnKey() {
		List<String> allValidationFails = new ArrayList<>();
		allValidationFails.addAll(App.whiteSpacesOnKey(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}

	@Test
	void testWhiteSpacesOnValue() {
		List<String> allValidationFails = new ArrayList<>();
		allValidationFails.addAll(App.whiteSpacesOnBeginOrEndOfValue(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	@Test
	void testNoKey() {
		List<String> allValidationFails = new ArrayList<>();
		allValidationFails.addAll(App.noKey(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	@Test
	void testNoValue() {
		List<String> allValidationFails = new ArrayList<>();
		allValidationFails.addAll(App.noValue(properties));
		
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	@Test
	void testEmptyLines() {
		List<Integer> allValidationFails = new ArrayList<>();
		allValidationFails.addAll(App.EmptyLines());
		
		System.out.println("Empty at lines:");
		allValidationFails.forEach(String -> System.out.println(String));
	}
	
	@Test
	void testTotalEntries() {
		System.out.print("Total Entries: ");
		System.out.println(App.totalEntries());
	}
}
