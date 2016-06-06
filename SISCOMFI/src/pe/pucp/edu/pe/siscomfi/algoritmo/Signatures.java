package pe.pucp.edu.pe.siscomfi.algoritmo;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import ij.IJ;
import ij.ImagePlus;
import pe.pucp.edu.pe.siscomfi.model.Point;

public class Signatures {

	public static Rectangle getRectangularArea(BufferedImage img) {
		Point top = null, left = null, right = null, bot = null;
		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < (img.getWidth()); j++) {
				if (img.getRGB(j, i) == Color.BLACK.getRGB()) {
					top = new Point(i - 1, j);
					break;
				}
			}
			if (top != null)
				break;
		}

		for (int j = 0; j < img.getWidth(); j++) {
			for (int i = 0; i < img.getHeight(); i++) {
				if (img.getRGB(j, i) == Color.BLACK.getRGB()) {
					left = new Point(i, j - 1);
					break;
				}
			}
			if (left != null)
				break;
		}

		for (int i = img.getHeight() - 1; i >= 0; i--) {
			for (int j = 0; j < img.getWidth(); j++) {
				if (img.getRGB(j, i) == Color.BLACK.getRGB()) {
					bot = new Point(i + 1, j);
					break;
				}
			}
			if (bot != null)
				break;
		}

		for (int j = img.getWidth() - 1; j >= 0; j--) {
			for (int i = 0; i < img.getHeight(); i++) {
				if (img.getRGB(j, i) == Color.BLACK.getRGB()) {
					right = new Point(i, j + 1);
					break;
				}
			}
			if (right != null)
				break;
		}
		Rectangle rec = new Rectangle(top.getX(), left.getY(), bot.getX() - top.getX(), right.getY() - left.getY());
		return rec;
	}	

	public static Point getAngleImage2(BufferedImage img) {
		int width = img.getWidth();
		int left = 0;
		int right = width-1;
		double angleProm = 0.0;
		int cont = 0;
		while(left < right){
			Point pL = null;
			Point pR = null;
			//Punto Izquierda
			for (int i = img.getHeight() - 1; i >= 0; i--) {
				if (img.getRGB(left, i) == Color.BLACK.getRGB()) {
					pL = new Point(i, left);
					break;
				}
			}
			//System.out.println("pL?: " + pL == null);
			//Punto Derecha
			for (int i = img.getHeight() - 1; i >= 0; i--) {
				if (img.getRGB(right, i) == Color.BLACK.getRGB()) {
					pR = new Point(i, right);
					break;
				}
			}
			//System.out.println("pR?: " + pR == null);
			//Angulo entre estos puntos
			if(pL != null && pR != null){
				double anglePoints = pL.getAnglePoint(pR);
				angleProm += anglePoints;
				//contador de puntos
				cont++;
			}			
			//Sigo contando para el siguiente punto
			left++;
			right--;
			
			
		}
		double angleFinal = angleProm / cont;
		Point pFinal = new Point(0,0);
		pFinal.setAngle(angleFinal);
		return pFinal;
	}
	
	public static Point getAngleImage(BufferedImage img) {

		double angleP = 0.0;
		for (int w = 0; w < 5; w++) {
			int y1 = 1 + 5 * w;
			Point p1 = null;
			for (int i = img.getHeight() - 1; i >= 0; i--) {
				if (img.getRGB(y1, i) == Color.BLACK.getRGB()) {
					p1 = new Point(i, y1);
					break;
				}
			}
			int y2 = img.getWidth() - 1 - 5 * w;
			Point p2 = null;
			for (int i = img.getHeight() - 1; i >= 0; i--) {
				if (img.getRGB(y2, i) == Color.BLACK.getRGB()) {
					p2 = new Point(i, y2);
					break;
				}
			}

			double angle = p1.getAnglePoint(p2) - (Math.PI / 2);
			angleP += angle;
			// pFinal = p1;
			// pFinal.setAngle(angle - Math.PI / 2);
		}
		double angleFF = angleP / 5;
		Point pX = new Point(0, 0);
		pX.setAngle(angleFF);
		return pX;

		/*
		 * int y1 = 1; Point p1 = null; for (int i = img.getHeight() - 1; i >=
		 * 0; i--) { if (img.getRGB(y1, i) == Color.BLACK.getRGB()) { p1 = new
		 * Point(i, y1); break; } } int y2 = img.getWidth() - 1; Point p2 =
		 * null; for (int i = img.getHeight() - 1; i >= 0; i--) { if
		 * (img.getRGB(y2, i) == Color.BLACK.getRGB()) { p2 = new Point(i, y2);
		 * break; } }
		 * 
		 * double angle = p1.getAnglePoint(p2); Point pFinal = p1;
		 * pFinal.setAngle(angle - Math.PI / 2); return pFinal;
		 */
		// System.out.println("angle anterior: " + Math.toDegrees(angle));
		// System.out.println("angle final:
		// "+Math.toDegrees(pFinal.getAngle()));

	}

	public static double compare(BufferedImage cDbimg, BufferedImage crop2) {
		int m = 0, n = 0, p = 0, pixelColor, color;
		int w = cDbimg.getWidth();
		int h = cDbimg.getHeight();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				pixelColor = cDbimg.getRGB(i, j);
				color = crop2.getRGB(i, j);
				if (pixelColor == Color.BLACK.getRGB()) {
					if (pixelColor == color)
						m++;
					else
						n++;
					p++;
				}
			}
		}
		double comp = (m * 1.0 / (n + p));
		return comp;
	}

	public static double compareSignatures(BufferedImage original, BufferedImage suspect) throws IOException {
		// original
		ImagePlus impOriginal = new ImagePlus("", original);
		IJ.run(impOriginal, "Make Binary", "");
		original = impOriginal.getBufferedImage();
		Rectangle recOriginal = Signatures.getRectangularArea(original);
		impOriginal.setRoi((int) recOriginal.getY(), (int) recOriginal.getX(), (int) recOriginal.getHeight(),
				(int) recOriginal.getWidth());
		IJ.run(impOriginal, "Crop", "");
		BufferedImage ccOriginal = impOriginal.getBufferedImage();

		// suspect
		ImagePlus impSuspect = new ImagePlus("", suspect);
		IJ.run(impSuspect, "Make Binary", "");
		suspect = impSuspect.getBufferedImage();
		Rectangle recSuspect = Signatures.getRectangularArea(suspect);

		// cortamos segun el rectangulo
		impSuspect.setRoi((int) recSuspect.getY(), (int) recSuspect.getX(), (int) recSuspect.getHeight(),
				(int) recSuspect.getWidth());
		IJ.run(impSuspect, "Crop", "");
		BufferedImage ccSuspect = impSuspect.getBufferedImage();

		// saca el angulo de rotacion
		Point pRot = Signatures.getAngleImage(ccSuspect);
		//System.out.println("Angle: " + Math.toDegrees(pRot.getAngle()));
		// rotamos la imagen
		if (Math.toDegrees(pRot.getAngle()) > 11)
			IJ.run(impSuspect, "Rotate... ",
					"angle=" + Math.toDegrees(pRot.getAngle()) + " grid=1 interpolation=Bilinear enlarge");
		if (Math.toDegrees(pRot.getAngle()) < 0)
			IJ.run(impSuspect, "Rotate... ",
					"angle=" + Math.toDegrees(pRot.getAngle()) + " grid=1 interpolation=Bilinear enlarge");
		IJ.run(impSuspect, "Make Binary", "");
		
		// formamos el rectangulo denuevo
		Rectangle rec2 = Signatures.getRectangularArea(impSuspect.getBufferedImage());
		//System.out.println("x: " + rec2.getX() + " y: " + rec2.getY() + " h: " + rec2.getHeight() + " w: " + rec2.getWidth());
		ImagePlus impSuspectN = new ImagePlus("", impSuspect.getBufferedImage());
		impSuspectN.setRoi((int) rec2.getY(), (int) rec2.getX(), (int) rec2.getHeight(), (int) rec2.getWidth());
		IJ.run(impSuspectN, "Crop", "");
		BufferedImage ccSuspectN = impSuspectN.getBufferedImage();
		ccSuspectN = HelperMethods.resizeImage(ccSuspectN, ccOriginal.getWidth(), ccOriginal.getHeight(),
				ccOriginal.getType());
		// w,h
		double res = compare(ccOriginal, ccSuspectN);
		return res;
	}
}
