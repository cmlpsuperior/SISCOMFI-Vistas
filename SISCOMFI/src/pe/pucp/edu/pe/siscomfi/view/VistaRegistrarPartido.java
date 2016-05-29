package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class VistaRegistrarPartido extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	public VistaRegistrarPartido() {
		setClosable(true);
		setTitle("Registrar nuevo partido politico");
		setBounds(100, 100, 456, 381);
		getContentPane().setLayout(null);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(71, 313, 113, 23);
		getContentPane().add(btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(255, 313, 113, 23);
		getContentPane().add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "PARTIDO POLITICO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 421, 164);
		getContentPane().add(panel);
		
		JLabel label_1 = new JLabel("Nombre (*)");
		label_1.setBounds(10, 52, 125, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Codigo");
		label_2.setBounds(10, 27, 125, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Direccion (*)");
		label_3.setBounds(10, 77, 125, 14);
		panel.add(label_3);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(145, 24, 102, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(145, 49, 266, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(145, 74, 266, 20);
		panel.add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(145, 99, 266, 20);
		panel.add(comboBox);
		
		JLabel label_4 = new JLabel("Departamento (*)");
		label_4.setBounds(10, 102, 125, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Provincia (*)");
		label_5.setBounds(10, 127, 125, 14);
		panel.add(label_5);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(145, 124, 266, 20);
		panel.add(comboBox_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "REPRESENTANTE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 186, 421, 116);
		getContentPane().add(panel_1);
		
		JLabel label = new JLabel("Nombre");
		label.setBounds(10, 28, 125, 14);
		panel_1.add(label);
		
		JLabel label_6 = new JLabel("Correo electronico");
		label_6.setBounds(10, 53, 125, 14);
		panel_1.add(label_6);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(145, 25, 266, 20);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(145, 50, 266, 20);
		panel_1.add(textField_4);
		
		JLabel label_7 = new JLabel("Telefono");
		label_7.setBounds(10, 78, 125, 14);
		panel_1.add(label_7);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(145, 75, 266, 20);
		panel_1.add(textField_5);

	}
}
