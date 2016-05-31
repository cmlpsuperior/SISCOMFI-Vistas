package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.Departamento;
import pe.pucp.edu.pe.siscomfi.model.Distrito;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;
import pe.pucp.edu.pe.siscomfi.model.Provincia;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Image;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class VistaMantPartido extends JInternalFrame {
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtRepresentante;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private JTable tblPartidos;
	private JComboBox cmbDistrito;
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
		setTitle("Partidos politicos");
		setClosable(true);
		setBounds(100, 100, 740, 541);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "PARTIDO POLITICO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 346, 188);
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
		txtCodigo.setEditable(false);
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
		cmbDepartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cmbProvincia.removeAllItems();
				cmbDistrito.removeAllItems();
				int idDepartamento = 0;
				if (cmbDepartamento.getSelectedItem() != null){
					String[] tokens = cmbDepartamento.getSelectedItem().toString().split(" ");
					idDepartamento = Integer.parseInt(tokens[0]);//Integer.parseInt(cmbDepartamento.getSelectedItem().toString().substring(0,2));					
					LlenarCmbProvincia(idDepartamento);
				}
			}
		});
		cmbDepartamento.setBounds(145, 99, 190, 20);
		panel.add(cmbDepartamento);
		
		JLabel label_3 = new JLabel("Departamento (*)");
		label_3.setBounds(10, 102, 125, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Provincia (*)");
		label_4.setBounds(10, 127, 125, 14);
		panel.add(label_4);
		
		cmbProvincia = new JComboBox();
		cmbProvincia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cmbDistrito.removeAllItems();
				if (cmbProvincia.getSelectedItem() != null){
					String[] tokens = cmbProvincia.getSelectedItem().toString().split(" ");
					int idProvincia = Integer.parseInt(tokens[0]);
					//int idProvincia = Integer.parseInt(cmbProvincia.getSelectedItem().toString().substring(0,2));
					LlenarCmbDistrito(idProvincia);
				}
			}
		});
		cmbProvincia.setBounds(145, 124, 190, 20);
		panel.add(cmbProvincia);
		
		JLabel lblDistrito = new JLabel("Distrito (*)");
		lblDistrito.setBounds(10, 155, 125, 14);
		panel.add(lblDistrito);
		
		cmbDistrito = new JComboBox();
		cmbDistrito.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		cmbDistrito.setBounds(145, 152, 190, 20);
		panel.add(cmbDistrito);
		
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
		panel_2.setBounds(10, 244, 702, 196);
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		tblPartidos = new JTable();
		tblPartidos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selRow = tblPartidos.getSelectedRow();
				if(selRow >= 0){
					int idPartido = Integer.parseInt(tblPartidos.getValueAt(selRow, 0).toString());
					//AHORA:
					PartidoPolitico p=null;
					try {
						p = siscomfiManager.queryPartidoById(idPartido);
						txtCodigo.setText(p.getIdPartidoPolitco() + "");
						txtNombre.setText(p.getNombrePartido());
						txtDireccion.setText(p.getDireccion());
						
						txtRepresentante.setText(p.getRepresentante());
						txtCorreo.setText(p.getCorreo());
						txtTelefono.setText(p.getTelefono());
						
						}
					catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		
		scrollPane.setViewportView(tblPartidos);
		tableModelPartido = new MyTableModel();
		tblPartidos.setModel(tableModelPartido);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = txtNombre.getText();
					String direccion = txtDireccion.getText();
					String representante = txtRepresentante.getText();
					String correo = txtCorreo.getText();
					String telefono = txtTelefono.getText();
					
					if (nombre.equals("")){
						JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacio");
					}
					
					else if (direccion.equals("")) {
						JOptionPane.showMessageDialog(null, "El campo direccion no puede estar vacio");
					}
					else if (cmbDistrito.getSelectedItem() ==null){
						JOptionPane.showMessageDialog(null, "Debe seleccionar un distrito");
					}
					else{
						String[] tokens = cmbDistrito.getSelectedItem().toString().split(" ");
						int idDistrito = Integer.parseInt(tokens[0]);
						Date fechaRegistro = new Date(); //fecha actual
						PartidoPolitico p = new PartidoPolitico ();
						p.setNombrePartido(nombre);
						p.setDireccion(direccion);
						p.setIdDistrito(idDistrito);
						p.setRepresentante(representante);
						p.setCorreo(correo);
						p.setTelefono(telefono);
						p.setFechaRegistro(fechaRegistro);
						
						siscomfiManager.addPartido(p);
						
						JOptionPane.showMessageDialog(null, "Se registro el partido satisfactoriamente");
						LimpiarTextos ();
						refreshTblPartidos();
					}	
					
				} catch (Exception a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}
			}
		});
		btnRegistrar.setBounds(114, 210, 89, 23);
		getContentPane().add(btnRegistrar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(txtCodigo.getText());
				String nombre = txtNombre.getText();
				String direccion = txtDireccion.getText();
				int idDistrito = Integer.parseInt(cmbDistrito.getSelectedItem().toString().substring(0,1));
				String representante = txtRepresentante.getText();
				String correo = txtCorreo.getText();
				String telefono = txtTelefono.getText();
				
				PartidoPolitico p = new PartidoPolitico();
				p.setIdPartidoPolitco(id);
				p.setNombrePartido(nombre);
				p.setDireccion(direccion);
				p.setIdDistrito(idDistrito);
				p.setRepresentante(representante);
				p.setCorreo(correo);
				p.setTelefono(telefono);
								
				try {
					siscomfiManager.updatePartido(p);
					JOptionPane.showMessageDialog(null, "Se actualizo el partido satisfactoriamente");
					refreshTblPartidos();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnModificar.setBounds(317, 210, 89, 23);
		getContentPane().add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(null, "¿Está seguro?");
				if (res == JOptionPane.OK_OPTION) {
					try {
						siscomfiManager.deletePartido(Integer.parseInt(txtCodigo.getText()));
						LimpiarTextos();
						refreshTblPartidos();						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}						
				}
			}
		});
		btnEliminar.setBounds(520, 210, 89, 23);
		getContentPane().add(btnEliminar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(182, 451, 89, 23);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(453, 451, 89, 23);
		getContentPane().add(btnCancelar);
		
		
		//Llenamos el combobox
		LlenarCmbDepartamento();
		//LlenarCmbDistrito();
	}
	
	class MyTableModel extends AbstractTableModel{		
		ArrayList<PartidoPolitico> listaPartido = null;
		String [] titles = {"Codigo", "Nombre",  "Representante", "Telefono", "EstadoActivo" };
		
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
			return 5;
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
				case 4:  value = "" + listaPartido.get(row).getEstadoActivo(); break;
			}
			return value;
		}
		
		
		public String getColumnName(int col){
			return titles[col];
		}
		
	}
	
	public void LlenarCmbDistrito(int idProvincia){ //mostrare solo los clientes que estan activos
		cmbDistrito.removeAllItems();
		ArrayList<Distrito> listaDistrito;
		try {
			listaDistrito = siscomfiManager.queryDistritosByIdProvincia(idProvincia);
			for (int i=0; i<listaDistrito.size();i++){				
				Distrito d = (Distrito)listaDistrito.get(i);
				cmbDistrito.addItem(d.getIdDistrito()+" - " + d.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void LlenarCmbProvincia(int idDepartamento){ //mostrare solo los clientes que estan activos
		cmbProvincia.removeAllItems();
		ArrayList<Provincia> listaProvincia;
		try {
			listaProvincia = siscomfiManager.queryProvinciaByIdDepartamento(idDepartamento);
			for (int i=0; i<listaProvincia.size();i++){				
				Provincia p = (Provincia)listaProvincia.get(i);
				cmbProvincia.addItem(p.getIdProvincia()+" - " + p.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void LlenarCmbDepartamento(){ //mostrare solo los clientes que estan activos
		cmbDepartamento.removeAllItems();
		ArrayList<Departamento> listaDepartamento;
		try {
			listaDepartamento = siscomfiManager.queryAllDepartamento();
			for (int i=0; i<listaDepartamento.size();i++){				
				Departamento d = (Departamento)listaDepartamento.get(i);
				cmbDepartamento.addItem(d.getIdDepartamento()+ " - " + d.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void refreshTblPartidos(){
		try {
			tableModelPartido.listaPartido = siscomfiManager.queryAllPartidos();
			tableModelPartido.fireTableChanged(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void LimpiarTextos (){
		txtCodigo.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtRepresentante.setText("");
		txtCorreo.setText("");
		txtTelefono.setText("");
	}
}
