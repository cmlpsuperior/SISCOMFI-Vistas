package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class VistaReporte extends JInternalFrame implements ActionListener{
	private JTable tblReporte;
	private JComboBox cmbTipo;
	private JComboBox cmbFase;
	private JComboBox cmbUbicacion;
	private JComboBox cmbAnio;
	private JComboBox cmbEstadoP;
	private JButton btnGenerar;
	private JButton btnExportar;
	private JButton btnCancelar;
	private MyTableModel reporteModel;
	
	public VistaReporte() {
		setTitle("Reportes - Partido Pol\u00EDtico");
		setBounds(100, 100, 457, 354);
		setClosable(true);
		getContentPane().setLayout(null);
		
		JLabel lblFase = new JLabel("Fase:");
		lblFase.setBounds(264, 11, 73, 14);
		getContentPane().add(lblFase);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 11, 46, 14);
		getContentPane().add(lblTipo);
		
		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setBounds(10, 40, 46, 14);
		getContentPane().add(lblAo);
		
		JLabel lblEstadoDelPartido = new JLabel("Estado del Partido:");
		lblEstadoDelPartido.setBounds(10, 71, 113, 14);
		getContentPane().add(lblEstadoDelPartido);
		
		JLabel lblUbicacin = new JLabel("Ubicaci\u00F3n:");
		lblUbicacin.setBounds(264, 40, 73, 14);
		getContentPane().add(lblUbicacin);
		
		cmbTipo = new JComboBox();
		cmbTipo.addItem("Nacional");
		cmbTipo.addItem("Regional");
		cmbTipo.addItem("Municipal");
		cmbTipo.addItem("Institucional");
		cmbTipo.setBounds(121, 8, 104, 20);
		getContentPane().add(cmbTipo);
		
		cmbAnio = new JComboBox();
		Calendar c = Calendar.getInstance();
		int anio = c.get(Calendar.YEAR);
		for(int i = anio - 10; i <= anio + 20;i++){
			cmbAnio.addItem(""+i);
		}
		cmbAnio.setBounds(121, 37, 104, 20);
		getContentPane().add(cmbAnio);
		
		cmbEstadoP = new JComboBox();
		cmbEstadoP.setBounds(121, 68, 104, 20);
		getContentPane().add(cmbEstadoP);
		
		cmbFase = new JComboBox();
		cmbFase.addItem("Fase 1");
		cmbFase.addItem("Fase 2");
		cmbFase.setBounds(328, 8, 104, 20);
		getContentPane().add(cmbFase);
		
		cmbUbicacion = new JComboBox();
		cmbUbicacion.setBounds(328, 37, 104, 20);
		getContentPane().add(cmbUbicacion);
		
		btnGenerar = new JButton("Generar");
		btnGenerar.setBounds(328, 67, 89, 23);
		getContentPane().add(btnGenerar);
		
		JPanel pnTabla = new JPanel();
		pnTabla.setBounds(10, 96, 422, 163);
		getContentPane().add(pnTabla);
		pnTabla.setLayout(null);
		
		JScrollPane spnTabla = new JScrollPane();
		spnTabla.setBounds(0, 0, 422, 163);
		pnTabla.add(spnTabla);
		
		tblReporte = new JTable();
		reporteModel = new MyTableModel();
		tblReporte.setModel(reporteModel);
		
		spnTabla.setViewportView(tblReporte);
				
		btnExportar = new JButton("Exportar");
		btnExportar.setBounds(34, 279, 89, 23);
		getContentPane().add(btnExportar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(328, 279, 89, 23);
		getContentPane().add(btnCancelar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCancelar){
			this.dispose();
		}
		if (e.getSource() == btnGenerar){
			String tipo = cmbTipo.getSelectedItem().toString();
			String ubicacion = cmbUbicacion.getSelectedItem().toString();
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
}
