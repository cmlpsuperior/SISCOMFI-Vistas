package pe.pucp.edu.pe.siscomfi.algoritmo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.Duplicator;
import ij.process.ImageProcessor;
import pe.pucp.edu.pe.siscomfi.bm.BD.siscomfiManager;
import pe.pucp.edu.pe.siscomfi.model.Adherente;
import pe.pucp.edu.pe.siscomfi.model.Cronometro;
import pe.pucp.edu.pe.siscomfi.model.Planillon;
import pe.pucp.edu.pe.siscomfi.model.RegistroElector;
import pe.pucp.edu.pe.siscomfi.model.UsuarioLogeado;

public class TestMain {

	public static void main(String[] args) throws IOException {
		File padrons = new File("/home/osboxes/Desktop/Padrones/9");
		File[] padronPaths = padrons.listFiles();
		String pathObsPartido = "/home/osboxes/Desktop/Observados/" + "1" + "/"
				+ "9 - A";
		File pPartido = new File(pathObsPartido + "/Fase1");
		pPartido.mkdirs();
		for (File padron : padronPaths) {
			// empezamos a calcular el tiempo de planillonv2
			Cronometro crnPlanillon = new Cronometro();
			crnPlanillon.start();
			// leemos el planillon
			ImagePlus imgPlanillon = IJ.openImage(padron.getAbsolutePath());
			// procesamos el planillon
			imgPlanillon = HelperMethods.procesarPlanillon(imgPlanillon);
			//imgPlanillon.show();
			// sacamos el tama�o de los campos
			ImagePlus auxImg = new Duplicator().run(imgPlanillon);
			int[] tCampos = HelperMethods.cabeceraPlanillon(auxImg);
			// sacamos las filas
			List<ImagePlus> filas = HelperMethods
					.sacarFilasPlanillon(imgPlanillon);
			int nFila = 8;
			// procesamos cada fila
			for (ImagePlus fila : filas) {
				List<ImagePlus> partes = HelperMethods.sacarDatosFila(fila,
						tCampos);

				// empezamos a calcular el tiempro de proceso del
				// adherentexplanillonv2
				Cronometro crnAdherente = new Cronometro();
				crnAdherente.start();
				// sacamoslos digitos del DNI (8)
				List<ImagePlus> digitosNumero = HelperMethods.getDatosParte(
						partes.get(0), 8);
				String dni = "", digit;
				for (ImagePlus dNumb : digitosNumero) {
					if (dNumb != null) {
						digit = OcrProy.ocrNumbers.reconocer(dNumb
								.getBufferedImage());
						dni += digit;
					}
				}

				List<Adherente> lista = siscomfiManager
						.getPosiblesAdherentes(dni);
				if (lista != null && lista.size() != 0) {

					int contIguales = 0;
					String resultado = "";
					// las leidas del planillon
					ImagePlus imgHuella = null;
					ImagePlus imgFirma = null;
					// las originales del rnv
					ImagePlus imgHuellaOriginal = null;
					ImagePlus imgFirmaOriginal = null;
					// porcentajes de firma y huella
					double pFirma = 0, pHuella = 0;
					Adherente adherente = null;
					for (Adherente adh : lista) {

						ImagePlus huella = HelperMethods.quitarBorde(partes
								.get(3));
						imgHuella = new Duplicator().run(huella);
						ImagePlus huellaRnv = HelperMethods.buscarImagen(
								"/home/osboxes/Desktop/ImagenesRNV2/huellas",
								adh.getrHuella());
						imgHuellaOriginal = new Duplicator().run(huellaRnv);
						if ((huella == null) || (huellaRnv == null)) {
							pHuella = 0.6;
							resultado = "Observado";
							break;
						}
						double[][] original = Fingerprint.imageGraph(huellaRnv);
						adherente = adh;
						double [][] sospechosa = {};
						try{
							sospechosa = Fingerprint
									.imageGraph(huella);
						}catch (NullPointerException ex){
							pHuella = 0.6;
							resultado = "Observado";
							break;
						}
						if ((original.length == 0) || (sospechosa.length == 0)) {
							pHuella = 0.6;
							resultado = "Observado";
							break;
						}
						double porcentaje = Fingerprint.comparition(original,
								sospechosa);
						resultado = Fingerprint.resultado(porcentaje);
						if (resultado.compareTo("Iguales") == 0) {
							contIguales++;
						}
						// ***
						pHuella = porcentaje;

					}

					// solo debe de haber 1 igual si se encontraron
					// muchos matches
					if (contIguales == 1) {

						ImagePlus firma = HelperMethods.quitarBorde(partes
								.get(2));
						imgFirma = new Duplicator().run(firma);
						ImagePlus firmaRnv = HelperMethods.buscarImagen(
								"/home/osboxes/Desktop/ImagenesRNV2//firmas",
								adherente.getrFirma());
						imgFirmaOriginal = new Duplicator().run(firmaRnv);
						firmaRnv = Signatures.formatoFirma(firmaRnv);
						firma = Signatures.formatoFirma(firma);
						double res = Signatures.compareSignatures(firmaRnv,
								firma);
						pFirma = res;
						System.out.println("Fila " + nFila + " Res: " + res);

						// aceptado
						adherente.setEstado(1);

					} else {
						// asignar estado de adherente a observado
						String estadoFinal = "";
						// si hay mas de 1 igual rechazamos al adherente
						if (resultado.compareTo("Diferentes") == 0) {
							estadoFinal = "Rechazado";

							adherente.setEstado(0);
						} else {
							estadoFinal = "Observado";

							adherente.setEstado(2);
							// guardar las imagenes en la carpeta de
							// observados
							File fAdherente = new File(
									pPartido.getAbsoluteFile() + "/"
											+ adherente.getDni());
							fAdherente.mkdir();
							// carpeta de huellas
							File fHuella = new File(
									fAdherente.getAbsolutePath() + "/huella");
							fHuella.mkdir();
							if (imgHuella != null) {
								IJ.saveAs(imgHuella, "Jpeg",
										fHuella.getAbsolutePath()
												+ "/observado.jpg");
								IJ.saveAs(imgHuellaOriginal, "Jpeg",
										fHuella.getAbsolutePath()
												+ "/original.jpg");
							}
							if (imgFirma != null) {
								// carpeta de firmas
								File fFirma = new File(
										fAdherente.getAbsolutePath() + "/firma");
								fFirma.mkdir();

								IJ.saveAs(imgFirma, "Jpeg",
										fFirma.getAbsolutePath()
												+ "/observado.jpg");
								IJ.saveAs(imgFirmaOriginal, "Jpeg",
										fFirma.getAbsolutePath()
												+ "/original.jpg");
							}
						}

					}
					adherente.setrPlanillon(padron.getName());
					adherente.setpFirma(pFirma);
					adherente.setpHuella(pHuella);
					// verificar que adherente no se repita

				} else {
					// si no se encuentra se rechaza al adherente

				}
				// se termina
				nFila--;
			}
		}
	}
}
/*
 * String path = "/home/osboxes/Desktop/ImagenesRNV2/huellas"; File pHuella =
 * new File(path); File [] imagenes = pHuella.listFiles(); String rHuella =
 * "hhu070"; ImagePlus imgHuella = null; //encontramos for(File file :
 * imagenes){ //System.out.println(file.getName()); if
 * (file.getName().startsWith(rHuella)){ imgHuella =
 * IJ.openImage(file.getAbsolutePath()); break; } } imgHuella.show();
 * ImageProcessor imp_fing = imgHuella.getProcessor();
 * imp_fing.setBackgroundValue(0); imp_fing = imp_fing.resize(600, 600);
 * 
 * ImagePlus newFing = new ImagePlus("small", imp_fing); IJ.run(newFing,
 * "Make Binary", ""); IJ.run(newFing, "Skeletonize", ""); IJ.run(newFing,
 * "Make Binary", ""); newFing.show(); double[][] p =
 * Fingerprint.imageGraph(imgHuella); //System.out.println(p.length); /*File
 * imagenes = new File (path); File [] lista = imagenes.listFiles(); for(File
 * img: lista){ String name = img.getName(); System.out.println("name:" +name);
 * int n = name.indexOf("."); String ss = name.substring(0, n); String ssName =
 * ss + ".jpg"; img.renameTo(new File(path+"/"+ssName)); }
 */
