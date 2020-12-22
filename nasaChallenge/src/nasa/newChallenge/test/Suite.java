import org.testng.TestNG;

public class Suite {

	public static void main(String[] args) {
		TestNG testSuite = new TestNG();
		testSuite.setTestClasses(new Class[] { TestCases.class });
		testSuite.addListener(new testSuiteListener());
		testSuite.setDefaultSuiteName("Nasa Test Suite");
		testSuite.setDefaultTestName("Nasa Tests");
		testSuite.setOutputDirectory("/temp/testng-output");
		testSuite.run();
	}

}