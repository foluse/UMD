package fighters;

import framework.BattleField;
import framework.Random131;

public class BasicSoldier {
	//final static constants
	//soldiers attributes (sum of all attributes must be 100)
	public final static int INITIAL_HEALTH = 10;
	public final static int ARMOR = 20;
	public final static int STRENGTH = 30;
	public final static int SKILL = 40;
	//used by method to specify directions (don't change)
	public final static int UP = 0;
	public final static int RIGHT = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	public final static int UP_AND_RIGHT = 4;
	public final static int DOWN_AND_RIGHT = 5;
	public final static int DOWN_AND_LEFT = 6;
	public final static int UP_AND_LEFT = 7;
	public final static int NEUTRAL = -1;
	//soldier's instance variables 
	public final BattleField grid;
	public int row, col;
	public int health;
	public final int team;

	//initializes the soldier at the beginning of battle
	public BasicSoldier(BattleField gridIn, int teamIn, int rowIn, int colIn) {
		grid = gridIn;
		team = teamIn;
		row = rowIn;
		col = colIn;
		health = INITIAL_HEALTH;
	}

	//determines whether the soldier can move
	public boolean canMove() {
		if (grid.get(row-1, col) == BattleField.EMPTY || grid.get(row+1,  col) 
				== BattleField.EMPTY || grid.get(row, col-1) == 
				BattleField.EMPTY || grid.get(row, col+1) == BattleField.EMPTY)
		{ return true;
		} else {
			return false;
		}
	}

	//scans the battleField for any enemies remaining
	public int numberOfEnemiesRemaining() {
		int counter = 0;
		for (int battleFieldRow = 0; battleFieldRow < grid.getRows(); 
				battleFieldRow++) {
			for(int battleFieldCol = 0; battleFieldCol < grid.getCols();
					battleFieldCol++) {
				if(team != findEnemyTeam() && grid.get(battleFieldRow, 
						battleFieldCol) == findEnemyTeam()) {
					counter++;
				}
			}
		}
		return counter;
	}

	//finds out the enemy team
	public int findEnemyTeam() { 
		int enemyTeam = 0;
		if(team == BattleField.BLUE_TEAM) {
			enemyTeam = BattleField.RED_TEAM;
		} else if (team == BattleField.RED_TEAM) {
			enemyTeam = BattleField.BLUE_TEAM;
		}
		return enemyTeam;
	}

	//finds the distance between the soldier to reach a destination
	public int getDistance(int destinationRow, int destinationCol) {
		int moves = Math.abs(destinationCol - col) + Math.abs(destinationRow 
				- row); 
		return moves;
	}

	//determines which way the soldier should travel to reach the destination
	public int getDirection(int destinationRow, int destinationCol) {
		if (destinationRow < row && destinationCol == col) {
			return UP;
		} else if (destinationRow > row && destinationCol == col) {
			return DOWN;
		} else if (destinationRow == row && destinationCol < col) {
			return LEFT;
		} else if (destinationRow == row && destinationCol > col) {
			return RIGHT;
		} else if (destinationRow < row && destinationCol < col) {
			return UP_AND_LEFT;
		} else if (destinationRow < row && destinationCol > col) {
			return UP_AND_RIGHT;
		} else if (destinationRow > row && destinationCol < col) {
			return DOWN_AND_LEFT;
		} else if (destinationRow > row && destinationCol > col) {
			return DOWN_AND_RIGHT;
		} else {
			return NEUTRAL;
		}
	}

	//returns the direction of the nearest teammate
	public int getDirectionOfNearestFriend() {
		int direction = NEUTRAL; //directions to teammate
		int moves = grid.getRows() + grid.getCols() + 1; //max amount of moves

		for (int bfRow = 0; bfRow < grid.getRows(); bfRow++) {
			for (int bfCol = 0; bfCol < grid.getCols(); bfCol++) {
				if (grid.get(bfRow, bfCol) == team && !(bfRow == row && 
						bfCol == col)) /* makes sure soldier's location isn't 
						included */
				{
					if (getDistance(bfRow, bfCol) <= moves) {
						moves = getDistance(bfRow, bfCol);
						direction = getDirection (bfRow, bfCol);
					}
				}
			}
		}
		return direction;
	}

	//counts the number of teammates within a specified radius
	public int countNearbyFriends (int radius) {
		int moves = -1; 
		for (int battleFieldRow = 0; battleFieldRow < grid.getRows();
				battleFieldRow++) {
			for (int battleFieldCol = 0; battleFieldCol < grid.getCols(); 
					battleFieldCol++) {
				if (getDistance(battleFieldRow, battleFieldCol) <= radius && 
						grid.get(battleFieldRow, battleFieldCol) == team) {
					moves++;
				}
			}
		}
		return moves;
	}

	//returns the direction of the closest enemy
	public int getDirectionOfNearestEnemy(int radius) {
		int enemyTeam = findEnemyTeam();
		int direction = NEUTRAL; //directions to enemy
		int moves = grid.getRows() + grid.getCols() + 1; //max amount of moves

		for (int bfRow = 0; bfRow < grid.getRows(); bfRow++) {
			for (int bfCol = 0; bfCol < grid.getCols(); bfCol++) {
				if (grid.get(bfRow, bfCol) == enemyTeam && 
						getDistance(bfRow, bfCol) <= radius)  {
					if (getDistance(bfRow, bfCol) < moves && getDistance 
							(bfRow, bfCol) <= radius) {
						moves = getDistance(bfRow, bfCol);
						direction = getDirection (bfRow, bfCol);
					}
				}
			}
		}

		return direction;
	}
	
//controls the soldier's actions
	public void performMyTurn() {
		int enemyTeam = findEnemyTeam(); 
		if (grid.get(row-1, col) == enemyTeam) {
			grid.attack(row-1, col);
		} else if (grid.get(row+1, col) == enemyTeam) {
			grid.attack(row+1,col);
		} else if (grid.get(row, col-1) == enemyTeam) {
			grid.attack(row, col-1);
		} else if (grid.get(row, col+1) == enemyTeam) {
			grid.attack(row, col+1);
		} else if (grid.get(row-1, col) == BattleField.EMPTY) {
			row--;
		} else if (grid.get(row+1, col) == BattleField.EMPTY) {
			row++;
		} else if (grid.get(row, col-1) == BattleField.EMPTY) {
			col--;
		} else if (grid.get(row, col+1) == BattleField.EMPTY) {
			col++;
		}
	}	
}

