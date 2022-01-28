package studentCode;
import java.awt.Color;
import GridTools.MyGrid;

public class FlagMaker {

	public static void drawFlag(MyGrid grid, int countryCode) {
		//named constants
		final int CODE_INDONESIA = 1; final int CODE_LITHUANIA = 2; 
		final int CODE_RWANDA = 3; final int CODE_MALTA = 4;
		final int CODE_AFGHANISTAN = 5; final int CODE_ERITREA = 6;
		final int CODE__MACEDONIA = 7; final int CODE_BAHAMAS = 8; 
		final int CODE_ZIMBABWE = 9;

		if (countryCode == CODE_INDONESIA) {
			Indonesia(grid, Color.RED, Color.WHITE);
		} else if (countryCode == CODE_LITHUANIA) {
			Lithuania (grid, Color.YELLOW, Color.GREEN, Color.RED);
		} else if (countryCode == CODE_RWANDA) {
			Rwanda (grid, Color.BLUE, Color.YELLOW, Color.GREEN);
		} else if (countryCode == CODE_MALTA) {
			Malta(grid, Color.WHITE, Color.RED);
		} else if (countryCode == CODE_AFGHANISTAN) {
			Afghanistan (grid,Color.BLACK, Color.RED, Color.GREEN);
		} else if (countryCode == CODE_ERITREA) { 
			Eritrea (grid, Color.RED, Color.GREEN, Color.BLUE);
		} else if (countryCode == CODE__MACEDONIA) {
			Macedonia(grid, Color.RED, Color.ORANGE);
		}else if (countryCode == CODE_BAHAMAS) {
			Bahamas(grid, Color.BLACK, Color.BLUE, Color.YELLOW);
		} else if (countryCode == CODE_ZIMBABWE) {
			Zimbabwe (grid);
		} else {
			Error(grid);
		}
	}



	//Indonesian flag
	public static void Indonesia(MyGrid grid, Color color, Color color2) {
		int width = grid.getWd();
		int height = grid.getHt();
		if ((height % 2 == 0) && (height >= 4) && (height <= 30)) {
			//top half
			for (int row = 0; row < height/2; row++) {
				for (int col = 0; col < width; col++) {
					grid.setColor(row, col, color);
				}
			}
			//bottom half
			for (int row = height/2; row < height; row++) {
				for (int col = 0; col < width; col++) {
					grid.setColor(row, col, color2);
				}
			}
		} else {
			Error(grid);
		}
	}
	//Lithuanian flag
	public static void Lithuania (MyGrid grid, Color color, Color color2, Color
			color3) {
		int width = grid.getWd();
		int height = grid.getHt();
		if (height % 3 == 0 && height >=4 && height <=30) {
			//yellow portion
			for (int row = 0; row < height/3; row++) {
				for (int col = 0; col < width; col++) {
					grid.setColor(row, col, color);
				}
			}
			//green portion
			for (int row = height/3; row < height; row++) {
				for (int col = 0; col < width; col++) {
					grid.setColor(row, col, color2);
				}
			}
			//red portion
			for (int row = (height/3) +(height/3); row < height; row++) {
				for (int col = 0; col < width; col++) {
					grid.setColor(row, col, color3);
				}
			}
		} else {
			Error(grid);
		}
	}
	//Rwanda flag
	public static void Rwanda (MyGrid grid, Color color, Color color2, Color 
			color3) {
		int width = grid.getWd();
		int height = grid.getHt();
		if (height % 4 == 0 && height >=4 && height <=30) {
			//blue portion (half of flag)
			for (int row = 0; row < height/2; row++) {
				for (int col = 0; col < width; col++) {
					grid.setColor(row, col, color);
				}
			}
			//yellow portion (quarter of the flag)
			for (int row = height/2; row < height; row++) {
				for (int col = 0; col < width; col++) {
					grid.setColor(row, col, color2);
				}
			}
			//green portion (quarter of the flag)
			for (int row = height/2 + height/4; row < height; row++) {
				for (int col = 0; col < width; col++) {
					grid.setColor(row,  col, color3);
				}
			}
		} else {
			Error(grid);
		}
	}

