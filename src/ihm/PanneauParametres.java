package ihm;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import environnement.Espece;

@SuppressWarnings("serial")
public class PanneauParametres extends JPanel{
	private JComboBox<Object> choixEspece;
	private JButton btnReset;
	private JButton btnQuitter;
	
	public PanneauParametres() {
		this.setLayout(new FlowLayout());
		choixEspece = new JComboBox<Object>(Espece.values());
		this.add(choixEspece);
		btnReset = new JButton("Réinitialiser");
		this.add(btnReset);
		btnQuitter = new JButton("Quitter");
		this.add(btnQuitter);
	}
	
	public void paintComponent(Graphics g) {
	}
}
