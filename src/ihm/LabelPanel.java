package ihm;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import life.Espece;

@SuppressWarnings("serial")
public class LabelPanel extends JPanel{
	
	private Espece espece;
	
	public LabelPanel(Espece e) {
		this.espece = e;
		this.setLayout(new FlowLayout());
		this.add(new JLabel("   "));
		this.add(new JLabel(espece.getName()));
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(espece.getColor());
		g.fillRect(0, 8, 10, 10);
	}
}
