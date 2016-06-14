package pe.pucp.edu.pe.siscomfi.algoritmo;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.Line;
import ij.plugin.Duplicator;
import ij.process.ImageProcessor;
import pe.pucp.edu.pe.siscomfi.model.Point;

public class HelperMethods {

	// Para moverse con pixeles ->
	private static int negroDerecha(int r, int x, int y, ImagePlus planillon) {
		while (r != 0) {
			r = planillon.getPixel(x, y)[0];
			x++;
		}
		// System.out.println("Buscando x (negro derecha): " + x);
		return x;
	}

	private static int negroIzquierda(int r, int x, int y, ImagePlus planillon) {
		while (r != 0) {
			r = planillon.getPixel(x, y)[0];
			x--;
		}
		// System.out.println("Buscando x (negro izquierda): " + x);
		return x;
	}

	private static int negroArriba(int r, int x, int y, ImagePlus planillon) {
		while (r != 0) {
			r = planillon.getPixel(x, y)[0];
			y--;
		}
		return y;
	}

	private static int negroAbajo(int r, int x, int y, ImagePlus planillon) {
		while (r != 0) {
			r = planillon.getPixel(x, y)[0];
			y++;
		}
		// System.out.println("Buscando x (negro Abajo): " + y);
		return y;
	}

	private static int blancoDerecha(int r, int x, int y, ImagePlus planillon) {
		while (r == 0) {
			r = planillon.getPixel(x, y)[0];
			x++;
		}
		// System.out.println("Buscando y (blanco derecha): " + x);
		return x;
	}

	private static int blancoIzquierda(int r, int x, int y, ImagePlus planillon) {
		while (r == 0) {
			r = planillon.getPixel(x, y)[0];
			x--;
		}
		// System.out.println("Buscando x (blanco izquierda): " + x);
		return x;
	}

	private static int blancoArriba(int r, int x, int y, ImagePlus planillon) {
		while (r == 0) {
			r = planillon.getPixel(x, y)[0];
			y--;
		}
		return y;
	}

	private static int blancoAbajo(int r, int x, int y, ImagePlus planillon) {
		while (r == 0) {
			r = planillon.getPixel(x, y)[0];
			y++;
		}
		// System.out.println("Buscando y (blanco Abajo): " + y);
		return y;
	}

	// para cortar el lado izquierdo del planillon
	public static ImagePlus procesarPlanillon(ImagePlus planillon) {
		ImageProcessor imp = planillon.getProcessor();
		imp = imp.resize(3500);
		planillon = new ImagePlus("aux", imp);
		planillon = cortarIzquierdaPlanillon(planillon);
		planillon = cortarDerechaPlanillon(planillon);
		planillon = cortarAbajoPlanillon(planillon);
		planillon = girarPlanillon(planillon);
		return planillon;
	}

	private static ImagePlus cortarIzquierdaPlanillon(ImagePlus planillon) {
		ImagePlus auxPlanillon = new Duplicator().run(planillon);
		IJ.run(planillon, "Make Binary", "");
		// seteando coordenadas
		int x = 0, y = (planillon.getHeight() - 1) / 2;
		// ignorar si hay un borde negro en la izquierda
		x = negroDerecha(1, x, y, planillon);
		// seguir hasta encontrar la tabla
		x = blancoDerecha(0, x + 1, y, planillon);
		// cortar el lado izquierdo de la imagen
		auxPlanillon.setRoi(x, 0, planillon.getWidth(), planillon.getHeight());
		IJ.run(auxPlanillon, "Crop", "");
		return auxPlanillon;
	}

	private static ImagePlus cortarDerechaPlanillon(ImagePlus planillon) {
		ImagePlus auxPlanillon = new Duplicator().run(planillon);
		IJ.run(planillon, "Make Binary", "");
		int x = 10, y = 0;
		// vamos al inicio de la tabla
		y = negroAbajo(1, x, y, planillon);
		y = blancoAbajo(0, x, y + 1, planillon);
		// Como puede estar girado aumento un offset al y
		y += 10;
		// recorro desde el lado derecho de la imagen hasta llegar ala tabla
		x = negroIzquierda(1, planillon.getWidth() - 1, y, planillon);
		x = blancoIzquierda(0, x - 1, y, planillon);
		// cortamos el lado derecho de la imagen
		auxPlanillon.setRoi(0, 0, x, planillon.getHeight());
		IJ.run(auxPlanillon, "Crop", "");
		return auxPlanillon;

	}

