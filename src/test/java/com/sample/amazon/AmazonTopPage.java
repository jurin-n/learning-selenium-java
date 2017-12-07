package com.sample.amazon;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sample.PageObjectBase;

public class AmazonTopPage extends PageObjectBase {
	public AmazonTopPage(WebDriver driver) {
		this.driver = driver;

		if (!"Amazon | 本, ファッション, 家電から食品まで | アマゾン".equals(driver.getTitle())) {
			throw new IllegalStateException("現在のページが間違っています。" + this.driver.getTitle());
		}
	}

	public List<WebElement> getScriptTags() {
		return this.driver.findElements(By.tagName("script"));
	}

	public List<WebElement> getStyleTags() {
		return this.driver.findElements(By.tagName("link"));
	}

	public AmazonSearchResultPage search(String keyWord) throws InterruptedException {
		WebElement e = driver.findElement(By.id("twotabsearchtextbox"));
		e.sendKeys(keyWord);
		e.sendKeys(Keys.ENTER);

		WebDriverWait wait = new WebDriverWait(driver, 60);
		// フッター表示まで待機
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navFooterLine")));
		return new AmazonSearchResultPage(driver);
	}
}
