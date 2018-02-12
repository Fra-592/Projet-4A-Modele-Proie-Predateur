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
	private ResetAction lReset;
	private EndAction lEnd;
	
	public PanneauInitialisation(FenetrePrincipale f) {
		this.setLayout(new FlowLayout());
		this.f = f;
		
		this.add(new JLabel("Ajouter un:"));
		
		//Sélecteur d'espèce
		choixEspece = new JComboBox<Espece>(Espece.values());
		this.add(choixEspece);
		
		//Bouton réinitialisation
		btnReset = new JButton("Réinitialiser");
		lReset = new ResetAction();
		btnReset.addActionListener(lReset);
		this.add(btnReset);
		
		//Bouton fin
		btnQuitter = new JButton("Terminer");
		lEnd = new EndAction();
		btnQuitter.addActionListener(lEnd);
		this.add(btnQuitter);
		
		this.add(new JLabel("Cliquez sur la carte pour ajouter un animal."));

	}
	
	public void killListeners() {
		btnReset.removeActionListener(lReset);
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
