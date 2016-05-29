package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

public class VistaBuscarPartido extends JInternalFrame {
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTable tblPartido;
	private JTextField textField;
	
	private MyTableModel tableModelPartidos;
	
	public VistaBuscarPartido() {
		setClosable(true);
		setTitle("Buscar partido politico");
		setBounds(100, 100, 384, 328);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 39, 94, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 14, 94, 14);
		getContentPane().add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(114, 11, 116, 20);
		getContentPane().add(txtCodigo);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(114, 36, 244, 20);
		getContentPane().add(txtNombre);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 122, 348, 129);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane spnPartido = new JScrollPane();
		panel.add(spnPartido);
		
		tblPartido = new JTable();
		spnPartido.setViewportView(tblPartido);
		tableModelPartidos = new MyTableModel();
		tblPartido.setModel(tableModelPartidos);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(10, 88, 89, 23);
		getContentPane().add(btnBuscar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(63, 262, 89, 23);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(215, 262, 89, 23);
		getContentPane().add(btnCancelar);
		
		JLabel lblRepresentante = new JLabel("Representante");
		lblRepresentante.setBounds(10, 63, 94, 14);
		getContentPane().add(lblRepresentante);
		
		textField = new JTextField();
		textField.setBounds(114, 60, 244, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

	}
	
	
	class MyTableModel extends DefaultTableModel{
		
		String titles[] = {"CODIGO","PARTIDO","REPRESENT.","TELEFONO"};
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return titles.length;
		}

		public String getColumnName(int col){
			return titles[col];
		}		

		@Override
		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}
			
	}
}
