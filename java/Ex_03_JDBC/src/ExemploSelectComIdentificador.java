import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ExemploSelectComIdentificador {

	public static void main(String[] args) {

		try (Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "123456")) {
			Scanner leitor = new Scanner(System.in);

			System.out.print("Digite o e-mail da busca: ");
			String email_busca = leitor.nextLine();

			leitor.close();

			PreparedStatement stmt = conexao.prepareStatement("select * from contato where email = ?");
			stmt.setString(1, email_busca);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				int idade = rs.getInt("idade");

				System.out.println("ID: " + id);
				System.out.println("Nome: " + nome);
				System.out.println("E-Mail: " + email);
				System.out.println("Idade: " + idade);
				System.out.println("-------------------------");
			} else {
				System.out.println("O e-mail informado não foi encontrado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
