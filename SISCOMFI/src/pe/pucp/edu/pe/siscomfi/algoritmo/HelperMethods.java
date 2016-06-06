package pe.pucp.edu.pe.siscomfi.algoritmo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.Duplicator;
import ij.process.ImageProcessor;
import pe.pucp.edu.pe.siscomfi.model.Point;

public class HelperMethods {
	
	public static ImagePlus removeBlanks(ImagePlus ip) {
        ImagePlus trimImage = new Duplicator().run(ip);
        int lowerBound = 0, upperBound = ip.getHeight();

        int threshold = 3;
        //lowerBound
        int col = threshold;
        boolean reachedNumber = false;
        while (reachedNumber == false && (col < (ip.getHeight() / 2))) {
            for (int row = 0; row < ip.getWidth(); row++) {
                if (ip.getPixel(row, col)[0] == 255) {
                    reachedNumber = true;
                }
            }
            if (reachedNumber == false) {
                col++;
            }
        }
        lowerBound = col;

        //upperBound
        col = ip.getHeight() - threshold;
        reachedNumber = false;
        while (reachedNumber == false && (col > (ip.getHeight() / 2))) {
            for (int row = 0; row < ip.getWidth(); row++) {
                if (ip.getPixel(row, col)[0] == 255) {
                    reachedNumber = true;
                }
            }
            if (reachedNumber == false) {
                col--;
            }
        }
        upperBound = col;

        if (upperBound > lowerBound && lowerBound < trimImage.getWidth() / 2 && upperBound > trimImage.getWidth() / 2) {
            trimImage.setRoi(0, lowerBound - 1, trimImage.getWidth(), upperBound - lowerBound + 1);
            IJ.run(trimImage, "Crop", "");
        }
        return trimImage;
    }
	
	public static List<ImagePlus> cropSection(ImagePlus originalImg, int len) {
        ImagePlus img = new Duplicator().run(originalImg);
        List<ImagePlus> imageList = new ArrayList<ImagePlus>();
        int defaultHeight = 5, lowerBound;
        for (int i = 0; i < len; i++) {
            int sectionWidth = 0;

            ImagePlus element = new Duplicator().run(img);
            while (element.getPixel(sectionWidth, defaultHeight)[0] != 0) {
                sectionWidth++;
            }

            lowerBound = sectionWidth;
            while (element.getPixel(sectionWidth, defaultHeight)[0] == 0) {
                sectionWidth++;
            }

            element.setRoi(lowerBound, 5, sectionWidth - 2, element.getHeight() - 10);
            IJ.run(element, "Crop", "");
            imageList.add(element);
            img.setRoi(sectionWidth, 0, img.getWidth() - sectionWidth + 1, img.getHeight());
            IJ.run(img, "Crop", "");
        }
        return imageList;
    }
	
	public static byte[][] imgToByte(int[][] img){
		byte[][] arr = new byte[img[0].length][img.length];
		for(int i = 0 ; i < img[0].length;i++){
			for(int j = 0 ; j < img.length;j++){
				arr[i][j] = (byte)img[i][j];
			}
		}		
		return arr;
	}

	public static int getTamTabla(ImagePlus planillon) {
		int x = 1;
		int y = 0;
		int r = 0;
		while (r == 0) {
			r = planillon.getPixel(x, y)[0];
			y++;
		}
		// System.out.println("yTopTabla: " + y);
		// y += 2;
		while (r != 0) {
			r = planillon.getPixel(x, y)[0];
			x++;
		}
		return x;
	}

	public static int getTamFila(ImagePlus planillon) {
		int x = 5, r = 1, y = planillon.getHeight() - 1;
		while (r != 0) {
			r = planillon.getPixel(x, y)[0];
			y--;
		}
		int yBotFila = y + 1;
		// System.out.println("Bot: " + yBotFila);

		while (r == 0) {
			r = planillon.getPixel(x, y)[0];
			y--;
		}
		int yTopFila = y;
		// System.out.println("Top: " + yTopFila);
		int tamFila = yBotFila - yTopFila;
		return tamFila;
	}

	public static List<ImagePlus> getFilasPlanillon(ImagePlus planillon) {

		IJ.run(planillon, "Make Binary", "");
		// System.out.println("h: " + planillon.getHeight() + " w: " +
		// planillon.getWidth());

		int tamTabla = getTamTabla(planillon);
		// System.out.println("tamTabla: " + tamTabla);
		int x = 1, r = 1, y = planillon.getHeight() - 1;
		// int tamFila = getTamFila(planillon);
		List<ImagePlus> filas = new ArrayList<ImagePlus>();
		ImagePlus original = new Duplicator().run(planillon);
		// System.out.println("tamFila: " + tamFila + " tamTabla: " + tamTabla);
		y = planillon.getHeight() - 1;
		for (int i = 1; i < 9; i++) {
			x = 5;
			r = 1;
			while (r != 0) {
				r = planillon.getPixel(x, y)[0];
				y--;
			}
			int yBotFila = y + 1;
			while (r == 0) {
				r = planillon.getPixel(x, y)[0];
				y--;
			}
			int yTopFila = y;
			int tamFila = yBotFila - yTopFila;
			planillon.setRoi(0, yTopFila, tamTabla, tamFila + 2);
			IJ.run(planillon, "Crop", "");
			filas.add(planillon);
			planillon = new Duplicator().run(original);
			y = yTopFila;
		}

		return filas;
	}

