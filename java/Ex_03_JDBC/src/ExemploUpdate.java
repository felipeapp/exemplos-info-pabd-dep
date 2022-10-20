import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ExemploUpdate {

	public static void main(String[] args) {

		try (Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "123456")) {
			Scanner leitor = new Scanner(System.in);

			System.out.print("Digite o e-mail do contato: ");
			String busca_email = leitor.nextLine();

			System.out.print("Digite a nova idade do contato: ");
			int nova_idade = leitor.nextInt();

			leitor.close();

			PreparedStatement stmt = conexao.prepareStatement("update contato set idade = ? where email = ?");
			stmt.setInt(1, nova_idade);
			stmt.setString(2, busca_email);

			int n = stmt.executeUpdate();
			System.out.println("Linhas afetadas: " + n);
		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o contato!");
			// e.printStackTrace();
		}

	}

}
