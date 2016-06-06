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
		//0 -> DNI, 1 -> nombre + apellido
		int len = 48;
		List<ImagePlus> datos = HelperMethods.cropSection(parteLista.get(1), len);
		for (ImagePlus mm : datos) {
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
		 * IJ.run(impOriginal, "Make Binary", ""); IJ.run(impOriginal,
		 * "Skeletonize", ""); double angleOriginal =
		 * Signatures.getAngleImage2(impOriginal.getBufferedImage()).getAngle();
		 * IJ.saveAs(impOriginal, "Jpeg",
		 * "C:\\Users\\samoel\\Desktop\\TestImage\\prep\\original.jpg");
		 * 
		 * ImagePlus impSuspect =
		 * IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\t1.jpg");
		 * IJ.run(impSuspect, "Make Binary", ""); IJ.run(impSuspect,
		 * "Skeletonize", ""); double angleSuspect =
		 * Signatures.getAngleImage2(impSuspect.getBufferedImage()).getAngle();
		 * IJ.saveAs(impSuspect, "Jpeg",
		 * "C:\\Users\\samoel\\Desktop\\TestImage\\prep\\suspect.jpg");
		 * 
		 * angleOriginal = (angleOriginal < 0) ? Math.PI * 2 - angleOriginal :
		 * angleOriginal; angleSuspect = (angleSuspect < 0) ? Math.PI * 2 -
		 * angleSuspect : angleSuspect; System.out.println(
		 * "Angulo del Original: " + angleOriginal); System.out.println(
		 * "Angulo del Suspect: " + angleSuspect);
		 * 
		 * double angleProm = (angleOriginal + angleSuspect) / 2; double
		 * angleExtraOriginal = angleProm - angleOriginal; double
		 * angleExtraSuspect = angleProm - angleSuspect; System.out.println(
		 * "Angulo Promedio: " + angleProm); System.out.println(
		 * "Extra Original: " + Math.toDegrees(angleExtraOriginal));
		 * System.out.println("Extra Suspect: " +
		 * Math.toDegrees(angleExtraSuspect));
		 * 
		 * 
		 * ImageProcessor ipOriginal = impOriginal.getProcessor();
		 * ipOriginal.setBackgroundValue(0);
		 * ipOriginal.rotate(Math.toDegrees(angleExtraOriginal)); ImagePlus
		 * ipOriginalGirada = new ImagePlus("Original Girada", ipOriginal);
		 * IJ.saveAs(ipOriginalGirada, "Jpeg",
		 * "C:\\Users\\samoel\\Desktop\\TestImage\\prep\\originalGirada.jpg");
		 * 
		 * ImageProcessor ipSuspect = impSuspect.getProcessor();
		 * ipSuspect.setBackgroundValue(255);
		 * ipSuspect.rotate(Math.toDegrees(angleExtraSuspect));
		 * ipSuspect.resize(impOriginal.getWidth(), impOriginal.getHeight());
		 * ImagePlus ipSuspectGirada = new ImagePlus("", ipSuspect);
		 * IJ.saveAs(ipSuspectGirada, "Jpeg",
		 * "C:\\Users\\samoel\\Desktop\\TestImage\\prep\\suspectGirada.jpg");
		 * 
		 * System.out.println("Original: h = " + impOriginal.getHeight() +
		 * " w = " + impOriginal.getWidth()); System.out.println("Suspect: h = "
		 * + impSuspect.getHeight() + " w = " + impSuspect.getWidth());
		 * 
		 * double res = Signatures.compare(impOriginal.getBufferedImage(),
		 * impSuspect.getBufferedImage()); System.out.println(res);
		 */

	}

}
