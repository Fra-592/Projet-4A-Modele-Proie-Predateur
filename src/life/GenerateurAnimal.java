package life;

public class GenerateurAnimal {
	
	public Animal creerAnimal(Espece espece, int x, int y) {
		Animal animal = null;
		switch(espece) {
			case LOUP: animal = new Loup(x, y);break;
			case MOUTON: animal = new Mouton(x, y);break;
		}
		return(animal);
	}
}
