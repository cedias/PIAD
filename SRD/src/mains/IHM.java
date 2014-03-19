package mains;

import ihm.MainWindow;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class IHM {

	public static void main(String[] args) {
		try {

			 for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()){
		            if(laf.toString() == "com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			 }

		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainWindow window = new MainWindow();
		window.setVisible(true);


	}
	
}
