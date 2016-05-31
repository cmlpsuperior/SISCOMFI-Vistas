package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VistaIniciarProceso extends JInternalFrame implements ActionListener{
	private JTextField txtRuta;
	private JTextField txtFase;
	private JComboBox<String> cbPartido;
	private JButton btnRuta;
	private JButton btnCancelar;
	private JButton btnProcesar;
	private JFileChooser jfcRuta;
	private JComboBox cbDescProceso;	

	public VistaIniciarProceso() {
		setClosable(true);
		setTitle("Iniciar Proceso");
		setBounds(100, 100, 386, 292);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Partido Pol\u00EDtico:");
		lblNewLabel.setBounds(12, 108, 126, 16);
		getContentPane().add(lblNewLabel);
		
        cbPartido = new JComboBox<String>();
		cbPartido.setBounds(168, 106, 173, 20);
		getContentPane().add(cbPartido);
		fillCustomerCmb();
		
		cbDescProceso = new JComboBox();
		cbDescProceso.setBounds(168, 35, 173, 22);
		getContentPane().add(cbDescProceso);
		fillDescProcesoCmb();
		
		JLabel lblRuta = new JLabel("Ruta:");
		lblRuta.setBounds(12, 144, 85, 16);
		getContentPane().add(lblRuta);
		
		txtRuta = new JTextField();
		txtRuta.setBounds(168, 141, 116, 22);
		getContentPane().add(txtRuta);
		txtRuta.setColumns(10);
		
		btnRuta = new JButton("...");
		btnRuta.setBounds(252, 95, 45, 25);
		getContentPane().add(btnRuta);
		
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
		btnProcesar.setBounds(85, 207, 97, 25);
		getContentPane().add(btnProcesar);
		
		txtFase = new JTextField();
		txtFase.setEditable(false);
		txtFase.setText("Fase 1");
		txtFase.setBounds(168, 70, 170, 22);
		getContentPane().add(txtFase);
		txtFase.setColumns(10);
				
		JLabel lblFaseDelProceso = new JLabel("Fase del proceso:");
		lblFaseDelProceso.setBounds(12, 73, 108, 16);
		getContentPane().add(lblFaseDelProceso);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(216, 207, 97, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblDescripcionDelProceso = new JLabel("descripcion del proceso");
		lblDescripcionDelProceso.setBounds(12, 38, 143, 16);
		getContentPane().add(lblDescripcionDelProceso);
		
		

		//listener
		btnCancelar.addActionListener(this);
		btnRuta.addActionListener(this);
		btnProcesar.addActionListener(this);
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
	
	public void fillDescProcesoCmb(){ 
		cbDescProceso.removeAllItems();
		ArrayList<Proceso> ProcesoList;
		try {
			ProcesoList = siscomfiManager.queryAllProcesos(); //en realidad solo veremos su descripcion
			for (int i=0; i<ProcesoList.size();i++){				
				Proceso pro = (Proceso)ProcesoList.get(i);
				cbDescProceso.addItem(pro.getIdProceso() + " - " + pro.getDescripción());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar){
			this.dispose();
		}
		
		if (e.getSource() == btnRuta){
			jfcRuta =  new JFileChooser();
			jfcRuta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jfcRuta.showOpenDialog(this);
			File fEscogido = jfcRuta.getSelectedFile();
			txtRuta.setText(fEscogido.getPath());
		}
	}
}
