package sk.stuba.fei.uim.oop;

import java.awt.event.*;
import javax.swing.*;


public class Player extends JPanel implements ActionListener{
	
	private int xPos=1;
	private int yPos=1;
	private int wins=0;
	private final Board board;
	
	public Player(Board board){
		this.board = board;
	}
	

	public void actionPerformed(ActionEvent e){
		repaint();
	}
	

	public class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyChar() == 'a') 	moveLeft(board);
			if(e.getKeyChar() == 'w') 	moveUp(board);
			if(e.getKeyChar() == 'd')	moveRight(board);
			if(e.getKeyChar() == 's')	moveDown(board);
		
		}
	}
	

	public void moveLeft(Board board){
		if(board.get(xPos-1, yPos) == 'f')	Win();
        if((board.get(xPos-1, yPos) == 'v')){
            board.set(xPos, yPos, 'v');
            xPos--;
			board.set(xPos, yPos, 'p');

		}
	}
	
	public void moveRight(Board board){
        if(board.get(xPos+1, yPos) == 'f')	Win();
        if((board.get(xPos+1, yPos) == 'v')){
            board.set(xPos, yPos, 'v');
            xPos++;
			board.set(xPos, yPos, 'p');

        }
        
	}
	
	public void moveUp(Board board){
		if(board.get(xPos, yPos-1) == 'f')	Win();
        if((board.get(xPos, yPos-1) == 'v')){
            board.set(xPos, yPos, 'v');
            yPos--;
			board.set(xPos, yPos, 'p');

		}
	}
	
	public void moveDown(Board board){
		if(board.get(xPos, yPos+1) == 'f') Win();
        if((board.get(xPos, yPos+1) == 'v')){
			board.set(xPos, yPos, 'v');
            yPos++;
            board.set(xPos, yPos, 'p');

		}
	}
	
	public void Win(){
		new Maze(++wins);
        repaint();        
	}	
}	