	private static ImagePlus cortarAbajoPlanillon(ImagePlus planillon) {
		ImagePlus auxPlanillon = new Duplicator().run(planillon);
		IJ.run(planillon, "Make Binary", "");
		int y = negroArriba(1, 10, planillon.getHeight(), planillon);
		y = blancoArriba(0, 10, y, planillon);
		auxPlanillon.setRoi(0, 0, planillon.getWidth(), y);
		IJ.run(auxPlanillon, "Crop", "");
		return auxPlanillon;
	}

	private static ImagePlus girarPlanillon(ImagePlus planillon) {
		ImagePlus auxPlanillon = new Duplicator().run(planillon);
		IJ.run(planillon, "Make Binary", "");
		// Lado Izquierdo
		int xI = 10, yI = 0;
		// LadoDerecho
		int xD = planillon.getWidth() - 10, yD = 0;
		// bajamos al lado izquierdo de la tabla
		yI = negroAbajo(1, xI, yI, planillon);
		yI = blancoAbajo(0, xI, yI + 1, planillon);
		// bajamos al lado derecho de la tabla
		yD = negroAbajo(1, xD, yD, planillon);
		yD = blancoAbajo(0, xD, yD + 1, planillon);
		// hallamos el angulo
		double angulo = new Line(1, 500, 18, 500).getAngle(xI, yI, xD, yD);
		//System.out.println("angulo: " + angulo);
		ImageProcessor imp = auxPlanillon.getProcessor();
		imp.setBackgroundValue(255);
		imp.rotate(angulo);
		auxPlanillon = new ImagePlus("girado", imp);
		return auxPlanillon;
	}

	public static int[] cabeceraPlanillon(ImagePlus planillon) {
		ImagePlus auxPlanillon = new Duplicator().run(planillon);

		IJ.run(planillon, "Make Binary", "");
		int x = 10, y = 0;
		y = negroAbajo(1, x, y, planillon);
		y = blancoAbajo(0, x, y + 1, planillon);
		y = negroAbajo(1, x, y + 1, planillon);
		y += 2;

		int[] tCampos = new int[5];
		for (int i = 0; i < 5; i++) {
			x = negroDerecha(1, x, y, planillon);
			int xLeft = x;
			// System.out.print("Campo: " + i + " FilaLeft: " + xLeft);
			x = blancoDerecha(0, x + 1, y, planillon);
			int xRight = x;
			// System.out.println(" FilaRight: " + xRight);
			int espacioCampo = xRight - xLeft + 1;
			tCampos[i] = espacioCampo;
		}
		// 0 N°,1 DNI, 2 Nombre y Apelido, 3 Firma, 4 Huella
		planillon = new ImagePlus("", auxPlanillon.getProcessor());
		return tCampos;
	}

	public static List<ImagePlus> sacarFilasPlanillon(ImagePlus planillon) {
		ImagePlus auxPlanillonCrop = new Duplicator().run(planillon);
		ImagePlus auxPlanillon = new Duplicator().run(planillon);
		IJ.run(planillon, "Make Binary", "");
		int x = 15, y = planillon.getHeight() - 1;
		// int tamFila = getTamFila(planillon);
		List<ImagePlus> filas = new ArrayList<ImagePlus>();

		// System.out.println("tamFila: " + tamFila + " tamTabla: " + tamTabla);
		for (int i = 0; i < 8; i++) {
			y = negroArriba(1, x, y, planillon);
			int yBotFila = y + 1;
			y = blancoArriba(0, x, y + 1, planillon);
			int yTopFila = y;
			int tamFila = yBotFila - yTopFila;
			auxPlanillonCrop.setRoi(0, yTopFila, planillon.getWidth(), tamFila + 2);
			IJ.run(auxPlanillonCrop, "Crop", "");
			// para asegurarme que la fila tenga almenos un borde negro
			ImageProcessor imP = auxPlanillonCrop.getProcessor();
			imP.setRoi(0, 0, auxPlanillonCrop.getWidth(), 5);
			imP.setValue(0);
			imP.fill();
			//
			filas.add(auxPlanillonCrop);
			auxPlanillonCrop = new Duplicator().run(auxPlanillon);
			y = yTopFila;
		}
		return filas;
	}

