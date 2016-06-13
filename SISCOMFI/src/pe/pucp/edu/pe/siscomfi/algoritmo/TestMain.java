package pe.pucp.edu.pe.siscomfi.algoritmo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.Duplicator;
import ij.process.ImageProcessor;

public class TestMain {

	public static void main(String[] args) throws IOException {

		ImagePlus img = IJ.openImage("Imagenes\\p1.jpg");
		img = HelperMethods.cotarIzquierdaPlanillon(img);
		//img.show();
		//img = HelperMethods.cortarAbajoPlanillon(img);
		img.show();
		// PARTE PARA CORTAR PLANILLON

		/*ImagePlus img = IJ.openImage("Imagenes\\001.jpg");
		ImagePlus recortado = HelperMethods.recortarPlanillonO(img, img); //
		// recortado.show(); //IJ.saveAs(recortado, "Jpeg",
		// "C:\\Users\\samoel\\Desktop\\TestImage\\padron\\pp.jpg");
		ImagePlus recortadoOriginal = new Duplicator().run(recortado);

		List<ImagePlus> lista = HelperMethods.getFilasPlanillonO(recortado);

		// for (ImagePlus mm : lista) { mm.show(); }
		for(int i = 0; i < lista.size() ; i++){
			List<ImagePlus> parteLista = HelperMethods.getPartesFilaO(lista.get(i), recortadoOriginal);
			for (ImagePlus mm : parteLista) {
				mm.show();
			}
			System.out.print("Fila " + (i+1) + ": ");
			int len = 8;
			List<ImagePlus> datos = HelperMethods.cropSection(parteLista.get(0), len);
			String dni = " ";
			for (ImagePlus mm : datos) {
				String digito = OcrProy.ocrNumbers.reconocer(mm.getBufferedImage());
				dni += digito;
			}
			System.out.print(dni + " ");
			len = 48;
			datos = HelperMethods.cropSection(parteLista.get(1), len);
			String nombreApellido = " ";
			for (ImagePlus mm : datos) {
				//mm.show();
				String letra = OcrProy.ocrLetters.reconocer(mm.getBufferedImage());
				nombreApellido += letra;
			}
			System.out.print(nombreApellido); System.out.println();
		}*/
		
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

		/*
		 * double[][] graphOriginal = Fingerprint.imageGraph(
		 * "C:\\Users\\samoel\\Desktop\\TestImage\\imagenes\\002_1.jpg"); String
		 * filename = ""; for (int i = 2; i < 50; i++) { for (int j = 1; j < 3;
		 * j++) { if (i < 10) filename =
		 * "C:\\Users\\samoel\\Desktop\\TestImage\\imagenes\\00" + i + "_" + j +
		 * ".jpg"; else filename =
		 * "C:\\Users\\samoel\\Desktop\\TestImage\\imagenes\\0" + i + "_" + j +
		 * ".jpg";
		 * 
		 * double[][] graphSuspect = Fingerprint.imageGraph(filename); double
		 * res = Fingerprint.comparition(graphOriginal, graphSuspect);
		 * System.out.println(Fingerprint.resultado(res) + " Porcentaje: " +
		 * res); } }
		 */

		// FIRMAS

		/*
		 * ImagePlus impOriginal =
		 * IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\f3.jpg");
		 * ImagePlus impSuspect =
		 * IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\f3.jpg"); double
		 * res = Signatures.compareSignatures(impOriginal.getBufferedImage(),
		 * impSuspect.getBufferedImage()); System.out.println(res);
		 */

	}

}