	//Malta flag
	public static void Malta (MyGrid grid, Color color, Color color2) {
		int width = grid.getWd();
		int height = grid.getHt();
		if (height >= 4 && height<=30) {
			// for left side of flag
			for (int col = 0; col < width/2; col++) {
				for (int row = 0; row < height; row++) {
					grid.setColor(row, col, color);
				}
			}
			// for right side of flag
			for (int col = width/2; col < width; col++) {
				for (int row = 0; row < height; row++) {
					grid.setColor(row, col, color2);
				}
			}
		} else {
			Error(grid);
		}
	}
	//Afghanistan flag
	public static void Afghanistan(MyGrid grid, Color color, Color color2, 
			Color color3) {
		int width = grid.getWd();
		int height = grid.getHt();
		if (height % 3 == 0 && height >=4 && height <=30) {

			//for left side of flag
			for (int col = 0; col < width/3; col++) {
				for (int row = 0; row < height; row++) {
					grid.setColor(row, col, color);
				}
			}
			//for the middle of the flag
			for (int col = width/ 3; col < width; col++) {
				for (int row = 0; row < height; row++) {
					grid.setColor(row, col, color2);
				}
			}
			//for the right side of the flag
			for (int col = (width/ 3) + (width/3); col < width; col++) {
				for (int row = 0; row < height; row++) {
					grid.setColor(row, col, color3);
				}
			}
		} else {
			Error(grid);
		}
	}
	//Eritrea flag
	public static void Eritrea (MyGrid grid, Color color, Color color2, Color
			color3) {
		int width = grid.getWd();
		int height = grid.getHt();
		if (height % 2 == 1 && height >= 4 && height <= 30) {
			//if the size is odd
			//red triangle
			for (int row = 0; row < (height/2); row++) {
				for (int col = 0; col <((row+1) * 4); col++) {
					grid.setColor(row, col, color);
				}
			}
			for (int row = (height/2); row < ((height/2) + 1); row++) {
				for (int col = 0; col < (row*4)+2; col++) {
					grid.setColor(row, col, color);
				}
			}
			for (int row = (height/2)+1; row < height; row++) {
				for (int col = 0; col < (height-row)*4; col++) {
					grid.setColor(row, col, color);
				}
			}
			//green triangle
			for (int row = 0; row < height/2; row++) {
				for (int col = (row+1)*4; col < width; col++) {
					grid.setColor(row, col, color2);
				}
			}

			//blue triangle
			for (int row = (height/2)+1; row<height; row++) {
				for (int col = ((row-height) * (-4)); col < width; col++) {
					grid.setColor(row, col, color3);
				}
			}
		} else if (height % 2 == 0 && height >= 4 && height <=30) {
			//red triangle
			for (int row = 0; row < height/2; row++) {
				for (int col = 0; col < (row+1)*4; col++) {
					grid.setColor(row, col, color);
				}
			}
			for (int row = height /2; row < height; row++) {
				for (int col = 0 ; col < (row-height) * -4; col++) {
					grid.setColor(row, col, color);
				}
			}

			//green triangle
			for (int row = height /2; row < height; row++) {
				for (int col = (row-height) * -4; col < width; col++) {
					grid.setColor(row, col, color3);
				}
			}
			//blue triangle
			for (int row = 0; row < height; row++) {
				for(int col = 4 *(row+1); col < width; col++) {
					grid.setColor(row, col, color2) ;
				}
			} 
		} else {
			Error(grid);
		}
	}
	//Macedonia
	public static void Macedonia (MyGrid grid, Color color, Color color2) {
		int height = grid.getHt();
		int width = grid.getWd();
		if (height % 2 == 0 && height >= 8 && height <= 30) {
			//red part of flag
			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					grid.setColor(row, col, color);
				}
			}
			//horizontal line
			for (int row = (height/2)-1; row < (height/2)+1; row++) {
				for (int col = 0; col < width; col++) {
					grid.setColor(row, col, color2);
				}
			}
			//vertical line
			for (int col = (width/2) - 1; col < (width/2) + 1; col++) {
				for (int row = 0; row < height; row++) {
					grid.setColor(row, col, color2);
				}
			}

