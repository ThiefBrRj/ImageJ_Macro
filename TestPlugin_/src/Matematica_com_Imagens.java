import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.plugin.PlugIn;
import ij.plugin.ImageCalculator;;
 
public class Matematica_com_Imagens  implements PlugIn {

	public void run(String arg) {
		ImageCalculator calculadoraDeImagens = new ImageCalculator();
		ImagePlus imp1 = IJ.openImage("/home/fabio/Área de Trabalho/Imagens Processadas/Imagem1.jpg");
		ImagePlus imp2 = IJ.openImage("/home/fabio/Área de Trabalho/Imagens Processadas/Imagem2.jpg");
		//ImagePlus imp1 = WindowManager.getImage("imagem1.jpg");
		//ImagePlus imp2 = WindowManager.getImage("imagem2.jpg");
		imp1.show();
		imp2.show();
		String estrategia[] = {"Add","Subtract","Multiply","Divide","AND","OR","XOR","Min","Max"
				,"Average","Difference","Copy","Transparent–zero"};
		for (int i = 0; i < estrategia.length; i++) {
			ImagePlus imp3 = calculadoraDeImagens.run(estrategia[i] + "create", imp1, imp2);
			imp3.updateAndDraw();
			imp3.show();
			IJ.saveAs(imp3, "Jpeg", "/home/fabio/Área de Trabalho/Imagens Processadas/" + estrategia[i] + "_blobs.jpg");
			imp3.close();			
		}
	}
}