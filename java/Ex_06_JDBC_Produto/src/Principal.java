import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws SQLException {

		Scanner leitor = new Scanner(System.in);
		Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/mercado", "root", "123456");

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

			if (opcao == 0) {
				System.out.println("Finalizando o programa...");
				break;
			} else if (opcao == 1) { // Cadastro
				System.out.print("Digite o nome do produto: ");
				String nome = leitor.nextLine();

				System.out.print("Digite a medida do produto: ");
				String medida = leitor.nextLine();

				System.out.print("Digite o preço do produto: ");
				double preco = leitor.nextDouble();

				System.out.print("Digite a quantidade do produto: ");
				int quantidade = leitor.nextInt();

				PreparedStatement stmt = conexao
						.prepareStatement("insert into produto (nome, medida, preco, quantidade) values (?, ?, ?, ?)");
				stmt.setString(1, nome);
				stmt.setString(2, medida);
				stmt.setDouble(3, preco);
				stmt.setInt(4, quantidade);

				try {
					if (stmt.executeUpdate() == 1)
						System.out.println("Produto adicionado com sucesso!");
					else
						System.out.println("Erro ao adicionar produto!");
				} catch (SQLException e) {
					System.out.println("O produto informado já está cadastrado!");
				}

				stmt.close();
			} else if (opcao == 2) { // Busca pelo nome
				System.out.print("Digite o nome do produto: ");
				String nome = leitor.nextLine();

				PreparedStatement stmt = conexao.prepareStatement("select * from produto where nome = ?");
				stmt.setString(1, nome);

				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					System.out.println("ID: " + rs.getInt("id"));
					System.out.println("Nome: " + rs.getString("nome"));
					System.out.println("Preço: " + rs.getDouble("preco"));
					System.out.println("Quantidade: " + rs.getInt("quantidade"));
					System.out.println("Medida: " + rs.getString("medida"));
				} else {
					System.out.println("Nenhum produto foi encontrado!");
				}

				rs.close();
				stmt.close();
			} else if (opcao == 3) { // Listagem
				PreparedStatement stmt = conexao.prepareStatement("select * from produto");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					System.out.println("ID: " + rs.getInt("id"));
					System.out.println("Nome: " + rs.getString("nome"));
					System.out.println("Preço: " + rs.getDouble("preco"));
					System.out.println("Quantidade: " + rs.getInt("quantidade"));
					System.out.println("Medida: " + rs.getString("medida"));
					System.out.println("###");
				}

				rs.close();
				stmt.close();
			} else if (opcao == 4) { // Atualização
				System.out.print("Digite o nome do produto a ser atualizado: ");
				String nome = leitor.nextLine();

				System.out.print("Digite o novo preço do produto: ");
				double novo_preco = leitor.nextDouble();

				System.out.print("Digite a nova quantidade do produto: ");
				int nova_quantidade = leitor.nextInt();

				PreparedStatement stmt = conexao
						.prepareStatement("update produto set preco = ?, quantidade = ? where nome = ?");
				stmt.setDouble(1, novo_preco);
				stmt.setInt(2, nova_quantidade);
				stmt.setString(3, nome);

				if (stmt.executeUpdate() == 1)
					System.out.println("Preço do produto atualizado com sucesso!");
				else
					System.out.println("O produto não foi encontrado!");

				stmt.close();
			} else if (opcao == 5) { // Remoção
				System.out.print("Digite o nome do produto a ser removido: ");
				String nome = leitor.nextLine();

				PreparedStatement stmt = conexao.prepareStatement("delete from produto where nome = ?");
				stmt.setString(1, nome);

				if (stmt.executeUpdate() == 1)
					System.out.println("Produto removido com sucesso!");
				else
					System.out.println("O produto não foi encontrado!");

				stmt.close();
			} else {
				System.out.println("Opção inválida, tente novamente!");
			}
		}

		leitor.close();
		conexao.close();

	}

}
