// CIIC4010 Advanced Programming
// Project 1 : MineSweeper RETRO
//Jahn Carlo Marrero and Santos E. Medina
// October/14/2016
// 

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("MineSweeper RETRO");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(400, 400);

		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);
		
	
		//Place Random Bombs set by number 9 
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				Random rand = new Random();
				int randomNum = rand.nextInt((10) + 1);
				if (randomNum == 1){
					myPanel.numbersArray[i][j] = 9;
				}
			}
		}
		
		//Assign proximity numbers
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				//Do only if there is not a bomb already:
				if(myPanel.numbersArray[i][j] != 9){
					int counter = 0;
					if (i == 0 && j == 0){
						//Looking on top left corner
						if (myPanel.numbersArray[i+1][j] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i+1][j+1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i][j+1] == 9){
							counter++;
						}
						myPanel.numbersArray[i][j] = counter;
					}
					if (i == 9 && j == 0){
						//Looking on top right corner
						if (myPanel.numbersArray[i-1][j] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i-1][j+1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i][j+1] == 9){
							counter++;
						}
						myPanel.numbersArray[i][j] = counter;
					}
					if (i == 0 && j == 9){
						//Looking on bottom left corner
						if (myPanel.numbersArray[i][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i+1][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i+1][j] == 9){
							counter++;
						}
						myPanel.numbersArray[i][j] = counter;
					}
					if(i == 9 && j == 9){
						//Looking on bottom right corner
						if (myPanel.numbersArray[i-1][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i-1][j] == 9){
							counter++;
						}
						myPanel.numbersArray[i][j] = counter;
					}
					if(i == 0 && j > 0 && j < 9){
						//Looking in left border and not looking at corners
						if (myPanel.numbersArray[i][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i+1][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i+1][j] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i+1][j+1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i][j+1] == 9){
							counter++;
						}
						myPanel.numbersArray[i][j] = counter;
					}
					if(j == 0 && i > 0 && i < 9){
						//Looking in top border and not looking at corners
						if (myPanel.numbersArray[i-1][j] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i-1][j+1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i][j+1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i+1][j+1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i+1][j] == 9){
							counter++;
						}
						myPanel.numbersArray[i][j] = counter;
					}
					if(i == 9 && j > 0 && j < 9){
						//Looking in right border and not looking at corners
						if (myPanel.numbersArray[i][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i-1][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i-1][j] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i-1][j+1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i][j+1] == 9){
							counter++;
						}
						myPanel.numbersArray[i][j] = counter;
					}
					if(j == 9 && i > 0 && i < 9){
						//Looking in bottom border and not looking at corners
						if (myPanel.numbersArray[i-1][j] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i-1][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i+1][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i+1][j] == 9){
							counter++;
						}
						myPanel.numbersArray[i][j] = counter;
					}
					if (i > 0 && i < 9 && j > 0 && j < 9){
						//Look in rest of the body
						if (myPanel.numbersArray[i-1][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i+1][j-1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i+1][j] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i+1][j+1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i][j+1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i-1][j+1] == 9){
							counter++;
						}
						if (myPanel.numbersArray[i-1][j] == 9){
							counter++;
						}
						myPanel.numbersArray[i][j] = counter;
					}
				}
			}
		}
		



		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
		
		
		
	}
}