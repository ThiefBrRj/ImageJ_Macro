import java.awt.Rectangle;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;

public class Macro_Levedura implements PlugIn {
	
	public void run(String arg) {
		ImagePlus imagem = IJ.getImage();
		
		imagem.setRoi(618,324,1968,1908);
		imagem = imagem.crop();
		ImagePlus imagemCopy = imagem.duplicate();

		IJ.run(imagemCopy, "8-bit", "");
		IJ.run(imagemCopy, "Median...", "radius=20");
		IJ.setAutoThreshold(imagemCopy, "Default dark no-reset");
		IJ.run(imagemCopy, "Convert to Mask", "");
		IJ.doWand(imagemCopy, 1068, 964, 0.0, "Legacy");
		Rectangle bounds = imagemCopy.getRoi().getBounds();
		
		imagem.setRoi(bounds);
		imagem = imagem.crop();
		imagem.updateAndDraw();
		
		IJ.save(imagem, IJ.getDirectory("Destino")+"image"+"-croped");
	}
}
