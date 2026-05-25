package br.com.rocketskills.petlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

class Cadastro {

	@Test
	@DisplayName("Deve cadastrar um ponto de doação")
	void createPoint() {
		WebDriver driver = new ChromeDriver(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		driver.get("https://petlov.vercel.app/signup");

		WebElement title = driver.findElement(By.cssSelector("h1"));

		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(d -> title.isDisplayed());

		assertEquals("Cadastro de ponto de doação", title.getText(), "Verificando a página");

		WebElement name = driver.findElement(By.cssSelector("input[placeholder='Nome do ponto de doação']"));
		name.sendKeys("Doação 1");

		WebElement email = driver.findElement(By.cssSelector("input[name=email]"));
		email.sendKeys("email@teste.com");

		WebElement cep = driver.findElement(By.cssSelector("input[name=cep]"));
		cep.sendKeys("71655520");

		driver.findElement(By.cssSelector("input[type=button]")).click();
		
		WebElement addressNumber = driver.findElement(By.cssSelector("input[name=addressNumber"));
		addressNumber.sendKeys("888");

		WebElement addressDetails = driver.findElement(By.cssSelector("input[name=addressDetails]"));
		addressDetails.sendKeys("Complemento teste");
		
		driver.findElement(By.xpath("//span[text()=\"Cachorros\"]/..")).click();

		driver.findElement(By.className("button-register")).click();

		WebElement result = driver.findElement(By.cssSelector("main p"));

		Wait<WebDriver> waitResult = new WebDriverWait(driver, Duration.ofSeconds(2));
		waitResult.until(d -> result.isDisplayed());

		String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";

		assertEquals(target, result.getText(), "Confirmando o ponto de doação");
		
		driver.close(); 
	}
}
