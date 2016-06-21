package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.TipoProceso;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class VistaReporte extends JInternalFrame implements ActionListener{
	private JTable tblReporte;
	private JComboBox cmbTipo;
	private JComboBox cmbFase;
	private JComboBox cmbAnio;
	private JComboBox cmbEstadoP;
	private JButton btnGenerar;
	private JButton btnExportar;
	private JButton btnCancelar;
	private MyTableModel reporteModel;
	
	public VistaReporte() {
		setTitle("Reportes - Partido Pol\u00EDtico");
		setBounds(100, 100, 566, 328);
		setClosable(true);
		getContentPane().setLayout(null);
		
		JLabel lblFase = new JLabel("Fase:");
		lblFase.setBounds(264, 11, 113, 14);
		getContentPane().add(lblFase);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 11, 61, 14);
		getContentPane().add(lblTipo);
		
		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setBounds(10, 40, 61, 14);
		getContentPane().add(lblAo);
		
		JLabel lblEstadoDelPartido = new JLabel("Estado del Partido:");
		lblEstadoDelPartido.setBounds(264, 37, 113, 14);
		getContentPane().add(lblEstadoDelPartido);
		
		cmbTipo = new JComboBox();		
		cmbTipo.setBounds(81, 8, 154, 20);
		getContentPane().add(cmbTipo);
		
		cmbAnio = new JComboBox();
		Calendar c = Calendar.getInstance();
		int anio = c.get(Calendar.YEAR);
		for(int i = anio - 10; i <= anio ;i++){
			cmbAnio.addItem(""+i);
		}
		cmbAnio.setBounds(81, 37, 154, 20);
		getContentPane().add(cmbAnio);
		
		cmbEstadoP = new JComboBox();
		cmbEstadoP.addItem("0 - Rechazados");
		cmbEstadoP.addItem("1 - Aceptados");
		cmbEstadoP.setBounds(387, 34, 154, 20);
		getContentPane().add(cmbEstadoP);
		
		cmbFase = new JComboBox();
		cmbFase.addItem("Fase 1");
		cmbFase.addItem("Fase 2");
		cmbFase.setBounds(387, 8, 154, 20);
		getContentPane().add(cmbFase);
		
		btnGenerar = new JButton("Generar");
		btnGenerar.setBounds(70, 253, 89, 23);
		getContentPane().add(btnGenerar);
		reporteModel = new MyTableModel();
				
		btnExportar = new JButton("Exportar");
		btnExportar.setBounds(229, 253, 89, 23);
		getContentPane().add(btnExportar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(388, 253, 89, 23);
		getContentPane().add(btnCancelar);
		
		JScrollPane spnTabla = new JScrollPane();
		spnTabla.setBounds(10, 68, 531, 163);
		getContentPane().add(spnTabla);
		
		tblReporte = new JTable();
		tblReporte.setModel(reporteModel);
		
		spnTabla.setViewportView(tblReporte);
		
		//llenamos los combos:
		LlenarCmbTipoProceso();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCancelar){
			this.dispose();
		}
		if (e.getSource() == btnGenerar){
			String tipo = cmbTipo.getSelectedItem().toString();
			//String ubicacion = cmbUbicacion.getSelectedItem().toString();
			String estadoP = cmbEstadoP.getSelectedItem().toString();
			String fase = cmbFase.getSelectedItem().toString();
			int anio = Integer.parseInt(cmbAnio.getSelectedItem().toString());
			
		}
	}
	
	class MyTableModel extends DefaultTableModel{
		
		String titles[] = {"NOMBRE","REPRESENTANTE","ESTADO","PORC."};
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
	
	public void LlenarCmbTipoProceso(){ //mostrare solo los clientes que estan activos
		cmbTipo.removeAllItems();
		ArrayList<TipoProceso> listaTipoProceso;
		try {
			listaTipoProceso = siscomfiManager.queryAllTipoProcesos();
			for (int i=0; i<listaTipoProceso.size();i++){				
				TipoProceso t = (TipoProceso)listaTipoProceso.get(i);
				cmbTipo.addItem(t.getIdTipoProceso()+ " - " + t.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void LlenarCmbAnio(){ //mostrare los años en el que se tienen procesos
		cmbAnio.removeAllItems();
		ArrayList<Integer> listaAnio;
		try {
			listaAnio = new ArrayList<Integer> (); //siscomfiManager.ObtenerAniosProcesos();
			for (int i=0; i<listaAnio.size();i++){				
				Integer t =  (Integer)listaAnio.get(i);
				cmbAnio.addItem(t.intValue() + "");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
