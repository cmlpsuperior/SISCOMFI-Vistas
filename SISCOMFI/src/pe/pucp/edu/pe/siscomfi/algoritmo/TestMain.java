package pe.pucp.edu.pe.siscomfi.algoritmo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.Duplicator;
import ij.process.ImageProcessor;
import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.Adherente;

public class TestMain {

	public static void main(String[] args) throws IOException {
		/*
		 * List<Adherente> lista =
		 * siscomfiManager.getPosiblesAdherentes("46136008"); for(Adherente adh
		 * : lista){ System.out.println(adh.getDni()); }
		 */

		/*
		 * ImagePlus img = IJ.openImage("Imagenes\\p1.jpg"); img =
		 * HelperMethods.procesarPlanillon(img); ImagePlus auxImg = new
		 * Duplicator().run(img); int[] tCampos =
		 * HelperMethods.cabeceraPlanillon(auxImg); List<ImagePlus> filas =
		 * HelperMethods.sacarFilasPlanillon(img); int nFila = 0; for (ImagePlus
		 * fila : filas) { List<ImagePlus> partes =
		 * HelperMethods.sacarDatosFila(fila, tCampos);
		 * 
		 * // dni 8, n y ap 48 //System.out.print("Fila " + (nFila + 1) +
		 * ": DNI-> "); //List<ImagePlus> digitosNumero =
		 * HelperMethods.getDatosParte(partes.get(0), 8); System.out.println(
		 * "Fila " + (nFila +1)); System.out.println("Firma"); ImagePlus firma =
		 * HelperMethods.quitarBorde(partes.get(2));
		 * System.out.println("Huella"); ImagePlus huella =
		 * HelperMethods.quitarBorde(partes.get(3)); IJ.saveAs(firma, "Jpeg",
		 * "C:\\Users\\samoel\\Desktop\\TestImage\\auxiliar\\firma"+nFila+".jpg"
		 * ); IJ.saveAs(huella, "Jpeg",
		 * "C:\\Users\\samoel\\Desktop\\TestImage\\auxiliar\\huella"+nFila+
		 * ".jpg"); System.out.println("Firma: w= " + firma.getWidth() + " h= "
		 * + firma.getHeight()); System.out.println("Huella: w= " +
		 * huella.getWidth() + " h= " + huella.getHeight()); /*String dni = " ",
		 * digit; for (ImagePlus dNumb : digitosNumero) { if (dNumb != null) {
		 * digit = OcrProy.ocrNumbers.reconocer(dNumb.getBufferedImage()); dni
		 * += digit; } } System.out.print(dni); List<ImagePlus> digitosLetra =
		 * HelperMethods.getDatosParte(partes.get(1), 48); String nombreAp = " "
		 * ; for (ImagePlus dLet : digitosLetra) { if (dLet != null) { digit =
		 * OcrProy.ocrLetters.reconocer(dLet.getBufferedImage()); nombreAp +=
		 * digit; } } System.out.println(nombreAp);
		 */
		/*
		 * nFila++; }
		 */

		// img = HelperMethods.cortarAbajoPlanillon(img);

		// PARTE PARA CORTAR PLANILLON

		/*
		 * ImagePlus img = IJ.openImage("Imagenes\\001.jpg"); ImagePlus
		 * recortado = HelperMethods.recortarPlanillonO(img, img); // //
		 * recortado.show(); //IJ.saveAs(recortado, "Jpeg", //
		 * "C:\\Users\\samoel\\Desktop\\TestImage\\padron\\pp.jpg"); ImagePlus
		 * recortadoOriginal = new Duplicator().run(recortado);
		 * 
		 * List<ImagePlus> lista = HelperMethods.getFilasPlanillonO(recortado);
		 * 
		 * // for (ImagePlus mm : lista) { mm.show(); } for(int i = 0; i <
		 * lista.size() ; i++){ List<ImagePlus> parteLista =
		 * HelperMethods.getPartesFilaO(lista.get(i), recortadoOriginal); for
		 * (ImagePlus mm : parteLista) { mm.show(); } System.out.print("Fila " +
		 * (i+1) + ": "); int len = 8; List<ImagePlus> datos =
		 * HelperMethods.cropSection(parteLista.get(0), len); String dni = " ";
		 * for (ImagePlus mm : datos) { String digito =
		 * OcrProy.ocrNumbers.reconocer(mm.getBufferedImage()); dni += digito; }
		 * System.out.print(dni + " "); len = 48; datos =
		 * HelperMethods.cropSection(parteLista.get(1), len); String
		 * nombreApellido = " "; for (ImagePlus mm : datos) { //mm.show();
		 * String letra = OcrProy.ocrLetters.reconocer(mm.getBufferedImage());
		 * nombreApellido += letra; } System.out.print(nombreApellido);
		 * System.out.println(); }
		 */

		// CARGAR OCR - COMPARAR

		/*
		 * OcrFinal ocr = new OcrFinal(); ocr.cargarEntrenamiento();
		 * ocr.entrenarRed(); for (int i = 1; i < 25; i++) { ImagePlus imp =
		 * IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\test\\p" + i +
		 * ".JPG"); IJ.run(imp, "Make Binary", ""); BufferedImage img2 =
		 * imp.getBufferedImage(); System.out.print("resultado de p" + i + ": "
		 * ); ocr.reconocer(img2); }
		 */

		// HUELLA

		double[][] graphOriginal = Fingerprint.imageGraph("C:\\Users\\samoel\\Desktop\\TestImage\\imagenes\\002_1.jpg");
		String filename = "";
		for (int i = 2; i < 50; i++) {
			for (int j = 1; j < 3; j++) {
				if (i < 10)
					filename = "C:\\Users\\samoel\\Desktop\\TestImage\\imagenes\\00" + i + "_" + j + ".jpg";
				else
					filename = "C:\\Users\\samoel\\Desktop\\TestImage\\imagenes\\0" + i + "_" + j + ".jpg";

				double[][] graphSuspect = Fingerprint.imageGraph(filename);
				double res = Fingerprint.comparition(graphOriginal, graphSuspect);
				System.out.println(Fingerprint.resultado(res) + " Porcentaje: " + res);
			}
		}

		// FIRMAS
		/*
		 * ImagePlus impOriginal = IJ.openImage(
		 * "C:\\Users\\samoel\\Desktop\\ImagenesRnv\\firmas\\f002.jpg");
		 * impOriginal = Signatures.formatoFirma(impOriginal); impOriginal =
		 * Signatures.formatoFirmaSospechosa(impOriginal); for(int i = 1; i <
		 * 59; i++){ String name = "f"; String number = (i <
		 * 10)?("00"+i):("0"+i); name += number; System.out.println(name);
		 * ImagePlus impSuspect = IJ.openImage(
		 * "C:\\Users\\samoel\\Desktop\\ImagenesRnv\\firmas\\"+name+".jpg");
		 * impSuspect = Signatures.formatoFirma(impSuspect); impSuspect =
		 * Signatures.formatoFirmaSospechosa(impSuspect); double res =
		 * Signatures.compareSignatures(impOriginal, impSuspect);
		 * System.out.println(res); }
		 */
	}
}
