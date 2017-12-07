package com.sample;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class PageObjectBase {
	protected WebDriver driver;

	public String getPageSource() {
		return driver.getPageSource();
	}

	public File getScreenshotAsFile() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}
}
