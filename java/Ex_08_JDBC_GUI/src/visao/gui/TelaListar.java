package visao.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import modelo.dao.ProdutoDAO;
import modelo.entidade.Produto;
import visao.DateUtil;

public class TelaListar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListar frame = new TelaListar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaListar() {
		setTitle("Listagem de Produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 300);

		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		String[] colunas = { "ID", "Nome", "Preço", "Quantidade", "Medida", "Data de Cadastro" };
		Object[][] linhas = listar();
		tbProduto = new JTable(linhas, colunas);
		tbProduto.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane(tbProduto);
		tbProduto.setFillsViewportHeight(true);
		contentPane.add(scrollPane);
	}

	private Object[][] listar() {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.listar();
		dao.fechar();

		Object[][] matriz = new Object[produtos.size()][6];

		for (int i = 0; i < matriz.length; i++) {
			matriz[i][0] = produtos.get(i).getId();
			matriz[i][1] = produtos.get(i).getNome();
			matriz[i][2] = produtos.get(i).getPreco();
			matriz[i][3] = produtos.get(i).getQuantidade();
			matriz[i][4] = produtos.get(i).getMedida();
			matriz[i][5] = DateUtil.dateToString(produtos.get(i).getCadastro());
		}

		return matriz;
	}

}
