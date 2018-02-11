package erreurs;

@SuppressWarnings("serial")
public class CaseOccupeeException extends Exception{

	public CaseOccupeeException(int x, int y) {
		System.out.println("La case " + x + "," + y + " est déjà occupée.");
	}
}
