package ihm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import environnement.World;
import life.Espece;

@SuppressWarnings("serial")
public class PanneauInitialisation extends JPanel{
	public JComboBox<Espece> choixEspece;
	private JButton btnReset;
	private JButton btnQuitter;
	private FenetrePrincipale f;
	
	public PanneauInitialisation(FenetrePrincipale f) {
		this.setLayout(new FlowLayout());
		this.f = f;
		
		this.add(new JLabel("Ajouter un:"));
		
		//Sélecteur d'espèce
		choixEspece = new JComboBox<Espece>(Espece.values());
		this.add(choixEspece);
		
		//Bouton réinitialisation
		btnReset = new JButton("Réinitialiser");
		btnReset.addActionListener(new ResetAction());
		this.add(btnReset);
		
		//Bouton fin
		btnQuitter = new JButton("Terminer");
		btnQuitter.addActionListener(new EndAction());
		this.add(btnQuitter);
		
		this.add(new JLabel("Cliquez sur la carte pour ajouter un animal."));

	}
	
	private class ResetAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			World.reset();
		}
	}
	
	private class EndAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			f.quitSimulation();
		}
	}
}
