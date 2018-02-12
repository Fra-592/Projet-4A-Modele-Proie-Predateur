package ihm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

@SuppressWarnings("serial")
public class PanneauControle extends JPanel{
	private JButton btnPause;
	private JButton btnLecture;
	private JSlider setVitesse;
	private FenetrePrincipale f;
	private CommencerAction lStart;
	private PauserAction lPause;
	private ReprendreAction lResume;
	
	public PanneauControle(FenetrePrincipale f) {
		this.f = f;
		this.setLayout(new FlowLayout());
		btnLecture = new JButton("Commencer");
		lStart = new CommencerAction();
		btnLecture.addActionListener(lStart);
		this.add(btnLecture);
		
		btnPause = new JButton("Pause");
		lPause = new PauserAction();
		btnPause.addActionListener(lPause);
		this.add(btnPause);
		
		this.add(new JLabel("Vitesse:"));
		setVitesse = new JSlider(1, 20, 1);
		this.add(setVitesse);
	}
	
	public void killListeners() {
		btnLecture.removeActionListener(lResume);
		btnPause.removeActionListener(lPause);
	}
	
	public int getSpeedValue() {
		return(setVitesse.getValue());
	}
	
	private class CommencerAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			f.startSimulation();
			btnLecture.setText("Reprendre");
			btnLecture.removeActionListener(lStart);
			lResume = new ReprendreAction();
			btnLecture.addActionListener(lResume);
		}
	}
	
	private class PauserAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			f.pauseSimulation();
		}
	}
	
	private class ReprendreAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			f.playSimulation();
		}
	}

	
}
