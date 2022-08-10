import java.awt.Rectangle;
import java.io.File;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;

public class Multi_Macro_Levedura implements PlugIn {
	
	public void run(String arg) {
		
		File pasta = new File(IJ.getDirectory("Origem"));
		String destino = IJ.getDirectory("Destino");
		String[] imagens = pasta.list();
		
		for (int controle = 0; controle < imagens.length; controle++) {
			ImagePlus imagem = IJ.openImage(pasta + "\\" + imagens[controle]);
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
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			
			imagem = imagem.crop();
			IJ.saveAs(imagem, "tif", destino + imagemNome + "-croped");
		}
	}
}
