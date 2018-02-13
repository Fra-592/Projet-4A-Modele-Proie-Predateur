package environnement;

import java.util.ArrayList;

import erreurs.CaseOccupeeException;
import ihm.FenetrePrincipale;
import life.Animal;
import life.Espece;
import life.GenerateurAnimal;
import life.Loup;
import life.Mouton;

public class World {
	
	private static Case[][] map;
	private static ArrayList<Animal> animaux;
	private static int height;
	private static int width;
	private static World instance = null;
	private static ArrayList<Animal> morts;
	private static ArrayList<Animal> naissances;
	private static GenerateurAnimal generateur;
	private static boolean fini;
	private static int tours;
	
	public static void main(String[] args) throws CaseOccupeeException {
		World.getInstance();
		Loup.initialize();
		Mouton.initialize();

		new FenetrePrincipale();
	}
	
	
	//Création & initialisation
	private World(int height, int width) {
		World.height = height;
		World.width = width;
		World.map = new Case[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				map[i][j] = new Case(i, j);
			}
		}
		World.animaux = new ArrayList<Animal>();
		World.morts = new ArrayList<Animal>();
		World.naissances = new ArrayList<Animal>();
		World.generateur = new GenerateurAnimal();
		World.fini = false;
		World.tours = 0;
	}
	
	public static World getInstance() {
		if(World.instance == null) {
			World.instance = new World(80, 100);
		}
		return(World.instance);
	}
	
	public static void reset() {
		System.out.println("Réinitialisation.");
		World.instance = null;
		World.getInstance();
	}
	
	
	//Gestion des spawns
	public static void placeAnimal(Espece e, int x, int y) {
		generateur.creerAnimal(e, x, y);
		World.spawnAnimaux();
	}
	
	public static void queueAnimal(Animal a) {
		World.naissances.add(a);
	}
	
	public static void spawnAnimaux() {
		for(Animal a: World.naissances) {
			World.animaux.add(a);
			try {
				World.getCase(a.getX(), a.getY()).setOccupant(a);
			} catch (CaseOccupeeException e) {
				e.printStackTrace();
			}
		}
		World.naissances.clear();
	}
	
	
	//Gestion des morts
	public static void queueMort(Animal a) {
		World.getCase(a.getX(), a.getY()).delOccupant();
		World.morts.add(a);
	}
	
	public static void delMorts() {
		for(Animal a: World.morts) {
			World.animaux.remove(a);
		}
		World.morts.clear();
	}
	
	
	//Accesseurs et modifieurs
	public static ArrayList<Animal> getAnimaux(){
		return(World.animaux);
	}
	
	public static Case getCase(int x, int y) {
		while(x <= 0) {
			x += World.width;
		}
		while(y <= 0) {
			y += World.height;
		}
		x = x%(World.width);
		y = y%(World.height);

		return(World.map[x][y]);
	}
	
	public static int getHeight() {
		return(World.height);
	}
	
	public static int getWidth() {
		return(World.width);
	}
	
	//Simulation
	public static void tour() throws CaseOccupeeException {
		for(Animal a: World.animaux) {
			a.tour();
		}
		World.delMorts();
		World.spawnAnimaux();
		World.tours++;
	}

	public static boolean estTermine( ) {
		if(World.animaux.isEmpty() && World.tours != 0) {
			World.fini = true;
			System.out.println("Fini!");
			System.out.print("Il y a eu " + World.tours + " tours.");
		}
		return(World.fini);
	}
	
	public static void terminer() {
		World.fini = true;
	}
}