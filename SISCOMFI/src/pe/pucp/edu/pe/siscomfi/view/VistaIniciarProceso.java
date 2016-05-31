package pe.pucp.edu.pe.siscomfi.view;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;

import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;

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
	
	public VistaIniciarProceso() {
		setClosable(true);
		setTitle("Iniciar Proceso");
		setBounds(100, 100, 326, 255);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Partido Pol\u00EDtico:");
		lblNewLabel.setBounds(12, 63, 126, 16);
		getContentPane().add(lblNewLabel);
		
        cbPartido = new JComboBox<String>();
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
		txtRuta.setEditable(false);
		getContentPane().add(txtRuta);
		txtRuta.setColumns(10);
		
		btnRuta = new JButton("...");
		btnRuta.setBounds(252, 95, 45, 25);
		getContentPane().add(btnRuta);
		
		btnProcesar = new JButton("Procesar");
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
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(172, 162, 97, 25);
		getContentPane().add(btnCancelar);

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