			//rectangle in middle
			for (int row = (height/2) - 2; row < (height/2) + 2; row++) {
				for (int col = (width/2) - 4; col < (width/2) +4; col++) {
					grid.setColor(row, col, color2);
				}
			}
			//left to right diagonal
			for (int row = 0; row < height; row++) {
				for (int col = (2*(height-row))-1; col > (2*height) - ((2*row)+3
						); col--) {
					grid.setColor(row, col, color2);
				}
			}
			//right to left diagonal
			for (int row = 0; row < height ; row++) {
				for (int col = 2*row; col < (2*row) +2; col++) {
					grid.setColor(row,col,color2);
				}
			}
		} else {
			Error(grid);
		}
	}
	//Bahamas flag
	public static void Bahamas (MyGrid grid, Color color, Color color2, Color
			color3) {
		int width = grid.getWd();
		int height = grid.getHt();
		if (height % 3 == 0 && height %2!= 0 && height >=4 && height <=30) {
			//top half of triangle
			for (int row = 0; row < (height/2)+1; row++) {
				for (int col = 0; col < row + 1; col++) {
					grid.setColor(row, col, color);
				}
			}
			//bottom half of triangle
			for (int row = (height/2) + 1; row < height; row++) {
				for (int col = 0; col < height - row; col++) {
					grid.setColor(row, col, color);
				}
			}

			//for blue part of flag
			for(int row = 0; row < (height/3); row++) {
				for (int col = row + 1; col < width; col++) {
					grid.setColor(row, col, color2);
				}
			}
			for (int row = (height/3) + (height/3); row < height; row++) {
				for (int col = height - row; col < width; col++) {
					grid.setColor(row, col, color2);
				}
			}
			//for yellow part
			for (int row = height/3; row < (height/2) + 1; row++) {
				for (int col = row + 1; col < width; col++) {
					grid.setColor(row, col, color3);
				}
			}
			for (int row = (height/2) + 1; row < (height/3) + (height/3); row++)
			{
				for (int col = height-row; col < width; col++) {
					grid.setColor(row, col, color3);
				}
			}
		} else {
			Error(grid);
		}
	}
	//Zimbabwe flag
	public static void Zimbabwe (MyGrid grid) {
		int width = grid.getWd();
		int height = grid.getHt();
		if (height % 7 == 0 && height % 2 != 0 && height >=4 && height <=30) {
			//if the size is odd
			//row 1 green
			for (int row = 0; row < (height/7); row++) {
				for(int col = (row+1); col < width; col++) {
					grid.setColor(row, col, Color.GREEN);
				}
			}
			//row 2 yellow
			for (int row = height/7 ; row < (2*height/7); row++) {
				for (int col = (row+1); col < width; col++) {
					grid.setColor(row, col, Color.YELLOW);
				}
			}
			//row 3 red
			for (int row = (2*height/7); row<(3*height/7); row++) {
				for (int col = (row+1); col < width; col++) {
					grid.setColor(row, col, Color.RED);
				}
			}
			//row 4 black (change to halves)
			//half1
			for (int row = (3*height/7); row < (4*height/7); row++) {
				for (int col = (row+1); col < width; col++) {
					grid.setColor(row, col, Color.BLACK);
				}
			}
			//half2
			for (int row = (height/2) + 1; row < (4*height/7); row++) {
				for (int col = height-row; col<width; col++) {
					grid.setColor(row, col, Color.BLACK);
				}
			}
			//row 5 red
			for (int row = (4*height/7); row <(5*height/7); row++) {
				for (int col = height - row; col < width; col++) {
					grid.setColor(row, col, Color.RED);
				}
			}
			//row 6 yellow
			for (int row = (5*height/7); row < (6*height/7); row++) {
				for (int col = height - row; col < width; col++) {
					grid.setColor(row, col, Color.YELLOW);
				}
			}
			//row 7 green
			for (int row = (6*height/7); row < (7*height/7); row++) {
				for (int col = height - row; col < width; col++) {
					grid.setColor(row, col, Color.GREEN);
				}
			}
			//triangle
			//top half
			for (int row = 0; row <(height/2) + 1; row ++) {
				for (int col = 0; col<(row+1); col++) {
					grid.setColor(row, col, Color.WHITE);
				}
			}
			//bottom half
			for (int row = (height/2)+1; row < height; row++) {
				for (int col = 0; col<(height - row); col++)
					grid.setColor(row, col, Color.WHITE);
			}
		} else if (height % 7 == 0 && height % 2 == 0 && height >=4 && height 
				<=30) { //if the size is even
			//row 1 green
			for (int row = 0; row < height/7; row++) {
				for (int col = row + 1; col < width; col++) {
					grid.setColor(row, col, Color.GREEN);
				}
			}
			//row 2 yellow
			for (int row = height/7; row < (2*height)/7; row++) {
				for (int col  = row + 1; col < width; col++) {
					grid.setColor(row, col, Color.YELLOW);
				}
			}
			//row 3 red
			for (int row = (2*height)/7; row < (3*height)/7; row++) {
				for(int col = row + 1; col < width; col++) {
					grid.setColor(row, col, Color.RED);
				}
			}
			//row 4 black
			for (int row =(3*height)/7; row < (height/2); row++) {
				for (int col = row +1; col < width; col++) {
					grid.setColor(row, col, Color.BLACK);
				}
			}
			//row 4 (2nd half)
			for (int row = (height/2); row < (4*height)/7; row++) {
				for (int col = height-row; col< width; col++) {
					grid.setColor(row, col, Color.BLACK);
				}
			}
			//row 5 red
			for (int row = (4*height)/7; row < (5*height)/7; row++) {
				for (int col = height - row; col < width; col++) {
					grid.setColor(row, col, Color.RED);
				}
			}
			//row 6 yellow
			for (int row = (5*height)/7; row <(6*height)/7; row++ ) { 
				for (int col = height - row; col < width; col++) {
					grid.setColor(row, col, Color.YELLOW);
				}
			}
			//row 7 green
			for (int row = (6*height)/7; row < (7*height)/7; row++) {
				for (int col = height - row; col < width; col++) {
					grid.setColor(row, col, Color.GREEN);
				}
			}
			//triangle top half
			for (int row = 0; row < height/2; row++) {
				for (int col = 0; col < row + 1; col++) {
					grid.setColor(row, col, Color.WHITE);
				}
			}
			//triangle bottom half
			for (int row = height/2; row < height; row++) {
				for (int col = 0; col < height - row; col++) {
					grid.setColor(row, col, Color.WHITE);
				}
			}
		} else {
			Error(grid);
		}
	}

	//error flag
	public static void Error (MyGrid grid) {
		int width = grid.getWd();
		int height = grid.getHt();
		for (int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				grid.setColor(row, col, Color.WHITE);
			}
		}
		grid.setColor(0, 0, Color.RED);
		grid.setColor(0,  --width, Color.YELLOW);
		grid.setColor(--height, 0, Color.BLUE);
		grid.setColor(height--, width--, Color.GREEN);
	}
}