	public static List<ImagePlus> sacarDatosFila(ImagePlus fila, int[] tCampos) {
		List<ImagePlus> partes = new ArrayList<ImagePlus>();
		ImagePlus filaOriginal = new Duplicator().run(fila);
		int dist_x = 12 + tCampos[0];
		for (int w = 1; w < 5; w++) {
			// System.out.print("Antes: " + dist_x + " Tam: " + tCampos[w]);
			// System.out.println(" Despues:" + dist_x);
			fila.setRoi(dist_x, 0, tCampos[w], fila.getHeight());
			IJ.run(fila, "Crop", "");
			// cortar el borde blanco que se puede generar por el girado
			ImagePlus auxParte = new Duplicator().run(fila);
			IJ.run(fila, "Make Binary", "");
			int x = blancoDerecha(0, 0, (fila.getHeight() / 2) - 1, fila);
			auxParte.setRoi(x - 1, 0, auxParte.getWidth() - x, auxParte.getHeight());
			IJ.run(auxParte, "Crop", "");
			// ---
			partes.add(auxParte);
			fila = new Duplicator().run(filaOriginal);
			dist_x += tCampos[w];
		}
		return partes;
	}

	// Para sacar los espacios de cada parte de la fila del planillon
	private static ImagePlus borrarBordeIzquierda(ImagePlus img) {
		int x, y;
		x = 0;
		y = 10;
		int r = img.getPixel(x, y)[0];
		if (r == 255) {
			while (r == 255) {
				x = x + 1;

				r = img.getPixel(x, y)[0];
			}
			img.setRoi(x, 0, img.getWidth() - x, img.getHeight());
			img = new Duplicator().run(img);
		}
		return img;
	}

	private static ImagePlus borrarBordeDerecha(ImagePlus img) {
		int x, y;
		x = img.getWidth();
		y = 10;
		int r = img.getPixel(x, y)[0];
		if (r == 255) {
			while (r == 255) {
				x--;
				r = img.getPixel(x, y)[0];
			}
			img.setRoi(0, 0, x, img.getHeight());
			img = new Duplicator().run(img);
		}
		return img;
	}

	private static ImagePlus borrarBordeArriba(ImagePlus img) {
		int x, y;
		x = derechaBlanco(0,img);
		x = derechaNegro(x+1, 30, img) + 5;
		y = 0;
		int r = img.getPixel(x, y)[0];
		if (r == 255) {
			while (r == 255) {
				y++;
				r = img.getPixel(x, y)[0];
			}
			
			//System.out.println("borrarBordeArriba y : " + y);
			//System.out.println("borrarBordeArriba w:" + img.getWidth() + " h: " +img.getHeight());
			img.setRoi(0, y, img.getWidth(), img.getHeight());
			img = new Duplicator().run(img);
			//System.out.println("borrarBordeArriba w:" + img.getWidth() + " h: " +img.getHeight());
		}
		return img;
	}

	private static ImagePlus borrarBordeAbajo(ImagePlus img) {
		int x, y;
		x = 10;
		y = img.getHeight() - 1;
		int r = img.getPixel(x, y)[0];
		if (r == 255) {
			while (r == 255) {
				y--;
				r = img.getPixel(x, y)[0];
			}
			//System.out.println("adnmaskldlasdmaslmdlksa -> y " + y );
			

			img.setRoi(0, 0, img.getWidth(), y);
			img = new Duplicator().run(img);
		}
		return img;
	}

	private static int derechaBlanco(int x, ImagePlus img) {
		int y = 10;
		int r = img.getPixel(x, y)[0];
		while (r == 0) {
			x++;
			r = img.getPixel(x, y)[0];
			if (x > img.getWidth())
				break;
		}
		return x;
	}

	private static int derechaNegro(int x, int y, ImagePlus img) {

		int r = img.getPixel(x, y)[0];
		while (r == 255) {
			x++;
			r = img.getPixel(x, y)[0];
			if (x > img.getWidth())
				break;
		}
		return x;
	}

	private static ImagePlus cutPadding(ImagePlus img) {
		int y = 10, min = 10000, max = 0;
		int r;

		for (int i = 10; i < img.getWidth() - 10; i++) {
			y = 10;
			while (y < img.getHeight()) {
				r = img.getPixel(i, y)[0];
				if (r == 255) {
					if (y < min) {
						min = y;
					}
					break;
				}
				y = y + 1;
			}
			y = img.getHeight() - 10;
			while (y > 0) {
				r = img.getPixel(i, y)[0];
				if (r == 255) {
					if (y > max) {
						max = y;
					}
					break;
				}
				y = y - 1;
			}

		}

		min = min - 10;
		max = max + 10;

		img.setRoi(0, min, img.getWidth(), max - min);
		img = new Duplicator().run(img);
		// si la imagen es pequeña la ignoramos
		if (img.getHeight() < 10 || img.getWidth() < 10) {
			img = null;
		}
		return img;
	}

