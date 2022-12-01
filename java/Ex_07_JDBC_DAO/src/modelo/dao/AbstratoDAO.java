package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstratoDAO {

	protected Connection conexao;

	public AbstratoDAO() {
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/mercado", "root", "123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fechar() {
		try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
