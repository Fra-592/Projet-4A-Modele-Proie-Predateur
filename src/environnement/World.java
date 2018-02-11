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
	public static int nbAnimaux;
	private static World instance = null;
	private static ArrayList<Animal> morts;
	
	public static void main(String[] args) throws CaseOccupeeException {
		World.getInstance();
		Loup.initialize();
		Mouton.initialize();

		/*for(int i = 0; i < 10; i++) {
			try {
				new Mouton();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for(int i = 0; i < 3; i++) {
			try {
				new Loup();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		new FenetrePrincipale();
	}
	
	private World(int height, int width) {
		World.height = height;
		World.width = width;
		World.map = new Case[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				map[i][j] = new Case(i, j);
			}
		}
		World.nbAnimaux = 0;
		World.animaux = new ArrayList<Animal>();
		World.morts = new ArrayList<Animal>();
	}
	
	public static World getInstance() {
		if(World.instance == null) {
			World.instance = new World(50, 50);
		}
		return(World.instance);
	}
	
	public static void spawnAnimal(Espece e, int x, int y) throws CaseOccupeeException {
		GenerateurAnimal generateur = new GenerateurAnimal();
		generateur.creerAnimal(e, x, y);
		System.out.println("Vous avez ajouté un " + e + " en " + x + "," + y +".");
	}
	
	public static void addAnimal(Animal a) throws CaseOccupeeException {
		World.animaux.add(a);
		World.nbAnimaux++;
		World.getCase(a.getX(), a.getY()).setOccupant(a);
	}
	
	public static void addMort(Animal a) {
		World.morts.add(a);
		World.nbAnimaux--;
	}
	
	public static void appMorts() {
		for(Animal a: morts) {
			World.animaux.remove(a);
			World.getCase(a.getX(), a.getY()).delOccupant();
		}
		World.morts.clear();
	}
	
	public static ArrayList<Animal> getAnimaux(){
		return(World.animaux);
	}
	
	public static Case getCase(int x, int y) {
		while(x < 0) {
			x += World.width;
		}
		while(y < 0) {
			y += World.height;
		}
		x = x%(World.width);
		y = y%(World.height);

		return(World.map[x][y]);
	}
	
	public static void tour() throws CaseOccupeeException {
		for(Animal a: World.animaux) {
			System.out.println(World.nbAnimaux);
			a.tour();
		}
		World.appMorts();
	}
	
	public static boolean estFini() {
		return(World.nbAnimaux==0);
	}
	
	public static int getHeight() {
		return(World.height);
	}
	
	public static int getWidth() {
		return(World.width);
	}
}