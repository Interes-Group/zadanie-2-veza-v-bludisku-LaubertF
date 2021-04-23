package sk.stuba.fei.uim.oop;


import java.awt.*;
import javax.swing.*;
import java.util.*;


public class Board extends JPanel {
	
	private char[][] board;
	private int size;
	private int wins;
	
	public Board(int size,int wins){
		this.wins = wins;
		size *= 2; 
		size++;
		board = new char [size][size];
		this.size = size;
		generateBoard();
	}	

	public void generateBoard(){
		for (int i=0; i < size; i++){
			for (int j=0; j < size; j++){
                if (i%2==0 || j%2==0){
                    if(i==0|| i==size-1 || j==0 || j==size-1){
                        board[i][j] = 'h';//hrana
                    }else{
                        board[i][j] = 's';//stena
                    }  
                }else{
                    board[i][j] = 'u';//unvisited
                }
            }
		}
		createMaze(new Position(1,1));
		board[1][1]='p';//player
		board[23][23] = 'f';//finish
	}
	
	

	public void paint(Graphics g){
		super.paint(g);
		int n = 15;
		
		for(int i = 0; i < size; i++){
			for( int j = 0;j  < size; j++){
				if((board[i][j] == 'h')){
					g.setColor(Color.black);
					g.fillRect(i*n, j*n, n, n);
				} else if(board[i][j] == 's'){
					g.setColor(Color.black);
					g.fillRect(i*n, j*n, n, n);
				} else if(board[i][j] == 'f'){
					g.setColor(Color.red); //finish
					g.fillRect(i*n, j*n, n, n);
				} else if(board[i][j] == 'p'){
					g.setColor(Color.green); //player
					g.fillRect(i*n, j*n, n, n);
				}
			}
		}
	}	
	
	public char get(int x, int y){
		return board[x][y];
	}

	public void set(int x, int y, char value){
		board[x][y] = value;
        repaint();
	}

		

	public char[] findCandidates(Position cC){
        char candidates[] ={'n','n','n','n'};//ničota
		if (get(cC.getX(),cC.getY()+1) != 'h')
            candidates[0] = get(cC.getX(), cC.getY()+2);
		if (get(cC.getX(),cC.getY()-1) != 'h')
            candidates[1] = get(cC.getX(), cC.getY()-2);
		if (get(cC.getX()-1,cC.getY()) != 'h')
            candidates[2] = get(cC.getX()-2, cC.getY());
		if (get(cC.getX()+1,cC.getY()) != 'h')
            candidates[3] = get(cC.getX()+2, cC.getY());		
		return candidates;
	}

	public void createMaze(Position currentPosition){
		set(currentPosition.getX(),currentPosition.getY(), 'v'); // mark visited

		Position nextPosition = randomUnvisitedNeighbour(currentPosition);
		while(nextPosition != null){
			connectCells(currentPosition, nextPosition);
			createMaze(nextPosition);
			nextPosition = randomUnvisitedNeighbour(currentPosition);
		}
	}
	public Position randomUnvisitedNeighbour(Position vertex){
		Stack<Position> posStack = new Stack<Position>();
		
		char candidates[] = findCandidates(vertex);
		
		
		if (candidates[0] == 'u')
            posStack.push(new Position(vertex.getX(),vertex.getY()+2));
		if (candidates[1] == 'u')
            posStack.push(new Position(vertex.getX(),vertex.getY()-2));
		if (candidates[2] == 'u')
            posStack.push(new Position(vertex.getX()-2,vertex.getY()));
		if (candidates[3] == 'u')
            posStack.push(new Position(vertex.getX()+2,vertex.getY()));
	
		if (posStack.isEmpty()){
			return null;
		}
		Random generator = new Random();
		int random = generator.nextInt(posStack.size());
		return posStack.get(random);
	}
	public void connectCells(Position cell1, Position cell2){
		int xBridge=(cell1.getX()+cell2.getX())/2;
		int yBridge=(cell1.getY()+cell2.getY())/2;
		set(xBridge, yBridge, 'v');
	}
	public int getWins() {
		return wins;
	}
}

