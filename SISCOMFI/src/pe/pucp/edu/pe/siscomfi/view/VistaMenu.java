package pe.pucp.edu.pe.siscomfi.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VistaMenu extends JFrame {

	private JDesktopPane desktop;
	private VistaRegistrarPartido vRegistrarPartido;
	private VistaModificarPartido vModificarPartido;
	private VistaBorrarPartido vBorrarPartido;
	private VistaBuscarPartido vBuscarPartido;
	private VistaObservados vObservados;
	
	private VistaReporte vReportePartido;
	private VistaRegistrarUsuario vRegistrarUsuario;
	private VistaIniciarProceso vProcesar;
	private VistaRegistrarProceso vRegistrarProceso;
	
	private VistaMantPartido vMantPartido;
	private VistaMenu me = this;
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaMenu frame = new VistaMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	public VistaMenu() {
		setTitle("SISCOMFI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 864, 649);	
		
		desktop = new JDesktopPane();
		setContentPane(desktop);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBounds(0, 0, 843, 21);
		desktop.add(menuBar);
		
		JMenu mnPartidoPolitico = new JMenu("Partido Politico");
		menuBar.add(mnPartidoPolitico);
		
		JMenuItem mntmRegistrar = new JMenuItem("Registrar");
		mntmRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vRegistrarPartido = new VistaRegistrarPartido();
				desktop.add(vRegistrarPartido);
				vRegistrarPartido.setVisible(true);
				
			}
		});
		
		JMenuItem mntmPartido = new JMenuItem("Partido Politico");
		mntmPartido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vMantPartido = new VistaMantPartido();
				desktop.add(vMantPartido);
				vMantPartido.setVisible(true);
			}
		});
		mnPartidoPolitico.add(mntmPartido);
		mnPartidoPolitico.add(mntmRegistrar);
		
		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vModificarPartido = new VistaModificarPartido(me); //debo pasar el padre si o si
				desktop.add(vModificarPartido);
				vModificarPartido.setVisible(true);
			}
		});
		mnPartidoPolitico.add(mntmModificar);
		
		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vBorrarPartido = new VistaBorrarPartido(me); //seteo el padre, es obligatorio
				desktop.add(vBorrarPartido);
				vBorrarPartido.setVisible(true);
			}
		});
		mnPartidoPolitico.add(mntmEliminar);
		
		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vBuscarPartido = new VistaBuscarPartido ();
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
				vRegistrarUsuario = new VistaRegistrarUsuario();
				desktop.add(vRegistrarUsuario);
				vRegistrarUsuario.setVisible(true);
				
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
		
		JMenuItem mntmProcesarPlanillones = new JMenuItem("Procesar planillones");
		mntmProcesarPlanillones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vProcesar = new VistaIniciarProceso ();
				desktop.add(vProcesar);
				vProcesar.setVisible(true);
				
			}
		});
		mnProceso.add(mntmProcesarPlanillones);
		
		JMenuItem mntmObservados = new JMenuItem("Observados");
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
				vRegistrarProceso =  new VistaRegistrarProceso ();
				desktop.add(vRegistrarProceso);
				vRegistrarProceso.setVisible(true);
			}
		});
		mnProceso.add(mntmRegistrarNuevoProceso);
	}
}
