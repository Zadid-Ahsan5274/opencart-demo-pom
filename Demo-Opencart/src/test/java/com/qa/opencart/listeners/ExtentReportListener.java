package com.qa.opencart.listeners;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.opencart.factory.PlaywrightFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener{
	
	private static final String OUTPUT_FOLDER = "./build/";
	private static final String FILE_NAME = "TestExecutionReport.html";
	
	private static ExtentReports extent = init();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ExtentReports extentReports;
	
	private static ExtentReports init() {
		Path path = Paths.get(OUTPUT_FOLDER);
		// If directory exists
		if(!Files.exists(path))
		try {
			Files.createDirectory(path);
		} catch(Exception e) {
			// Failed to create directory
			e.printStackTrace();
		}
		extentReports = new ExtentReports();
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(OUTPUT_FOLDER+FILE_NAME);
		extentSparkReporter.config().setReportName("Open Cart Test Automation Suite");
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("OS", "Windows 11");
		extentReports.setSystemInfo("Author", "NBMIS - Zadid");
		extentReports.setSystemInfo("Build Number", "#1.1");
		extentReports.setSystemInfo("Team", "MIS");
		extentReports.setSystemInfo("Customer Name", "HPL");
		extentReports.setSystemInfo("Environment", System.getProperty("env"));
		return extentReports;
	}
	
	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Test Suite Started");
	}
	
	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println("Test Suite Finished");
		extent.flush();
		test.remove();
	}
	
	@Override
	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid+1, last);
		
		System.out.println(methodName + " started");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		extentTest.assignCategory(className);
		test.set(extentTest);
		// test.get().getModel().setStartTime(getTime(result.getStartMillis()));
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}
	
	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName()+" Passed!");
		test.get().pass("Test Passed");
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}
	
	public synchronized void onTestFailure(ITestResult result) {
		System.out.println(result.getMethod().getMethodName()+" Failed!");
		test.get().fail(result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(PlaywrightFactory.takeScreenshot()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}
	
	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println(result.getMethod().getMethodName()+" Skipped!");
		test.get().fail(result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(PlaywrightFactory.takeScreenshot()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}
	
	public synchronized void onTestFailedButWithSuccessPercentage(ITestResult result) {
		System.out.println("onTestFailedButWithSuccessPercentage for "+result.getMethod().getMethodName());
	}
	
	private Date getTime(long milis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milis);
		return calendar.getTime();
	}
	
	

}
