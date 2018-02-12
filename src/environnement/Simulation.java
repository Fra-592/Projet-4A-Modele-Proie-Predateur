package environnement;

import ihm.FenetrePrincipale;

public class Simulation implements Runnable {
	
	private FenetrePrincipale f ;
	public boolean enCours;
    private final Object moniteur = new Object();
	
	public Simulation(FenetrePrincipale f) {
		this.f = f;
		this.enCours = false;
	}
	
	public void run() {
		//Condition de fin ici
		while(true) {
			this.tour();
		}
	}
	
	public void tour() {
		synchronized (moniteur) {
			if(enCours) {
				//Magic Goes Here
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
}
