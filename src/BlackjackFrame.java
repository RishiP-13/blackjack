import javax.swing.JFrame;
public class BlackjackFrame {
		public static void main(String[] args)
		{
			
			int w = 1500;
			int h = 1000;
			
			JFrame frame = new JFrame("Template");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(new BlackjackPanel(w,h));
			frame.pack();
			frame.setVisible(true);

		}
	}
