package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VistaRegistrarProceso extends JInternalFrame {
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField txtMinAdherentes;

	public VistaRegistrarProceso() {
		setClosable(true);
		setTitle("Registrar nuevo proceso");
		setBounds(100, 100, 614, 341);
		getContentPane().setLayout(null);
		
		JLabel lblTipoProceso = new JLabel("Tipo Proceso:");
		lblTipoProceso.setBounds(33, 45, 83, 16);
		getContentPane().add(lblTipoProceso);
		
		JLabel lblFase = new JLabel("Fecha inicio fase 1:");
		lblFase.setBounds(33, 80, 118, 16);
		getContentPane().add(lblFase);
		
		textField_1 = new JTextField();
		textField_1.setBounds(163, 77, 116, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblFechaTopeFase = new JLabel("Fecha inicio fase 2:");
		lblFechaTopeFase.setBounds(33, 113, 118, 16);
		getContentPane().add(lblFechaTopeFase);
		
		textField_2 = new JTextField();
		textField_2.setBounds(163, 110, 116, 22);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(138, 271, 97, 25);
		getContentPane().add(btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(373, 271, 97, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblFechaInicioFase = new JLabel("Fecha fin fase 1:");
		lblFechaInicioFase.setBounds(321, 80, 116, 16);
		getContentPane().add(lblFechaInicioFase);
		
		JLabel lblFechaInicioFase_1 = new JLabel("Fecha fin fase 2:");
		lblFechaInicioFase_1.setBounds(321, 113, 116, 16);
		getContentPane().add(lblFechaInicioFase_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(456, 77, 116, 22);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(456, 110, 116, 22);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(33, 153, 118, 16);
		getContentPane().add(lblDescripcin);
		
		textField_5 = new JTextField();
		textField_5.setBounds(35, 182, 534, 78);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblMinimoDeAdherentes = new JLabel("Minimo de adherentes: ");
		lblMinimoDeAdherentes.setBounds(321, 45, 136, 16);
		getContentPane().add(lblMinimoDeAdherentes);
		
		txtMinAdherentes = new JTextField();
		txtMinAdherentes.setBounds(456, 42, 116, 22);
		getContentPane().add(txtMinAdherentes);
		txtMinAdherentes.setColumns(10);
		
		JComboBox cbTipoProceso = new JComboBox();
		cbTipoProceso.setBounds(163, 43, 116, 20);
		getContentPane().add(cbTipoProceso);

	}
}
