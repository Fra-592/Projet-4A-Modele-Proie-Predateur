package environnement;

import erreurs.CaseOccupeeException;
import life.Animal;

public class Case {
	private Animal occupant;
	private int x;
	private int y;
	
	public Case(int x, int y) {
		this.occupant = null;
		this.x = x;
		this.y = y;
	}
	
	public boolean estVide() {
		return(this.occupant == null);
	}
	
	public Animal getOccupant() {
		return(this.occupant);
	}
	
	public void delOccupant() {
		this.occupant = null;
	}
	
	public void setOccupant(Animal a) throws CaseOccupeeException {
		if(!this.estVide() && a != null) {
			throw new CaseOccupeeException(this.x, this.y);
		} else {
			this.occupant = a;
			System.out.println("Animal posé en " + x + "," + y + ".");
		}
	}
}
