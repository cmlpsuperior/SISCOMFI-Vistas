package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JComboBox;

public class VistaBorrarPartido extends JInternalFrame {
		
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private VistaBorrarPartido me= this;
	private JFrame miPadre;	
	private VistaBuscarPartido vBuscarPartido; 
	
	public VistaBorrarPartido(JFrame padre) {
		setPadre(padre); //seteo el padre
		
		setTitle("Borrar partido politico");
		setClosable(true);
		setBounds(100, 100, 459, 254);
		getContentPane().setLayout(null);
		
		JButton tbnBorrar = new JButton("Borrar");
		tbnBorrar.setBounds(72, 186, 113, 23);
		getContentPane().add(tbnBorrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(257, 186, 113, 23);
		getContentPane().add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "PARTIDO POLITICO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 421, 164);
		getContentPane().add(panel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 52, 125, 14);
		panel.add(lblNombre);
		
		JLabel label_1 = new JLabel("Codigo");
		label_1.setBounds(10, 27, 125, 14);
		panel.add(label_1);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(10, 77, 125, 14);
		panel.add(lblDireccion);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(145, 24, 102, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(145, 49, 266, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(145, 74, 266, 20);
		panel.add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setBounds(145, 99, 266, 20);
		panel.add(comboBox);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(10, 102, 125, 14);
		panel.add(lblDepartamento);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 127, 125, 14);
		panel.add(lblProvincia);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setEnabled(false);
		comboBox_1.setBounds(145, 124, 266, 20);
		panel.add(comboBox_1);
		
		JButton button = new JButton("Buscar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vBuscarPartido = new VistaBuscarPartido ();
				miPadre.getContentPane().add(vBuscarPartido);
				vBuscarPartido.setVisible(true);
			}
		});
		button.setBounds(293, 23, 118, 23);
		panel.add(button);

	}

	public void setPadre (JFrame padre){
		miPadre = padre;
	}
}
