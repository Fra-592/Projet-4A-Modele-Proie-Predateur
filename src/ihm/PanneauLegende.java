package ihm;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import life.Espece;

@SuppressWarnings("serial")
public class PanneauLegende extends JPanel{
	public PanneauLegende() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for(Espece e: Espece.values()) {
			LabelPanel j = new LabelPanel(e);
			this.add(j);
		}
	}
}
