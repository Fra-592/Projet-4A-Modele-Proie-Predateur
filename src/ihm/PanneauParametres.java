package ihm;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import life.Espece;

@SuppressWarnings("serial")
public class PanneauParametres extends JPanel{
	public JComboBox<Espece> choixEspece;
	private JButton btnReset;
	private JButton btnQuitter;
	
	public PanneauParametres() {
		this.setLayout(new FlowLayout());
		choixEspece = new JComboBox<Espece>(Espece.values());
		this.add(choixEspece);
		btnReset = new JButton("Réinitialiser");
		this.add(btnReset);
		btnQuitter = new JButton("Quitter");
		this.add(btnQuitter);
	}
}
