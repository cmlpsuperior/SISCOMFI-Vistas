package pe.pucp.edu.pe.siscomfi.view;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;

import pe.pucp.edu.pe.siscomfi.algoritmo.Fingerprint;
import pe.pucp.edu.pe.siscomfi.algoritmo.HelperMethods;
import pe.pucp.edu.pe.siscomfi.algoritmo.OcrProy;
import pe.pucp.edu.pe.siscomfi.algoritmo.Signatures;
import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.Proceso;
import pe.pucp.edu.pe.siscomfi.model.UsuarioLogeado;
import pe.pucp.edu.pe.siscomfi.model.Adherente;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;
import pe.pucp.edu.pe.siscomfi.model.Planillon;

import javax.swing.UIManager;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.Duplicator;
import java.awt.Color;
import java.awt.event.ActionListener;
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
	private String pathPadronProcesar = null;
	private File[] padronPaths = null;
	private JScrollPane scpLog;
	private JProgressBar pgBar;
	private JPanel pnLog;
	private Proceso fase = null;
	private int numFase = 0;
	private JTextField txtAceptados;
	private JLabel lblResultadoFinal;
	private JTextField txtResultado;

	public VistaIniciarProceso() {
		boolean indicador = true;
		if (!UsuarioLogeado.verificarPaths()) {
			String result = UsuarioLogeado.setearPathsRnv(this);
			if (result.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Escojan un directorio de las imagenes del RNV");
				indicador = false;
			} else {
				result = UsuarioLogeado.setearPathsObservado(this);
				if (result.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Escojan un directorio para guardar los adherentes observados");
					indicador = false;
				}
			}
		}
		if (!indicador)
			return;

		txtFase = new JTextField();
		txtFase.setEditable(false);
		txtFase.setBounds(168, 70, 235, 22);
		getContentPane().add(txtFase);
		txtFase.setColumns(10);

		setClosable(true);
		setTitle("Iniciar Proceso");
		setBounds(100, 100, 436, 551);
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

		JLabel lblRuta = new JLabel("Ruta de los Padrones:");
		lblRuta.setBounds(12, 140, 146, 16);
		getContentPane().add(lblRuta);

		txtRuta = new JTextField();
		txtRuta.setBounds(168, 137, 180, 22);
		txtRuta.setEditable(false);
		getContentPane().add(txtRuta);
		txtRuta.setColumns(10);

		btnRuta = new JButton("...");
		btnRuta.setBounds(358, 136, 45, 25);
		getContentPane().add(btnRuta);

		btnProcesar = new JButton("Procesar");
		btnProcesar.setBounds(76, 263, 97, 25);
		getContentPane().add(btnProcesar);

		JLabel lblFaseDelProceso = new JLabel("Fase del proceso:");
		lblFaseDelProceso.setBounds(12, 73, 108, 16);
		getContentPane().add(lblFaseDelProceso);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(206, 263, 97, 25);
		getContentPane().add(btnCancelar);

		JLabel lblDescripcionDelProceso = new JLabel("Descripcion del proceso:");
		lblDescripcionDelProceso.setBounds(12, 38, 143, 16);
		getContentPane().add(lblDescripcionDelProceso);

		pnLog = new JPanel();
		pnLog.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Log", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnLog.setBounds(24, 337, 370, 173);
		getContentPane().add(pnLog);
		pnLog.setLayout(null);

		scpLog = new JScrollPane();
		scpLog.setBounds(5, 16, 360, 150);
		pnLog.add(scpLog);

		txtLog = new JTextArea();
		scpLog.setViewportView(txtLog);
		txtLog.setEditable(false);
		txtLog.setText("");

		pgBar = new JProgressBar();
		pgBar.setBounds(54, 309, 299, 17);
		getContentPane().add(pgBar);

		JLabel lblAceptados = new JLabel("Adherentes Aceptados:");
		lblAceptados.setBounds(12, 171, 146, 16);
		getContentPane().add(lblAceptados);

		txtAceptados = new JTextField();
		txtAceptados.setEditable(false);
		txtAceptados.setText("0");
		txtAceptados.setColumns(10);
		txtAceptados.setBounds(168, 169, 235, 22);
		getContentPane().add(txtAceptados);

		lblResultadoFinal = new JLabel("Resultado:");
		lblResultadoFinal.setBounds(12, 205, 146, 16);
		getContentPane().add(lblResultadoFinal);

		txtResultado = new JTextField();
		txtResultado.setEditable(false);
		txtResultado.setColumns(10);
		txtResultado.setBounds(168, 202, 235, 22);
		getContentPane().add(txtResultado);

		// listener
		btnCancelar.addActionListener(this);
		btnRuta.addActionListener(this);
		btnProcesar.addActionListener(this);
		cbDescProceso.addActionListener(this);
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
				cbDescProceso.addItem(pro.getIdProceso() + " - " + pro.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			this.dispose();
		}

		if (e.getSource() == cbDescProceso) {
			int tipoProceso = Integer.parseInt("" + cbDescProceso.getSelectedItem().toString().charAt(0));
			// Fase del Proceso
			fase = siscomfiManager.getFase1Proceso(tipoProceso);
			if (fase == null) {
				fase = siscomfiManager.getFase2Proceso(tipoProceso);
				if (fase == null) {
					JOptionPane.showMessageDialog(this, "No hay procesos electorales activos");
					txtFase.setText("");
				}
				numFase = 2;
				txtFase.setText("Fase 2");
			} else {
				numFase = 1;
				txtFase.setText("Fase 1");
			}
		}

		if (e.getSource() == btnRuta) {
			jfcRuta = new JFileChooser();
			jfcRuta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jfcRuta.showOpenDialog(this);
			File fEscogido = jfcRuta.getSelectedFile();
			if (fEscogido != null) {
				pathPadronProcesar = fEscogido.getAbsolutePath();
				txtRuta.setText(fEscogido.getPath());
				padronPaths = fEscogido.listFiles();
			}
		}

		if (e.getSource() == btnProcesar) {
			int numPadrones = 0;
			int idPlanillon = 0;
			String partido = cbPartido.getSelectedItem().toString();
			int idPartido = Integer.parseInt(partido.charAt(0) + "");
			int cantidadAceptados = 0;
			// tener que ver si el partidoxProceso no ha sido ya procesado
			// 1 procesado, 0 no procesado, 2 en proceso, 3 con observados
			// retorna 0 si no ha sido procesado y retorna -1 si fue procesado
			if (siscomfiManager.verificarPartidoProceso(idPartido, fase.getIdProceso()) == 0) {
				// agergar partidoxproceso
				siscomfiManager.addPartidoxProceso(idPartido, fase.getIdProceso(), 2, 0, 2);
				File pPartido = new File(UsuarioLogeado.pathObservadosPlanilon + "/" + partido);
				if (!pPartido.exists())
					pPartido.mkdir();
				if (pathPadronProcesar != null) {
					int cantPadrones = padronPaths.length;
					int nObservados = 0;
					for (File padron : padronPaths) {
						txtLog.append("Padron: " + (numPadrones + 1) + "\n");
						txtLog.update(txtLog.getGraphics());
						// agregamos el planillon a la bd
						idPlanillon = siscomfiManager.addPlanillon(new Planillon(0, 0, idPartido, fase.getIdProceso()));
						// leemos el planillon
						ImagePlus imgPlanillon = IJ.openImage(padron.getAbsolutePath());
						// procesamos el planillon
						imgPlanillon = HelperMethods.procesarPlanillon(imgPlanillon);
						// sacamos el tama�o de los campos
						ImagePlus auxImg = new Duplicator().run(imgPlanillon);
						int[] tCampos = HelperMethods.cabeceraPlanillon(auxImg);
						// sacamos las filas
						List<ImagePlus> filas = HelperMethods.sacarFilasPlanillon(imgPlanillon);
						int nFila = 8;
						// procesamos cada fila
						for (ImagePlus fila : filas) {
							List<ImagePlus> partes = HelperMethods.sacarDatosFila(fila, tCampos);
							txtLog.append("Fila " + nFila + ": Procesando Dni = ");
							txtLog.update(txtLog.getGraphics());
							// sacamoslos digitos del DNI (8)
							List<ImagePlus> digitosNumero = HelperMethods.getDatosParte(partes.get(0), 8);
							String dni = "", digit;
							for (ImagePlus dNumb : digitosNumero) {
								if (dNumb != null) {
									digit = OcrProy.ocrNumbers.reconocer(dNumb.getBufferedImage());
									dni += digit;
								}
							}
							txtLog.append(dni + "\n");
							txtLog.append("Obteniendo posibles adherentes:\n ");
							txtLog.update(txtLog.getGraphics());
							List<Adherente> lista = siscomfiManager.getPosiblesAdherentes(dni);
							if (lista != null && lista.size() != 0) {
								txtLog.append("Se encontraron -> " + lista.size() + " posibles adherentes\n");
								txtLog.update(txtLog.getGraphics());
								int contIguales = 0;
								String resultado = "";
								// las leidas del planillon
								ImagePlus imgHuella = null;
								ImagePlus imgFirma = null;
								// las originales del rnv
								ImagePlus imgHuellaOriginal = null;
								ImagePlus imgFirmaOriginal = null;
								double pFirma = 0, pHuella = 0;
								Adherente adherente = null;
								for (Adherente adh : lista) {
									txtLog.append("Procesando huella: ");
									txtLog.update(txtLog.getGraphics());
									ImagePlus huella = HelperMethods.quitarBorde(partes.get(3));
									imgHuella = new Duplicator().run(huella);
									ImagePlus huellaRnv = IJ.openImage(
											UsuarioLogeado.pathImagenesRnv + "/huellas/" + adh.getrHuella() + ".jpg");
									imgHuellaOriginal = new Duplicator().run(huellaRnv);
									double[][] original = Fingerprint.imageGraph(huellaRnv);
									double[][] sospechosa = Fingerprint.imageGraph(huella);
									double porcentaje = Fingerprint.comparition(original, sospechosa);
									resultado = Fingerprint.resultado(porcentaje);
									if (resultado.compareTo("Iguales") == 0) {
										contIguales++;
									}
									pHuella = porcentaje;
									adherente = adh;
									txtLog.append(" Resultado-> " + resultado + "\n");
									txtLog.update(txtLog.getGraphics());
								}
								int idAdherente = 0;

								if (contIguales == 1) {
									txtLog.append("Procesando Firma: ");
									txtLog.update(txtLog.getGraphics());
									ImagePlus firma = HelperMethods.quitarBorde(partes.get(2));
									imgFirma = new Duplicator().run(firma);
									ImagePlus firmaRnv = IJ.openImage(UsuarioLogeado.pathImagenesRnv + "/firmas/"
											+ adherente.getrFirma() + ".jpg");
									imgFirmaOriginal = new Duplicator().run(firmaRnv);
									firmaRnv = Signatures.formatoFirma(firmaRnv);
									firma = Signatures.formatoFirma(firma);
									double res = Signatures.compareSignatures(firmaRnv, firma);
									pFirma = res;
									System.out.println("Fila " + nFila + " Res: " + res);
									txtLog.append(res + "\n");
									txtLog.append("Adherente: Aceptado\n");
									txtLog.update(txtLog.getGraphics());
									// aceptado
									adherente.setEstado(1);
									cantidadAceptados++;
									txtAceptados.setText("" + cantidadAceptados);
								} else {
									// asignar estado de adherente a observado
									String estadoFinal = "";
									if (contIguales > 1) {
										estadoFinal = "Rechazado";
										adherente.setEstado(0);
									} else {
										estadoFinal = "Observado";
										nObservados++;

										txtLog.append("Adherente: " + estadoFinal + "\n");
										adherente.setEstado(2);
										// guardar las imagenes en la carpeta de
										// observados
										File fAdherente = new File(
												pPartido.getAbsoluteFile() + "/" + adherente.getDni());
										fAdherente.mkdir();
										// carpeta de huellas
										File fHuella = new File(fAdherente.getAbsolutePath() + "/huella");
										fHuella.mkdir();
										if (imgHuella != null) {
											IJ.saveAs(imgHuella, "Jpeg", fHuella.getAbsolutePath() + "/observado.jpg");
											IJ.saveAs(imgHuellaOriginal, "Jpeg",
													fHuella.getAbsolutePath() + "/original.jpg");
										}

										// carpeta de firmas
										File fFirma = new File(fAdherente.getAbsolutePath() + "/firma");
										fFirma.mkdir();
										if (imgFirma != null) {
											IJ.saveAs(imgFirma, "Jpeg", fFirma.getAbsolutePath() + "/observado.jpg");
											IJ.saveAs(imgFirmaOriginal, "Jpeg",
													fFirma.getAbsolutePath() + "/original.jpg");
										}
									}
									txtLog.update(txtLog.getGraphics());
								}
								adherente.setrPlanillon(padron.getName());
								adherente.setpFirma(pFirma);
								adherente.setpHuella(pHuella);
								// verificar que adherente no se repita
								if (siscomfiManager.verificarAdherenteRepetido(fase.getIdProceso(),
										adherente.getDni()) == -1) {
									// guardar adherente
									idAdherente = siscomfiManager.addAdherente(adherente);
									// guardamos el adherentexplanillon
									siscomfiManager.addAdherentexPlanillon(idAdherente, idPlanillon,
											adherente.getEstado(), 0, adherente.getpHuella(), adherente.getpFirma(),
											adherente.getrHuella(), adherente.getrFirma(), numFase);
								} else {
									siscomfiManager.updateEstadoAdherente(idAdherente, "0");
								}

							} else {
								txtLog.append("No se encontraron adherentes\n");
								txtLog.update(txtLog.getGraphics());
							}
							nFila--;
						}
						numPadrones++;
						pgBar.setValue(numPadrones * 100 / cantPadrones);
						pgBar.update(pgBar.getGraphics());
					}
					if (nObservados == 0)
						siscomfiManager.updateEstadoPartidoProceso(idPartido, fase.getIdProceso(), 1);
					else
						siscomfiManager.updateEstadoPartidoProceso(idPartido, fase.getIdProceso(), 3);
					// verificar si paso el minimo de adherentes del proceso
					int minimoAdherente = fase.getCantidadMinAdherentes();
					if (cantidadAceptados >= minimoAdherente) {
						txtResultado.setText("Cumple con el m�nimo de adherentes");
					} else {
						txtResultado
								.setText("No cumple con el minimo, faltan: " + (minimoAdherente - cantidadAceptados));
					}
				} else {
					JOptionPane.showMessageDialog(this, "Escojan un directorio.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Partido en Proceso o ya procesado");
			}
		}
	}
}