	public static int[][] imgToMat(BufferedImage bin){
		int[][] mati = new int[bin.getWidth()][];
		for (int x = 0; x < bin.getWidth(); x++) {
			mati[x] = new int[bin.getHeight()];
			for (int y = 0; y < bin.getHeight(); y++) {
				mati[x][y] = (byte) (bin.getRGB(x, y) == 0xFFFFFFFF ? 0 : 1);
			}
		}
		return mati;
	}
	
	public static List<ImagePlus> getPartesFila(ImagePlus fila, ImagePlus planillon) {
		IJ.run(planillon, "Make Binary", "");
		int x = 5;
		int y = 0;
		int r = 0;
		while (r == 0) {
			r = planillon.getPixel(x, y)[0];
			y++;
		}
		//System.out.println("TopNegro:" + y);
		while (r != 0) {
			r = planillon.getPixel(x, y)[0];
			y++;
		}
		//System.out.println("BotNegro:" + y);
		int[] tCampos = new int[5];

		for (int i = 0; i < 5; i++) {
			while (r != 0) {
				r = planillon.getPixel(x, y)[0];
				x++;
			}
			int xLeft = x;
			//System.out.print("Campo: " + i + " FilaLeft: " + xLeft);
			while (r == 0) {
				r = planillon.getPixel(x, y)[0];
				x++;
			}
			int xRight = x;
			//System.out.println(" FilaRight: " + xRight);
			int espacioCampo = xRight - xLeft + 1;
			tCampos[i] = espacioCampo;
		}
		List<ImagePlus> partes = new ArrayList<ImagePlus>();
		ImagePlus filaOriginal = new Duplicator().run(fila);
		int dist_x = 5 + tCampos[0];
		for (int w = 1; w < 5; w++) {
			//System.out.print("Antes: " + dist_x + " Tam: " + tCampos[w]);
			
			//System.out.println(" Despues:" + dist_x);
			fila.setRoi(dist_x,0,tCampos[w],fila.getHeight());
			IJ.run(fila, "Crop", "");
			partes.add(fila);
			fila = new Duplicator().run(filaOriginal);
			dist_x += tCampos[w];	
		}
		return partes;
	}

	public static ImagePlus recortarPlanillon(ImagePlus img, ImagePlus original) {
		ImagePlus planillon = new Duplicator().run(img);
		IJ.run(planillon, "Make Binary", "");

		int y = planillon.getHeight() / 2, x = 0, r = 0, g, b;
		while (r == 0) {
			r = planillon.getPixel(x, y)[0];
			x++;
		}
		planillon.setRoi(x - 1, 0, planillon.getWidth() - (x - 1), planillon.getHeight());
		IJ.run(planillon, "Crop", "");

		y = planillon.getHeight();
		x = 5;
		r = 0;
		while (r == 0) {
			r = planillon.getPixel(x, y)[0];
			y--;
		}

		planillon.setRoi(0, 0, planillon.getWidth(), y + 1);
		IJ.run(planillon, "Crop", "");

		original.setRoi(original.getWidth() - planillon.getWidth(), 0, planillon.getWidth(), planillon.getHeight());
		IJ.run(original, "Crop", "");

		ImageProcessor imP = img.getProcessor();
		imP.setRoi(0, 0, 1, original.getHeight());
		imP.setValue(0);
		imP.fill();
		imP.setRoi(0, original.getHeight() - 1, original.getWidth(), 1);
		imP.setValue(0);
		imP.fill();

		return original;
	}

	public static void quickSort(Point[] arr, int low, int high, Point ref) {
		if (arr == null || arr.length == 0)
			return;

		if (low >= high)
			return;

		// pick the pivot
		int middle = low + (high - low) / 2;
		Point pivot = arr[middle];

		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (ref.euclideanDistance(arr[i]) < ref.euclideanDistance(pivot)) {
				i++;
			}

			while ((ref.euclideanDistance(arr[j]) > ref.euclideanDistance(pivot))) {
				j--;
			}

			if (i <= j) {
				Point temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}

		// recursively sort two sub parts
		if (low < j)
			quickSort(arr, low, j, ref);

		if (high > i)
			quickSort(arr, i, high, ref);
	}

	public static int[][] removeNoise(int[][] mat) {
		// Mean filter
		float[][] filter = { { 0.0625f, 0.1250f, 0.0625f }, { 0.1250f, 0.2500f, 0.1250f },
				{ 0.0625f, 0.1250f, 0.0625f } };

		// Variables
		int limWidth = mat.length - 1;
		int limHeight = mat[0].length - 1;
		int[][] newmap = new int[mat.length][mat[0].length];
		float val;

		for (int i = 1; i < limWidth; ++i) {
			for (int j = 1; j < limHeight; ++j) {
				val = 0;

				// Apply the filter
				for (int ik = -1; ik <= 1; ++ik) {
					for (int jk = -1; jk <= 1; ++jk) {
						val += mat[i + ik][j + jk] * filter[1 + ik][1 + jk];
					}
				}
				newmap[i][j] = (val > 0.5) ? 1 : 0;
			}
		}

		return mat;
	}

	public static BufferedImage rotate(BufferedImage image, double angle) {
		double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
		int w = image.getWidth(), h = image.getHeight();
		int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
		GraphicsConfiguration gc = getDefaultConfiguration();
		BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
		Graphics2D g = result.createGraphics();
		g.translate((neww - w) / 2, (newh - h) / 2);
		g.rotate(angle, w / 2, h / 2);
		g.drawRenderedImage(image, null);
		g.dispose();
		return result;
	}

	private static GraphicsConfiguration getDefaultConfiguration() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		return gd.getDefaultConfiguration();
	}

	public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) {
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	}
}
