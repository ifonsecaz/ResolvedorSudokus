/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author ifonsecaz
 */
public class Sudoku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SudokuR a= new SudokuR();
        
        a.altaNum(5, 0, 0);
        a.altaNum(3, 0, 1);
        a.altaNum(7, 0, 4);
        a.altaNum(6, 1, 0);
        a.altaNum(1, 1, 3);
        a.altaNum(9, 1, 4);
        a.altaNum(5, 1, 5);
        a.altaNum(9, 2, 1);
        a.altaNum(8, 2, 2);
        a.altaNum(6, 2, 7);
        a.altaNum(8, 3, 0);
        a.altaNum(6, 3, 4);
        a.altaNum(3, 3, 8);
        a.altaNum(4, 4, 0);
        a.altaNum(8, 4, 3);
        a.altaNum(3, 4, 5);
        a.altaNum(1, 4, 8);
        a.altaNum(7, 5, 0);
        a.altaNum(2, 5, 4);
        a.altaNum(6, 5, 8);
        a.altaNum(6, 6, 1);
        a.altaNum(2, 6, 6);
        a.altaNum(8, 6, 7);
        a.altaNum(4, 7, 3);
        a.altaNum(1, 7, 4);
        a.altaNum(9, 7, 5);
        a.altaNum(5, 7, 8);
        a.altaNum(8, 8, 4);
        a.altaNum(7, 8, 7);
        a.altaNum(9, 8, 8);
        
        System.out.println(a.solucionador());
        System.out.println(a.toString());
    }
}
