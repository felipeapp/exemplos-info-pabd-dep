package visao.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.dao.ProdutoDAO;
import modelo.entidade.Produto;

public class TelaCadastro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfQuantidade;
	private JTextField tfMedida;
	private JTextField tfNome;
	private JTextField tfPreco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() {
		setTitle("Cadastro de Produto");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 134);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbNome = new JLabel("Nome:");
		lbNome.setBounds(10, 11, 40, 14);
		contentPane.add(lbNome);

		JLabel lbPreco = new JLabel("Preço:");
		lbPreco.setBounds(10, 36, 78, 14);
		contentPane.add(lbPreco);

		JLabel lbQuantidade = new JLabel("Quantidade:");
		lbQuantidade.setBounds(166, 11, 72, 14);
		contentPane.add(lbQuantidade);

		JLabel lbMedida = new JLabel("Medida:");
		lbMedida.setBounds(188, 36, 50, 14);
		contentPane.add(lbMedida);

		tfQuantidade = new JTextField();
		tfQuantidade.setBounds(248, 8, 96, 20);
		contentPane.add(tfQuantidade);
		tfQuantidade.setColumns(10);

		tfMedida = new JTextField();
		tfMedida.setBounds(248, 33, 96, 20);
		contentPane.add(tfMedida);
		tfMedida.setColumns(10);

		tfNome = new JTextField();
		tfNome.setBounds(60, 8, 96, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		tfPreco = new JTextField();
		tfPreco.setBounds(60, 33, 96, 20);
		contentPane.add(tfPreco);
		tfPreco.setColumns(10);

		JButton btCadastrar = new JButton("Cadastrar");
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}
		});
		btCadastrar.setBounds(136, 64, 102, 23);
		contentPane.add(btCadastrar);

		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		btCancelar.setBounds(255, 64, 89, 23);
		contentPane.add(btCancelar);
	}

	private void cadastrar() {
		Produto p = new Produto();
		p.setNome(tfNome.getText());
		p.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
		p.setPreco(Double.parseDouble(tfPreco.getText()));
		p.setMedida(tfMedida.getText());

		ProdutoDAO dao = new ProdutoDAO();
		if (dao.adicionar(p)) {
			JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			tfNome.setText("");
			tfQuantidade.setText("");
			tfPreco.setText("");
			tfMedida.setText("");
		} else {
			JOptionPane.showMessageDialog(this, "Dados do produto já cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		dao.fechar();
	}

	private void cancelar() {
		dispose();
	}
}
