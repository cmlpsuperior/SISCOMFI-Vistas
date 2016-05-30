package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.Rol; //aca tiene que ir tipoProceso
import pe.pucp.edu.pe.siscomfi.model.TipoProceso;
import pe.pucp.edu.pe.siscomfi.model.Proceso;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;

import javax.swing.UIManager;

import org.jdatepicker.DefaultComponentFactory;
import org.jdatepicker.JDatePicker;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VistaIniciarProceso extends JInternalFrame {
	private JTextField txtRuta;
	private JTextField txtFase;
	private JComboBox cbPartido;
	

	public VistaIniciarProceso() {
		setClosable(true);
		setTitle("Iniciar Proceso");
		setBounds(100, 100, 326, 255);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Partido Pol\u00EDtico:");
		lblNewLabel.setBounds(12, 63, 126, 16);
		getContentPane().add(lblNewLabel);
		
        cbPartido = new JComboBox();
		cbPartido.setBounds(185, 43, 198, 20);
		getContentPane().add(cbPartido);
		fillCustomerCmb();
		
		cbPartido.setBounds(124, 60, 170, 22);
		getContentPane().add(cbPartido);
		
		JLabel lblRuta = new JLabel("Ruta:");
		lblRuta.setBounds(12, 99, 85, 16);
		getContentPane().add(lblRuta);
		
		txtRuta = new JTextField();
		txtRuta.setBounds(124, 96, 116, 22);
		getContentPane().add(txtRuta);
		txtRuta.setColumns(10);
		
		JButton button = new JButton("...");
		button.setBounds(252, 95, 45, 25);
		getContentPane().add(button);
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Proceso p = new Proceso();
				//p.setDescripción("Proceso iniciado por " + (String)cbPartido.getSelectedItem());
				/*p.setFechaProceso1Inicio(fechaProceso1Inicio);
				p.setFechaProceso1Fin(fechaProceso1Fin);
				p.setFechaProceso2Inicio(fechaProceso2Inicio);
				p.setFechaProceso2Fin(fechaProceso2Fin);*/
			
			}
		});
		btnProcesar.setBounds(41, 162, 97, 25);
		getContentPane().add(btnProcesar);
		
		txtFase = new JTextField();
		txtFase.setEditable(false);
		txtFase.setText("Fase 1");
		txtFase.setBounds(124, 25, 170, 22);
		getContentPane().add(txtFase);
		txtFase.setColumns(10);
				
		JLabel lblFaseDelProceso = new JLabel("Fase del proceso:");
		lblFaseDelProceso.setBounds(12, 28, 108, 16);
		getContentPane().add(lblFaseDelProceso);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(172, 162, 97, 25);
		getContentPane().add(btnCancelar);

	}
	
	public void fillCustomerCmb(){ //mostrare solo los clientes que estan activos
		cbPartido.removeAllItems();
		ArrayList<PartidoPolitico> PartidoPoliticoList;
		try {
			PartidoPoliticoList = siscomfiManager.queryAllPartidos();
			for (int i=0; i<PartidoPoliticoList.size();i++){				
				PartidoPolitico pp = (PartidoPolitico)PartidoPoliticoList.get(i);
				cbPartido.addItem(pp.getIdPartidoPolitco() + " - " + pp.getNombrePartido());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
