package ihm;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

@SuppressWarnings("serial")
public class PanneauControle extends JPanel{
	private JButton btnPause;
	private JButton btnLecture;
	private JSlider setSpeed;
	private JLabel	lbSpeed;
	
	public PanneauControle(FenetrePrincipale f) {
		this.setLayout(new FlowLayout());
		lbSpeed = new JLabel("Vitesse");
		this.add(lbSpeed);
		setSpeed = new JSlider();
		this.add(setSpeed);
		btnLecture = new JButton("Commencer");
		this.add(btnLecture);
		btnPause = new JButton("Pause");
		this.add(btnPause);
	}
}
