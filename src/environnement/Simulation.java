package environnement;

import java.util.concurrent.TimeUnit;

import erreurs.CaseOccupeeException;
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
		while(!World.estTermine()) {
			synchronized (moniteur) {
				if(enCours) {
					try {
						World.tour();
						TimeUnit.MILLISECONDS.sleep(10000/f.controleSimulation.getSpeedValue());
					} catch (CaseOccupeeException | InterruptedException e) {
						e.printStackTrace();
					}
					f.repaint();
				} else {
					try {
						moniteur.wait();
					} catch (InterruptedException e) {}
				}
			}
		}
		this.end();
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
	
	public void end() {
		f.killListeners();
	}
}