/*
 * try {
 * 
 * FileInputStream file = new FileInputStream(
 * "/home/osboxes/Desktop/ImagenesRNV/huella");
 * 
 * // Get the workbook instance for XLS file XSSFWorkbook workbook = new
 * XSSFWorkbook(file);
 * 
 * // Get first sheet from the workbook XSSFSheet sheet =
 * workbook.getSheetAt(0);
 * 
 * // Iterate through each rows from first sheet Iterator<Row> rowIterator =
 * sheet.iterator(); List<RegistroElector> listaElectores = new
 * ArrayList<RegistroElector>(); // apellidos Paterno, materno, nombres, //
 * dni,ubigeo,rhuella,rfirma,habilitado int nrow = 0; while
 * (rowIterator.hasNext()) { Row row = rowIterator.next(); RegistroElector reg =
 * new RegistroElector(); // For each row, iterate through each columns
 * Iterator<Cell> cellIterator = row.cellIterator(); int cont = 0, maxRegs = 0;
 * while (cellIterator.hasNext()) { Cell cell = cellIterator.next(); switch
 * (cell.getCellType()) { case Cell.CELL_TYPE_BLANK: return; case
 * Cell.CELL_TYPE_NUMERIC: int number = (int) cell.getNumericCellValue(); if
 * (cont == 2) { reg.setDNI("" + number); } if (cont == 3) {
 * reg.setUbigeo(number); } if (cont == 6) { reg.setHabilitado(number); cont =
 * 0; break; } cont++; break; case Cell.CELL_TYPE_STRING: if (cont == 0) {//
 * apellidos String apellidos = cell.getStringCellValue(); String[] lista =
 * apellidos.split(" "); String apPaterno = lista[0]; String apMaterno = ""; for
 * (int i = 1; i < lista.length; i++) apMaterno += lista[i] + " ";
 * reg.setApellidoPaterno(apPaterno); reg.setApellidoMaterno(apMaterno); } if
 * (cont == 1) {// nombre String nombre = cell.getStringCellValue();
 * reg.setNombre(nombre); } if (cont == 4) { String rHuella =
 * cell.getStringCellValue(); reg.setrHuella(rHuella); } if (cont == 5) { String
 * rFirma = cell.getStringCellValue(); reg.setrFirma(rFirma); } cont++; break; }
 * if (maxRegs == 1) break; } if (nrow > 0) {
 * siscomfiManager.agregarRegistroElector(reg);
 * System.out.println(reg.toString()); } nrow++;
 * 
 * } workbook.close(); file.close(); // FileOutputStream out = new
 * FileOutputStream(new // File("C:\\test.xls")); // workbook.write(out); //
 * out.close();
 * 
 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
 * e) { e.printStackTrace(); }
 */

