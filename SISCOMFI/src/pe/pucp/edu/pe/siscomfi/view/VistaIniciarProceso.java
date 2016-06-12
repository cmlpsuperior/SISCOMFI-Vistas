package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import pe.pucp.edu.pe.siscomfi.algoritmo.Fingerprint;
import pe.pucp.edu.pe.siscomfi.algoritmo.HelperMethods;
import pe.pucp.edu.pe.siscomfi.algoritmo.OcrFinal;
import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.Rol; //aca tiene que ir tipoProceso
import pe.pucp.edu.pe.siscomfi.model.TipoProceso;
import pe.pucp.edu.pe.siscomfi.model.Proceso;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;

import javax.swing.UIManager;

import org.jdatepicker.DefaultComponentFactory;
import org.jdatepicker.JDatePicker;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.Duplicator;
import ij.process.ImageProcessor;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.font.ImageGraphicAttribute;
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
	private BufferedImage imageComparar;
	private String pathPadronProcesar;
	private File[] padronPaths;
	private JScrollPane scpLog;
	private JProgressBar pgBar;
	private JPanel pnLog;
	private OcrFinal ocrNumbers;

	public VistaIniciarProceso() {
		setClosable(true);
		setTitle("Iniciar Proceso");
		setBounds(100, 100, 436, 499);
		getContentPane().setLayout(null);

		// OCR--
		ocrNumbers = new OcrFinal();
		ocrNumbers.cargarEntrenamiento();
		ocrNumbers.entrenarRed();

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
				cbPartido.addItem(pp.getIdPartidoPolitco() + " - " + pp.getNombrePartido());
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
			ProcesoList = siscomfiManager.queryAllProcesos(); // en realidad
																// solo veremos
																// su
																// descripcion
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
			//System.out.println(pathPadronProcesar);
			if (pathPadronProcesar != null || !pathPadronProcesar.isEmpty() ) {
				int cantPadrones = padronPaths.length;
				for (File padron : padronPaths) {
					/*ImagePlus img = IJ.openImage(padron.getAbsolutePath());
					ImagePlus recortado = HelperMethods.recortarPlanillon(img, img);
					ImagePlus recortadoOriginal = new Duplicator().run(recortado);

					List<ImagePlus> lista = HelperMethods.getFilasPlanillon(recortado);
					for (ImagePlus mm : lista) { // mm.show();

					}
					List<ImagePlus> parteLista = HelperMethods.getPartesFila(lista.get(1), recortadoOriginal);
					// 0 -> DNI, 1 -> nombre + apellido
					int len = 8;
					List<ImagePlus> datos = HelperMethods.cropSection(parteLista.get(0), len);
					for (ImagePlus mm : datos) {
						mm.show();
					}
					break;*/
					System.out.println("Padron:" + (numPadrones + 1));
					ImagePlus imgPlanillon = IJ.openImage(padron.getAbsolutePath());
					ImagePlus planillonRecortado = HelperMethods.recortarPlanillon2(imgPlanillon, imgPlanillon);
					ImagePlus auxPlanillon = new Duplicator().run(planillonRecortado);
					List<ImagePlus> filasPlanillon = HelperMethods.getFilasPlanillon3(planillonRecortado);
					int nFilas = 1;
					for (ImagePlus fila : filasPlanillon) {
						List<ImagePlus> partesFila = HelperMethods.getPartesFila2(fila, auxPlanillon);
						List<ImagePlus> dniFila = HelperMethods.cropSection(partesFila.get(0), 8);
						List<ImagePlus> firmaFila = HelperMethods.cropSection(partesFila.get(2), 1);
						ImagePlus huellaFila = partesFila.get(3);
						String dni = " ";
						// DNI
						//partesFila.get(0).show();
						String log = "Fila "+ nFilas + ": Dni = ";
						for (ImagePlus numero : dniFila) {
							//numero.show();
							String number = ocrNumbers.reconocer(numero.getBufferedImage());
							//System.out.print(number);
							dni += number;
							//System.out.println(dni);
						}
						log += dni;
						System.out.println();
						txtLog.append(log + "\n");
						txtLog.update(txtLog.getGraphics());
						nFilas++;
					}
					pgBar.setValue(numPadrones * 100 / cantPadrones);
					pgBar.update(pgBar.getGraphics());
				}
			} else {
				JOptionPane.showMessageDialog(this, "Escojan un directorio.");
			}
			/*
			 * int num = 1; if (!pathImageComparar.isEmpty() &&
			 * imagesPaths.length != 0) { int cantFile = imagesPaths.length;
			 * double[][] grafoOriginal =
			 * Fingerprint.imageGraph(pathImageComparar); for (File file :
			 * imagesPaths) { // System.out.println(file.getAbsolutePath());
			 * double[][] grafoSuspect =
			 * Fingerprint.imageGraph(file.getAbsolutePath()); double porc =
			 * Fingerprint.comparition(grafoOriginal, grafoSuspect); String
			 * resultado = Fingerprint.resultado(porc); pgBar.setValue(num * 100
			 * / cantFile); pgBar.update(pgBar.getGraphics()); String lineaLog =
			 * "Imagen " + num++ + ": Resultado-> " + resultado +
			 * " - Porcentaje -> " + porc; txtLog.append(lineaLog + "\n");
			 * txtLog.update(txtLog.getGraphics());
			 * System.out.println(lineaLog); } } else
			 * JOptionPane.showMessageDialog(this,
			 * "Escoga una imagen o un directorio de imagenes a comparar");
			 */
		}
	}

	private BufferedImage setImagetoLabel(String path, int w, int h) {
		ImagePlus imgEscogida = IJ.openImage(path);
		imageComparar = imgEscogida.getBufferedImage();
		BufferedImage bfEscogida = HelperMethods.resizeImage(imageComparar, w, h, imageComparar.getType());
		return bfEscogida;
	}
}
