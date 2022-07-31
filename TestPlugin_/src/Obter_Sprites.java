
import java.awt.Rectangle;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.Roi;
import ij.plugin.PlugIn;
import ij.plugin.frame.RoiManager;
 
public class Obter_Sprites  implements PlugIn {
	public void run(String arg) {
		ImagePlus imagem = IJ.getImage();
		ImagePlus imagemCopy = imagem.duplicate();
		
		String destino = IJ.getDirectory("Destino");
        
        IJ.run("Make Binary");
        IJ.run("Fill Holes");
        IJ.run("Analyze Particles...", "display clear summarize add");
        
        RoiManager roiManager = new RoiManager().getInstance();
        Roi rois[] = roiManager.getRoisAsArray();
        
        for(int i=0; i<rois.length; i++){
        	Rectangle bounds = rois[i].getBounds();
        	imagemCopy.setRoi(bounds);
        	ImagePlus imageCroped = imagemCopy.crop();
        	imageCroped.updateAndDraw();
        	IJ.save(imageCroped, destino+"image"+i);
        }
	}
}