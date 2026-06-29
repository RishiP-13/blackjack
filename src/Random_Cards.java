import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Random_Cards extends JPanel{
  private int w,h;
  private Blackjack b1 = new Blackjack();
   int cardwidth = 250;
   int cardheight = 400;
	public Random_Cards(int w, int h)
	{
		this.w = w;
		this.h = h;
		this.setPreferredSize(new Dimension(w,h));
		b1.setDeck();
	}
		public void paintComponent(Graphics g)
		{

			super.paintComponent(g);
			for (int i = 0; i<5; i++) {
				Cards c;
				c = b1.deck.remove((int)(Math.random()*b1.deck.size()));
				ImageIcon card = new ImageIcon(c.getImage());
				Image c_ = card.getImage();
				Image Card = c_.getScaledInstance(cardwidth, cardheight, java.awt.Image.SCALE_SMOOTH);
				ImageIcon image = new ImageIcon(Card);
				image.paintIcon(this, g,20+(300*i),0);
				
			}
			
}
}
