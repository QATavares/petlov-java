package br.com.rocketskills.petlov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

class PontoDoacao {
	String nome;
	String email;
	String cep;
	Integer numero;
	String complemento;
	String pets;

	public PontoDoacao(String nome, String email, String cep, Integer numero, String complemento, String pets) {
		this.nome = nome;
		this.email = email;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.pets = pets;
	}
	
}
class Cadastro {

	private void accessPage () {
		open("https://petlov.vercel.app/signup");
		$("h1").shouldHave(text("Cadastro de ponto de doação"));
	}

	private void submitForm(PontoDoacao ponto) {
		
		$("input[placeholder='Nome do ponto de doação']").setValue(ponto.nome);
		$("input[name=email]").setValue(ponto.email);
		$("input[name=cep]").setValue(ponto.cep);
		$("input[type=button]").click();
		$("input[name=addressNumber").setValue(ponto.numero.toString());
		$("input[name=addressDetails]").setValue(ponto.complemento);
		$(By.xpath("//span[text()=\"" + ponto.pets + "\"]/..")).click();
		$(".button-register").click();
	
	}

	private void submitFormTwoPets() {
		$("input[placeholder='Nome do ponto de doação']").setValue("teste");
		$("input[name=email]").setValue("teste@teste.com");
		$("input[name=cep]").setValue("71655520");
		$("input[type=button]").click();
		$("input[name=addressNumber").setValue("888");
		$("input[name=addressDetails]").setValue("teste");
		$(By.xpath("//span[text()=\"Cachorros\"]/..")).click();
		$(By.xpath("//span[text()=\"Gatos\"]/..")).click();

		$(".button-register").click();
	}

	@Test
	@DisplayName("Deve cadastrar um ponto de doação para cachorros com sucesso")
	void createPointDog() {

		PontoDoacao ponto = new PontoDoacao(
			"Adoção de AUmigos",
			"atendimento@aumigos.com.br",
			"71655520",
			888,
			"Complemento teste",
			"Cachorros"
		);
		accessPage();
	
		submitForm(ponto);

		String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
		$("main p").shouldHave(text(target));

	}

	@Test
	@DisplayName("Deve cadastrar um ponto de doação para gatos com sucesso")
	void createPointCat() {

		PontoDoacao ponto = new PontoDoacao(
			"Lar dos Miaus",
			"atendimento@miaus.com.br",
			"71655520",
			888,
			"Complemento teste",
			"Gatos"
		);

		accessPage();
		
		submitForm(ponto);

		String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
		$("main p").shouldHave(text(target));

	}

	@Test
	@DisplayName("Deve cadastrar um ponto de doação para gatos com sucesso")
	void createPointDuo() {

		accessPage();

		submitFormTwoPets();

		String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
		$("main p").shouldHave(text(target));

	}
	
	@Test
	@DisplayName("Não deve cadastrar um ponto de doação")
	void createPointError() {

		PontoDoacao ponto = new PontoDoacao(
			"Estação Pet",
			"estacao&pet.com.br",
			"71655520",
			888,
			"Complemento teste",
			"Cachorros"
		);

		accessPage();
		
		submitForm(ponto);

		String messageError = "Informe um email válido";
		$(".alert-error").shouldHave(text(messageError));

	}
}
