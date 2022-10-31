import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ExemploInsert {

	public static void main(String[] args) {

		try (Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "123456")) {
			Scanner leitor = new Scanner(System.in);

			System.out.print("Digite o nome: ");
			String nome = leitor.nextLine();

			System.out.print("Digite o e-mail: ");
			String email = leitor.nextLine();

			System.out.print("Digite a idade: ");
			int idade = leitor.nextInt();

			leitor.close();

			PreparedStatement stmt = conexao.prepareStatement("insert into contato (nome, email, idade) values (?, ?, ?)");
			stmt.setString(1, nome);
			stmt.setString(2, email);
			stmt.setInt(3, idade);

			int n = stmt.executeUpdate();
			System.out.println("Linhas afetadas: " + n);
		} catch (SQLException e) {
			System.out.println("Não foi possível cadastrar o contato!");
			// e.printStackTrace();
		}

	}

}
