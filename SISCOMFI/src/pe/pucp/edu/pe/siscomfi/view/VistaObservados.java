package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.Adherente;
import pe.pucp.edu.pe.siscomfi.model.Departamento;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;

public class VistaObservados extends JInternalFrame {
	private JTextField txtCantObservados;
	private JTable tblAdherentes;
	private JTextField txtAdherente;
	private JTextField txtCodigoPlanillon;
	
	private JComboBox cmbPartido;
	private MyTableModel tableModelAdherentes ;

	public VistaObservados() {
		setClosable(true);
		setTitle("Adherentes observados");
		setBounds(100, 100, 432, 440);
		getContentPane().setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(153, 379, 113, 23);
		getContentPane().add(btnSalir);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "PARTIDO POLITICO", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 396, 81);
		getContentPane().add(panel);
		
		JLabel lblTotalObservados = new JLabel("Total observados");
		lblTotalObservados.setBounds(10, 49, 151, 14);
		panel.add(lblTotalObservados);
		
		JLabel lblPartidoPolitico = new JLabel("Partido politico");
		lblPartidoPolitico.setBounds(10, 24, 151, 14);
		panel.add(lblPartidoPolitico);
		
		txtCantObservados = new JTextField();
		txtCantObservados.setEditable(false);
		txtCantObservados.setColumns(10);
		txtCantObservados.setBounds(171, 46, 208, 20);
		panel.add(txtCantObservados);
		
		cmbPartido = new JComboBox();
		cmbPartido.setBounds(171, 21, 208, 20);
		panel.add(cmbPartido);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 228, 395, 140);
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		tblAdherentes = new JTable();
		scrollPane.setViewportView(tblAdherentes);
		tableModelAdherentes = new MyTableModel ();
		tblAdherentes.setModel(tableModelAdherentes);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "ADHERENTE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 103, 396, 114);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 27, 153, 14);
		panel_1.add(lblNombre);
		
		txtAdherente = new JTextField();
		txtAdherente.setEditable(false);
		txtAdherente.setBounds(173, 24, 204, 20);
		panel_1.add(txtAdherente);
		txtAdherente.setColumns(10);
		
		JButton btnAceptado = new JButton("Aceptado");
		btnAceptado.setBounds(42, 80, 134, 23);
		panel_1.add(btnAceptado);
		
		JLabel lblPlanillon = new JLabel("Planillon");
		lblPlanillon.setBounds(10, 52, 153, 14);
		panel_1.add(lblPlanillon);
		
		txtCodigoPlanillon = new JTextField();
		txtCodigoPlanillon.setEditable(false);
		txtCodigoPlanillon.setBounds(173, 49, 204, 20);
		panel_1.add(txtCodigoPlanillon);
		txtCodigoPlanillon.setColumns(10);
		
		JButton btnRechazado = new JButton("Rechazado");
		btnRechazado.setBounds(218, 80, 134, 23);
		panel_1.add(btnRechazado);
		
		LlenarCmbPartidosObservados();

	}
	
	
	class MyTableModel extends DefaultTableModel{
		ArrayList<Adherente> listaAdherente = null;
		String titles[] = {"DNI","NOMBRE","AP. PATERNO","AP. MATERNO"};
		
		/*
		public MyTableModel (){
			try {
				this.listaAdherente =  siscomfiManager.qu);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
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
	
	public void LlenarCmbPartidosObservados(){ //mostrare solo los clientes que estan activos
		cmbPartido.removeAllItems();
		ArrayList<PartidoPolitico> listaObservados;
		try {
			listaObservados = siscomfiManager.queryAllPartidosConObservados();
			for (int i=0; i<listaObservados.size();i++){				
				PartidoPolitico p = (PartidoPolitico)listaObservados.get(i);
				cmbPartido.addItem(p.getIdPartidoPolitco() + " - " + p.getNombrePartido());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	

}