	public static ImagePlus quitarBorde(ImagePlus img) {
		IJ.run(img, "Make Binary", "");
		//System.out.println("Original -> Firma: w= " + img.getWidth() + " h= " + img.getHeight());
		img = borrarBordeArriba(img);
		//System.out.println("Arriba -> Firma: w= " + img.getWidth() + " h= " + img.getHeight());
		img = borrarBordeIzquierda(img);
		//System.out.println("Izquierda -> Firma: w= " + img.getWidth() + " h= " + img.getHeight());
		img = borrarBordeDerecha(img);
		//System.out.println("Derecha -> Firma: w= " + img.getWidth() + " h= " + img.getHeight());
		img = borrarBordeAbajo(img);
		//System.out.println("Abajo -> Firma: w= " + img.getWidth() + " h= " + img.getHeight());
		return img;
	}

	public static ArrayList<ImagePlus> getDatosParte(ImagePlus img, int n) {
		ArrayList<ImagePlus> imgs = new ArrayList<>();
		IJ.run(img, "Make Binary", "");
		// Borrar bordes de todos los lados
		img = borrarBordeArriba(img);
		img = borrarBordeIzquierda(img);
		img = borrarBordeDerecha(img);
		img = borrarBordeAbajo(img);

		int x1 = 0, x2 = 0;
		for (int i = 0; i < n; i++) {
			// calcular el espacio de cada celdita
			x2 = derechaBlanco(x1, img);
			img.setRoi(x1, 0, x2 - x1, img.getHeight());
			ImagePlus aux = new Duplicator().run(img);
			aux = cutPadding(aux);
			if (aux != null) {
				imgs.add(aux);
			} else {
				imgs.add(null);
			}
			x1 = derechaNegro(x2, 10, img);
		}
		return imgs;
	}

	public static byte[][] imgToByte(int[][] img) {
		byte[][] arr = new byte[img[0].length][img.length];
		for (int i = 0; i < img[0].length; i++) {
			for (int j = 0; j < img.length; j++) {
				arr[i][j] = (byte) img[i][j];
			}
		}
		return arr;
	}

	public static int[][] imgToMat(BufferedImage bin) {
		int[][] mati = new int[bin.getWidth()][];
		for (int x = 0; x < bin.getWidth(); x++) {
			mati[x] = new int[bin.getHeight()];
			for (int y = 0; y < bin.getHeight(); y++) {
				mati[x][y] = (byte) (bin.getRGB(x, y) == 0xFFFFFFFF ? 0 : 1);
			}
		}
		return mati;
	}

	/*
	 * public static int[][] removeNoise(int[][] mat) { // Mean filter float[][]
	 * filter = { { 0.0625f, 0.1250f, 0.0625f }, { 0.1250f, 0.2500f, 0.1250f },
	 * { 0.0625f, 0.1250f, 0.0625f } }; // Variables int limWidth = mat.length -
	 * 1; int limHeight = mat[0].length - 1; int[][] newmap = new
	 * int[mat.length][mat[0].length]; float val; for (int i = 1; i < limWidth;
	 * ++i) { for (int j = 1; j < limHeight; ++j) { val = 0; // Apply the filter
	 * for (int ik = -1; ik <= 1; ++ik) { for (int jk = -1; jk <= 1; ++jk) { val
	 * += mat[i + ik][j + jk] * filter[1 + ik][1 + jk]; } } newmap[i][j] = (val
	 * > 0.5) ? 1 : 0; } } return mat; }
	 */

	/*
	 * public static BufferedImage rotate(BufferedImage image, double angle) {
	 * double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	 * int w = image.getWidth(), h = image.getHeight(); int neww = (int)
	 * Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w *
	 * sin); GraphicsConfiguration gc = getDefaultConfiguration(); BufferedImage
	 * result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	 * Graphics2D g = result.createGraphics(); g.translate((neww - w) / 2, (newh
	 * - h) / 2); g.rotate(angle, w / 2, h / 2); g.drawRenderedImage(image,
	 * null); g.dispose(); return result; }
	 * 
	 * private static GraphicsConfiguration getDefaultConfiguration() {
	 * GraphicsEnvironment ge =
	 * GraphicsEnvironment.getLocalGraphicsEnvironment(); GraphicsDevice gd =
	 * ge.getDefaultScreenDevice(); return gd.getDefaultConfiguration(); }
	 */

	public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) {
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	}
}
