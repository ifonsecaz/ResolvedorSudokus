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
public class Conjuntos {
    private ConjuntoADT<Integer> val;

    public Conjuntos() {
        val=new ConjuntoA(9);
    }
    
    public boolean agrega(int num){
        return val.agrega(num);
    }
    
    public int quita(int num){
        return val.quita(num);
    }
    
    public boolean contiene(int num){
        return val.contiene(num);
    }
    
    public boolean estaVacio(){
        return val.estaVacio();
    }
    
}
