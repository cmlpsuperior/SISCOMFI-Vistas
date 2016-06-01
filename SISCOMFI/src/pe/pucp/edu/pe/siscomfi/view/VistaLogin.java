package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import pe.pucp.edu.pe.siscomfi.algoritmo.HelperMethods;

import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VistaLogin implements ActionListener{

	private JFrame frmSiscomfi;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnIngresar;
	
	private VistaMenu vMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin window = new VistaLogin();
					window.frmSiscomfi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public VistaLogin() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frmSiscomfi = new JFrame();
		BufferedImage imgMiniLogo = ImageIO.read(new File("./Imagenes/minilogo.png"));
		frmSiscomfi.setIconImage(imgMiniLogo);
		frmSiscomfi.setTitle("SISCOMFI");
		frmSiscomfi.setBounds(100, 100, 498, 300);
		frmSiscomfi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSiscomfi.getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Correo:");
		lblUsuario.setBounds(197, 51, 86, 14);
		frmSiscomfi.getContentPane().add(lblUsuario);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setBounds(197, 111, 86, 14);
		frmSiscomfi.getContentPane().add(lblContrasena);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(305, 48, 137, 20);
		frmSiscomfi.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(305, 108, 137, 20);
		frmSiscomfi.getContentPane().add(txtPassword);
		
		btnIngresar = new JButton("INGRESAR");
		btnIngresar.setBounds(323, 158, 108, 23);
		frmSiscomfi.getContentPane().add(btnIngresar);
		
		JLabel lblRecuperar = new JLabel("\u00BFOlvid\u00F3 su contrase\u00F1a?");
		lblRecuperar.setBounds(294, 236, 178, 14);
		frmSiscomfi.getContentPane().add(lblRecuperar);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 11, 177, 239);
		BufferedImage logo = ImageIO.read(new File("./Imagenes/logofinal.png"));
		logo = HelperMethods.resizeImage(logo, lblLogo.getWidth(), lblLogo.getHeight(), logo.getType());
		lblLogo.setIcon(new ImageIcon(logo));
		frmSiscomfi.getContentPane().add(lblLogo);

		//listener
		btnIngresar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == btnIngresar){
			//frmSiscomfi.setVisible(false);
			frmSiscomfi.dispose();
			vMenu = new VistaMenu();
			vMenu.setVisible(true);
			
		}
	}
}
