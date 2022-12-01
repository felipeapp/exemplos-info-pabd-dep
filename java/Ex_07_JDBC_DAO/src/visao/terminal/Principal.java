package visao.terminal;

import java.util.List;
import java.util.Scanner;

import modelo.dao.ProdutoDAO;
import modelo.entidade.Produto;

public class Principal {

	public static void main(String[] args) {

		Scanner leitor = new Scanner(System.in);

		while (true) {
			System.out.println("-------------------------------");
			System.out.println("0. Finalizar programa");
			System.out.println("1. Adicionar produto");
			System.out.println("2. Buscar produto pelo nome");
			System.out.println("3. Listar todos os produtos");
			System.out.println("4. Atualizar preço e quantidade do produto");
			System.out.println("5. Remover produto");
			System.out.print("Escolha sua opção: ");
			int opcao = leitor.nextInt();
			leitor.nextLine();

			if (opcao == 0) { // Finalizar
				System.out.println("Finalizando o programa...");
				break;
			} else if (opcao == 1) { // Cadastro
				Produto p = new Produto();

				System.out.print("Digite o nome do produto: ");
				p.setNome(leitor.nextLine());

				System.out.print("Digite a medida do produto: ");
				p.setMedida(leitor.nextLine());

				System.out.print("Digite o preço do produto: ");
				p.setPreco(leitor.nextDouble());

				System.out.print("Digite a quantidade do produto: ");
				p.setQuantidade(leitor.nextInt());

				ProdutoDAO dao = new ProdutoDAO();

				if (dao.adicionar(p))
					System.out.println("Produto adicionado com sucesso!");
				else
					System.out.println("Dados do produto já cadastrados!");

				dao.fechar();
			} else if (opcao == 2) { // Busca pelo nome
				System.out.print("Digite o nome do produto: ");
				String nome = leitor.nextLine();

				ProdutoDAO dao = new ProdutoDAO();
				Produto p = dao.buscarPorNome(nome);

				if (p == null)
					System.out.println("Produto não encontrado!");
				else
					System.out.println(p);

				dao.fechar();
			} else if (opcao == 3) { // Listagem
				ProdutoDAO dao = new ProdutoDAO();
				List<Produto> produtos = dao.listar();

				if (produtos.isEmpty())
					System.out.println("Não existem produtos cadastrados!");
				else
					for (Produto p : produtos)
						System.out.println(p);

				dao.fechar();
			} else if (opcao == 4) { // Atualização
				System.out.print("Digite o nome do produto a ser atualizado: ");
				String nome = leitor.nextLine();

				System.out.print("Digite o novo preço do produto: ");
				double novo_preco = leitor.nextDouble();

				System.out.print("Digite a nova quantidade do produto: ");
				int nova_quantidade = leitor.nextInt();

				ProdutoDAO dao = new ProdutoDAO();

				if (dao.atualizar(nome, novo_preco, nova_quantidade))
					System.out.println("Produto atualizado com sucesso!");
				else
					System.out.println("Produto não encontrado!");

				dao.fechar();
			} else if (opcao == 5) { // Remoção
				System.out.print("Digite o nome do produto a ser removido: ");
				String nome = leitor.nextLine();

				ProdutoDAO dao = new ProdutoDAO();

				if (dao.remover(nome))
					System.out.println("Produto removido com sucesso!");
				else
					System.out.println("Produto não encontrado!");

				dao.fechar();
			} else {
				System.out.println("Opção inválida, tente novamente!");
			}
		}

		leitor.close();

	}

}
