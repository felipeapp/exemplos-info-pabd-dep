package modelo.entidade;

import java.time.LocalDateTime;

import visao.terminal.DateUtil;

public class Produto {

	private int id;
	private String nome;
	private double preco;
	private int quantidade;
	private String medida;
	private LocalDateTime cadastro;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public LocalDateTime getCadastro() {
		return cadastro;
	}

	public void setCadastro(LocalDateTime cadastro) {
		this.cadastro = cadastro;
	}

	public String toString() {
		return "ID: " + id + ", Nome: " + nome + ", Preço: " + preco + ", Quantidade: " + quantidade + ", Medida: " + medida + ", Data de Cadastro: "
				+ DateUtil.dateToString(cadastro);
	}

}
