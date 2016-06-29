package pe.pucp.edu.pe.siscomfi.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;

public class VistaMenu extends JFrame {

	private JDesktopPane desktop;
	private VistaRegistrarPartido vRegistrarPartido;
	private VistaModificarPartido vModificarPartido;
	private VistaBorrarPartido vBorrarPartido;
	private VistaBuscarPartido vBuscarPartido;
	private VistaLoginPermiso_regProceso vLoginPermiso; // nuevo
	private VistaLoginPermiso_regUsuario vLoginPermiso_user;
	private VistaObservados vObservados;

	private VistaReporte vReportePartido;
	private VistaRegistrarUsuario vRegistrarUsuario;
	private VistaIniciarProceso vProcesar;
	private VistaRegistrarProceso vRegistrarProceso;

	private VistaMantPartido vMantPartido;
	private VistaMenu me = this;

	private boolean loginPermisoResult = false;

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { VistaMenu frame = new VistaMenu();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * }); }
	 */

	/*
	 * picker1 = new DefaultComponentFactory().createJDatePicker();
	 * picker1.setTextEditable(true); picker1.setShowYearButtons(true);
	 * 
	 * picker2 = new DefaultComponentFactory().createJDatePicker();
	 * picker2.setTextEditable(true); picker2.setShowYearButtons(true);
	 * 
	 * //JDATE PICKER 1 IZQUIERDA JPanel jPanel1 = new JPanel();
	 * jPanel1.setBounds(10, 28, 212, 40); getContentPane().add(jPanel1);
	 * jPanel1.add((JComponent)picker1);
	 */

	public VistaMenu() {
		setTitle("SISCOMFI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1089, 649);

		desktop = new JDesktopPane();
		setContentPane(desktop);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBounds(0, 0, 1089, 21);
		desktop.add(menuBar);

		JMenu mnPartidoPolitico = new JMenu("Partido Politico");
		menuBar.add(mnPartidoPolitico);

		JMenuItem mntmPartido = new JMenuItem("Hacer Mantenimiento");
		mntmPartido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vMantPartido = new VistaMantPartido();
				desktop.add(vMantPartido);
				vMantPartido.setVisible(true);
			}
		});
		mnPartidoPolitico.add(mntmPartido);

		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vBuscarPartido = new VistaBuscarPartido();
				desktop.add(vBuscarPartido);
				vBuscarPartido.setVisible(true);
			}
		});
		mnPartidoPolitico.add(mntmBuscar);

		JMenu mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);

		JMenuItem mntmRegistrarUsuario = new JMenuItem("Registrar usuario");
		mntmRegistrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vLoginPermiso_user = new VistaLoginPermiso_regUsuario();
				desktop.add(vLoginPermiso_user);
				vLoginPermiso_user.setDesktopFather(desktop);
				vLoginPermiso_user.setVisible(true);
				/*
				 * vRegistrarUsuario = new VistaRegistrarUsuario();
				 * desktop.add(vRegistrarUsuario);
				 * vRegistrarUsuario.setVisible(true);
				 */
			}
		});
		mnUsuario.add(mntmRegistrarUsuario);

		JMenu mnReporte = new JMenu("Reporte");
		menuBar.add(mnReporte);

		JMenuItem mntmReportePartidoPolitico = new JMenuItem("Reporte partido politico");
		mntmReportePartidoPolitico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vReportePartido = new VistaReporte();
				desktop.add(vReportePartido);
				vReportePartido.setVisible(true);
			}
		});
		mnReporte.add(mntmReportePartidoPolitico);

		JMenu mnProceso = new JMenu("Proceso");
		menuBar.add(mnProceso);

		JMenuItem mntmProcesarPlanillones = new JMenuItem("Iniciar Proceso");
		mntmProcesarPlanillones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vProcesar = new VistaIniciarProceso();
				desktop.add(vProcesar);
				vProcesar.setVisible(true);

			}
		});

		JMenuItem mntmAgregarBdDe = new JMenuItem("Agregar BD de la RNV");
		mntmAgregarBdDe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfcChooser = new JFileChooser();
				jfcChooser.setDialogTitle("Seleccione archivo de los electores de la RNV (formato XLSX)");
				int result = jfcChooser.showOpenDialog(desktop);
				String archivoRNV = "";
				File fEscogido = null;
				while (fEscogido == null) {
					fEscogido = jfcChooser.getSelectedFile();
					if (fEscogido != null) {
						archivoRNV = fEscogido.getAbsolutePath();
						//mensaje de confirmacion
						if (siscomfiManager.llenarRegistroElector(archivoRNV))
							JOptionPane.showMessageDialog(desktop, "Excel del RNV cargado correctamente!");
						else
							JOptionPane.showMessageDialog(desktop, "Hubo un problema! Comunicarse con un administrador.");						
					} else {
						if (result == JFileChooser.CANCEL_OPTION)
							break;
						result = jfcChooser.showOpenDialog(desktop);
					}
				}

			}
		});
		mnProceso.add(mntmAgregarBdDe);
		mnProceso.add(mntmProcesarPlanillones);

		JMenuItem mntmObservados = new JMenuItem("Procesar Observados");
		mntmObservados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vObservados = new VistaObservados();
				desktop.add(vObservados);
				vObservados.setVisible(true);
			}
		});
		mnProceso.add(mntmObservados);

		JMenuItem mntmRegistrarNuevoProceso = new JMenuItem("Registrar nuevo proceso");
		mntmRegistrarNuevoProceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vLoginPermiso = new VistaLoginPermiso_regProceso();
				desktop.add(vLoginPermiso);
				vLoginPermiso.setDesktopFather(desktop);
				vLoginPermiso.setVisible(true);
				/*
				 * vRegistrarProceso = new VistaRegistrarProceso ();
				 * desktop.add(vRegistrarProceso);
				 * vRegistrarProceso.setVisible(true);
				 */
			}
		});
		mnProceso.add(mntmRegistrarNuevoProceso);
	}
}
