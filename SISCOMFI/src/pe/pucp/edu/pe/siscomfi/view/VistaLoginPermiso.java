package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import pe.pucp.edu.pe.siscomfi.algoritmo.HelperMethods;
import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;

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

public class VistaLoginPermiso extends JInternalFrame {

	//private JFrame frmSiscomfi;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnIngresar;

	private VistaMenu vMenu;
	private VistaIniciarProceso vProcesar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLoginPermiso window = new VistaLoginPermiso();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public VistaLoginPermiso() {
		//frmSiscomfi = new JFrame();
		// BufferedImage imgMiniLogo = ImageIO.read( new
		// File("Imagenes\\minilogo.png"));
		setClosable(true);
		//BufferedImage imgMiniLogo = ImageIO.read(getClass().getClassLoader().getResource("resources/minilogo.png"));
		//frmSiscomfi.setIconImage(imgMiniLogo);
		setTitle("Verificación de Usuario");
		setBounds(100, 100, 398, 264);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblUsuario = new JLabel("Correo:");
		lblUsuario.setBounds(33, 78, 86, 14);
		getContentPane().add(lblUsuario);

		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setBounds(33, 111, 86, 14);
		getContentPane().add(lblContrasena);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(141, 75, 151, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(141, 108, 151, 20);
		getContentPane().add(txtPassword);

		btnIngresar = new JButton("INGRESAR");
		
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == btnIngresar || event.getSource() == txtPassword || event.getSource() == txtUsuario) {
					try {
						loginLogic();
					} catch (Exception a) {
						a.printStackTrace();
						return;
					}
				}
			}
		}); 
		btnIngresar.setBounds(141, 158, 108, 23);
		getContentPane().add(btnIngresar);
		
		JLabel lblMensaje = new JLabel("Est\u00E1 a punto de ingresar a una funcionalidad restringida. \r\n");
		lblMensaje.setBounds(23, 13, 425, 34);
		getContentPane().add(lblMensaje);
		
		JLabel lblNewLabel = new JLabel("Se requiere el permiso del administrador del sistema.");
		lblNewLabel.setBounds(23, 46, 425, 16);
		getContentPane().add(lblNewLabel);

		//JLabel lblLogo = new JLabel("");
		//lblLogo.setBounds(10, 11, 177, 239);
		// BufferedImage logo = ImageIO.read(new
		// File("Imagenes\\logofinal.png"));
		//BufferedImage logo = ImageIO.read(getClass().getClassLoader().getResource("resources/logofinal.png"));
		//logo = HelperMethods.resizeImage(logo, lblLogo.getWidth(), lblLogo.getHeight(), logo.getType());
		//lblLogo.setIcon(new ImageIcon(logo));
		//frmSiscomfi.getContentPane().add(lblLogo);
		
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	/* private void initialize() throws IOException {
		
		

		// listener
		btnIngresar.addActionListener(this);
		txtUsuario.addActionListener(this);
		txtPassword.addActionListener(this);
	}*/

	private void loginLogic() {
		String nombreCorreo = txtUsuario.getText();
		char[] pass1 = txtPassword.getPassword();
		String pass = new String(pass1);
		if (!nombreCorreo.isEmpty() && !pass.isEmpty()) {
			boolean valor = siscomfiManager.queryByLogin(nombreCorreo, pass);
			if (valor) {
				//this.dispose();
				vProcesar = new VistaIniciarProceso ();
				//desktop.add(vProcesar);
				vProcesar.setVisible(true);
				this.dispose();
				/*vMenu = new VistaMenu();
				vMenu.setVisible(true); */
			} else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Ingrese un usuario o contraseña");
		}
	}
	
	//@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnIngresar || event.getSource() == txtPassword || event.getSource() == txtUsuario) {
			try {
				loginLogic();
			} catch (Exception a) {
				a.printStackTrace();
				return;
			}
		}
	}

	
}
