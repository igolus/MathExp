package batch;

import java.io.File;
import java.util.List;

import com.jenetics.mathexp.math.IBigIntegerSuite;

/**
 * Created by igolus on 24/09/2017.
 */
public class BatchGenerator {
    
	private static final String INPUT_SUITE_FOLDER = "naturalSuite";
	private static final String INPUT_CONVERTED_FOLDER = "convertedSuite";

	static {
		if (!new File(INPUT_SUITE_FOLDER).isDirectory()) {
			new File(INPUT_SUITE_FOLDER).mkdir();
		}
		if (!new File(INPUT_CONVERTED_FOLDER).isDirectory()) {
			new File(INPUT_CONVERTED_FOLDER).mkdir();
		}
	}
	
	public List<String> getConvertedSuiteList() {
		return null;
    }
	
	public List<String> getNaturalSuiteList() {
		return null;
    }
	
	public boolean generateConvertedSuite(String fileName, IBigIntegerSuite suite, String separator) {
		
		return true;
    }

    public boolean generateInputFile(String fileName, String suite, String separator) {
        return true;
    }
}
