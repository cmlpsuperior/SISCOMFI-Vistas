package pe.pucp.edu.pe.siscomfi.algoritmo;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import pe.pucp.edu.pe.siscomfi.model.Point;

public class Fingerprint {
	private static int width;
	private static int height;
	private static int k = 6;

	// return the crossing number of the 3x3 matrix
	private static int CrossingNumber(int[][] CNMatrix) {
		int cn = 0, width = 3, heigth = 3;
		for (int i = 0; i < width; i++)
			for (int j = 0; j < heigth; j++) {
				if (i != j)
					if (CNMatrix[i][j] == 1)
						cn++;
			}
		return cn;
	}

	// returns the 3x3 matrix around the x,y point
	private static int[][] getCNMatrix(int[][] skelMatrix, int x, int y) {
		int[][] cnm = new int[3][3];
		if (x > 0 && y > 0 && x < width - 1 && y < height - 1) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					cnm[i][j] = skelMatrix[i + x - 1][j + y - 1];
				}
			}
		}
		return cnm;
	}

	private static boolean isBifurcation(int[][] cnmatrix) {
		int P1, P2, P3, P4, P5, P6, P7, P8;
		P8 = cnmatrix[0][0];
		P1 = cnmatrix[0][1];
		P2 = cnmatrix[0][2];
		P7 = cnmatrix[1][0];
		P3 = cnmatrix[1][2];
		P6 = cnmatrix[2][0];
		P5 = cnmatrix[2][1];
		P4 = cnmatrix[2][2];
		if ((P3 == 0 && P5 == 0 && P7 == 0) || (P3 == 0 && P5 == 1 && P7 == 0 && P8 == 1 && P2 == 1)
				|| (P3 == 0 && P5 == 0 && P7 == 1 && P2 == 1 && P4 == 1)
				|| (P3 == 0 && P5 == 0 && P7 == 0 && P4 == 1 && P6 == 1)
				|| (P3 == 1 && P5 == 0 && P7 == 0 && P6 == 1 && P8 == 1)
				|| (P8 != P6 && P1 != P5 && P2 != P4 && P7 == P3 && P8 == P2 && P6 == P4)
				|| (P8 == P6 && P1 == P5 && P2 == P4 && P7 != P3 && P8 != P2 && P6 != P4)
				|| (P8 == P2 && P2 == P4 && P4 == P6 && P6 == P8 && P8 == 0)
				|| ((P8 + P3 + P5) == 3 || (P2 + P7 + P5) == 3 || (P1 + P4 + P7) == 3 || (P1 + P3 + P6) == 3))
			return true;
		else
			return false;
	}

	// get the list of possible minutaes from the skeletized matrix
	private static List<Point> getMinutiaes(int[][] skelMatrix) {
		height = skelMatrix[0].length;
		width = skelMatrix.length;

		List<Point> minutaes = new ArrayList<Point>();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i > 0 && j > 0 && i < width - 1 && j < height - 1 && skelMatrix[i][j] == 1) {
					int[][] cnmatrix = getCNMatrix(skelMatrix, i, j);
					int cn = CrossingNumber(cnmatrix);

					if (cn == 1 || (cn == 3 && isBifurcation(cnmatrix)))
						if (skelMatrix[i][j] == 1) {
							Point point = new Point(i, j);
							point.setValue(skelMatrix[i][j]);
							point.setType(cn);
							minutaes.add(point);
							// }
						}
				}
			}
		}
		return minutaes;
	}

	// gives the K nearest point given a point x
	private static Point[] getNearestNeighbourType(Point x, List<Point> lista) {
		Point[] retur = new Point[k];
		double fjernest = Double.MIN_VALUE;
		int index = 0;

		for (int i = 0; i < lista.size(); i++) {
			double distance = x.euclideanDistance(lista.get(i));
			if (retur[retur.length - 1] == null) {
				int j = 0;
				while (j < retur.length) {
					if (retur[j] == null) {
						retur[j] = lista.get(i);
						break;
					}
					j++;
				}
				if (distance > fjernest) {
					index = j;
					fjernest = distance;
				}
			} else {
				if (distance < fjernest) {
					retur[index] = lista.get(i);
					double f = 0.0;
					int ind = 0;
					for (int j = 0; j < retur.length; j++) {
						double dt = retur[j].euclideanDistance(x);
						if (dt > f) {
							f = dt;
							ind = j;
						}
					}
					fjernest = f;
					index = ind;
				}
			}
		}
		return retur;
	}

	// compares the edges of two neighbours
	private static boolean compareEdges(double[] aS, double[] aT) {
		int cont_umb = 0;
		for (int i = 0; i < aS.length; i++) {
			if (aS[i] != 0) {
				double pesoS = aS[i];
				for (int j = 0; j < aT.length; j++) {
					if (aT[j] != 0) {
						double pesosT = aT[j];
						if (Math.abs(pesoS - pesosT) < 3.5) {
							cont_umb++;
						} else
							break;
					}
				}
			}
		}
		// System.out.println(cont_umb);
		boolean matchNeigh = (cont_umb * 1.0 / k) > 0.75;
		return matchNeigh;
	}

	// converts a list of minutaes in a graph, each minutae has its K nearest
	// neigbours
	private static double[][] matToGraph(List<Point> minutaes) {
		double[][] grafoS = new double[minutaes.size()][k];
		for (int i = 0; i < minutaes.size(); i++) {
			Point[] vecinos = Fingerprint.getNearestNeighbourType(minutaes.get(i), minutaes);
			for (int j = 1; j < vecinos.length; j++) {
				Point p = vecinos[j];
				grafoS[i][j - 1] = p.euclideanDistance(minutaes.get(i));
			}
		}
		return grafoS;
	}

	// Removes false minutaes
	private static List<Point> removeFalseMinutae(int[][] ske, List<Point> minutaes) {
		int[][][] matM = new int[ske.length][ske[0].length][4];
		for (Point p : minutaes) {
			int x = p.getX();
			int y = p.getY();
			int t = p.getType();
			matM[x][y][t] = 1;
		}
		int tk = 1, T = 14;
		Point P1t = null, P2t = null, P1b = null, P2b = null;
		for (int x = T; x < ske.length - T; x++) {
			for (int y = T; y < ske[0].length - T; y++) {
				if (matM[x][y][1] == 1) {
					P1t = new Point(x, y);
					P2t = new Point(-1, -1);
					P1b = new Point(-1, -1);
					tk = 1;
					for (int i = -T / 2; i < T / 2; i++) {
						for (int j = -T / 2; j < T / 2; j++) {
							if (matM[i + x][j + y][1] == 1) {
								if ((i + x) != x || (j + y) != y && tk == 1) {
									P2t.setY(j + y);
									P2t.setX(i + x);
									tk = 0;
								}
							}
							if (matM[i + x][j + y][2] == 1) {
								P1b.setY(y + j);
								P1b.setX(i + x);
							}
						}
					}
					if (P1b.getX() != -1) {
						matM[P1t.getX()][P1t.getY()][1] = 0;
						matM[P1b.getX()][P1b.getY()][2] = 0;
					}
					if (P2t.getX() != -1) {
						matM[P1t.getX()][P1t.getY()][1] = 0;
						matM[P2t.getX()][P2t.getY()][1] = 0;
					}
				}
				if (matM[x][y][2] == 1) {
					P1b = new Point(x, y);
					P2b = new Point(-1, -1);
					for (int i = -T / 2; i < T / 2; i++) {
						for (int j = -T / 2; j < T / 2; j++) {
							if (matM[i + x][j + y][2] == 1) {
								if ((i + x) != x || (j + y) != y) {
									P2b.setY(j + y);
									P2b.setX(i + x);
								}
							}
						}
					}
					if (P2b.getX() != -1) {
						matM[P1b.getX()][P1b.getY()][2] = 0;
						if (P1b.euclideanDistance(P2b) > 2) {
							matM[P2b.getX()][P2b.getY()][2] = 0;
						}
					}
				}
			}
		}
		List<Point> nueva = new ArrayList<Point>();
		for (int i = 0; i < matM.length; i++) {
			for (int j = 0; j < matM[0].length; j++) {
				if (matM[i][j][1] != 0 || matM[i][j][2] != 0 || matM[i][j][3] != 0) {
					nueva.add(new Point(i, j));
				}
			}
		}
		return nueva;
	}

	// Gives the result according to the comparition percent
	public static String resultado(double res) {
		if (res >= 0.9)
			return "Iguales";
		if (res < 0.9 && res >= 0.6)
			return "Observado";
		return "Diferentes";
	}

	// compares two graphs of fingerprints
	public static double comparition(double[][] grafoS, double[][] grafoT) {
		//if (grafoS.length == 0 || grafoT.length == 0) return 0.0;
		int match = 0;
		for (int i = 0; i < grafoS.length; i++) {
			double[] vecindadS = grafoS[i];
			int[] vec_aceptada = new int[grafoT.length];
			for (int j = 0; j < grafoT.length; j++) {
				if (vec_aceptada[j] == 0) {
					double[] vecindadT = grafoT[j];
					boolean matchNeigh = Fingerprint.compareEdges(vecindadS, vecindadT);
					if (matchNeigh) {
						match++;
						vec_aceptada[j] = 1;
						break;
					}
				}
			}
		}
		double comparition = (Math.pow(match, 2) / (grafoT.length * grafoS.length));
		double ratio = (grafoT.length < grafoS.length) ? grafoT.length * 1.0 / grafoS.length
				: grafoS.length * 1.0 / grafoT.length;
		return comparition * ratio;
	}

	// convertes an image to a graph given its directory
	public static double[][] imageGraph(String filename1) {
		ImagePlus fingerprint = IJ.openImage(filename1);
		ImageProcessor imp_fing = fingerprint.getProcessor();
		imp_fing.setInterpolate(true);
		imp_fing = imp_fing.resize(600, 600);

		ImagePlus newFing = new ImagePlus("small", imp_fing);
		IJ.run(newFing, "Make Binary", "");
		IJ.run(newFing, "Skeletonize", "");
		BufferedImage bin = newFing.getBufferedImage();

		int[][] ske = HelperMethods.imgToMat(bin);
		List<Point> fMinutaes = Fingerprint.getMinutiaes(ske);
		List<Point> tMinutaes = Fingerprint.removeFalseMinutae(ske, fMinutaes);
		double[][] grafoS = Fingerprint.matToGraph(tMinutaes);
		return grafoS;
	}

	// convertes an image to a graph
	public static double[][] imageGraph(ImagePlus filename1) {
		ImageProcessor imp_fing = filename1.getProcessor();
		imp_fing.setInterpolate(true);
		imp_fing = imp_fing.resize(600, 600);

		ImagePlus newFing = new ImagePlus("small", imp_fing);
		IJ.run(newFing, "Make Binary", "");
		IJ.run(newFing, "Skeletonize", "");
		BufferedImage bin = newFing.getBufferedImage();
		int[][] ske = HelperMethods.imgToMat(bin);
		List<Point> fMinutaes = Fingerprint.getMinutiaes(ske);
		List<Point> tMinutaes = Fingerprint.removeFalseMinutae(ske, fMinutaes);
		double[][] grafoS = Fingerprint.matToGraph(tMinutaes);
		return grafoS;
	}
}
