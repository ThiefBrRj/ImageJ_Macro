import java.awt.Rectangle;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;

public class Multi_Macro_Levedura implements PlugIn {
	
	public void run(String arg) {
		ImagePlus imagem = IJ.getImage();
		String imagemNome = imagem.getShortTitle();
		
		imagem.setRoi(558,354,2088,1836);
		imagem = imagem.crop();
		ImagePlus imagemCopy = imagem.duplicate();
		
		IJ.run(imagemCopy, "8-bit", "");
		IJ.run(imagemCopy, "Median...", "radius=20");
		IJ.setThreshold(imagemCopy, 172, 255);
		IJ.run(imagemCopy, "Convert to Mask", "");
		IJ.doWand(imagemCopy, 1068, 964, 0.0, "Legacy");
		Rectangle bounds = imagemCopy.getRoi().getBounds();
		
		imagem.setRoi(bounds);
		imagem = imagem.crop();
		imagem.updateAndDraw();
		imagem.show();
		imagem.setTitle(imagemNome+"-croped.tif");
	}
}
