package br.com.rocketskills.petlov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

class Cadastro {

	@Test
	@DisplayName("Deve cadastrar um ponto de doação")
	void createPoint() {
		open("https://petlov.vercel.app/signup");
		$("h1").shouldHave(text("Cadastro de ponto de doação"));

		$("input[placeholder='Nome do ponto de doação']").setValue("Estação Pet");
		$("input[name=email]").setValue("estacao@pet.com.br");
		$("input[name=cep]").setValue("71655520");
		$("input[type=button]").click();
		$("input[name=addressNumber").setValue("888");
		$("input[name=addressDetails]").setValue("Complemento teste");
		$(By.xpath("//span[text()=\"Cachorros\"]/..")).click();
		$(".button-register").click();
	
		String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
		$("main p").shouldHave(text(target));

	}
}
