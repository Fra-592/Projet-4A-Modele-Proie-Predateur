package ihm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanneauControle extends JPanel{
	private JButton btnPause;
	private JButton btnLecture;
	private FenetrePrincipale f;
	private CommencerAction l;
	
	public PanneauControle(FenetrePrincipale f) {
		this.f = f;
		this.setLayout(new FlowLayout());
		btnLecture = new JButton("Commencer");
		l = new CommencerAction();
		btnLecture.addActionListener(l);
		this.add(btnLecture);
		
		btnPause = new JButton("Pause");
		btnPause.addActionListener(new PauserAction());
		this.add(btnPause);
	}
	
	private class CommencerAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			f.startSimulation();
			btnLecture.setText("Reprendre");
			btnLecture.removeActionListener(l);
			btnLecture.addActionListener(new ReprendreAction());
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
