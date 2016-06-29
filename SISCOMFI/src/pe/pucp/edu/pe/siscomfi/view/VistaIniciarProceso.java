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
import pe.pucp.edu.pe.siscomfi.model.Cronometro;
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
import java.util.Date;
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
	private JComboBox<String> cmbPartido;
	private JButton btnRuta;
	private JButton btnCancelar;
	private JButton btnProcesar;
	private JFileChooser jfcRuta;
	private JComboBox<String> cmbProceso;
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
		txtFase.setBounds(292, 67, 235, 22);
		getContentPane().add(txtFase);
		txtFase.setColumns(10);

		setClosable(true);
		setTitle("Iniciar Proceso");
		setBounds(100, 100, 554, 551);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Partido Pol\u00EDtico:");
		lblNewLabel.setBounds(12, 108, 205, 16);
		getContentPane().add(lblNewLabel);

		cmbPartido = new JComboBox<String>();
		cmbPartido.setBounds(292, 106, 235, 20);
		getContentPane().add(cmbPartido);
		fillPartidoCmb();

		cmbProceso = new JComboBox<String>();
		cmbProceso.setBounds(292, 35, 235, 22);
		getContentPane().add(cmbProceso);
		fillProcesoCmb();

		JLabel lblRuta = new JLabel("Ruta de los Padrones:");
		lblRuta.setBounds(12, 140, 205, 16);
		getContentPane().add(lblRuta);

		txtRuta = new JTextField();
		txtRuta.setBounds(292, 138, 180, 22);
		txtRuta.setEditable(false);
		getContentPane().add(txtRuta);
		txtRuta.setColumns(10);

		btnRuta = new JButton("...");
		btnRuta.setBounds(482, 136, 45, 25);
		getContentPane().add(btnRuta);

		btnProcesar = new JButton("Procesar");
		btnProcesar.setBounds(120, 244, 97, 25);
		getContentPane().add(btnProcesar);

		JLabel lblFaseDelProceso = new JLabel("Fase del proceso:");
		lblFaseDelProceso.setBounds(12, 73, 205, 16);
		getContentPane().add(lblFaseDelProceso);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(292, 244, 97, 25);
		getContentPane().add(btnCancelar);

		JLabel lblProceso = new JLabel("Proceso Electoral:");
		lblProceso.setBounds(12, 38, 205, 16);
		getContentPane().add(lblProceso);

		pnLog = new JPanel();
		pnLog.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Log", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnLog.setBounds(24, 337, 503, 173);
		getContentPane().add(pnLog);
		pnLog.setLayout(null);

		scpLog = new JScrollPane();
		scpLog.setBounds(12, 16, 479, 150);
		pnLog.add(scpLog);

		txtLog = new JTextArea();
		scpLog.setViewportView(txtLog);
		txtLog.setEditable(false);
		txtLog.setText("");

		pgBar = new JProgressBar();
		pgBar.setBounds(24, 308, 503, 17);
		getContentPane().add(pgBar);

		JLabel lblAceptados = new JLabel("Adherentes Aceptados:");
		lblAceptados.setBounds(12, 171, 205, 16);
		getContentPane().add(lblAceptados);

		txtAceptados = new JTextField();
		txtAceptados.setEditable(false);
		txtAceptados.setText("0");
		txtAceptados.setColumns(10);
		txtAceptados.setBounds(292, 169, 235, 22);
		getContentPane().add(txtAceptados);

		lblResultadoFinal = new JLabel("Resultado:");
		lblResultadoFinal.setBounds(12, 205, 205, 16);
		getContentPane().add(lblResultadoFinal);

		txtResultado = new JTextField();
		txtResultado.setEditable(false);
		txtResultado.setColumns(10);
		txtResultado.setBounds(292, 203, 235, 22);
		getContentPane().add(txtResultado);

		// listener
		btnCancelar.addActionListener(this);
		btnRuta.addActionListener(this);
		btnProcesar.addActionListener(this);
		cmbProceso.addActionListener(this);
	}

	public void fillPartidoCmb() {
		cmbPartido.removeAllItems();
		ArrayList<PartidoPolitico> PartidoPoliticoList;
		try {
			PartidoPoliticoList = siscomfiManager.queryAllPartidos();
			for (int i = 0; i < PartidoPoliticoList.size(); i++) {
				PartidoPolitico pp = (PartidoPolitico) PartidoPoliticoList.get(i);
				cmbPartido.addItem(pp.getIdPartidoPolitico() + " - " + pp.getNombrePartido());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillProcesoCmb() {
		cmbProceso.removeAllItems();
		ArrayList<Proceso> ProcesoList;
		try {
			ProcesoList = siscomfiManager.queryProcesosDisponibles();
			for (int i = 0; i < ProcesoList.size(); i++) {
				Proceso pro = (Proceso) ProcesoList.get(i);
				//Agrego la fase del primer proceso que saldrá en el combo:
				if (i==0){
					Date ahora = new Date();
					Date inicioFase1 = new Date (pro.getFechaProceso1Inicio().getTime());
					Date finFase1 = new Date (pro.getFechaProceso1Fin().getTime());
					Date inicioFase2 = new Date (pro.getFechaProceso2Inicio().getTime());
					Date finFase2 = new Date (pro.getFechaProceso2Fin().getTime());
					
					if (ahora.after(inicioFase1) && ahora.before(finFase1) ){
						numFase = 1;
						txtFase.setText("Fase 1");
					}
					else if (ahora.after(inicioFase2) && ahora.before(finFase2) ){
						numFase = 2;
						txtFase.setText("Fase 2");
					}
					else {
						numFase = 0;
						txtFase.setText("No se encontro Fase Disponible");
					}
				}
				
				cmbProceso.addItem(pro.getIdProceso() + " - " + pro.getDescripcion());
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

		if (e.getSource() == cmbProceso) {
			int tipoProceso = Integer.parseInt("" + cmbProceso.getSelectedItem().toString().charAt(0));
			// Fase del Proceso
			fase = siscomfiManager.getFase1Proceso(tipoProceso);
			if (fase == null) {
				fase = siscomfiManager.getFase2Proceso(tipoProceso);
				if (fase == null) {
					JOptionPane.showMessageDialog(this, "No hay procesos electorales activos");
					txtFase.setText("");
					return;
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
			String partido = cmbPartido.getSelectedItem().toString();
			int idPartido = Integer.parseInt(partido.charAt(0) + "");
			int cantidadAceptados = 0;
			//verificamos denuevo de que haya una fase activa para iniciar el procesov2
			if (fase == null){
				JOptionPane.showMessageDialog(this, "No hay procesos electorales activos");
				return;
			}
			// tener que ver si el partidoxProceso no ha sido ya procesado
			// 1 procesado, 0 no procesado, 2 en proceso, 3 con observados
			// retorna 0 si no ha sido procesado y retorna -1 si fue procesado
			if (siscomfiManager.verificarPartidoProceso(idPartido, fase.getIdProceso()) == 0) {
				// lo agregamos a partidoxproceso con estado 2
				siscomfiManager.addPartidoxProceso(idPartido, fase.getIdProceso(), 2, 0, 2);
				//agregamos los observados en Observados>Proceso>Fase>Partidov2
				String proceso = cmbProceso.getSelectedItem().toString();
				String txtfase = txtFase.getText();
				String pathObsPartido = UsuarioLogeado.pathObservadosPlanilon + "/"+proceso+"/"+txtfase; 
				File pPartido = new File(pathObsPartido + "/" + partido);
				if (!pPartido.exists())
					pPartido.mkdir();
				if (pathPadronProcesar != null) {
					int cantPadrones = padronPaths.length;
					int nObservados = 0;
					//cronometro del partido
					Cronometro crnPartido = new Cronometro();
					crnPartido.start();
					for (File padron : padronPaths) {
						txtLog.append("Padron: " + (numPadrones + 1) + "\n");
						txtLog.update(txtLog.getGraphics());
						// agregamos el planillon a la bd
						idPlanillon = siscomfiManager.addPlanillon(new Planillon(0, 0, idPartido, fase.getIdProceso()));
						//empezamos a calcular el tiempo de planillonv2
						Cronometro crnPlanillon = new Cronometro();
						crnPlanillon.start();
						// leemos el planillon
						ImagePlus imgPlanillon = IJ.openImage(padron.getAbsolutePath());
						// procesamos el planillon
						imgPlanillon = HelperMethods.procesarPlanillon(imgPlanillon);
						// sacamos el tamaï¿½o de los campos
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
							//empezamos a calcular el tiempro de proceso del adherentexplanillonv2
							Cronometro crnAdherente = new Cronometro();
							crnAdherente.start();
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
								//porcentajes de firma y huella
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
								//solo debe de haber 1 igual si se encontraron muchos matches
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
									//si hay mas de 1 igual rechazamos al adherente
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
									//seteamos el tiempo fin
									crnAdherente.stop();
									// guardamos el adherentexplanillon
									siscomfiManager.addAdherentexPlanillon(idAdherente, idPlanillon,
											adherente.getEstado(), crnAdherente.getElapsedSeconds(), adherente.getpHuella(), adherente.getpFirma(),
											adherente.getrHuella(), adherente.getrFirma(), numFase);
								} else {
									//seteamos el tiempo fin
									crnAdherente.stop();
									siscomfiManager.updateEstadoAdherente(idAdherente,idPlanillon, crnAdherente.getElapsedSeconds(),"0");
								}

							} else {
								txtLog.append("No se encontraron adherentes\n");
								txtLog.update(txtLog.getGraphics());
								//si no se encuentra se rechaza al adherente
							}
							//se termina 
							nFila--;
						}
						//termina de procesar el planillon
						crnPlanillon.stop();
						//setear el tiempo de proceso para el planillon
						numPadrones++;
						pgBar.setValue(numPadrones * 100 / cantPadrones);
						pgBar.update(pgBar.getGraphics());
					}
					//termina de procesar el partido
					crnPartido.stop();
					if (nObservados == 0)
						siscomfiManager.updateEstadoPartidoProceso(idPartido, fase.getIdProceso(),crnPartido.getElapsedSeconds(), 1);
					else
						siscomfiManager.updateEstadoPartidoProceso(idPartido, fase.getIdProceso(),crnPartido.getElapsedSeconds(), 3);

					// verificar si paso el minimo de adherentes del proceso
					int minimoAdherente = fase.getCantidadMinAdherentes();
					if (cantidadAceptados >= minimoAdherente) {
						txtResultado.setText("Cumple con el minimo de adherentes. Notificar al partido");
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
