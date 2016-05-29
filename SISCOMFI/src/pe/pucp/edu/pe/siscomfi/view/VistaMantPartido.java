package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaMantPartido extends JInternalFrame {
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtRepresentante;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private JTable tblPartidos;
	private JComboBox cmbProvincia;
	private JComboBox cmbDepartamento;
	
	/*Comentario para probar los cambios del github*/
	private MyTableModel tableModelPartido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaMantPartido frame = new VistaMantPartido();
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
	public VistaMantPartido() {
		setClosable(true);
		setBounds(100, 100, 740, 541);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "PARTIDO POLITICO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 346, 164);
		getContentPane().add(panel);
		
		JLabel label = new JLabel("Nombre (*)");
		label.setBounds(10, 52, 125, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Codigo");
		label_1.setBounds(10, 27, 125, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Direccion (*)");
		label_2.setBounds(10, 77, 125, 14);
		panel.add(label_2);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(145, 24, 102, 20);
		panel.add(txtCodigo);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(145, 49, 190, 20);
		panel.add(txtNombre);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(145, 74, 190, 20);
		panel.add(txtDireccion);
		
		cmbDepartamento = new JComboBox();
		cmbDepartamento.setBounds(145, 99, 190, 20);
		panel.add(cmbDepartamento);
		
		JLabel label_3 = new JLabel("Departamento (*)");
		label_3.setBounds(10, 102, 125, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Provincia (*)");
		label_4.setBounds(10, 127, 125, 14);
		panel.add(label_4);
		
		cmbProvincia = new JComboBox();
		cmbProvincia.setBounds(145, 124, 190, 20);
		panel.add(cmbProvincia);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "REPRESENTANTE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(366, 11, 346, 116);
		getContentPane().add(panel_1);
		
		JLabel label_5 = new JLabel("Nombre");
		label_5.setBounds(10, 28, 125, 14);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("Correo electronico");
		label_6.setBounds(10, 53, 125, 14);
		panel_1.add(label_6);
		
		txtRepresentante = new JTextField();
		txtRepresentante.setColumns(10);
		txtRepresentante.setBounds(145, 25, 190, 20);
		panel_1.add(txtRepresentante);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(145, 50, 190, 20);
		panel_1.add(txtCorreo);
		
		JLabel label_7 = new JLabel("Telefono");
		label_7.setBounds(10, 78, 125, 14);
		panel_1.add(label_7);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(145, 75, 190, 20);
		panel_1.add(txtTelefono);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 220, 702, 220);
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		tblPartidos = new JTable();
		scrollPane.setViewportView(tblPartidos);
		tableModelPartido = new MyTableModel();
		tblPartidos.setModel(tableModelPartido);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = txtNombre.getText();
					String direccion = txtDireccion.getText();
					int idprovincia = Integer.parseInt(cmbProvincia.getSelectedItem().toString().substring(0,1));
					String representante = txtRepresentante.getText();
					String correo = txtCorreo.getText();
					String telefono = txtTelefono.getText();
					
					PartidoPolitico p = new PartidoPolitico ();
					p.setNombrePartido(nombre);
					p.setDireccion(direccion);
					p.setIdProvincia(idprovincia);
					p.setRepresentante(representante);
					p.setCorreo(correo);
					p.setTelefono(telefono);
					
					siscomfiManager.addPartido(p);
					
					LimpiarTextos ();
					
				} catch (Exception a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}
			}
		});
		btnRegistrar.setBounds(150, 186, 89, 23);
		getContentPane().add(btnRegistrar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(323, 186, 89, 23);
		getContentPane().add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(498, 186, 89, 23);
		getContentPane().add(btnEliminar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(208, 451, 89, 23);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(366, 451, 89, 23);
		getContentPane().add(btnCancelar);

	}
	
	class MyTableModel extends AbstractTableModel{		
		ArrayList<PartidoPolitico> listaPartido = null;
		String [] titles = {"Codigo", "Nombre",  "Representante", "Telefono" };
		
		public MyTableModel (){
			try {
				this.listaPartido =  siscomfiManager.queryAllPartidos();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return listaPartido.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			String value = "";
			switch(col){
				case 0:  value = "" + listaPartido.get(row).getIdPartidoPolitco(); break;
				case 1:  value = listaPartido.get(row).getNombrePartido(); break;
				case 2:  value = "" + listaPartido.get(row).getRepresentante(); break;	
				case 3:  value = "" + listaPartido.get(row).getTelefono(); break;
			}
			return value;
		}
		
		public String getColumnName(int col){
			return titles[col];
		}
		
	}
	
	public void LimpiarTextos (){
		txtNombre.setText("");
		txtDireccion.setText("");
		txtRepresentante.setText("");
		txtCorreo.setText("");
		txtTelefono.setText("");
	}
	
}