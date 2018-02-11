package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import environnement.World;
import erreurs.CaseOccupeeException;
import life.Animal;
import life.Espece;

@SuppressWarnings("serial")
public class PanneauAffichage extends JPanel{
	
	private PanneauParametres controles;
	
	public PanneauAffichage(PanneauParametres panneau) {
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		this.setSize(10*World.getHeight(),10*World.getWidth());
		this.controles = panneau;
		this.addMouseListener(new Clic());
	}
	
	public void paintComponent(Graphics g) {
		super.repaint();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
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
				System.out.println("Clic en " + e.getX() + "," + e.getY());
				int x = e.getX() / 10;
				int y = e.getY() / 10;
				System.out.println("Clic en " + e.getX() + "," + e.getY());

				Espece espece = (Espece) controles.choixEspece.getSelectedItem();
				try {
					World.spawnAnimal(espece, x, y);
				} catch (CaseOccupeeException e1) {}
			}
		}
	}
}
