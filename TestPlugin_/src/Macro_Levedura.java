import java.awt.Rectangle;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;

public class Macro_Levedura implements PlugIn {
	
	public void run(String arg) {
		ImagePlus imagem = IJ.getImage();
		String imagemNome = imagem.getShortTitle();
		
		imagem.setRoi(558,354,2088,1836);
		imagem = imagem.crop();
		ImagePlus imagemCopy = imagem.duplicate();
		
		try {
			IJ.run(imagemCopy, "8-bit", "");
			IJ.run(imagemCopy, "Median...", "radius=18");
			IJ.setThreshold(imagemCopy, 155, 255);
			IJ.run(imagemCopy, "Convert to Mask", "");
			IJ.doWand(imagemCopy, imagemCopy.getWidth()/2, imagemCopy.getHeight()/2, 0.0, "Legacy");
			Rectangle bounds = imagemCopy.getRoi().getBounds();
			
			imagem.setRoi(bounds);
			imagem = imagem.crop();
			imagem.show();
			imagem.updateAndDraw();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		IJ.saveAs(imagem, "tif", IJ.getDirectory("Destino")+imagemNome + "-croped");
	}
}
