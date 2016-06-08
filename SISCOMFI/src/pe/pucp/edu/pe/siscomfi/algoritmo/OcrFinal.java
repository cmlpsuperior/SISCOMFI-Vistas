package pe.pucp.edu.pe.siscomfi.algoritmo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.som.SOM;
import org.encog.neural.som.training.clustercopy.SOMClusterCopyTraining;

import ij.IJ;
import ij.ImagePlus;

public class OcrFinal {
	static final int DOWNSAMPLE_WIDTH = 50;
	static final int DOWNSAMPLE_HEIGHT = 50;
	private final Entry entry = new Entry();
	private final DefaultListModel letterListModel = new DefaultListModel();
	private final List<String> lettersL = new ArrayList<String>();
	private final Sample sample = new Sample(DOWNSAMPLE_WIDTH, DOWNSAMPLE_HEIGHT);
	private SOM net;

	private BufferedImage mnistToBImg(DigitImage img) {
		BufferedImage img2 = new BufferedImage(DOWNSAMPLE_WIDTH, DOWNSAMPLE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		byte[] arr = img.getImageData();
		int cont = 0;
		for (int w = 0; w < 28; w++) {
			for (int y = 0; y < 28; y++) {
				img2.setRGB(y, w, arr[cont++]);
			}
		}

		ImagePlus imp = new ImagePlus("img", img2);
		IJ.run(imp, "Make Binary", "");
		return imp.getBufferedImage();
	}

	public void cargarEntrenamiento() {
		try {
			FileReader f;// the actual file stream
			BufferedReader r;// used to read the file line by line

			f = new FileReader(new File("./numberTrain.dat"));
			r = new BufferedReader(f);
			String line;
			int i = 0;

			this.letterListModel.clear();
			this.lettersL.clear();
			while ((line = r.readLine()) != null) {
				lettersL.add("" + line.charAt(0));
				final SampleData ds = new SampleData(line.charAt(0), DOWNSAMPLE_WIDTH,
						DOWNSAMPLE_HEIGHT);
				this.letterListModel.add(i++, ds);
				int idx = 2;
				for (int y = 0; y < ds.getHeight(); y++) {
					for (int x = 0; x < ds.getWidth(); x++) {
						ds.setData(x, y, line.charAt(idx++) == '1');
					}
				}
			}

			r.close();
			f.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public String reconocer(BufferedImage image) {
		if (this.net == null) {
			System.out.println("Red Neuronal no entrenada!");
			return "";
		}
		this.entry.setSample(this.sample);
		this.entry.entryImage = image;
		this.entry.downSample();

		final MLData input = new BasicMLData(DOWNSAMPLE_WIDTH * DOWNSAMPLE_HEIGHT);
		int idx = 0;
		final SampleData ds = this.sample.getData();
		for (int y = 0; y < ds.getHeight(); y++) {
			for (int x = 0; x < ds.getWidth(); x++) {
				input.setData(idx++, ds.getData(x, y) ? .5 : -.5);
			}
		}

		final int best = this.net.classify(input);
		final String letra = lettersL.get(best);
		System.out.println("La letra es " + letra + " (Neurona #" + best + " fired)");
		return letra;
	}

	public void entrenarRed() {
		try {
			final int inputNeuron = DOWNSAMPLE_HEIGHT * DOWNSAMPLE_WIDTH;
			final int outputNeuron = this.letterListModel.size();

			final MLDataSet trainingSet = new BasicMLDataSet();
			for (int t = 0; t < this.letterListModel.size(); t++) {
				final MLData item = new BasicMLData(inputNeuron);
				int idx = 0;
				final SampleData ds = (SampleData) this.letterListModel.getElementAt(t);
				for (int y = 0; y < ds.getHeight(); y++) {
					for (int x = 0; x < ds.getWidth(); x++) {
						item.setData(idx++, ds.getData(x, y) ? .5 : -.5);
					}
				}

				trainingSet.add(new BasicMLDataPair(item, null));
			}

			this.net = new SOM(inputNeuron, outputNeuron);
			this.net.reset();

			SOMClusterCopyTraining train = new SOMClusterCopyTraining(this.net, trainingSet);

			train.iteration();

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
