package ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import environnement.Simulation;

@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame{

	public PanneauAffichage affichageSimulation;
	public PanneauInitialisation ajoutEspeces;
	public PanneauControle controleSimulation;
	private PanneauLegende legendeAffichage;
	private Simulation simulation;
	
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
		
		simulation = new Simulation(this);
		new Thread(simulation).run();
	}
	
	
	//Contrôle simulation
	public void endSimulation()
	{
		simulation.quit();
		//Éventuellement affichage stats.
	}
	
	public void quitSimulation() {
		this.endSimulation();
		simulation.play();
		this.dispose();
	}

	public void startSimulation() {
		System.out.println("GO");
		simulation.play();
	}

	public void pauseSimulation() {
		simulation.pause();
		System.out.println("PAUSE");
	}


	public void playSimulation() {
		System.out.println("CONTINUE");
		simulation.play();
	}
	
	public void killListeners() {
		affichageSimulation.killListeners();
		controleSimulation.killListeners();
		ajoutEspeces.killListeners();
	}
}
