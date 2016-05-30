package pe.pucp.edu.pe.siscomfi.algoritmo;

public class DigitImage {
	int label;
	byte[] imageData;
	public DigitImage(int l, byte[] b){
		this.label = l;
		this.imageData = b;
	}
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	
}
