package environnement;

import ihm.FenetrePrincipale;

public class Simulation implements Runnable {
	
	private FenetrePrincipale f ;
	public boolean enCours;
    private final Object moniteur = new Object();
	
	public Simulation(FenetrePrincipale f) {
		this.f = f;
		this.enCours = false;
		System.out.println(3);
	}
	
	public void run() {
		//Condition de fin ici
		while(!World.estTermine()) {
			System.out.println(2);
			this.tour();
		}
	}
	
	public void tour() {
		synchronized (moniteur) {
			if(enCours) {
				//Magic Goes Here
				System.out.println(1);
				f.repaint();
			} else {
				try {
					moniteur.wait();
				} catch (InterruptedException e) {}
			}
		}
	}
	
	public void play() {
		synchronized(moniteur) {
			this.enCours = true;
			moniteur.notifyAll();
		}
	}
	
	public void pause()
	{
		this.enCours = false;
	}

	public void quit() {
		World.terminer();
	}
}
