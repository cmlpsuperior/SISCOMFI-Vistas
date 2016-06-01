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
	private JLabel lblExtra;
	private JLabel lblCompara;
	private JTextArea txtLog;
	private JButton btnCambiar;
	private BufferedImage imageComparar;
	private String pathImageComparar;
	private File[] imagesPaths;
	private JScrollPane scpLog;
	private JProgressBar pgBar;
	private JPanel pnLog;

	public VistaIniciarProceso() {
		setClosable(true);
		setTitle("Iniciar Proceso");
		setBounds(100, 100, 843, 535);
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
		btnProcesar.setBounds(155, 190, 97, 25);
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
		btnCancelar.setBounds(277, 190, 97, 25);
		getContentPane().add(btnCancelar);

		JLabel lblDescripcionDelProceso = new JLabel("Descripcion del proceso:");
		lblDescripcionDelProceso.setBounds(12, 38, 143, 16);
		getContentPane().add(lblDescripcionDelProceso);

		JPanel pnOriginal = new JPanel();
		pnOriginal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Imagen Original",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnOriginal.setBounds(12, 250, 170, 245);
		getContentPane().add(pnOriginal);
		pnOriginal.setLayout(null);

		lblExtra = new JLabel("");
		lblExtra.setBounds(6, 16, 158, 222);
		pnOriginal.add(lblExtra);

		JPanel pnComparacion = new JPanel();
		pnComparacion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Imagen a Comparar",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnComparacion.setBounds(224, 250, 170, 245);
		getContentPane().add(pnComparacion);
		pnComparacion.setLayout(null);

		lblCompara = new JLabel("");
		lblCompara.setBounds(6, 16, 158, 222);
		pnComparacion.add(lblCompara);

		pnLog = new JPanel();
		pnLog.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Log", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnLog.setBounds(427, 250, 353, 173);
		getContentPane().add(pnLog);
		pnLog.setLayout(null);

		scpLog = new JScrollPane();
		scpLog.setBounds(6, 16, 341, 150);
		pnLog.add(scpLog);

		txtLog = new JTextArea();
		scpLog.setViewportView(txtLog);
		txtLog.setEditable(false);
		txtLog.setText("");

		btnCambiar = new JButton("Cambiar");
		btnCambiar.setBounds(31, 191, 89, 23);
		getContentPane().add(btnCambiar);

		pgBar = new JProgressBar();
		pgBar.setBounds(458, 143, 299, 17);
		getContentPane().add(pgBar);

		// listener
		btnCancelar.addActionListener(this);
		btnRuta.addActionListener(this);
		btnProcesar.addActionListener(this);
		btnCambiar.addActionListener(this);
	}

	public void fillCustomerCmb() { // mostrare solo los clientes que estan
									// activos
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

		if (e.getSource() == btnCambiar) {
			JFileChooser extra = new JFileChooser();
			extra.showOpenDialog(this);
			File fEscogido = extra.getSelectedFile();
			int w = lblExtra.getWidth(), h = lblExtra.getHeight();
			pathImageComparar = fEscogido.getAbsolutePath();
			BufferedImage bfEscogida = setImagetoLabel(fEscogido.getAbsolutePath(), w, h);
			lblExtra.setIcon(new ImageIcon(bfEscogida));
		}
		if (e.getSource() == btnRuta) {
			jfcRuta = new JFileChooser();
			jfcRuta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jfcRuta.showOpenDialog(this);
			File fEscogido = jfcRuta.getSelectedFile();
			txtRuta.setText(fEscogido.getPath());
			imagesPaths = fEscogido.listFiles();
		}

		if (e.getSource() == btnProcesar) {
			int num = 1;
			if (!pathImageComparar.isEmpty() && imagesPaths.length != 0) {
				int cantFile = imagesPaths.length;
				double[][] grafoOriginal = Fingerprint.imageGraph(pathImageComparar);
				for (File file : imagesPaths) {
					// System.out.println(file.getAbsolutePath());
					double[][] grafoSuspect = Fingerprint.imageGraph(file.getAbsolutePath());
					double porc = Fingerprint.comparition(grafoOriginal, grafoSuspect);
					String resultado = Fingerprint.resultado(porc);
					pgBar.setValue(num * 100 / cantFile);
					pgBar.update(pgBar.getGraphics());
					String lineaLog = "Imagen " + num++ + ": Resultado-> " + resultado + " - Porcentaje -> " + porc;
					txtLog.append(lineaLog + "\n");
					txtLog.update(txtLog.getGraphics());
					System.out.println(lineaLog);
				}
			} else
				JOptionPane.showMessageDialog(this, "Escoga una imagen o un directorio de imagenes a comparar");
		}
	}

	private BufferedImage setImagetoLabel(String path, int w, int h) {
		ImagePlus imgEscogida = IJ.openImage(path);
		imageComparar = imgEscogida.getBufferedImage();
		BufferedImage bfEscogida = HelperMethods.resizeImage(imageComparar, w, h, imageComparar.getType());
		return bfEscogida;
	}
}