/*
 * List<Adherente> lista = siscomfiManager.getPosiblesAdherentes("46136008");
 * for(Adherente adh : lista){ System.out.println(adh.getDni()); }
 */

/*
 * ImagePlus img = IJ.openImage("Imagenes\\p1.jpg"); img =
 * HelperMethods.procesarPlanillon(img); ImagePlus auxImg = new
 * Duplicator().run(img); int[] tCampos =
 * HelperMethods.cabeceraPlanillon(auxImg); List<ImagePlus> filas =
 * HelperMethods.sacarFilasPlanillon(img); int nFila = 0; for (ImagePlus fila :
 * filas) { List<ImagePlus> partes = HelperMethods.sacarDatosFila(fila,
 * tCampos);
 * 
 * // dni 8, n y ap 48 //System.out.print("Fila " + (nFila + 1) + ": DNI-> ");
 * //List<ImagePlus> digitosNumero = HelperMethods.getDatosParte(partes.get(0),
 * 8); System.out.println( "Fila " + (nFila +1)); System.out.println("Firma");
 * ImagePlus firma = HelperMethods.quitarBorde(partes.get(2));
 * System.out.println("Huella"); ImagePlus huella =
 * HelperMethods.quitarBorde(partes.get(3)); IJ.saveAs(firma, "Jpeg",
 * "C:\\Users\\samoel\\Desktop\\TestImage\\auxiliar\\firma"+nFila+".jpg" );
 * IJ.saveAs(huella, "Jpeg",
 * "C:\\Users\\samoel\\Desktop\\TestImage\\auxiliar\\huella"+nFila+ ".jpg");
 * System.out.println("Firma: w= " + firma.getWidth() + " h= " +
 * firma.getHeight()); System.out.println("Huella: w= " + huella.getWidth() +
 * " h= " + huella.getHeight()); /*String dni = " ", digit; for (ImagePlus dNumb
 * : digitosNumero) { if (dNumb != null) { digit =
 * OcrProy.ocrNumbers.reconocer(dNumb.getBufferedImage()); dni += digit; } }
 * System.out.print(dni); List<ImagePlus> digitosLetra =
 * HelperMethods.getDatosParte(partes.get(1), 48); String nombreAp = " " ; for
 * (ImagePlus dLet : digitosLetra) { if (dLet != null) { digit =
 * OcrProy.ocrLetters.reconocer(dLet.getBufferedImage()); nombreAp += digit; } }
 * System.out.println(nombreAp);
 */
