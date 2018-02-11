package life;

import java.awt.Color;
import java.util.ArrayList;

import environnement.Case;
import environnement.World;
import erreurs.CaseOccupeeException;

public class Mouton extends Animal{
	private static int population;
	private static Color couleur = Color.WHITE;
	private static ArrayList<String> predateurs;
	
	public static void initialize() {
		Mouton.predateurs = new ArrayList<String>();
		Mouton.predateurs.add("life.Loup");
		Mouton.population = 0;
	}
	
	public Mouton() throws CaseOccupeeException {
		super(10, 10, 4, 4);
		System.out.println("Mouton spawné en " + this.x + "," + this.y + ".");
		Mouton.population++;
	}
	
	public Mouton(int xpos, int ypos) throws CaseOccupeeException {
		super(xpos, ypos, 10, 10, 4, 4);
		System.out.println("Mouton spawné en " + this.x + "," + this.y + ".");
		Mouton.population++;
	}
	
	
	@Override
	public void tour() throws CaseOccupeeException {
		Animal cible;
		if(this.cherchePredateurs()) {
			cible = this.getProchePredateur();
			this.aPeurDe(cible);
		} else {
			this.cherche();
		}
		this.vieillit();
	}
	
	@Override
	protected void meurt() {
		super.meurt();
		Mouton.population--;
		System.out.println("Mouton mort en " + this.x + "," + this.y + ".");
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
						System.out.println("Mouton voit un loup.");
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
			distance = Math.sqrt(Math.pow(this.x - a.x, 2) + Math.pow(this.y - a.y, 2));
			if(distance < distancemin) {
				pproche = a;
				distancemin = distance;
			}
		}
		return(pproche);
	}
	
	private boolean aPeurDe(Animal cible) {
		return(Mouton.predateurs.contains(cible.getClass().getName()));
	}
	
	public int getPopulation() {
		return(Mouton.population);
	}
	
	public void estAttaque() {
		this.vie = 0;
	}
	
	public Color getCouleur() {
		return(Mouton.couleur);
	}
}
