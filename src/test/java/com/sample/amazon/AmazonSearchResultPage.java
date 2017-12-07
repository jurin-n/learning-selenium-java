package com.sample.amazon;

import org.openqa.selenium.WebDriver;
import com.sample.PageObjectBase;

public class AmazonSearchResultPage extends PageObjectBase {
	public AmazonSearchResultPage(WebDriver driver) {
		this.driver = driver;
		if (!driver.getTitle().startsWith("Amazon.co.jp")) {
			throw new IllegalStateException("現在のページが間違っています。" + this.driver.getTitle());
		}
	}
}
