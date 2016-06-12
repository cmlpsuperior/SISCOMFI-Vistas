package pe.pucp.edu.pe.siscomfi.algoritmo;

import java.io.IOException;

import ij.IJ;
import ij.ImagePlus;
import ij.Prefs;
import ij.gui.PolygonRoi;
import ij.gui.Roi;
import ij.io.FileSaver;
import ij.plugin.Duplicator;
import ij.process.ImageProcessor;

public class PruebaNuevoOCR {
	static int  wPrueba= 1196; 
	static int  hPrueba = 87;
	static int 	colNumeracion = 27;
	static int colDniUnDig = 14;
	public static void main(String[] args) throws IOException {
		int i;
		
		ImagePlus img = IJ.openImage("Imagenes\\001.jpg");
		//IJ.run(img, "Make Binary", "");
		recortarCostadosProcesarPadron(img);
		
				
		int alturaFila= Math.round((hPrueba*(img.getWidth() - 1)) /wPrueba) ; //altura fija de una fila
		int anchoNumeracion = Math.round((colNumeracion*(img.getWidth()  - 1) ) /wPrueba );
		int anchoUnDigDni = Math.round((colDniUnDig*(img.getWidth() - 1) ) /wPrueba );
		int yFinal;
		
		yFinal = img.getHeight();
		for (i=0; i<8; i++){
			ImagePlus imgCopia = new Duplicator ().run(img);
			
			
			imgCopia.setRoi(2, yFinal - alturaFila, imgCopia.getWidth(), alturaFila);
            IJ.run(imgCopia, "Crop", "");   
             
            yFinal = (yFinal- alturaFila) ;
             
            quitarBordes(imgCopia,anchoNumeracion );
            
            
            
            IJ.saveAs(imgCopia, "Jpeg", "Imagenes\\001_salida_"+i+".jpg" );
            //nos quedamos con el bloque de dni;
            extraerDigDni(imgCopia);
            //extraerHuella(imgCopia);
            //extraerFirma(imgCopia);
            imgCopia.show();
            
		}
		IJ.saveAs(img, "Jpeg", "Imagenes\\001_salida.jpg" );
	}
	
	//extraerHuella(imgCopia);

	public static void quitarBordes (ImagePlus img, int anchoQuitar){
		
		img.setRoi(anchoQuitar, 10, img.getWidth()-8, img.getHeight()-10);		
        IJ.run(img, "Crop", ""); 
	}
	
	public static void extraerDigDni(ImagePlus img){
		int r = 0, i, m;    
		int posicionFinal=0;
		int anchototal = img.getWidth();
		int alturatotal = img.getHeight();
		for (m=0; m<8; m++){	
			ImagePlus imgCopia = new Duplicator ().run(img);
			anchototal = imgCopia.getWidth();
			alturatotal = imgCopia.getHeight();
				        
	        for(i=0 ;i<anchototal ;i++){
	            r = imgCopia.getPixel(i, 3)[0];
	            if(r!=0){
	                posicionFinal = i;
	                break;    
	            }
	        }
	        imgCopia.setRoi(0, 0, posicionFinal-1, alturatotal);		
	        IJ.run(imgCopia, "Crop", ""); 
	        imgCopia.show();
	        
	        //cortamos la img para quitar el digito ya sacado
	        img.setRoi(posicionFinal+2, 0, anchototal, alturatotal);		
	        IJ.run(img, "Crop", ""); 
	        img.show();
		}
		
	}
	 public static void alinearPadron(ImagePlus padronJ){  
		 
	        int width=padronJ.getWidth();
	        int height=padronJ.getHeight();
	        int i,r = 0;
	        
	        int PX1=0, PX2=0, PX3=0;
	        int PY1=0, PY2=0, PY3=0;
	        
	        for(i=0;i<width;i++){
	            r = padronJ.getPixel(i, height/2)[0];
	            if(r!=0){
	                PX1 = i;
	                PY1 = height/2;
	                break;    
	            }
	        }
	        //IJ.run(padronJ, "Make Binary", "");
	        
	        // Ahora situamos X, Y-40    
	        PX2 = i;
	        PY2 = PY1-40;
	        
	        //obtenemos el ultimo punto para formar el angulo
	        
	        for(i=PX2;i>0;i--){
	            r = padronJ.getPixel(i, PY2)[0];
	            if(r!=0){//r=255 , g=0 , b=0 
	                PX3 = i;
	                PY3 = PY2;
	                break;    
	            }
	        }
	        
	        int[] xpoints1 = {PX3,PX1,PX2};
	        int[] ypoints1 = {PY3,PY1,PY2};
	        /*
	        int j;
	        for (j=0; j<3;j++){
	        	System.out.println(xpoints1[j]);
	        	System.out.println(ypoints1[j]);
	        	
	        }
	        */
	        double anguloDouble = new PolygonRoi(xpoints1,ypoints1,3,Roi.ANGLE).getAngle();
	        int anguloInt = (int) Math.round(anguloDouble);
	        String str1 = "angle=" + anguloInt + " grid=0 interpolation=None";
	        //System.out.println(str1);
	        IJ.run(padronJ, "Rotate... ", str1);
	    }
	 
