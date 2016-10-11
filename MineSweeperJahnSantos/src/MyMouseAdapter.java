import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyMouseAdapter extends MouseAdapter {

	public void mousePressed(MouseEvent e) {

		switch (e.getButton()) {
		case 1:	//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}

			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:	
			//Right Mouse Button
			Component d = e.getComponent();
			while (!(d instanceof JFrame)) {
				d = d.getParent();
				if (d == null) {
					return;
				}
			}

			JFrame myFramed = (JFrame) d;
			MyPanel myPaneld = (MyPanel) myFramed.getContentPane().getComponent(0);
			Insets myInsetsd = myFramed.getInsets();
			int x1d = myInsetsd.left;
			int y1d = myInsetsd.top;
			e.translatePoint(-x1d, -y1d);
			int xd = e.getX();
			int yd = e.getY();
			myPaneld.x = xd;
			myPaneld.y = yd;
			myPaneld.mouseDownGridX = myPaneld.getGridX(xd, yd);
			myPaneld.mouseDownGridY = myPaneld.getGridY(xd, yd);
			myPaneld.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {

		switch (e.getButton()) {
		case 1:	//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}

			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			
			int minesNumber = 0;
			int graySpaces = 0;
			
			
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed

						//If the cell we clicked has a 0
						if (myPanel.numbersArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] ==0){
							//If there is no bomb or number, turn white
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE;

							//Turn white all surroundings, depending on where we clicked the first time
							if (gridX == 0 && gridY == 0){
								//Looking on top left corner
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX ][myPanel.mouseDownGridY + 1] = Color.WHITE;
							}
							if (gridX == 9 && gridY == 0){
								//Looking on top right corner
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX ][myPanel.mouseDownGridY + 1] = Color.WHITE;
							}
							if (gridX == 0 && gridY == 9){
								//Looking on bottom left corner
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY] = Color.WHITE;
							}
							if(gridX == 9 && gridY == 9){
								//Looking on bottom right corner
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY] = Color.WHITE;
							}
							if(gridX == 0 && gridY > 0 && gridY < 9){
								//Looking in left border and not looking at corners
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY + 1] = Color.WHITE;
							}
							if(gridY == 0 && gridX > 0 && gridX < 9){
								//Looking in top border and not looking at corners
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX ][myPanel.mouseDownGridY + 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY] = Color.WHITE;
							}
							if(gridX == 9 && gridY > 0 && gridY < 9){
								//Looking in right border and not looking at corners
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY + 1] = Color.WHITE;
							}
							if(gridY == 9 && gridX > 0 && gridX < 9){
								//Looking in bottom border and not looking at corners
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX ][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY] = Color.WHITE;
							}
							if (gridX > 0 && gridX < 9 && gridY > 0 && gridY < 9){
								//Look in rest of the body
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX ][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY - 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX + 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX ][myPanel.mouseDownGridY + 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY + 1] = Color.WHITE;
								myPanel.colorArray[myPanel.mouseDownGridX - 1][myPanel.mouseDownGridY] = Color.WHITE;
							}

							//Now we turn every surrounding '0' cell to white too, clearing everything that was adjacent to what we clicked
							//We run this at least 10 times(which is the dimension of the widht and height), to make sure every cell is covered.
							for (int k = 0; k < 10; k++){
								for (int i = 0; i < 10; i ++){
									for (int j = 0; j < 10; j ++){
										if (myPanel.colorArray[i][j] == Color.WHITE && myPanel.numbersArray[i][j] == 0){
											if (i == 0 && j == 0){
												//Looking on top left corner
												myPanel.colorArray[i + 1][j] = Color.WHITE;
												myPanel.colorArray[i + 1][j + 1] = Color.WHITE;
												myPanel.colorArray[i ][j + 1] = Color.WHITE;
											}
											else if (i == 9 && j == 0){
												//Looking on top right corner
												myPanel.colorArray[i - 1][j] = Color.WHITE;
												myPanel.colorArray[i - 1][j + 1] = Color.WHITE;
												myPanel.colorArray[i ][j + 1] = Color.WHITE;
											}
											else if (i == 0 && j == 9){
												//Looking on bottom left corner
												myPanel.colorArray[i][j - 1] = Color.WHITE;
												myPanel.colorArray[i + 1][j - 1] = Color.WHITE;
												myPanel.colorArray[i + 1][j] = Color.WHITE;
											}
											else if(i == 9 && j == 9){
												//Looking on bottom right corner
												myPanel.colorArray[i - 1][j - 1] = Color.WHITE;
												myPanel.colorArray[i][j - 1] = Color.WHITE;
												myPanel.colorArray[i - 1][j] = Color.WHITE;
											}
											else if(i == 0 && j > 0 && j < 9){
												//Looking in left border and not looking at corners
												myPanel.colorArray[i][j - 1] = Color.WHITE;
												myPanel.colorArray[i + 1][j - 1] = Color.WHITE;
												myPanel.colorArray[i + 1][j] = Color.WHITE;
												myPanel.colorArray[i + 1][j + 1] = Color.WHITE;
												myPanel.colorArray[i][j + 1] = Color.WHITE;
											}
											else if(j == 0 && i > 0 && i < 9){
												//Looking in top border and not looking at corners
												myPanel.colorArray[i - 1][j] = Color.WHITE;
												myPanel.colorArray[i - 1][j + 1] = Color.WHITE;
												myPanel.colorArray[i ][j + 1] = Color.WHITE;
												myPanel.colorArray[i + 1][j + 1] = Color.WHITE;
												myPanel.colorArray[i + 1][j] = Color.WHITE;
											}
											else if(i == 9 && j > 0 && j < 9){
												//Looking in right border and not looking at corners
												myPanel.colorArray[i][j - 1] = Color.WHITE;
												myPanel.colorArray[i - 1][j - 1] = Color.WHITE;
												myPanel.colorArray[i - 1][j] = Color.WHITE;
												myPanel.colorArray[i - 1][j + 1] = Color.WHITE;
												myPanel.colorArray[i][j + 1] = Color.WHITE;
											}
											else if(j == 9 && i > 0 && i < 9){
												//Looking in bottom border and not looking at corners
												myPanel.colorArray[i - 1][j] = Color.WHITE;
												myPanel.colorArray[i - 1][j - 1] = Color.WHITE;
												myPanel.colorArray[i ][j - 1] = Color.WHITE;
												myPanel.colorArray[i + 1][j - 1] = Color.WHITE;
												myPanel.colorArray[i + 1][j] = Color.WHITE;
											}
											else if (i > 0 && i < 9 && j > 0 && j < 9){
												//Look in rest of the body
												myPanel.colorArray[i - 1][j - 1] = Color.WHITE;
												myPanel.colorArray[i ][j - 1] = Color.WHITE;
												myPanel.colorArray[i + 1][j - 1] = Color.WHITE;
												myPanel.colorArray[i + 1][j] = Color.WHITE;
												myPanel.colorArray[i + 1][j + 1] = Color.WHITE;
												myPanel.colorArray[i ][j + 1] = Color.WHITE;
												myPanel.colorArray[i - 1][j + 1] = Color.WHITE;
												myPanel.colorArray[i - 1][j] = Color.WHITE;
											}
										}
									}
								}
							}
							myPanel.repaint();
						}
						//If we clicked 9
						if (myPanel.numbersArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == 9){
							//If there is a bomb, show red
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
							myPanel.repaint();
							JOptionPane.showMessageDialog(null, "YOU LOOSE", "MineSweeper -2.0 (Retro)", JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
						
						//If we clicked a number different from 0 and 9
						if (myPanel.numbersArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] > 0 && myPanel.numbersArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] < 9){
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE;
							myPanel.repaint();
						}
						
						//Check if user won already 
						for (int i = 0; i < 10; i ++){
							for (int j = 0; j < 10; j++){
								if (myPanel.numbersArray[i][j] == 9){
									minesNumber++;
								}
							}
						}
						for (int i = 0; i < 10; i ++){
							for (int j = 0; j < 10; j++){
								if (myPanel.colorArray[i][j] != Color.WHITE){
									graySpaces++;
								}
							}
						}
						if (graySpaces == minesNumber){
							JOptionPane.showMessageDialog(null, "YOU WIN!", "MineSweeper -2.0 (Retro)", JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
					}
				}
			}
			myPanel.repaint();
			break;
		case 3:	

			Component d = e.getComponent();
			while (!(d instanceof JFrame)) {
				d = d.getParent();
				if (d == null) {
					return;
				}
			}

			JFrame myFramed = (JFrame)d;
			MyPanel myPaneld = (MyPanel) myFramed.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsetsd = myFramed.getInsets();
			int x1d = myInsetsd.left;
			int y1d = myInsetsd.top;
			e.translatePoint(-x1d, -y1d);
			int xd = e.getX();
			int yd = e.getY();
			myPaneld.x = xd;
			myPaneld.y = yd;
			int gridXd = myPaneld.getGridX(xd, yd);
			int gridYd = myPaneld.getGridY(xd, yd);

			if ((myPaneld.mouseDownGridX == -1) || (myPaneld.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridXd == -1) || (gridYd == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPaneld.mouseDownGridX != gridXd) || (myPaneld.mouseDownGridY != gridYd)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						if (myPaneld.colorArray[myPaneld.mouseDownGridX][myPaneld.mouseDownGridY] == Color.GRAY){
							myPaneld.colorArray[myPaneld.mouseDownGridX][myPaneld.mouseDownGridY] = Color.RED;
						}
						else if(myPaneld.colorArray[myPaneld.mouseDownGridX][myPaneld.mouseDownGridY] == Color.RED){
							myPaneld.colorArray[myPaneld.mouseDownGridX][myPaneld.mouseDownGridY] = Color.GRAY;
						}
					}
				}
			}

			myPaneld.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}