import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExemploSelectSemIdentificador {

	public static void main(String[] args) {

		try (Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "123456")) {
			PreparedStatement stmt = conexao.prepareStatement("select * from contato");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				int idade = rs.getInt("idade");

				System.out.println("ID: " + id);
				System.out.println("Nome: " + nome);
				System.out.println("E-Mail: " + email);
				System.out.println("Idade: " + idade);
				System.out.println("-------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
