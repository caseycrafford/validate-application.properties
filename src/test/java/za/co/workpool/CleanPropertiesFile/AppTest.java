package za.co.workpool.CleanPropertiesFile;

import java.util.HashSet;
import java.util.List;
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

}
