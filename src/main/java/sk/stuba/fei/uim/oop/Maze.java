package sk.stuba.fei.uim.oop;
import javax.swing.*;


public class Maze {
    Maze(){
        JFrame frame = new JFrame("Maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000,1000);
        frame.setVisible(true);

        int[][] map = new int[12][12];

          System.out.println(map[2][3]);


    }
}


