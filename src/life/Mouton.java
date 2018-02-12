package life;

import java.awt.Color;
import java.util.ArrayList;

import environnement.Case;
import environnement.World;
import erreurs.CaseOccupeeException;

public class Mouton extends Animal{
	private static ArrayList<String> predateurs;
	private static Color couleur;

	
	public static void initialize() {
		Mouton.predateurs = new ArrayList<String>();
		Mouton.predateurs.add("life.Loup");
		Mouton.couleur = Color.WHITE;
	}
	
	public Mouton() throws CaseOccupeeException {
		super(30, 25, 10, 4);
	}
	
	public Mouton(int xpos, int ypos) {
		super(xpos, ypos, 30, 25, 10, 4);
	}
	
	
	@Override
	public void tour() throws CaseOccupeeException {
		if(this.estEnVie()) {
			Animal cible;
			if(this.cherchePredateurs()) {
				cible = this.getProchePredateur();
				this.aPeurDe(cible);
				this.fuit(cible);
			} else {
				this.cherche();
			}
			this.vieillit();
		} else {
			this.meurt();
		}
	}

	private boolean cherchePredateurs() {
		Animal cible;
		Case tcase;
		
		for(int i = -this.vue; i <= this.vue; i++) {
			int maxj = (int) Math.round(Math.sqrt(Math.pow(this.vue,2) - Math.pow(i,2)));
			for(int j = -maxj; j <= maxj; j++) {
				tcase = World.getCase(this.x + i, this.y + j);
				if(!tcase.estVide()) {
					cible = tcase.getOccupant();
					if(this.aPeurDe(cible)) {
						return(true);
					}
				}
			}
		}
		return(false);
	}
	
	private Animal getProchePredateur() {
		ArrayList<Animal> liste = World.getAnimaux();
		Animal pproche = null;
		double distancemin = Double.MAX_VALUE;
		double distance;
		for(Animal a: liste) {
			distance = Math.abs(this.x - a.x) + Math.abs(this.y - a.y);
			if(distance < distancemin && this.aPeurDe(a)) {
				pproche = a;
				distancemin = distance;
			}
		}
		return(pproche);
	}
	
	private boolean aPeurDe(Animal cible) {
		return(Mouton.predateurs.contains(cible.getClass().getName()));
	}
	
	public Color getCouleur() {
		return(Mouton.couleur);
	}
	
	public void fuit(Animal cible) {
		int xdir = 0;
		int ydir = 0;
		
		if(this.x != cible.x) {
			xdir = (this.x - cible.x)/Math.abs(this.x - cible.x);
		}
		if(this.y != cible.y) {
			ydir = (this.y - cible.y)/Math.abs(this.y - cible.y);
		}
		try {
			xdir = this.x + (xdir * this.vitesse)/(Math.abs(xdir) + Math.abs(ydir));
			ydir = this.y + (ydir * this.vitesse)/(Math.abs(xdir) + Math.abs(ydir));
		} catch(ArithmeticException e) {
			System.out.println(xdir + ydir);
		}

		this.bouge(xdir, ydir);
	}
	
	public static Color getColor() {
		return(Mouton.couleur);
	}
}
