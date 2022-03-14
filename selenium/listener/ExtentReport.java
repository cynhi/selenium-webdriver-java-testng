package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

//Extent Report inherit ITestListener interface => use "implements"
//As ITestListener is an interface => only have abstract functions
//Call listener in TC class file. Ex: @Listeners(ExtentReport.class)
//**Call listener in xml file to apply listener for all test
//<listeners>
//	<listener class-name ="listener.ExtentReport"/>
//</listeners>

public class ExtentReport implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("TC failed. Do something?!");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("TC skipped. Do something?!");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
