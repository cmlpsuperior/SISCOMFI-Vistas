package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import org.jdatepicker.DefaultComponentFactory;
import org.jdatepicker.JDatePicker;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.TipoProceso;
import pe.pucp.edu.pe.siscomfi.model.Proceso;
import pe.pucp.edu.pe.siscomfi.model.Rol;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class VistaRegistrarProceso extends JInternalFrame {
	private JTextField txtDescripcion;
	private JTextField txtMinAdherentes;
	private JDatePicker picker1, picker2, picker3, picker4;
	private JComboBox cbTipoProceso;
	
	public VistaRegistrarProceso() {
		setClosable(true);
		setTitle("Registrar nuevo proceso");
		setBounds(100, 100, 819, 354);
		getContentPane().setLayout(null);
		
		picker1 = new DefaultComponentFactory().createJDatePicker();
		picker1.setTextEditable(true);
	    picker1.setShowYearButtons(true);
		
	    picker2 = new DefaultComponentFactory().createJDatePicker();
		picker2.setTextEditable(true);
	    picker2.setShowYearButtons(true);
	    
	    picker3 = new DefaultComponentFactory().createJDatePicker();
		picker3.setTextEditable(true);
		picker3.setShowYearButtons(true);
	    
	    picker4 = new DefaultComponentFactory().createJDatePicker();
	    picker4.setTextEditable(true);
	    picker4.setShowYearButtons(true);
	    
	  //JDATE PICKER 1 IZQUIERDA
	    //JPanel jPanel1 = new JPanel();
	    /*jPanel1.setBounds(10, 28, 212, 40);
	    getContentPane().add(jPanel1);
	    jPanel1.add((JComponent)picker1);
		*/
		
		JLabel lblTipoProceso = new JLabel("Tipo Proceso:");
		lblTipoProceso.setBounds(33, 45, 83, 16);
		getContentPane().add(lblTipoProceso);
		
		JLabel lblFase = new JLabel("Fecha inicio fase 1:");
		lblFase.setBounds(33, 80, 118, 16);
		getContentPane().add(lblFase);
		
		JLabel lblFechaTopeFase = new JLabel("Fecha inicio fase 2:");
		lblFechaTopeFase.setBounds(33, 113, 118, 16);
		getContentPane().add(lblFechaTopeFase);
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(461, 273, 97, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblFechaInicioFase = new JLabel("Fecha fin fase 1:");
		lblFechaInicioFase.setBounds(407, 80, 116, 16);
		getContentPane().add(lblFechaInicioFase);
		
		JLabel lblFechaInicioFase_1 = new JLabel("Fecha fin fase 2:");
		lblFechaInicioFase_1.setBounds(407, 113, 116, 16);
		getContentPane().add(lblFechaInicioFase_1);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(33, 153, 118, 16);
		getContentPane().add(lblDescripcin);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(33, 182, 734, 78);
		getContentPane().add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblMinimoDeAdherentes = new JLabel("Mínimo de adherentes: ");
		lblMinimoDeAdherentes.setBounds(407, 45, 136, 16);
		getContentPane().add(lblMinimoDeAdherentes);
		
		txtMinAdherentes = new JTextField();
		txtMinAdherentes.setBounds(569, 42, 198, 22);
		getContentPane().add(txtMinAdherentes);
		txtMinAdherentes.setColumns(10);
		
		cbTipoProceso = new JComboBox();
		cbTipoProceso.setBounds(185, 43, 198, 20);
		getContentPane().add(cbTipoProceso);
		fillCustomerCmb();
		
		JPanel ppicker1_2 = new JPanel();
		ppicker1_2.setBounds(163, 106, 244, 35);
		getContentPane().add(ppicker1_2);
		ppicker1_2.add((JComponent)picker2);
		
		JPanel ppicker1_1 = new JPanel();
		ppicker1_1.setBounds(163, 73, 244, 35);
		getContentPane().add(ppicker1_1);
		ppicker1_1.add((JComponent)picker1);
		
		JPanel ppicker2_1 = new JPanel();
		ppicker2_1.setBounds(555, 71, 226, 37);
		getContentPane().add(ppicker2_1);
		ppicker2_1.add((JComponent)picker3);
		
		JPanel ppicker2_2 = new JPanel();
		ppicker2_2.setBounds(555, 106, 226, 35);
		getContentPane().add(ppicker2_2);
		ppicker2_2.add((JComponent)picker4);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proceso p = new Proceso();
				p.setDescripción(txtDescripcion.getText());
				try {
					//formato de extracción de fecha inicio fase 1
					int day1 = picker1.getModel().getDay();
					int month1 = picker1.getModel().getMonth();
					int year1 = picker1.getModel().getYear();
					
					String day1S = String.format("%02d", day1); //convierto a String de 2 digitos y 4 digitos para el año
					String month1S = String.format("%02d", month1+1);
					String year1S = String.format("%04d", year1);
					
					String fecha1_1 = year1S + "-" + month1S + "-" + day1S; //los uno en un solo String con formato yyyy-MM-dd				
					
					SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
					Date fechaIniFase1 = formatter1.parse(fecha1_1);
					p.setFechaProceso1Inicio(fechaIniFase1); //aca recien asignamos
					
					//formato de extracción de fecha Inicio fase 2
					int day2 = picker2.getModel().getDay();
					int month2 = picker2.getModel().getMonth();
					int year2 = picker2.getModel().getYear();
					
					String day2S = String.format("%02d", day2); //convierto a String de 2 digitos y 4 digitos para el año
					String month2S = String.format("%02d", month2+1);
					String year2S = String.format("%04d", year2);
					
					String fecha1_2 = year2S + "-" + month2S + "-" + day2S; //los uno en un solo String con formato yyyy-MM-dd
					
					SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
					Date fechaIniFase2 = formatter2.parse(fecha1_2);
					p.setFechaProceso2Inicio(fechaIniFase2); //aca recien asignamos
					
					//formato de extracción de fecha fin fase 1 
					int day3 = picker3.getModel().getDay();
					int month3 = picker3.getModel().getMonth();
					int year3 = picker3.getModel().getYear();
					
					String day3S = String.format("%02d", day3); //convierto a String de 2 digitos y 4 digitos para el año
					String month3S = String.format("%02d", month3+1);
					String year3S = String.format("%04d", year3);
					
					String fecha2_1 = year3S + "-" + month3S + "-" + day3S; //los uno en un solo String con formato yyyy-MM-dd
					
					SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
					Date fechaFinFase1 = formatter3.parse(fecha2_1);
					p.setFechaProceso1Fin(fechaFinFase1); //aca recien asignamos				
					
					//formato de extracción de fecha fin fase 2
					int day4 = picker4.getModel().getDay();
					int month4 = picker4.getModel().getMonth();
					int year4 = picker4.getModel().getYear();
					
					String day4S = String.format("%02d", day4); //convierto a String de 2 digitos y 4 digitos para el año
					String month4S = String.format("%02d", month4+1);
					String year4S = String.format("%04d", year4);
					
					String fecha2_2 = year4S + "-" + month4S + "-" + day4S; //los uno en un solo String con formato yyyy-MM-dd
					
					SimpleDateFormat formatter4 = new SimpleDateFormat("yyyy-MM-dd");
					Date fechaFinFase2 = formatter4.parse(fecha2_2);
					p.setFechaProceso2Fin(fechaFinFase2); //aca recien asignamos	
					
					p.setIdTipoProceso( Integer.parseInt(cbTipoProceso.getSelectedItem().toString().substring(0, 1)));
					
					siscomfiManager.addProceso(p);
				}
				catch (Exception a) {
					a.printStackTrace();
				}
				
			}
		});
		btnRegistrar.setBounds(226, 273, 97, 25);
		getContentPane().add(btnRegistrar);
	}
	
	public void fillCustomerCmb(){ //mostrare solo los clientes que estan activos
		cbTipoProceso.removeAllItems();
		ArrayList<TipoProceso> TipoProcesoList;
		try {
			TipoProcesoList = siscomfiManager.queryAllTipoProcesos();
			for (int i=0; i<TipoProcesoList.size();i++){				
				TipoProceso tp = (TipoProceso)TipoProcesoList.get(i);
				cbTipoProceso.addItem(tp.getIdTipoProceso()+" - " + tp.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
