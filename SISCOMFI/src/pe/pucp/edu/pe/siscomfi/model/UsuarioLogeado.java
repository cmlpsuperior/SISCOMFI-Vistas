package pe.pucp.edu.pe.siscomfi.model;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;

public class UsuarioLogeado {
	public static Usuario usuario = null;
	public static String pathImagenesRnv = "";
	public static String pathObservadosPlanilon = "";
	private static JFileChooser jFileChooser;

	public static boolean verificarPaths() {
		if (pathImagenesRnv.isEmpty() || pathObservadosPlanilon.isEmpty()) {
			return false;
		}
		return true;
	}

	public static String setearPathsRnv(JInternalFrame ventana) {
		jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jFileChooser.setDialogTitle("Seleccione la ruta de las imagenes de RNV");
		int result = jFileChooser.showOpenDialog(ventana);
		String pathPadronProcesar = "";
		File fEscogido = null;
		while (fEscogido == null) {
			fEscogido = jFileChooser.getSelectedFile();
			if (fEscogido != null)
				pathPadronProcesar = fEscogido.getAbsolutePath();
			else{
				if (result == JFileChooser.CANCEL_OPTION)
					return "";
				result = jFileChooser.showOpenDialog(ventana);								
			}
		}
		pathImagenesRnv = pathPadronProcesar;
		return pathPadronProcesar;
	}

	public static String setearPathsObservado(JInternalFrame ventana) {
		jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jFileChooser.setDialogTitle("Seleccione la ruta donde se guardan los observados del planillon");
		int result = jFileChooser.showOpenDialog(ventana);
		String pathPadronProcesar = "";
		File fEscogido = null;
		while (fEscogido == null) {
			fEscogido = jFileChooser.getSelectedFile();
			if (fEscogido != null)
				pathPadronProcesar = fEscogido.getAbsolutePath();
			else{
				if (result == JFileChooser.CANCEL_OPTION)
					return "";
				result = jFileChooser.showOpenDialog(ventana);
			}
		}
		pathObservadosPlanilon = pathPadronProcesar;
		return pathPadronProcesar;
	}
}
