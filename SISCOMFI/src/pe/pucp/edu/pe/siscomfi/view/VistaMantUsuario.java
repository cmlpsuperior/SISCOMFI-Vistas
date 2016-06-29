package pe.pucp.edu.pe.siscomfi.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.Departamento;
import pe.pucp.edu.pe.siscomfi.model.Distrito;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;
import pe.pucp.edu.pe.siscomfi.model.Provincia;
import pe.pucp.edu.pe.siscomfi.model.TipoProceso;
import pe.pucp.edu.pe.siscomfi.model.Usuario;
import pe.pucp.edu.pe.siscomfi.model.UsuarioLogeado;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Image;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class VistaMantUsuario extends JInternalFrame {
	
	/*Comentario para probar los cambios del github*/
	private MyTableModel tableModelPartido;
	private JTextField txtContrasenia;
	private JTextField txtCorreo;
	private JTextField txtNuevaContra;
	private JTextField txtRptaSecreta;
	private JTextField txtPregSecreta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaMantUsuario frame = new VistaMantUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaMantUsuario() {
		setTitle("Partidos politicos");
		setClosable(true);
		setBounds(100, 100, 788, 231);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DATOS PERSONALES", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 750, 122);
		getContentPane().add(panel);
		
		JLabel lblPreguntaSecreta = new JLabel("Pregunta Secreta (*): ");
		lblPreguntaSecreta.setBounds(390, 28, 146, 14);
		panel.add(lblPreguntaSecreta);
		
		JLabel lblRespuestaSecreta = new JLabel("Respuesta Secreta (*): ");
		lblRespuestaSecreta.setBounds(391, 58, 145, 14);
		panel.add(lblRespuestaSecreta);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a (*): ");
		lblContrasea.setBounds(12, 59, 125, 14);
		panel.add(lblContrasea);
		
		txtContrasenia = new JTextField();
		txtContrasenia.setBounds(147, 55, 190, 22);
		panel.add(txtContrasenia);
		txtContrasenia.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo (*):");
		lblCorreo.setBounds(12, 28, 125, 14);
		panel.add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(146, 24, 191, 22);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		JLabel lblNuevaContrasea = new JLabel("Nueva Contrase\u00F1a (*): ");
		lblNuevaContrasea.setBounds(12, 86, 137, 14);
		panel.add(lblNuevaContrasea);
		
		txtNuevaContra = new JTextField();
		txtNuevaContra.setBounds(147, 82, 190, 22);
		panel.add(txtNuevaContra);
		txtNuevaContra.setColumns(10);
		
		txtRptaSecreta = new JTextField();
		txtRptaSecreta.setBounds(548, 54, 190, 22);
		panel.add(txtRptaSecreta);
		txtRptaSecreta.setColumns(10);
		
		txtPregSecreta = new JTextField();
		txtPregSecreta.setBounds(548, 27, 190, 22);
		panel.add(txtPregSecreta);
		txtPregSecreta.setColumns(10);
		tableModelPartido = new MyTableModel();
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//int id = Integer.parseInt(txtCodigo.getText());
				String correo = txtCorreo.getText();
				String antContra = txtContrasenia.getText();
				String nuevaContra = txtNuevaContra.getText();
				String pregSecreta = txtPregSecreta.getText();
				String rptaSecreta = txtRptaSecreta.getText();
				//String cmbPregSecreta = cmbPregSecreta
				if (!correo.equals("") && !correo.toLowerCase().contains("@") )
					JOptionPane.showMessageDialog(null, "El correo debe tene el formato persona@partido.com");
				else if (!correo.isEmpty() && !antContra.isEmpty()) {
					boolean valor = siscomfiManager.queryByLogin(correo, antContra);
					if (valor) { //caso en que ya se valido el usuario y contraseña
						//todos llenos
						if (!nuevaContra.isEmpty() && !pregSecreta.isEmpty() && !rptaSecreta.isEmpty()){
							Usuario u = new Usuario();
							u.setCorreoElectronico(correo);
							u.setContrasenia(nuevaContra);
							u.setAntContrasenia(antContra);
							u.setPreguntaSecreta(pregSecreta);
							u.setRptaSecreta(rptaSecreta);
							
							try {
								siscomfiManager.updateUsuario(u);
								JOptionPane.showMessageDialog(null, "Se actualizo el usuario satisfactoriamente");
								LimpiarTextos();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
						LimpiarTextos();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor ingrese su correo y contraseña");
					LimpiarTextos();
				}
				
				
				
				
			}
		});
		btnModificar.setBounds(224, 139, 89, 23);
		getContentPane().add(btnModificar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(425, 139, 89, 23);
		getContentPane().add(btnCancelar);
		
		JLabel lblLlenarSolo = new JLabel("*Campos Obligatorios.");
		lblLlenarSolo.setBounds(12, 166, 323, 16);
		getContentPane().add(lblLlenarSolo);
		
		
	}
	
	class MyTableModel extends AbstractTableModel{		
		ArrayList<PartidoPolitico> listaPartido = null;
		String [] titles = {"CODIGO", "NOMBRE",  "REPRESENTANTE", "TELEFONO", "ESTADO ACTIVO"};
		
		public MyTableModel (){
			try {
				this.listaPartido =  siscomfiManager.queryAllPartidos();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 5;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return listaPartido.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			String value = "";
			switch(col){
				case 0:  value = "" + listaPartido.get(row).getIdPartidoPolitico(); break;
				case 1:  value = listaPartido.get(row).getNombrePartido(); break;
				case 2:  value = "" + listaPartido.get(row).getRepresentante(); break;	
				case 3:  value = "" + listaPartido.get(row).getTelefono(); break;
				case 4:  value = "" + listaPartido.get(row).getEstadoActivo(); break;
			}
			return value;
		}
		
		
		public String getColumnName(int col){
			return titles[col];
		}
		
	}
	
	
	

	
	public void refreshTblPartidos(){
		try {
			tableModelPartido.listaPartido = siscomfiManager.queryAllPartidos();
			tableModelPartido.fireTableChanged(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void LimpiarTextos (){
		txtCorreo.setText("");
		txtContrasenia.setText("");
		txtNuevaContra.setText("");
		txtRptaSecreta.setText("");
		txtPregSecreta.setText("");
	}
	
	public int ContieneNumero(String palabra){
		if (palabra.contains("1") || palabra.contains("2") || palabra.contains("3") || 
				palabra.contains("4") || palabra.contains("5") || palabra.contains("6") || 
				palabra.contains("7") || palabra.contains("8") || palabra.contains("9") || palabra.contains("0") )
			return 1;
		else 
			return 0;
	}
	
	public boolean EsNumeroEntero(String str) {
	    try {
	        Integer.parseInt(str);
	        return true;
	    } catch (NumberFormatException nfe) {}
	    return false;
	}
	
}
