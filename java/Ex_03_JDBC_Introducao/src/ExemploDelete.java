import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ExemploDelete {

	public static void main(String[] args) {

		try (Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "123456")) {
			Scanner leitor = new Scanner(System.in);

			System.out.print("Digite o e-mail do contato a ser removido: ");
			String email = leitor.nextLine();

			leitor.close();

			PreparedStatement stmt = conexao.prepareStatement("delete from contato where email = ?");
			stmt.setString(1, email);

			int n = stmt.executeUpdate();
			System.out.println("Linhas afetadas: " + n);
		} catch (SQLException e) {
			System.out.println("Não foi possível remover o contato!");
			// e.printStackTrace();
		}

	}

}
