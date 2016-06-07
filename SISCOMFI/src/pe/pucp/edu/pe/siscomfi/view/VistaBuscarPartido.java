package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VistaBuscarPartido extends JInternalFrame {
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTable tblPartido;
	private JTextField txtRepresentante;
	
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
		
		txtRepresentante = new JTextField();
		txtRepresentante.setBounds(114, 60, 244, 20);
		getContentPane().add(txtRepresentante);
		txtRepresentante.setColumns(10);
		
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
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PartidoPolitico pp = new PartidoPolitico();
				
				try{
					if (txtCodigo.getText().equals("")){						
						if (txtNombre.getText().equals("")){
							if (txtRepresentante.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Llene al menos un campo de busqueda");
							}
							else {
								//buscar por representante
								
							}
						}
						else {
							//buscar por Nombre del partido
						}
					}
					else {
						//buscar por ID de partido politico
						int idPartido = Integer.parseInt(txtCodigo.getText());
						//Integer.parseInt(cbTipoProceso.getSelectedItem().toString().substring(0, 1))
						//pp=siscomfiManager.queryPartidoById(idPartido);
						refreshTblPartidos_byId(idPartido);
						
					}
				}
				catch (Exception a) {
					a.printStackTrace();
				}
					
			}
		});
		btnBuscar.setBounds(10, 88, 89, 23);
		getContentPane().add(btnBuscar);
		
		JButton btnAceptar = new JButton("Aceptar");		
		btnAceptar.setBounds(63, 262, 89, 23);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(215, 262, 89, 23);
		getContentPane().add(btnCancelar);
		
		JLabel lblRepresentante = new JLabel("Representante");
		lblRepresentante.setBounds(10, 63, 94, 14);
		getContentPane().add(lblRepresentante);
		
	}
	
	
	class MyTableModel extends AbstractTableModel{
		ArrayList<PartidoPolitico> listaPartidos = null;
		String [] titles = {"CODIGO","PARTIDO","REPRESENT.","TELEFONO"};
		
		public MyTableModel (){
			try {
				this.listaPartidos =  siscomfiManager.queryAllPartidos();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return titles.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			String value = "";
			switch(col){
				case 0:  value = "" + listaPartidos.get(row).getIdPartidoPolitco(); break;
				case 1:  value = listaPartidos.get(row).getNombrePartido(); break;
				case 2:  value = "" + listaPartidos.get(row).getRepresentante(); break;	
				case 3:  value = "" + listaPartidos.get(row).getTelefono(); break;
				case 4:  value = "" + listaPartidos.get(row).getEstadoActivo(); break;
			}
			return value;
		}
		
		public String getColumnName(int col){
			return titles[col];
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return listaPartidos.size();
		}
			
	}
	
	public void refreshTblPartidos_byId(int idPartido){
		try {
			tableModelPartidos.listaPartidos.clear();
			tableModelPartidos.listaPartidos.add(siscomfiManager.queryPartidoById(idPartido));
			tableModelPartidos.fireTableChanged(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	
}
