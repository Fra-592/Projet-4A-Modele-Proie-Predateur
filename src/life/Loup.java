package life;

import java.awt.Color;
import java.util.ArrayList;

import environnement.Case;
import environnement.World;
import erreurs.CaseOccupeeException;

public class Loup extends Animal{
	private static ArrayList<String> proies;
	private static Color couleur;
	
	public Loup() throws CaseOccupeeException {
		super(15, 10, 8, 8);
	}
	
	public Loup(int xpos, int ypos) {
		super(xpos, ypos, 15, 10, 8, 8);
	}
	
	
	public static void initialize() {
		Loup.proies = new ArrayList<String>();
		Loup.proies.add("life.Mouton");
		Loup.couleur = Color.GRAY;
	}
	
	@Override
	public void tour() throws CaseOccupeeException {
		Animal cible;
		if(this.estEnVie()) {
			if(this.chercheProies()) {
				cible = this.getProcheProie();
				this.chasse(cible);
			} else {
				this.cherche();
			}
			this.vieillit();
		} else {
			this.meurt();
		}
	}	

	private boolean chercheProies() {
		Animal cible;
		Case tcase;
		
		for(int i = -this.vue; i <= this.vue; i++) {
			int maxj = (int) Math.round(Math.sqrt(Math.pow(this.vue,2) - Math.pow(i,2)));
			for(int j = -maxj; j <= maxj; j++) {
				tcase = World.getCase(this.x + i, this.y + j);
				if(!tcase.estVide()) {
					cible = tcase.getOccupant();
					if(this.peutChasser(cible)) {
						return(true);
					}
				}
			}
		}
		return(false);
	}
	
	private Animal getProcheProie() {
		ArrayList<Animal> liste = World.getAnimaux();
		Animal pproche = null;
		double distancemin = Double.MAX_VALUE;
		double distance;
		for(Animal a: liste) {
			distance = Math.sqrt(Math.pow(this.x - a.x, 2) + Math.pow(this.y - a.y, 2));
			if(distance < distancemin && this.peutChasser(a)) {
				pproche = a;
				distancemin = distance;
			}
		}
		return(pproche);
	}
	
	private boolean peutChasser(Animal cible) {
		return(Loup.proies.contains(cible.getClass().getName()));
	}
	
	private void chasse(Animal cible) throws CaseOccupeeException {
			this.mange(cible);
			this.bouge(cible.x, cible.y);
	}
	
	private void mange(Animal cible) {
		cible.estAttaque();
		this.nourriture += cible.nourriture/2;
		this.vie += cible.nourriture/4;
	}
	
	public Color getCouleur() {
		return(Loup.couleur);
	}
	
	public static Color getColor() {
		return(Loup.couleur);
	}
}
