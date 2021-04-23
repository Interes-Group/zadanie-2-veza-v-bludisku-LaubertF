package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Maze {
	
	JFrame frame = new JFrame("Maze");
	JPanel game = new JPanel(new BorderLayout());
	JPanel arrowKeys = new JPanel(new BorderLayout());
	JPanel newGame = new JPanel(new BorderLayout());
    JPanel menu = new JPanel(new BorderLayout());
    JPanel row1 = new JPanel(new BorderLayout());   
    JPanel buttons = new JPanel(new BorderLayout()); 
	

	public Maze(int counterN){		
		final Board board = new Board(12, counterN);
		final Player player = new Player(board);
        JLabel counter = new JLabel(String.valueOf(counterN),JLabel.CENTER);
        JLabel counterLabel = new JLabel("Counter:",JLabel.CENTER);
		game.add(board, BorderLayout.CENTER);
		game.setFocusable(true);
		frame.add(game, BorderLayout.CENTER);				
        row1.add(counter,BorderLayout.WEST);
        row1.add(newGame,BorderLayout.EAST);
        buttons.add(row1,BorderLayout.NORTH);
        buttons.add(arrowKeys,BorderLayout.SOUTH);
        menu.add(buttons,BorderLayout.SOUTH);
		menu.add(counterLabel, BorderLayout.WEST);
		frame.add(menu,BorderLayout.SOUTH);
		
		JButton down = new JButton("Down");
		down.setFocusable(false);
		arrowKeys.add(down, BorderLayout.CENTER);			
		down.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				player.moveDown(board);
			}
		});
		
		JButton up = new JButton("Up");
		up.setFocusable(false);
		row1.add(up, BorderLayout.CENTER);
		up.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				player.moveUp(board);
			}
		});
		
		JButton right = new JButton("Right");
		right.setFocusable(false);
		arrowKeys.add(right, BorderLayout.EAST);
		right.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				player.moveRight(board);
			}
		});		
		
		
		JButton left = new JButton("Left");
		left.setFocusable(false);
		arrowKeys.add(left, BorderLayout.WEST);
		left.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				player.moveLeft(board);
			}
		});	
		
		JButton menu = new JButton("New Game");
		menu.setFocusable(false);
		newGame.add(menu, BorderLayout.EAST);
		menu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				frame.dispose();
				new Maze(0);
			}
		});	
				
		game.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e){				
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_LEFT)
					player.moveLeft(board);
				if(keyCode == KeyEvent.VK_RIGHT)
					player.moveRight(board);
				if(keyCode == KeyEvent.VK_UP)
					player.moveUp(board);
				if(keyCode == KeyEvent.VK_DOWN)
					player.moveDown(board);
			}
		});	
		
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
