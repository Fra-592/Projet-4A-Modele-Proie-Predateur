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
	private Thread thread;
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
		thread = new Thread(simulation);
		thread.run();
	}
	
	
	//Contrôle simulation
	public void endSimulation()
	{
		simulation.quit();
		if(thread != null && thread.isAlive()) {
			thread.interrupt();
		}
		//new FenetreStats();
	}
	
	public void quitSimulation() {
		this.endSimulation();
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
		System.out.println("REGO");
		simulation.play();
	}
}
