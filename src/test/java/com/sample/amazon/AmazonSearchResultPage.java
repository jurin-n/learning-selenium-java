package com.sample.amazon;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AmazonSearchResultPage {

	private WebDriver driver;

	public AmazonSearchResultPage(WebDriver driver) {
		this.driver = driver;
		if (!driver.getTitle().startsWith("Amazon.co.jp")) {
			throw new IllegalStateException("現在のページが間違っています。" + this.driver.getTitle());
		}
	}

	public String getPageSource() {
		return this.driver.getPageSource();
	}

	public File getScreenshotAsFile() {
		return ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
	}
}
