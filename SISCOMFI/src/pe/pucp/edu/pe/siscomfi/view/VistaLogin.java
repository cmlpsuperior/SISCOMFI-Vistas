package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import pe.pucp.edu.pe.siscomfi.algoritmo.HelperMethods;
import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.Usuario;
import pe.pucp.edu.pe.siscomfi.model.UsuarioLogeado;

import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class VistaLogin implements ActionListener {

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
	 * 
	 * @throws IOException
	 */
	public VistaLogin() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frmSiscomfi = new JFrame();
		// BufferedImage imgMiniLogo = ImageIO.read( new
		// File("Imagenes\\minilogo.png"));
		BufferedImage imgMiniLogo = ImageIO.read(getClass().getClassLoader().getResource("resources/minilogo.png"));
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
		btnIngresar.setBounds(331, 154, 108, 23);
		frmSiscomfi.getContentPane().add(btnIngresar);

		JLabel lblRecuperar = new JLabel("\u00BFOlvid\u00F3 su contrase\u00F1a?");
		lblRecuperar.setBounds(294, 236, 178, 14);
		frmSiscomfi.getContentPane().add(lblRecuperar);

		lblRecuperar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String rptaSecreta = JOptionPane.showInputDialog("Ingrese su respuesta secreta");
				if (txtUsuario.getText().equals("")) 
					JOptionPane.showMessageDialog(null, "Por favor ingrese su dirección de correo");
				else {
					String password = siscomfiManager.queryByCorreo_RptaSecreta(txtUsuario.getText(), rptaSecreta);
					System.out.println(txtUsuario.getText()+ " " + rptaSecreta + " " + password);
					if (password!="")	
						JOptionPane.showMessageDialog(null, "Su contraseña es: " + password);
					else
						JOptionPane.showMessageDialog(null, "No se encontraron coincidencias entre el correo y la respuesta ingresada");
				}
				/*String correo = JOptionPane.showInputDialog("Ingrese su usuario");
				String password = siscomfiManager.queryRecuperarContrasenia(correo);
				JOptionPane.showMessageDialog(null, "Su contraseña es: " + password);*/
			}
		});
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 11, 177, 239);
		// BufferedImage logo = ImageIO.read(new
		// File("Imagenes\\logofinal.png"));
		BufferedImage logo = ImageIO.read(getClass().getClassLoader().getResource("resources/logofinal.png"));
		logo = HelperMethods.resizeImage(logo, lblLogo.getWidth(), lblLogo.getHeight(), logo.getType());
		lblLogo.setIcon(new ImageIcon(logo));
		frmSiscomfi.getContentPane().add(lblLogo);

		// listener
		btnIngresar.addActionListener(this);
		txtUsuario.addActionListener(this);
		txtPassword.addActionListener(this);
	}

	private void loginLogic() {
		String nombreCorreo = txtUsuario.getText();
		char[] pass1 = txtPassword.getPassword();
		String pass = new String(pass1);
		if (!nombreCorreo.isEmpty() && !pass.isEmpty()) {
			boolean valor = siscomfiManager.queryByLogin(nombreCorreo, pass);
			if (valor) {
				Usuario usr = siscomfiManager.queryByCorreo(nombreCorreo);
				JFileChooser jfcRnv = new JFileChooser();
				jfcRnv.setDialogTitle("Seleccione el directorio de las imagenes del RNV");
				jfcRnv.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				File pathRnv = null;
				jfcRnv.showOpenDialog(frmSiscomfi);
				pathRnv = jfcRnv.getSelectedFile();
				// rnv
				if (pathRnv != null)
					UsuarioLogeado.pathImagenesRnv = pathRnv.getAbsolutePath();
				// usuario logueado
				UsuarioLogeado.usuario = usr;
				// observados
				JFileChooser jfcObservado = new JFileChooser();
				jfcObservado.setDialogTitle("Seleccione el directorio donde se guardaran los padrones observados");
				jfcObservado.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				File pathObvs = null;
				jfcObservado.showOpenDialog(frmSiscomfi);
				pathObvs = jfcObservado.getSelectedFile();
				if (pathObvs != null)
					UsuarioLogeado.pathObservadosPlanilon = pathObvs.getAbsolutePath();
				if (pathRnv != null && pathObvs != null){
					frmSiscomfi.dispose();
					vMenu = new VistaMenu();
					vMenu.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Debe seleccionar las carpetas de los padrones y la de sus observados");
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Ingrese un usuario o contraseña");
		}
	}

	@Override
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
