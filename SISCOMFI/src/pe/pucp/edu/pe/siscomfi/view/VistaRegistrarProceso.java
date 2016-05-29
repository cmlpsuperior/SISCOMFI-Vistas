package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import org.jdatepicker.DefaultComponentFactory;
import org.jdatepicker.JDatePicker;

import javax.swing.JButton;
import javax.swing.JPanel;

public class VistaRegistrarProceso extends JInternalFrame {
	private JTextField textField_5;
	private JTextField txtMinAdherentes;
	private JDatePicker picker1, picker2, picker3, picker4;

	public VistaRegistrarProceso() {
		setClosable(true);
		setTitle("Registrar nuevo proceso");
		setBounds(100, 100, 614, 341);
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
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(138, 271, 97, 25);
		getContentPane().add(btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(373, 271, 97, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblFechaInicioFase = new JLabel("Fecha fin fase 1:");
		lblFechaInicioFase.setBounds(321, 80, 116, 16);
		getContentPane().add(lblFechaInicioFase);
		
		JLabel lblFechaInicioFase_1 = new JLabel("Fecha fin fase 2:");
		lblFechaInicioFase_1.setBounds(321, 113, 116, 16);
		getContentPane().add(lblFechaInicioFase_1);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(33, 153, 118, 16);
		getContentPane().add(lblDescripcin);
		
		textField_5 = new JTextField();
		textField_5.setBounds(35, 182, 534, 78);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblMinimoDeAdherentes = new JLabel("Minimo de adherentes: ");
		lblMinimoDeAdherentes.setBounds(321, 45, 136, 16);
		getContentPane().add(lblMinimoDeAdherentes);
		
		txtMinAdherentes = new JTextField();
		txtMinAdherentes.setBounds(456, 42, 116, 22);
		getContentPane().add(txtMinAdherentes);
		txtMinAdherentes.setColumns(10);
		
		JComboBox cbTipoProceso = new JComboBox();
		cbTipoProceso.setBounds(163, 43, 116, 20);
		getContentPane().add(cbTipoProceso);
		
		JPanel ppicker1_2 = new JPanel();
		ppicker1_2.setBounds(163, 106, 178, 63);
		getContentPane().add(ppicker1_2);
		ppicker1_2.add((JComponent)picker2);
		
		JPanel ppicker1_1 = new JPanel();
		ppicker1_1.setBounds(163, 73, 116, 23);
		getContentPane().add(ppicker1_1);
		ppicker1_1.add((JComponent)picker1);
		
		JPanel ppicker2_1 = new JPanel();
		ppicker2_1.setBounds(432, 74, 116, 23);
		getContentPane().add(ppicker2_1);
		ppicker2_1.add((JComponent)picker3);
		
		JPanel ppicker2_2 = new JPanel();
		ppicker2_2.setBounds(432, 109, 116, 23);
		getContentPane().add(ppicker2_2);
		ppicker2_2.add((JComponent)picker4);
	}
}
