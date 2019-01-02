package com.qa.barcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class BarcodeTest {
	
public static void main (String[] args) throws NotFoundException, IOException {
	
barcodeTest();
}
	@Test
	public static void barcodeTest() throws IOException, NotFoundException{
		
	System.setProperty("webdriver.chrome.driver","/Users/gvijaykumarreddy/Desktop/Learning from Dec 2018/chromedriver");
	
	WebDriver driver = new ChromeDriver();
	
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	
	driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
	driver.get("https://barcode.tec-it.com/en/Code128?data=Vijay%20is%20good%20boy");
	
	String barcodeURL = driver.findElement(By.tagName("img")).getAttribute("src");
	System.out.println(barcodeURL);
	
	URL url =new URL(barcodeURL);
	
	BufferedImage bufferedImage = ImageIO.read(url); //returns buffered image
	 
	LuminanceSource luminanceSource =  new BufferedImageLuminanceSource(bufferedImage);
	
	BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
	
	Result result = new MultiFormatReader().decode(binaryBitmap);
	
	result.getText();
	
	System.out.println(result.getText());
	
	driver.close();
	driver.quit();
	}
}

