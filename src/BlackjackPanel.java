import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class BlackjackPanel extends JPanel{
	private int w, h;
	private int cardwidth = 300;
	private int cardheight = 400;
	private Blackjack b1;
	JRadioButton hit, stand;
	private ImageIcon c1,c2;
	private ImageIcon hiddenCard;
	ImageIcon Hidden;
	String msg;
	public BlackjackPanel(int w, int h)
	{
		this.w = w;
		this.h = h;
		this.setPreferredSize(new Dimension(w,h));
		b1 = new Blackjack();
		//sets up the values for the player and dealer
		b1.setDeck();
		b1.setPlayerHand();
		b1.setHand(b1.card1);
		b1.setHand(b1.card2);
		b1.setDealerValue();
		b1.setDealerHand(b1.dealer_upcard);
		b1.setDealerHand(b1.dealer_downcard);
		hit = new JRadioButton("Hit");
		stand = new JRadioButton("Stand");
		System.out.println(b1.card1.getImage());
		this.add(hit);
		this.add(stand);
		System.out.println(b1.getHandvalue(b1.player_hand));
		//image for the dealer face down card
		hiddenCard = new ImageIcon("src/BackCard.png");
		Image hiddencard_ = hiddenCard.getImage();
		//changes size of the image
		Image newHiddencard = hiddencard_.getScaledInstance(cardwidth, cardheight, java.awt.Image.SCALE_SMOOTH);
		hiddenCard = new ImageIcon(newHiddencard);
		this.setBackground(new Color(2,75,32));
		hit.addActionListener(new HitListener());
		stand.addActionListener(new StandListener());
	
	}
	
	

	public void paintComponent(Graphics g)
	{

		super.paintComponent(g);
		//images for the dealers cards
		for (int i = 0; i< b1.dealer_hand.size(); i++) {
			Cards card = b1.dealer_hand.get(i);
			ImageIcon image = new ImageIcon(card.getImage());
			Image c = image.getImage();
			Image c_ = c.getScaledInstance(cardwidth, cardheight, java.awt.Image.SCALE_SMOOTH);
			image = new ImageIcon(c_);
			image.paintIcon(this, g,20+(cardwidth*i),50);
			
		}
		//once the stand radiobutton is disabled the hidden card will disable
	    if(stand.isEnabled()) {
		hiddenCard.paintIcon(this,g,20,50);
	}
	    //images for the players cards
		for (int i = 0; i< b1.player_hand.size(); i++) {
			Cards card = b1.player_hand.get(i);
			ImageIcon image = new ImageIcon(card.getImage());
			Image c = image.getImage();
			Image c_ = c.getScaledInstance(cardwidth, cardheight, java.awt.Image.SCALE_SMOOTH);
			image = new ImageIcon(c_);
			image.paintIcon(this, g,20+(cardwidth*i),600);
	
			
		}
		//once the game is over the winner is determined and a message is written after
	      if(!stand.isEnabled()) {
		  if (b1.handvalue>21) {
			  msg = "dealer wins";
		  }
		  else if (b1.dealervalue>21) {
			  msg = "player wins";
		  }
		  else if(b1.dealervalue == b1.handvalue) {
			  msg = "tie";
		  }
		  else if(b1.dealervalue >21 && b1.handvalue>21) {
			  msg = "player loses";
		  }
		  else if (b1.handvalue > b1.dealervalue) {
			  msg = "player wins";
		  }
		  else if(b1.handvalue == 21 && b1.dealervalue == 21) {
			  msg = "push";
		  }
		  else if (b1.handvalue < b1.dealervalue) {
			  msg = "dealer wins";
		  }
		  g.setFont(new Font("Time New Roman", Font.BOLD,30));
		  g.setColor(Color.white);
		  g.drawString(msg, 700, 530);
	}
	}                 
	public class HitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//executes hit method in blackjack class which adds the card for the player
			b1.Hit(b1.player_hand);
			//if player hits 21 or goes over 21 the hit buttons is disabled so the player can't get another card
			if (b1.handvalue>= 21) {
				hit.setEnabled(false);
			}
			//if the player goes over 21 the stand button is disabled because the dealer already won
			if (b1.handvalue>21) {
				stand.setEnabled(false);
			}
			repaint();
			
	
		}
		
	}
	public class StandListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		  //once the stand button is clicked, I disabled it so the hidden card can be removed
		  stand.setEnabled(false);
		  //executes stand method in blackjack class which adds card for the dealer until they get over 17
		  b1.Stand(b1.dealer_hand);
		  repaint();
		}
		
	}

}
