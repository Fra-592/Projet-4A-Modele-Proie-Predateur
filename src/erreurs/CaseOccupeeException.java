package erreurs;

@SuppressWarnings("serial")
public class CaseOccupeeException extends Exception{
	private int x;
	private int y;

	public CaseOccupeeException(int x, int y) {
		System.out.println("La case " + x + "," + y + " est déjà occupée.");
		this.x = x;
		this.y = y;
		new FenetreErreur(this);
	}
	
	@Override
	public String getMessage() {
		return("La case " + x + "," + y + " est déjà occupée.");
	}
}
