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

		// PARTE PARA CORTAR PLANILLON

		/*
		 * ImagePlus img = IJ.openImage(
		 * "C:\\Users\\samoel\\Desktop\\TestImage\\padron\\padron4.jpg");
		 * ImagePlus recortado = HelperMethods.recortarPlanillon2(img, img); //
		 * recortado.show(); // IJ.saveAs(recortado, //
		 * "Jpeg","C:\\Users\\samoel\\Desktop\\TestImage\\padron\\pp.jpg");
		 * ImagePlus recortadoOriginal = new Duplicator().run(recortado);
		 * 
		 * List<ImagePlus> lista = HelperMethods.getFilasPlanillon3(recortado);
		 * for (ImagePlus mm : lista) { mm.show(); }
		 * 
		 * /* List<ImagePlus> parteLista =
		 * HelperMethods.getPartesFila2(lista.get(1), recortadoOriginal); for
		 * (ImagePlus mm : parteLista) { mm.show(); } // 0 -> DNI, 1 -> nombre +
		 * apellido /*int len = 8; List<ImagePlus> datos =
		 * HelperMethods.cropSection(parteLista.get(0), len); for (ImagePlus mm
		 * : datos) { mm.show(); }
		 */

		// CARGAR OCR - COMPARAR
		OcrFinal ocr = new OcrFinal();
		ocr.cargarEntrenamiento();
		ocr.entrenarRed();
		for (int i = 1; i < 25; i++) {
			ImagePlus imp = IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\test\\p" + i + ".JPG");
			IJ.run(imp, "Make Binary", "");
			BufferedImage img2 = imp.getBufferedImage();
			System.out.print("resultado de p" + i + ": ");
			ocr.reconocer(img2);
		}

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
		 * IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\ff.jpg"); double
		 * res = Signatures.compareSignatures(impOriginal.getBufferedImage(),
		 * impSuspect.getBufferedImage()); System.out.println(res);
		 */

	}

}
