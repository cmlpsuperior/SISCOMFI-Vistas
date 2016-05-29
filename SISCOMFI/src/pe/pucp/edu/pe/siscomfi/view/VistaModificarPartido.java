package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class VistaModificarPartido extends JInternalFrame {
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtNombreRep;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	
	private JFrame miPadre;	
	private VistaBuscarPartido vBuscarPartido; 
	
	public VistaModificarPartido(JFrame padre) {
		
		setPadre(padre);
		
		setClosable(true);
		setTitle("Modificar partido politico");
		setBounds(100, 100, 458, 382);
		getContentPane().setLayout(null);
		
		JButton btnGuardar = new JButton("Guardar cambios");
		btnGuardar.setBounds(56, 313, 137, 23);
		getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(249, 313, 137, 23);
		getContentPane().add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "PARTIDO POLITICO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 421, 164);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 52, 125, 14);
		panel.add(lblNombre);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(10, 27, 125, 14);
		panel.add(lblCodigo);
		
		JLabel lblDireccion = new JLabel("Direccion (*)");
		lblDireccion.setBounds(10, 77, 125, 14);
		panel.add(lblDireccion);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(145, 24, 102, 20);
		panel.add(txtCodigo);
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(145, 49, 266, 20);
		panel.add(txtNombre);
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(145, 74, 266, 20);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JComboBox cbDepartamento = new JComboBox();
		cbDepartamento.setBounds(145, 99, 266, 20);
		panel.add(cbDepartamento);
		
		JLabel lblDepa = new JLabel("Departamento (*)");
		lblDepa.setBounds(10, 102, 125, 14);
		panel.add(lblDepa);
		
		JLabel lblProvincia = new JLabel("Provincia (*)");
		lblProvincia.setBounds(10, 127, 125, 14);
		panel.add(lblProvincia);
		
		JComboBox cbProvincia = new JComboBox();
		cbProvincia.setBounds(145, 124, 266, 20);
		panel.add(cbProvincia);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vBuscarPartido = new VistaBuscarPartido ();
				miPadre.getContentPane().add(vBuscarPartido);
				vBuscarPartido.setVisible(true);
			}
		});
		btnBuscar.setBounds(293, 23, 118, 23);
		panel.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "REPRESENTANTE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 186, 421, 116);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNombreRep = new JLabel("Nombre");
		lblNombreRep.setBounds(10, 28, 125, 14);
		panel_1.add(lblNombreRep);
		
		JLabel lblCorreo = new JLabel("Correo electronico");
		lblCorreo.setBounds(10, 53, 125, 14);
		panel_1.add(lblCorreo);
		
		txtNombreRep = new JTextField();
		txtNombreRep.setBounds(145, 25, 266, 20);
		panel_1.add(txtNombreRep);
		txtNombreRep.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(145, 50, 266, 20);
		panel_1.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 78, 125, 14);
		panel_1.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(145, 75, 266, 20);
		panel_1.add(txtTelefono);
		txtTelefono.setColumns(10);

	}
	
	public void setPadre (JFrame padre){
		miPadre = padre;
	}
}
