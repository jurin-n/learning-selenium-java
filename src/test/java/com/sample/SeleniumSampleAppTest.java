package com.sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sample.amazon.AmazonSearchResultPage;
import com.sample.amazon.AmazonTopPage;
//import org.openqa.selenium.edge.EdgeDriver;

public class SeleniumSampleAppTest {
	WebDriver driver;

	@Before
	public void setUp() {
		// ChromeDriver
		String basePathToDriver = System.getProperty("user.dir") + File.separator + "drivers" + File.separator;
		System.setProperty("webdriver.chrome.driver", basePathToDriver + "chromedriver");
		driver = new ChromeDriver();

		// EdgeDriver
		// WebDriver driver = new EdgeDriver();
		// 指定のウィンドウサイズに変更
		int width = 1560;
		int height = 800;
		driver.manage().window().setSize(new Dimension(width, height));

		// 最大化
		// driver.manage().window().maximize();

		// ドライバ起動
		driver.get("https://www.amazon.co.jp/");
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Test
	public void test() throws InterruptedException, IOException {
		AmazonTopPage topPage = new AmazonTopPage(driver);
		List<WebElement> scriptElemets = topPage.getScriptTags();
		System.out.println("[DEBUG]JavaScript URLs ");
		for (WebElement e : scriptElemets) {
			System.out.println(e.getAttribute("src"));
		}
		System.out.println("[DEBUG]CSS URLs ");
		List<WebElement> styleElemets = topPage.getStyleTags();
		for (WebElement e : styleElemets) {
			System.out.println(e.getAttribute("href"));
		}
		AmazonSearchResultPage resultPage = topPage.search("AWS実践");

		String testResultBase = System.getProperty("user.dir") + File.separator + "result" + File.separator;
		try (BufferedWriter writer = Files.newBufferedWriter(
				Paths.get(testResultBase + "html" + File.separator + "pagesource.html"), StandardCharsets.UTF_8)) {
			writer.append(resultPage.getPageSource());
		}

		FileUtils.copyFile(resultPage.getScreenshotAsFile(),
				new File(testResultBase + "screenshot" + File.separator + "screenshot.png"));
	}
}
