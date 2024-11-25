
package automationPrcatics;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SawanProject1 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup(); 
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        try {
            
            driver.get("https://www.fitpeo.com/");
            driver.manage().window().maximize();
            

            
            WebElement revenueCalculatorLink = driver.findElement(By.linkText("Revenue Calculator"));
            revenueCalculatorLink.click();

            
            WebElement sliderSection = driver.findElement(By.id("slider-section")); 
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sliderSection);

            
            WebElement slider = driver.findElement(By.id("slider")); 
            Actions action = new Actions(driver);
            action.clickAndHold(slider).moveByOffset(200, 0).release().perform(); 
            Thread.sleep(2000);

            
            WebElement sliderValue = driver.findElement(By.id("slider-value")); // Replace with actual ID/XPath
            if (sliderValue.getAttribute("value").equals("820")) {
                System.out.println("Slider adjusted to 820 successfully.");
            } else {
                throw new Exception("Slider value mismatch!");
            }

           
            WebElement textField = driver.findElement(By.id("slider-text")); 
            textField.click();
            textField.clear();
            textField.sendKeys("560");
            Thread.sleep(2000);

            
            if (slider.getAttribute("value").equals("560")) {
                System.out.println("Text field updated to 560 successfully.");
            } else {
                throw new Exception("Slider value mismatch after text input!");
            }

           
            String[] cptCodes = {"99091", "99453", "99454", "99474"};
            for (String code : cptCodes) {
                WebElement checkbox = driver.findElement(By.id("cpt-" + code));
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }
            System.out.println("CPT codes selected successfully.");

           
            WebElement reimbursement = driver.findElement(By.id("total-reimbursement"));
            if (reimbursement.getText().equals("$110700")) {
                System.out.println("Total Recurring Reimbursement validated successfully.");
            } else {
                throw new Exception("Total Recurring Reimbursement value mismatch!");
            }

            System.out.println("All test cases passed successfully!");
        } catch (Exception e) {
            System.out.println("Test Case Failed: " + e.getMessage());
        } finally {
            
            driver.quit();
        }
    }
}
