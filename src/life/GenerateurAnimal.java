package life;

import erreurs.CaseOccupeeException;

public class GenerateurAnimal {
	
	public Animal creerAnimal(Espece espece, int x, int y) throws CaseOccupeeException {
		Animal animal = null;
		switch(espece) {
			case LOUP: animal = new Loup(x, y);break;
			case MOUTON: animal = new Mouton(x, y);break;
		}
		return(animal);
	}
}
