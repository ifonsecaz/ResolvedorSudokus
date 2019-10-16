/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author edi
 */
public class P {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SudokuR a= new SudokuR();
        
        a.altaNum(8, 0, 2);
        a.altaNum(7, 0, 3);
        a.altaNum(5, 0, 5);
        a.altaNum(1, 1, 0);
        a.altaNum(5, 1, 1);
        a.altaNum(4, 1, 2);
        a.altaNum(6, 1, 3);
        a.altaNum(9, 2, 0);
        a.altaNum(8, 2, 3);
        a.altaNum(4, 2, 4);
        a.altaNum(2, 2, 5);
        a.altaNum(3, 2, 6);
        a.altaNum(5, 2, 7);
        a.altaNum(2, 3, 1);
        a.altaNum(6, 3, 4);
        a.altaNum(7, 3, 6);
        a.altaNum(4, 3, 8);
        a.altaNum(7, 4, 2);
        a.altaNum(9, 4, 5);
        a.altaNum(1, 4, 6);
        a.altaNum(6, 4, 8);
        a.altaNum(6, 5, 0);
        a.altaNum(9, 5, 2);
        a.altaNum(7, 5, 5);
        a.altaNum(3, 5, 7);
        a.altaNum(5, 5, 8);
        a.altaNum(7, 6, 0);
        a.altaNum(8, 6, 1);
        a.altaNum(1, 6, 3);
        a.altaNum(6, 6, 5);
        a.altaNum(4, 6, 7);
        a.altaNum(9, 7, 1);
        a.altaNum(3, 7, 3);
        a.altaNum(5, 7, 4);
        a.altaNum(7, 7, 8);
        a.altaNum(3, 8, 0);
        a.altaNum(6, 8, 1);
        a.altaNum(5, 8, 6);
        a.altaNum(1, 8, 7);
                
        System.out.println(a.solucionador());
        System.out.println(a.toString());
    }
}
