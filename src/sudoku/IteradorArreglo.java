/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author edi
 */
public class IteradorArreglo <T> implements Iterator<T>{
    
    private T[] colección;
    private int total;
    private int actual;

    public IteradorArreglo(int total, T[] arre) {
        this.total = total;
        colección =arre;
        actual=0;
    }
 
    public boolean hasNext() {
        return actual<total;
    }

    public T next() {
        if(hasNext()){
            T res= colección[actual];
            actual++;
            return res;
        }
        else{
            throw new NoSuchElementException();
        }
    }

    public void remove() {
        throw new UnsupportedOperationException("Operación no implementada");
    }
    
    
    
}
