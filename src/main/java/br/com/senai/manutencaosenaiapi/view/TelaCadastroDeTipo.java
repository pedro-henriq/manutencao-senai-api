package br.com.senai.manutencaosenaiapi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.TipoDePeca;
import br.com.senai.manutencaosenaiapi.service.TipoDePecaService;

@Component
public class TelaCadastroDeTipo extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField edtId;
	private JTextField edtDescricao;

	@Autowired
	private TipoDePecaService service;

	@Autowired
	@Lazy
	private TelaConsultaDeTipo telaDeConsulta;

	/**
	 * Create the frame.
	 */
	public TelaCadastroDeTipo() {
		setTitle("Cadastro de Tipo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 127);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblId = new JLabel("ID");

		edtId = new JTextField();
		edtId.setEnabled(false);
		edtId.setColumns(10);

		JLabel lblDescricao = new JLabel("Descrição");

		edtDescricao = new JTextField();
		edtDescricao.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (edtId.getText() != null && edtId.getText().length() > 0) {
						TipoDePeca tipoSalvo = new TipoDePeca();
						tipoSalvo.setDescricao(edtDescricao.getText());
						tipoSalvo.setId(Integer.parseInt(edtId.getText()));
						service.alterar(tipoSalvo);
						JOptionPane.showMessageDialog(contentPane, "Tipo alterado com sucesso");
					} else {
						TipoDePeca novoTipo = new TipoDePeca();
						novoTipo.setDescricao(edtDescricao.getText());
						TipoDePeca tipoSalvo = service.inserir(novoTipo);
						edtId.setText(tipoSalvo.getId().toString());
						JOptionPane.showMessageDialog(contentPane, "Tipo inserido com sucesso");
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaDeConsulta.setVisible(true);
				setVisible(false);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane
				.createParallelGroup(
						Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(edtId, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblId))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblDescricao).addGap(392))
								.addGroup(
										gl_contentPane.createSequentialGroup()
												.addComponent(edtDescricao, GroupLayout.DEFAULT_SIZE, 428,
														Short.MAX_VALUE)
												.addContainerGap())))
				.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnCancelar)
						.addPreferredGap(ComponentPlacement.RELATED, 352, Short.MAX_VALUE).addComponent(btnSalvar)
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(lblId).addComponent(lblDescricao))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(edtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(edtDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 223, Short.MAX_VALUE).addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(btnCancelar).addComponent(btnSalvar))));
		contentPane.setLayout(gl_contentPane);
	}

	public void colocarEmEdicao(TipoDePeca tipoSalvo) {
		edtId.setText(tipoSalvo.getId().toString());
		edtDescricao.setText(tipoSalvo.getDescricao());
	}

	public void colocarEmInclusao() {
		edtId.setText("");
		edtDescricao.setText("");
		setVisible(true);
	}
}