package ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame{

	public PanneauAffichage affichageSimulation;
	public PanneauInitialisation ajoutEspeces;
	public PanneauControle controleSimulation;
	private PanneauLegende legendeAffichage;
	private PanneauStats affichageStats;
	private Thread thread;
	
	public FenetrePrincipale() {
		this.setTitle("Wildlife Simulator v1.0");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		ajoutEspeces = new PanneauInitialisation(this);
		this.add(ajoutEspeces, BorderLayout.NORTH);
		affichageSimulation = new PanneauAffichage(this);
		this.add(affichageSimulation, BorderLayout.CENTER);
		controleSimulation = new PanneauControle(this);
		this.add(controleSimulation, BorderLayout.SOUTH);
		legendeAffichage = new PanneauLegende();
		this.add(legendeAffichage, BorderLayout.EAST);
		
		//Adaptation taille
		this.setSize(100+affichageSimulation.getWidth(),100+affichageSimulation.getHeight());
		this.setVisible(true);
	}
	
	public void killSimulation()
	{
		thread.interrupt();
		
		this.remove(affichageSimulation);
		this.remove(ajoutEspeces);
		this.remove(controleSimulation);
	}
	
	public void displayStats() {
		affichageStats = new PanneauStats();
		this.add(affichageStats);
	}
}
