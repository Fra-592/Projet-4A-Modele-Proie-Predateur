package life;

import java.awt.Color;

public enum Espece {
	LOUP,
	MOUTON;
	
	public String getName() {
		String s;
		switch(this) {
			case LOUP: s = "Loup";break;
			case MOUTON: s = "Mouton";break;
			default: s = "Inconnu";
		}
		return(s);
	}
	
	public Color getColor() {
		Color c;
		switch(this){
			case LOUP: c = Loup.getColor();break;
			case MOUTON: c = Mouton.getColor();break;
			default: c = Color.BLACK;
		}
		return(c);
	}
}
