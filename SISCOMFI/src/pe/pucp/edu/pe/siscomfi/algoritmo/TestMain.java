package pe.pucp.edu.pe.siscomfi.algoritmo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.Duplicator;

public class TestMain {

	public static void main(String[] args) throws IOException {

		// PARTE PARA CORTAR PLANILLON

		ImagePlus img = IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\padron\\padron3.jpg");
		ImagePlus recortado = HelperMethods.recortarPlanillon(img, img);
		ImagePlus recortadoOriginal = new Duplicator().run(recortado);

		List<ImagePlus> lista = HelperMethods.getFilasPlanillon(recortado);
		for (ImagePlus mm : lista) {
			// mm.show();

		}
		List<ImagePlus> parteLista = HelperMethods.getPartesFila(lista.get(1), recortadoOriginal);
		for (ImagePlus mm : parteLista) {
			mm.show();
		}

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

		// FIRMAS
		ImagePlus impOriginal = IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\f3.jpg");
		ImagePlus impSuspect = IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\ff.jpg");
		double res = Signatures.compareSignatures(impOriginal.getBufferedImage(), impSuspect.getBufferedImage());
		System.out.println(res);

	}

}
