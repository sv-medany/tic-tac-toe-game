/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.assignment.pkg1;

import javax.swing.JOptionPane;

/**
 *
 * @author DR- Mohamed
 */
public class Grid {

    int grid[][];
    int counter;  //counts the total number of moves
    int draw = 1; // incase of draw 
    int winner; //to determine the winner
    int flag; //catcher to help in construct of board shape

    // x=88 o=79
    public Grid(int x, int y) {
        grid = new int[x][y];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = 32;    //initialize all elements to ascii code of  ' '
            }
        }
        counter = 1;  //counts the number of moves 
    }

    public int checkWinner(int i, int j) {
        if (checkH(i) == 3 * 88 || checkV(j) == 3 * 88 || checkD() == 3 * 88) { //test for x being the winner
            return 3 * 88;
        }
        if (checkH(i) == 3 * 79 || checkV(j) == 3 * 79 || checkD() == 3 * 79) { // test for o being the winner
            return 3 * 79;
        }
        return 0; //no winner found
    }

    public void printGrid() {

        for (int z = 0; z < grid[0].length; z++) {
            if (flag == 0) { //check if we are in the 1st loop 
                System.out.print("     " + (z + 1));
                flag = 1;
            } else {
                System.out.print("    " + (z + 1));
                if (z == 0) {
                    System.out.print("    " + (z + 1));
                } else {
                    System.out.print("     " + (z + 1));
                }
            }
            flag = 0; //allignment and avoid repitition 
        }

        System.out.print("\n ");
        for (int z = 0; z < grid[0].length; z++) {
            System.out.print("------"); //boarders between column headings and grid
        }
        System.out.print("-\n  ");

        for (int i = 0; i < grid.length; i++) {
            for (int z = 0; z < grid[0].length; z++) {
                System.out.print("|     "); //allignment
            }
            System.out.print("\n ");
            for (int j = 0; j < grid[0].length; j++) {
                if (j == 0) {
                    System.out.print((i + 1) + "|  " + (char) grid[i][j] + "  |");//casting to print as character not ascii code
                } else if (j != grid[0].length - 1) {
                    System.out.print("  " + (char) grid[i][j] + "  |"); //to avoid inserting an unnecessary boarder at the end of the grid
                } else {
                    System.out.print("  " + (char) grid[i][j]);
                }

            }
            System.out.print("\n  ");
            for (int k = 0; k < grid[0].length; k++) {
                System.out.print("|     ");
            }
            System.out.print("\n ");
            for (int z = 0; z < grid[0].length; z++) {
                System.out.print("------"); //seperate each boarder from the next one
            }
            if (i == grid.length - 1) {
                System.out.print("-\n");
            } else {
                System.out.print("-\n  "); //allignment
            }

        }
    }

    public int checkH(int row) //check winner by row
    {
        for (int j = 0; j < grid[0].length - 2; j++) {
            if (grid[row][j] + grid[row][j + 1] + grid[row][j + 2] == 3 * 88) //if three consecutive Xs horizontally
            {
                return 3 * 88;
            } else if (grid[row][j] + grid[row][j + 1] + grid[row][j + 2] == 3 * 79) //if three consecutive Os horizontally
            {
                return 3 * 79;
            }
        }
        return 0;   //no winner yet
    }

    public int checkV(int col) //check winner by coloumn
    {
        for (int j = 0; j < grid.length - 2; j++) {
            if (grid[j][col] + grid[j + 1][col] + grid[j + 2][col] == 3 * 88) //if three consecutive Xs vertically
            {
                return 3 * 88;
            } else if (grid[j][col] + grid[j + 1][col] + grid[j + 2][col] == 3 * 79) //if three consecutive Os vertically
            {
                return 3 * 79;
            }
        }
        return 0;    //no winner yet

    }

    public int checkD() {
        int i, j;
        for (i = 0; i < (grid.length - 2); i++) { //start from 0 as we are looking in the negative slope direction
            for (j = 0; j < (grid[0].length - 2); j++) {
                if (grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2] == 3 * 88) { //check negative gradient diagonal of X's with elements that are next row to the right//
                    return 3 * 88;
                }
                if (grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2] == 3 * 79) {//check negative gradient diagonal of O's with elements that are next row to the right//
                    return 3 * 79;
                }
            }
        }
        for (i = grid.length - 1; i > 1; i--) { //start from last row as we are looking in positive gradient direction
            for (j = 0; j < (grid[0].length - 2); j++) {
                if (grid[i][j] + grid[i - 1][j + 1] + grid[i - 2][j + 2] == 3 * 88) { //check positive gradient diagonal of X's which are in the two rows above and to the right//
                    return 3 * 88;
                } else if (grid[i][j] + grid[i - 1][j + 1] + grid[i - 2][j + 2] == 3 * 79) {//check positive gradient diagonal of O's which are in the two rows above and to the right//
                    return 3 * 79;
                }
            }
        }
        return 0;
    }

    public int checkInput(int row, int col) {
        if (row > grid.length - 1 || col > grid[0].length - 1 || row < 0 || col < 0) { //check input is in the correct range of the dimensions of the board
            return 0;
        }

        if (grid[row][col] != 32) { //check the input data is not already occupied
            return 2;
        }

        return 1;
    }

    public void insertInput(int row, int col) {
        if (counter % 2 == 0) { //in case of even it means player 2's turn which is O
            grid[row][col] = 79; //ascii code of O
        } else {
            grid[row][col] = 88; //ascii code of X
        }
    }

    public void run() {

        String num1;
        int tr = 0;
        int x = 0, y = 0;
        do {
            printGrid();
            if (counter % 2 == 0) { //determine whose turn
                num1 = JOptionPane.showInputDialog(null, "It is O's turn,please enter the row you wish to play in");
            } else {
                num1 = JOptionPane.showInputDialog(null, "It is X's turn,please enter the row you wish to play in");
            }

            String num2 = JOptionPane.showInputDialog(null, "please enter the column you wish to play in");
            try {
                x = Integer.parseInt(num1); // convert row to integer to be able to use in functions
                x = x - 1; // as indexing starts from zero
                y = Integer.parseInt(num2); // convert column to integer to be able to use in functions
                y--; // indexing starts from zero
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input,please re-enter");
                tr = 1;
            }
            while (checkInput(x, y) != 1 || tr == 1) { //check for invalid inputs
                if (tr != 1) {
                    if (checkInput(x, y) == 2) { //check cell is occupied 
                        JOptionPane.showMessageDialog(null, "Cell occupied,please re-enter");
                    } else {
                        JOptionPane.showMessageDialog(null, "Range overflow,please re-enter");
                    }
                }
                tr = 0;
                printGrid(); //grid displayed after each move
                if (counter % 2 == 0) {
                    num1 = JOptionPane.showInputDialog(null, "It is O's turn,please enter the row you wish to play in");
                } else {
                    num1 = JOptionPane.showInputDialog(null, "It is X's turn,please enter the row you wish to play in");
                }
                num2 = JOptionPane.showInputDialog(null, "please enter the column you wish to play in");
                try {
                    x = Integer.parseInt(num1);
                    x = x - 1;
                    y = Integer.parseInt(num2);
                    y--;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input,please re-enter");
                    tr = 1;
                }

            }
            insertInput(x, y); //place input of user using the entered coordinates after validation
            if (checkWinner(x, y) != 0) { //check for a winner
                winner = checkWinner(x, y); // place 3*ascii code of winner in the variable to print the winner
                draw = 0; //  game can not be a draw if a winner is already found
                break;
            }
            counter++; //increase number of moves after each turn
        } while (counter < grid.length * grid[0].length + 1); //to be dynamic and compatible with any dimensions
        printGrid(); //print final grid
        if (draw == 1) {
            JOptionPane.showMessageDialog(null, "The game ended in a draw");
        } else if (winner == 3 * 88) {
            JOptionPane.showMessageDialog(null, " X is the winner");
        } else {
            JOptionPane.showMessageDialog(null, " O is the winner");
        }
    }

}
