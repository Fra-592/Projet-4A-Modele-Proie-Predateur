package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import environnement.World;
import life.Animal;
import life.Espece;

@SuppressWarnings("serial")
public class PanneauAffichage extends JPanel{
	
	private PanneauInitialisation controles;
	
	public PanneauAffichage(FenetrePrincipale f) {
		this.setLayout(null);
		this.setSize(10*World.getHeight(),10*World.getWidth());
		this.controles = f.ajoutEspeces;
		this.addMouseListener(new Clic());
	}
	
	@SuppressWarnings("static-access")
	public void paintComponent(Graphics g) {
		super.repaint();
		g.setColor(Color.BLACK);
		for(int i = 0; i < World.getWidth(); i++) {
			for(int j = 0; j < World.getHeight(); j++) {
				g.fillRect(10*i, 10*j, 10, 10);				
			}
		}
		for(Animal a: World.getAnimaux()) {
			g.setColor(a.getCouleur());
			g.fillRect(a.getX()*10, a.getY()*10, 10, 10);
		}
	}
	
	private class Clic extends MouseAdapter {
		
		private boolean validClic(MouseEvent e) {
			if(e.getX() < 0 || e.getY() < 0 || e.getX() > 10*World.getWidth() || e.getY() > 10*World.getHeight()) {
				return(false);
			} else {
				return(true);
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1 && this.validClic(e)) {
				int x = e.getX() / 10;
				int y = e.getY() / 10;

				Espece espece = (Espece) controles.choixEspece.getSelectedItem();
				World.placeAnimal(espece, x, y);
			}
		}
	}
}
