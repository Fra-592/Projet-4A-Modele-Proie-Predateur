package ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame{

	private PanneauAffichage affichageSimulation;
	private PanneauParametres ajoutEspeces;
	private PanneauControle controleSimulation;
	
	public FenetrePrincipale() {
		this.setTitle("Wildlife Simulator v1.0");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		ajoutEspeces = new PanneauParametres();
		this.add(ajoutEspeces, BorderLayout.NORTH);
		affichageSimulation = new PanneauAffichage(ajoutEspeces);
		this.add(affichageSimulation, BorderLayout.CENTER);
		controleSimulation = new PanneauControle();
		this.add(controleSimulation, BorderLayout.SOUTH);
		this.setSize(affichageSimulation.getWidth(),100+affichageSimulation.getHeight());
		this.setVisible(true);
	}
}
