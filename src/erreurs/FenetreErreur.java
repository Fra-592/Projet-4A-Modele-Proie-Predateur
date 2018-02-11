package erreurs;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FenetreErreur extends JFrame{
	private JPanel panel;
	
	public FenetreErreur(Exception erreur) {
		this.setTitle(erreur.getClass().getSimpleName());
		panel = new JPanel();
		panel.add(new JLabel(erreur.getMessage()));
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(400,75);
		this.setResizable(true);
		this.setVisible(true);
	}

}
