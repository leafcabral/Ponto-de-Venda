package com.leafcabral.pontoDeVenda;

import com.leafcabral.pontoDeVenda.views.TelaPrincipal;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author ftfer
 */
public class PontoDeVenda {
	public static void main(String[] args) {
		setupFlatLaf();
		
		java.awt.EventQueue.invokeLater(() -> {
			TelaPrincipal tela = new TelaPrincipal();
			tela.setVisible(true);
		});
	}
    
	private static void setupFlatLaf() {
		try { UIManager.setLookAndFeel(new FlatLightLaf()); }
		catch (Exception ex) {
			System.err.println("Failed to initialize FlatLaf");
			try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
			catch (Exception e) { }
		}
	}
}