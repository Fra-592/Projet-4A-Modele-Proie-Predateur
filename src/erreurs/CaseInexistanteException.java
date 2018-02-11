package erreurs;

@SuppressWarnings("serial")
public class CaseInexistanteException extends Exception{

	public CaseInexistanteException(int x, int y) {
		System.out.println("La case " + x + "," + y + " est hors du terrain.");
	}
}
