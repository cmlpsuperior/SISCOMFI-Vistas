package pe.pucp.edu.pe.siscomfi.view;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;

import pe.pucp.edu.pe.siscomfi.algoritmo.HelperMethods;
import pe.pucp.edu.pe.siscomfi.algoritmo.OcrProy;
import pe.pucp.edu.pe.siscomfi.algoritmo.PruebaNuevoOCR;
import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.Proceso;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;

import javax.swing.UIManager;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.Duplicator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class VistaIniciarProceso extends JInternalFrame implements ActionListener {
	private JTextField txtRuta;
	private JTextField txtFase;
	private JComboBox<String> cbPartido;
	private JButton btnRuta;
	private JButton btnCancelar;
	private JButton btnProcesar;
	private JFileChooser jfcRuta;
	private JComboBox<String> cbDescProceso;
	private JTextArea txtLog;
	private String pathPadronProcesar =null;
	private File[] padronPaths=  null;
	private JScrollPane scpLog;
	private JProgressBar pgBar;
	private JPanel pnLog;

	public VistaIniciarProceso() {
		setClosable(true);
		setTitle("Iniciar Proceso");
		setBounds(100, 100, 436, 499);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Partido Pol\u00EDtico:");
		lblNewLabel.setBounds(12, 108, 126, 16);
		getContentPane().add(lblNewLabel);

		cbPartido = new JComboBox<String>();
		cbPartido.setBounds(168, 106, 235, 20);
		getContentPane().add(cbPartido);
		fillCustomerCmb();

		cbDescProceso = new JComboBox<String>();
		cbDescProceso.setBounds(168, 35, 235, 22);
		getContentPane().add(cbDescProceso);
		fillDescProcesoCmb();

		JLabel lblRuta = new JLabel("Ruta:");
		lblRuta.setBounds(12, 144, 85, 16);
		getContentPane().add(lblRuta);

		txtRuta = new JTextField();
		txtRuta.setBounds(168, 141, 180, 22);
		txtRuta.setEditable(false);
		getContentPane().add(txtRuta);
		txtRuta.setColumns(10);

		btnRuta = new JButton("...");
		btnRuta.setBounds(358, 140, 45, 25);
		getContentPane().add(btnRuta);

		btnProcesar = new JButton("Procesar");
		btnProcesar.setBounds(75, 190, 97, 25);
		getContentPane().add(btnProcesar);

		txtFase = new JTextField();
		txtFase.setEditable(false);
		txtFase.setText("Fase 1");
		txtFase.setBounds(168, 70, 235, 22);
		getContentPane().add(txtFase);
		txtFase.setColumns(10);

		JLabel lblFaseDelProceso = new JLabel("Fase del proceso:");
		lblFaseDelProceso.setBounds(12, 73, 108, 16);
		getContentPane().add(lblFaseDelProceso);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(230, 190, 97, 25);
		getContentPane().add(btnCancelar);

		JLabel lblDescripcionDelProceso = new JLabel("Descripcion del proceso:");
		lblDescripcionDelProceso.setBounds(12, 38, 143, 16);
		getContentPane().add(lblDescripcionDelProceso);

		pnLog = new JPanel();
		pnLog.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Log", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnLog.setBounds(31, 278, 353, 173);
		getContentPane().add(pnLog);
		pnLog.setLayout(null);

		scpLog = new JScrollPane();
		scpLog.setBounds(6, 16, 341, 150);
		pnLog.add(scpLog);

		txtLog = new JTextArea();
		scpLog.setViewportView(txtLog);
		txtLog.setEditable(false);
		txtLog.setText("");

		pgBar = new JProgressBar();
		pgBar.setBounds(63, 250, 299, 17);
		getContentPane().add(pgBar);

		// listener
		btnCancelar.addActionListener(this);
		btnRuta.addActionListener(this);
		btnProcesar.addActionListener(this);
	}

	public void fillCustomerCmb() {
		cbPartido.removeAllItems();
		ArrayList<PartidoPolitico> PartidoPoliticoList;
		try {
			PartidoPoliticoList = siscomfiManager.queryAllPartidos();
			for (int i = 0; i < PartidoPoliticoList.size(); i++) {
				PartidoPolitico pp = (PartidoPolitico) PartidoPoliticoList.get(i);
				cbPartido.addItem(pp.getIdPartidoPolitico() + " - " + pp.getNombrePartido());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void fillDescProcesoCmb() {
		cbDescProceso.removeAllItems();
		ArrayList<Proceso> ProcesoList;
		try {
			ProcesoList = siscomfiManager.queryAllProcesos();
			for (int i = 0; i < ProcesoList.size(); i++) {
				Proceso pro = (Proceso) ProcesoList.get(i);
				cbDescProceso.addItem(pro.getIdProceso() + " - " + pro.getDescripción());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			this.dispose();
		}

		if (e.getSource() == btnRuta) {
			jfcRuta = new JFileChooser();
			jfcRuta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jfcRuta.showOpenDialog(this);
			File fEscogido = jfcRuta.getSelectedFile();
			pathPadronProcesar = fEscogido.getAbsolutePath();
			txtRuta.setText(fEscogido.getPath());
			padronPaths = fEscogido.listFiles();
		}

		if (e.getSource() == btnProcesar) {
			int numPadrones = 0;
			String log = "";
			// System.out.println(pathPadronProcesar);
			if (pathPadronProcesar != null || !pathPadronProcesar.isEmpty()) {
				int cantPadrones = padronPaths.length;
				for (File padron : padronPaths) {
					System.out.println("Padron: " + (numPadrones + 1));
					txtLog.append("Padron: " + (numPadrones + 1) + "\n");

					ImagePlus imgPlanillon = IJ.openImage(padron.getAbsolutePath());
					try {
					PruebaNuevoOCR.procesarPlanillon(imgPlanillon, padron.getName());
					}  catch (Exception a) {
						// TODO Auto-generated catch block
						a.printStackTrace();
					}
					//ImagePlus planillonRecortado = HelperMethods.recortarPlanillonO(imgPlanillon, imgPlanillon);
					//ImagePlus auxPlanillon = new Duplicator().run(planillonRecortado);
					//List<ImagePlus> filasPlanillon = HelperMethods.getFilasPlanillonO(planillonRecortado);
					//int nFilas = 1;
					//for (ImagePlus fila : filasPlanillon) {
					//	List<ImagePlus> partesFila = HelperMethods.getPartesFilaO(fila, auxPlanillon);
					//	List<ImagePlus> dniFila = HelperMethods.cropSection(partesFila.get(0), 8);
					//	List<ImagePlus> firmaFila = HelperMethods.cropSection(partesFila.get(2), 1);
					//	ImagePlus huellaFila = partesFila.get(3);
					//	String dni = " ";
						// DNI
						// partesFila.get(0).show();

					//	log = "Fila " + nFilas + ": Procesando Dni = ";
						/*
						for (ImagePlus numero : dniFila) {
							// numero.show();
							String number = OcrProy.ocrNumbers.reconocer(numero.getBufferedImage());
							// System.out.print(number);
							dni += number;
							// System.out.println(dni);
						}
						log += dni;
						System.out.println();
						txtLog.append(log + "\n");
						txtLog.update(txtLog.getGraphics());
						
						nFilas++;
					}*/
					numPadrones++;
					pgBar.setValue(numPadrones * 100 / cantPadrones);
					pgBar.update(pgBar.getGraphics());
				}
			} else {
				JOptionPane.showMessageDialog(this, "Escojan un directorio.");
			}
		}
	}
}
