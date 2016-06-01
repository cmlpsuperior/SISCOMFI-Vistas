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

		ImagePlus img = IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\padron\\padron3.jpg");
		ImagePlus recortado = HelperMethods.recortarPlanillon(img, img);
		ImagePlus recortadoOriginal = new Duplicator().run(recortado);

		List<ImagePlus> lista = HelperMethods.getFilasPlanillon(recortado);
		for (ImagePlus mm : lista) { // mm.show();

		}
		List<ImagePlus> parteLista = HelperMethods.getPartesFila(lista.get(1), recortadoOriginal);
		for (ImagePlus mm : parteLista) {
			mm.show();
		}

		// CARGAR OCR - COMPARAR

		/*
		 * OcrFinal ocr = new OcrFinal(); ocr.cargarEntrenamiento();
		 * ocr.entrenarRed(); for (int i = 1; i < 25; i++) { ImagePlus imp =
		 * IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\test\\p" + i +
		 * ".JPG"); IJ.run(imp, "Make Binary", ""); BufferedImage img2 =
		 * imp.getBufferedImage(); System.out.print("resultado de p" + i + ": "
		 * ); ocr.reconocer(img2); }
		 */
		// FIRMAS

		/*
		 * ImagePlus impOriginal =
		 * IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\f3.jpg");
		 * ImagePlus impSuspect =
		 * IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\f4.jpg");
		 * 
		 * double res =
		 * Signatures.compareSignatures(impOriginal.getBufferedImage(),
		 * impSuspect.getBufferedImage()); System.out.println("Comparacion: " +
		 * res);
		 */

		/*
		 * ImagePlus impOriginal =
		 * IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\t2.jpg");
		 * IJ.run(impOriginal,"Make Binary",""); double angleOriginal =
		 * Signatures.getAngleImage2(impOriginal.getBufferedImage()).getAngle();
		 * 
		 * ImagePlus impSuspect =
		 * IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\t1.jpg");
		 * IJ.run(impSuspect,"Make Binary",""); double angleSuspect =
		 * Signatures.getAngleImage2(impSuspect.getBufferedImage()).getAngle();
		 * 
		 * angleOriginal = (angleOriginal < 0) ? Math.PI * 2 - angleOriginal :
		 * angleOriginal; angleSuspect = (angleSuspect < 0) ? Math.PI * 2 -
		 * angleSuspect : angleSuspect;
		 * 
		 * double angleProm = (angleOriginal + angleSuspect) / 2; double
		 * angleExtraOriginal = angleProm - angleOriginal; double
		 * angleExtraSuspect = angleProm - angleSuspect;
		 * 
		 * ImageProcessor ipOriginal = impOriginal.getProcessor();
		 * ipOriginal.setBackgroundValue(255);
		 * ipOriginal.rotate(angleExtraOriginal); impOriginal = new
		 * ImagePlus("", ipOriginal);
		 * 
		 * ImageProcessor ipSuspect = impSuspect.getProcessor();
		 * ipSuspect.setBackgroundValue(255);
		 * ipSuspect.rotate(angleExtraSuspect);
		 * ipSuspect.resize(impOriginal.getWidth(), impOriginal.getHeight());
		 * impSuspect = new ImagePlus("", ipSuspect);
		 * System.out.println(impSuspect);
		 * 
		 * System.out.println("Original: h = " + impOriginal.getHeight() +
		 * " w = "+ impOriginal.getWidth()); System.out.println("Suspect: h = "
		 * + impSuspect.getHeight() + " w = "+ impSuspect.getWidth());
		 * 
		 * 
		 * double res = Signatures.compare(impOriginal.getBufferedImage(),
		 * impSuspect.getBufferedImage()); System.out.println(res);
		 */

	}

}