/*
 * nFila++; }
 */

// img = HelperMethods.cortarAbajoPlanillon(img);

// PARTE PARA CORTAR PLANILLON

/*
 * ImagePlus imgPlanillon = IJ.openImage(
 * "C:\\Users\\samoel\\Desktop\\PUCP\\2016-1\\Desarrollo de Programas 1\\Padron\\part.H.original9.jpg"
 * ); // procesamos el planillon imgPlanillon =
 * HelperMethods.procesarPlanillon(imgPlanillon); // sacamos el tama�o de los
 * campos ImagePlus auxImg = new Duplicator().run(imgPlanillon); int[] tCampos =
 * HelperMethods.cabeceraPlanillon(auxImg); // sacamos las filas List<ImagePlus>
 * filas = HelperMethods.sacarFilasPlanillon(imgPlanillon);
 */
/*
 * for (ImagePlus fila : filas) { List<ImagePlus> partes =
 * HelperMethods.sacarDatosFila(fila, tCampos); /*List<ImagePlus> digitosNumero
 * = HelperMethods.getDatosParte(partes.get(0), 8); String dni = "", digit; for
 * (ImagePlus dNumb : digitosNumero) { if (dNumb != null) { digit =
 * OcrProy.ocrNumbers.reconocer(dNumb.getBufferedImage()); dni += digit; } }
 * System.out.print("DNI: " + dni); String nombre = "",letter; List<ImagePlus>
 * letraNombre = HelperMethods.getDatosParte(partes.get(1), 48); for(ImagePlus
 * letra: letraNombre){ if (letra != null){ letter =
 * OcrProy.ocrLetters.reconocer(letra.getBufferedImage()); nombre += letter;
 * }else nombre += " "; } System.out.println("   Nombre: " + nombre); ImagePlus
 * huella = HelperMethods.quitarBorde(partes.get(3)); huella.show(); ImagePlus
 * firma = HelperMethods.quitarBorde(partes.get(2)); firma.show(); }
 */

// CARGAR OCR - COMPARAR

/*
 * OcrFinal ocr = new OcrFinal(); ocr.cargarEntrenamiento(); ocr.entrenarRed();
 * for (int i = 1; i < 25; i++) { ImagePlus imp =
 * IJ.openImage("C:\\Users\\samoel\\Desktop\\TestImage\\test\\p" + i + ".JPG");
 * IJ.run(imp, "Make Binary", ""); BufferedImage img2 = imp.getBufferedImage();
 * System.out.print("resultado de p" + i + ": " ); ocr.reconocer(img2); }
 */

// HUELLA

/*
 * double[][] graphOriginal = Fingerprint.imageGraph(
 * "C:\\Users\\samoel\\Desktop\\TestImage\\imagenes\\002_1.jpg"); String
 * filename = ""; for (int i = 2; i < 50; i++) { for (int j = 1; j < 3; j++) {
 * if (i < 10) filename = "C:\\Users\\samoel\\Desktop\\TestImage\\imagenes\\00"
 * + i + "_" + j + ".jpg"; else filename =
 * "C:\\Users\\samoel\\Desktop\\TestImage\\imagenes\\0" + i + "_" + j + ".jpg";
 * 
 * double[][] graphSuspect = Fingerprint.imageGraph(filename); double res =
 * Fingerprint.comparition(graphOriginal, graphSuspect);
 * System.out.println(Fingerprint.resultado(res) + " Porcentaje: " + res); } }
 */

// FIRMAS
/*
 * ImagePlus impOriginal = IJ.openImage(
 * "C:\\Users\\samoel\\Desktop\\ImagenesRnv\\firmas\\f002.jpg"); impOriginal =
 * Signatures.formatoFirma(impOriginal); impOriginal =
 * Signatures.formatoFirmaSospechosa(impOriginal); for(int i = 1; i < 59; i++){
 * String name = "f"; String number = (i < 10)?("00"+i):("0"+i); name += number;
 * System.out.println(name); ImagePlus impSuspect = IJ.openImage(
 * "C:\\Users\\samoel\\Desktop\\ImagenesRnv\\firmas\\"+name+".jpg"); impSuspect
 * = Signatures.formatoFirma(impSuspect); impSuspect =
 * Signatures.formatoFirmaSospechosa(impSuspect); double res =
 * Signatures.compareSignatures(impOriginal, impSuspect);
 * System.out.println(res); }
 */

