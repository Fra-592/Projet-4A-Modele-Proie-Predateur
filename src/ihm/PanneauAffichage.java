package ihm;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import environnement.World;
import life.Animal;

@SuppressWarnings("serial")
public class PanneauAffichage extends JPanel{
	
	public PanneauAffichage() {
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		this.setSize(10*World.getHeight(),10*World.getWidth());
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
}