	 public static void eliminarLineasNegras(ImagePlus padronJ){
	        
	        int width=padronJ.getWidth();
	        int height=padronJ.getHeight();
	        int pixels = 10;

	        //Verificamos el lado izquierdo
	        for (int i = 0; i< width; i++ ){
	            int R = padronJ.getPixel(i, height/2)[0];
	            if ( R == 0 ){
	                //System.out.println("entre aka xd");
	                if (i != 0){
	                    padronJ.setRoi(i,0,width-i-1,height-1);
	                    IJ.run(padronJ, "Crop", "");    
	                    //padronJ.show();
	                }
	                break;
	            }
	        }
	        
	        //Verificamos el lado derecho
	        /*
	        for(int j=width;j>0;j--){
	             int R = padronJ.getPixel(j, pixels)[0];
	            if(R == 0){
	                if (j !=0){
	                    padronJ.setRoi(0,j,width-j-1,height-1);
	                    IJ.run(padronJ, "Crop", "");    
	                    break;    
	                }
	            }
	        }

	        
	        //Verificamos abajo

	        for(int i=height; i>0 ; i--){
	             int R = padronJ.getPixel(pixels, i)[0];
	            if(R==0){//r=255 , g=0 , b=0 
	                if (i != 0){
	                    padronJ.setRoi(i,0,width-i-1,height-1);
	                    IJ.run(padronJ, "Crop", "");    
	                    //padronJ.show();
	                }                
	                break;            
	            }
	        }
	        */
	 }
	 
	 public static void recortarCostadosProcesarPadron(ImagePlus padronJ){
	        
	        int widthPar=2073;
	        int heightPar=972;
	        int personasxPadron = 8; // kappa
	        
	        Prefs.blackBackground = false;        
	        IJ.run(padronJ, "Make Binary", "");
	        
	        
	        //Verificamos si es que no existe lineas negras en la imagen
	        
	        eliminarLineasNegras(padronJ);        
	        alinearPadron(padronJ);


	        
	        //////////////
	        //ELIMINA LA PARTE DE LA IZQUIERDA
	        //////////////
	        
	        int width=padronJ.getWidth();
	        int height=padronJ.getHeight();
	        
	        int i,r = 0,g = 0,b = 0 , m = 0;
	        for(i=0;i<width;i++){
	             r = padronJ.getPixel(i, height/2)[0];
	             g = padronJ.getPixel(i, height/2)[1];
	             b = padronJ.getPixel(i, height/2)[2];
	            //System.out.println(i);
	            //System.out.println(r + " "+ g + " " + b);
	            if(r!=0)//r=255 , g=0 , b=0 
	                //System.out.println(r + " "+ g + " " + b);
	                break;                                            
	        }

	        padronJ.setRoi(i,0,width-i-1,height-1);
	        IJ.run(padronJ, "Crop", "");
	        //////////////
	        //ELIMINA LA PARTE DE ABAJO
	        //////////////
	        width=padronJ.getWidth();
	        height=padronJ.getHeight();
	        
	        int pixels=10;
	        for(i=height;i>0;i--){
	             r = padronJ.getPixel(pixels, i)[0];
	             g = padronJ.getPixel(pixels, i)[1];
	             b = padronJ.getPixel(pixels, i)[2];
	            //System.out.println(r + " "+ g + " " + b);
	            if(r!=0)//r=255 , g=0 , b=0 
	                //System.out.println(r + " "+ g + " " + b);
	                break;        
	        }
	        
	        padronJ.setRoi(0,0,width-1,i-1);
	        IJ.run(padronJ, "Crop", "");

	        //////////////
	        //ELIMINA LA PARTE DE LA DERECHA
	        //////////////
	        width=padronJ.getWidth();
	        height=padronJ.getHeight();
	        
	        for(i=0;i<height;i++){
	             r = padronJ.getPixel(pixels, i)[0];
	             g = padronJ.getPixel(pixels, i)[1];
	             b = padronJ.getPixel(pixels, i)[2];
	            //System.out.println(r + " "+ g + " " + b);
	            if(r!=0)//r=255 , g=0 , b=0 
	                //System.out.println(r + " "+ g + " " + b);
	                break;        
	        }
	        i++;
	        int j;
	        for(j=width;j>0;j--){
	             r = padronJ.getPixel(j, i)[0];
	             g = padronJ.getPixel(j, i)[1];
	             b = padronJ.getPixel(j, i)[2];
	            //System.out.println(r + " "+ g + " " + b);
	            if(r!=0)//r=255 , g=0 , b=0 
	                //System.out.println(r + " "+ g + " " + b);
	                break;    
	        }
	        
	        padronJ.setRoi(0,0,j,height);
	        IJ.run(padronJ, "Crop", "");	        
	        //IJ.run(padronJ, "Skeletonize", "");
	            
	        
	    }
	
}