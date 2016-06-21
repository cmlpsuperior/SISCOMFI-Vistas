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
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;
import pe.pucp.edu.pe.siscomfi.model.Reporte;
import pe.pucp.edu.pe.siscomfi.model.TipoProceso;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.GridLayout;


public class VistaReporte extends JInternalFrame implements ActionListener{
	private JComboBox cmbTipo;
	private JComboBox cmbFase;
	private JComboBox cmbAnio;
	private JComboBox cmbEstadoP;
	private JButton btnGenerar;
	private JButton btnExportar;
	private JButton btnCancelar;
	private MyTableModel reporteModel;
	private JTable tblReporte;
	
	public VistaReporte() {
		setTitle("Reportes - Partido Pol\u00EDtico");
		setBounds(100, 100, 673, 328);
		setClosable(true);
		getContentPane().setLayout(null);
		
		JLabel lblFase = new JLabel("Fase:");
		lblFase.setBounds(340, 11, 113, 14);
		getContentPane().add(lblFase);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 11, 61, 14);
		getContentPane().add(lblTipo);
		
		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setBounds(10, 40, 61, 14);
		getContentPane().add(lblAo);
		
		JLabel lblEstadoDelPartido = new JLabel("Estado del Partido:");
		lblEstadoDelPartido.setBounds(340, 40, 113, 14);
		getContentPane().add(lblEstadoDelPartido);
		
		cmbTipo = new JComboBox();		
		cmbTipo.setBounds(81, 8, 184, 20);
		getContentPane().add(cmbTipo);
		
		cmbAnio = new JComboBox();
		Calendar c = Calendar.getInstance();
		int anio = c.get(Calendar.YEAR);
		for(int i = anio - 10; i <= anio ;i++){
			cmbAnio.addItem(""+i);
		}
		cmbAnio.setBounds(81, 37, 184, 20);
		getContentPane().add(cmbAnio);
		
		cmbEstadoP = new JComboBox();
		cmbEstadoP.addItem("0 - Rechazados");
		cmbEstadoP.addItem("1 - Aceptados");
		cmbEstadoP.setBounds(463, 37, 184, 20);
		getContentPane().add(cmbEstadoP);
		
		cmbFase = new JComboBox();
		cmbFase.addItem("Fase 1");
		cmbFase.addItem("Fase 2");
		cmbFase.setBounds(463, 8, 184, 20);
		getContentPane().add(cmbFase);
		
		btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Primero vemos si tiene idTipoProceso:
				int idTipoProceso = -1;
				String[] tokens = cmbTipo.getSelectedItem().toString().split(" ");
				if (tokens[0].equals("Todos")) 
					idTipoProceso = -1;
				else 
					idTipoProceso = Integer.parseInt(tokens[0]);				
				
				reporteModel.listaReporte = siscomfiManager.queryReportePartidos(idTipoProceso, -1, -1, -1);
				reporteModel.fireTableChanged(null);
			}
		});
		btnGenerar.setBounds(97, 253, 89, 23);
		getContentPane().add(btnGenerar);		
				
		btnExportar = new JButton("Exportar");
		btnExportar.setBounds(283, 253, 89, 23);
		getContentPane().add(btnExportar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(469, 253, 89, 23);
		getContentPane().add(btnCancelar);
		
		JPanel panTabla = new JPanel();
		panTabla.setBounds(10, 77, 637, 165);
		getContentPane().add(panTabla);
		panTabla.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panTabla.add(scrollPane);
		
		tblReporte = new JTable();
		scrollPane.setViewportView(tblReporte);		
		reporteModel = new MyTableModel();
		tblReporte.setModel(reporteModel);
		
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
	
	class MyTableModel extends AbstractTableModel{
		ArrayList<Reporte> listaReporte = null; //se inicializa vacio
		String titles[] = {"PARTIDO","ANIO","PROCESO","TIPO", "FASE 1", "FASE 2", "#ADHERE.", "ESTADO FINAL"};
		
		
		public MyTableModel (){
			try {
				this.listaReporte =  new ArrayList <Reporte>();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 8;
		}
		
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return listaReporte.size();
		}
	
		@Override
		public Object getValueAt(int row, int col) {
			String value = "";
			switch(col){
				case 0:  value = "" + listaReporte.get(row).getPartido(); break;
				case 1:  value = "" + listaReporte.get(row).getAnio(); break;
				case 2:  value = "" + listaReporte.get(row).getIdProceso(); break;	
				case 3:  value = "" + listaReporte.get(row).getTipoProceso(); break;
				case 4:  value = "" + listaReporte.get(row).getFase1(); break;
				case 5:  value = "" + listaReporte.get(row).getFase2(); break;
				case 6:  value = "" + listaReporte.get(row).getNumeroAdherentes(); break;
				case 7:  value = "" + listaReporte.get(row).getEstadoFinal(); break;				
			}
			return value;
		}
		
		@Override
		public String getColumnName(int col){
			return titles[col];
		}	
		
	}
	
	public void LlenarCmbTipoProceso(){ //mostrare solo los clientes que estan activos
		cmbTipo.removeAllItems();
		cmbTipo.addItem("Todos");
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
