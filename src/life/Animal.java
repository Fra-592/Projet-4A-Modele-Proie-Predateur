package life;

import java.awt.Color;
import java.util.Random;

import environnement.World;
import erreurs.CaseOccupeeException;;

public class Animal {
	protected int x;
	protected int y;
	protected int nourriture;
	protected int vie;
	protected int vue;
	protected int vitesse;
	protected  Color couleur;
	
	public Animal(int x, int y, int nourriture, int vie, int vue, int vitesse) {
		this.x = x;
		this.y = y;
		this.nourriture = nourriture;
		this.vie = vie;
		this.vue = vue;
		this.vitesse = vitesse;
		World.queueAnimal(this);
	}
	
	public Animal(int nourriture, int vie, int vue, int vitesse) throws CaseOccupeeException {
		int essais = 0;
		Random generateur = new Random();
		do {
			this.x = generateur.nextInt(World.getWidth());
			this.y = generateur.nextInt(World.getHeight());
			essais++;
		} while(!World.getCase(this.x, this.y).estVide() && essais < (10));
		this.nourriture = nourriture;
		this.vie = vie;
		this.vue = vue;
		this.vitesse = vitesse;
		World.queueAnimal(this);
	}
	
	protected void cherche() {
		int essais = 0;
		int xtry = 0;
		int ytry = 0;
		Random generateur = new Random();
		do {
			int dir = generateur.nextInt(180);
			int i = generateur.nextInt(this.vitesse);
			xtry = (int) Math.round(this.x + i * Math.sin(Math.toRadians(dir)));
			ytry = (int) Math.round(this.y + i * Math.cos(Math.toRadians(dir)));
			essais++;
		} while(!World.getCase(xtry, ytry).estVide() && essais < (10));
		if(World.getCase(xtry, ytry).estVide()) {
			this.bouge(xtry, ytry);
		}
	}
	
	protected void bouge(int x, int y) {
		try {
			World.getCase(this.x, this.y).delOccupant();
			while(x <= 0) {
				x += World.getWidth();
			}
			while(y <= 0) {
				y += World.getHeight();
			}
			x = x%(World.getWidth());
			y = y%(World.getHeight());
			this.x = x;
			this.y = y;
			World.getCase(x, y).setOccupant(this);
		} catch (CaseOccupeeException e) {
			e.printStackTrace();
		}
	}
	
	protected void vieillit() {
		this.vie--;
		this.nourriture--;
		if(this.vie < 1) {
			this.meurt();
		}
	}
	
	protected void meurt() {
		this.vie = 0;
		World.queueMort(this);
	}
	
	public void tour() throws CaseOccupeeException {
	}
	
	public int getX() {
		return(this.x);
	}
	
	public int getY() {
		return(this.y);
	}
	
	protected void estAttaque() {
		this.meurt();
	}
	
	public boolean estEnVie() {
		return((this.vie > 0));
	}

	public Color getCouleur() {
		return(this.getCouleur());
	}
}
