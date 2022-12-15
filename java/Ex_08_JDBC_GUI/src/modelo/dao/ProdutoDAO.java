package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelo.entidade.Produto;

public class ProdutoDAO extends AbstratoDAO {

	public boolean adicionar(Produto p) {
		boolean sucesso;
		String sql = "insert into produto (nome, preco, quantidade, medida) values (?, ?, ?, ?)";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, p.getNome());
			stmt.setDouble(2, p.getPreco());
			stmt.setInt(3, p.getQuantidade());
			stmt.setString(4, p.getMedida());

			sucesso = stmt.executeUpdate() == 1;
		} catch (SQLException e) {
			sucesso = false;
		}

		return sucesso;
	}

	public boolean atualizar(String nome, double novo_preco, int nova_quantidade) {
		boolean sucesso;
		String sql = "update produto set preco = ?, quantidade = ? where nome = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setDouble(1, novo_preco);
			stmt.setInt(2, nova_quantidade);
			stmt.setString(3, nome);

			sucesso = stmt.executeUpdate() == 1;
		} catch (SQLException e) {
			sucesso = false;
		}

		return sucesso;
	}

	public boolean remover(String nome) {
		boolean sucesso;
		String sql = "delete from produto where nome = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, nome);
			sucesso = stmt.executeUpdate() == 1;
		} catch (SQLException e) {
			sucesso = false;
		}

		return sucesso;
	}

	public Produto buscarPorNome(String nome) {
		Produto p = null;
		String sql = "select * from produto where nome = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				p = new Produto();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setPreco(rs.getDouble("preco"));
				p.setMedida(rs.getString("medida"));
				p.setQuantidade(rs.getInt("quantidade"));
				p.setCadastro(rs.getObject("cadastro", LocalDateTime.class));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

	public List<Produto> listar() {
		List<Produto> resultado = new ArrayList<Produto>();
		String sql = "select * from produto";

		try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setPreco(rs.getDouble("preco"));
				p.setMedida(rs.getString("medida"));
				p.setQuantidade(rs.getInt("quantidade"));
				p.setCadastro(rs.getObject("cadastro", LocalDateTime.class));

				resultado.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado;
	}

}
