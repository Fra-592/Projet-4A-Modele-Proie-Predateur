package ihm;

import java.awt.BorderLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame{

	//private PanneauAffichage affichageSimulation;
	private PanneauParametres ajoutEspeces;
	private PanneauControle controleSimulation;
	
	public FenetrePrincipale() {
		this.setTitle("Wildlife Simulator v1.0");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		controleSimulation = new PanneauControle();
		this.add(controleSimulation, BorderLayout.SOUTH);
		ajoutEspeces = new PanneauParametres();
		this.add(ajoutEspeces, BorderLayout.NORTH);
		this.repaint();
		this.setVisible(true);
	}
}
