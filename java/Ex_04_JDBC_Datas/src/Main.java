import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {

		Scanner leitor = new Scanner(System.in);
		Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/exemplo_04_jdbc", "root", "123456");

		while (true) {
			System.out.println("#########################");
			System.out.println("0. Sair");
			System.out.println("1. Adicionar");
			System.out.println("2. Listar");
			System.out.print(">> ");
			int opcao = leitor.nextInt();
			leitor.nextLine();

			if (opcao == 0) {
				System.out.println("Saindo do programa...");
				break;
			} else if (opcao == 1) {
				System.out.print("Digite o nome do usuário: ");
				String nome = leitor.nextLine();

				// A string digitada pelo usuário é convertida em um LocalDate
				System.out.print("Digite a data de nascimento do usuário (dd/mm/aaaa): ");
				LocalDate data_nascimento = DateUtil.stringToDate(leitor.nextLine(), LocalDate.class);

				// A string digitada pelo usuário é convertida em um LocalDateTime
				System.out.print("Digite a data limite de acesso do usuário (dd/mm/aaaa hh:mm:ss): ");
				LocalDateTime data_acesso = DateUtil.stringToDate(leitor.nextLine(), LocalDateTime.class);

				System.out.print("Digite o PIN do usuário: ");
				int pin = leitor.nextInt();

				PreparedStatement stmt = conexao
						.prepareStatement("insert into usuario (nome, pin, data_nascimento, data_acesso) values (?, ?, ?, ?)");

				// Definindo valores para inserir, as datas são passadas como objetos
				stmt.setString(1, nome);
				stmt.setInt(2, pin);
				stmt.setObject(3, data_nascimento);
				stmt.setObject(4, data_acesso);

				boolean sucesso;

				try {
					sucesso = stmt.executeUpdate() == 1;
				} catch (Exception e) {
					sucesso = false;
				}

				stmt.close();

				if (sucesso)
					System.out.println("Usuário adicionado com sucesso!");
				else
					System.out.println("Erro ao adicionar usuário!");
			} else if (opcao == 2) {
				PreparedStatement stmt = conexao.prepareStatement("select * from usuario");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					// Recuperando os dados do result set
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					int pin = rs.getInt("pin");
					// Note que é preciso indicar o tipo do objeto de data
					LocalDate data_nascimento = rs.getObject("data_nascimento", LocalDate.class);
					LocalDateTime data_acesso = rs.getObject("data_acesso", LocalDateTime.class);

					// Mostrando no terminal
					System.out.println("-------------------------");
					System.out.println("ID: " + id);
					System.out.println("Nome: " + nome);
					System.out.println("PIN: " + pin);
					// Note que as datas são convertidas para serem mostradas com o formato adequado
					System.out.println("Data de Nascimento: " + DateUtil.dateToString(data_nascimento));
					System.out.println("Data de Acesso: " + DateUtil.dateToString(data_acesso));
				}

				rs.close();
				stmt.close();
			} else {
				System.out.println("Opção inválida, tente novamente!");
			}

		}

		leitor.close();
		conexao.close();

	}

}
