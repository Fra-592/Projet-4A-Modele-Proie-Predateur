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
	protected Color couleur;
	
	public Animal(int x, int y, int nourriture, int vie, int vue, int vitesse) throws CaseOccupeeException {
		System.out.println("Création animal.");
		this.x = x;
		this.y = y;
		this.nourriture = nourriture;
		this.vie = vie;
		this.vue = vue;
		this.vitesse = vitesse;
		World.addAnimal(this);
	}
	
	public Animal(int nourriture, int vie, int vue, int vitesse) throws CaseOccupeeException {
		int essais = 0;
		Random generateur = new Random();
		do {
			this.x = generateur.nextInt(World.getWidth());
			this.y = generateur.nextInt(World.getHeight());
			essais++;
		} while(!World.getCase(this.x, this.y).estVide() && essais < (this.vue*4));
		this.nourriture = nourriture;
		this.vie = vie;
		this.vue = vue;
		this.vitesse = vitesse;
		World.addAnimal(this);
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
		} while(!World.getCase(xtry, ytry).estVide() && essais < (this.vue*4));
		if(World.getCase(xtry, ytry).estVide()) {
			this.bouge(xtry, ytry);
		}
	}
	
	protected void bouge(int x, int y) {
		try {
			World.getCase(this.x, this.y).delOccupant();
			World.getCase(x, y).setOccupant(this);
			while(x < 0) {
				x += World.getWidth();
			}
			while(y < 0) {
				y += World.getHeight();
			}
			x = x%(World.getWidth());
			y = y%(World.getHeight());
			this.x = x;
			this.y = y;
		} catch (CaseOccupeeException e) {
			e.printStackTrace();
		}
	}
	
	protected void vieillit() {
		this.vie--;
		this.nourriture--;
		if(this.vie < 1 || this.nourriture < 1) {
			this.meurt();
		}
	}
	
	protected void meurt() {
		World.addMort(this);
	}
	
	protected boolean aFaim() {
		return(this.nourriture <= 5);
	}
	
	public void tour() throws CaseOccupeeException {
		if(this.estEnVie()) {
			this.cherche();
			this.vieillit();
		} else {
			this.meurt();
		}
	}
	
	public int getX() {
		return(this.x);
	}
	
	public int getY() {
		return(this.y);
	}
	
	protected void estAttaque() {
		this.vie = 0;
	}
	
	public boolean estEnVie() {
		return((this.vie > 0 && this.nourriture > 0));
	}

	public Color getCouleur() {
		return(this.getCouleur());
	}
}
