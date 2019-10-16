/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.Iterator;

/**
 *
 * @author edi
 */
public class ConjuntoA <T> implements ConjuntoADT<T>{

    private T[] conjunto;
    private int cardinalidad;
    private final int MAX=20;

    public ConjuntoA() {
        conjunto= (T[])new Object[MAX];
        cardinalidad=0;
    }
    
    public ConjuntoA(int max) {
        conjunto= (T[])new Object[max];
        cardinalidad=0;
    }

    public Iterator<T> iterator() {
        return new IteradorArreglo(cardinalidad,conjunto);
    }

    public boolean agrega(T elem) {
        boolean res=false;
        
        if(!contiene(elem)){
            res=true;
            if(cardinalidad==conjunto.length){
                expande();
            }
            conjunto[cardinalidad]=elem;
            cardinalidad++;
        }
        return res;
    }
    
    private void expande(){
        T[] nuevo= (T[]) new Object[conjunto.length*2];
        
        for(int i=0; i<cardinalidad; i++){
            nuevo[i]=conjunto[i];
        }
        
        conjunto=nuevo;
    }

    public T quita(T elem) {
        int pos=buscaPos(elem);
        T res;
        
        if(pos>=0){
            res=conjunto[pos];
            conjunto[pos]=conjunto[cardinalidad-1];
            conjunto[cardinalidad-1]=null;
            cardinalidad--;
        }
        else{
            res=null;
        }
        return res;
    }
    
    private int buscaPos(T elem){
        int i=0;
        
        while(i<cardinalidad&&!conjunto[i].equals(elem)){
            i++;
        }
        
        if(i==cardinalidad){
            i=-1;
        }
        
        return i;
    }

    public boolean contiene(T elem) {
        boolean res=false;
        Iterator<T> it = iterator();
        
        while(it.hasNext()&&!res){
            res=elem.equals(it.next());
        }
        
        return res;
    }

    public boolean contieneRI(T elem){
        return contieneRI(elem,iterator());
    }
    
    private boolean contieneRI(T elem, Iterator<T> it){
        if(!it.hasNext()){
            return false;
        }
        if(it.next().equals(elem)){
            return true;
        }
        else{
            return contieneRI(elem,it);
        }
    }
    
    public int getCardinalidad() {
        return cardinalidad;
    }

    public boolean estaVacio() {
        return cardinalidad==0;
    }

    public ConjuntoADT<T> u(ConjuntoADT<T> conj2){
        Iterator<T> it1;
        Iterator<T> it2;
        ConjuntoADT<T> res;
        
        if(conj2!=null){
            res = new ConjuntoA(cardinalidad + conj2.getCardinalidad());
            it1= iterator();
            it2= conj2.iterator();
            
            while(it1.hasNext()){
                res.agrega(it1.next());
            }
            while(it2.hasNext()){
                res.agrega(it2.next());
            }
        }
        else{
            res=null;
        }
        
        return res;
    }
    
    public void unión(ConjuntoADT<T> conj2) {
        Iterator<T> it;
        
        if(conj2!=null){
            it= conj2.iterator();
            
            while(it.hasNext()){
                agrega(it.next());
            }
        }
    }

    public void intersección(ConjuntoA conj2) {
        Iterator<T> it1=iterator();
        T elem;
        
        if(conj2!=null){
            
            while(it1.hasNext()){
                elem= it1.next();
                if(!conj2.contieneRI(elem)){
                    quita(elem);
                }
            }
        }
    }
    
    public ConjuntoADT<T> inter(ConjuntoADT<T> conj2){
        ConjuntoADT<T> res;
        int max;
        Iterator<T> it;
        T aux;
        
        if(conj2!=null){
            res=new ConjuntoA(conj2.getCardinalidad());
            it= conj2.iterator();
            
            while(it.hasNext()){
                aux=it.next();
                
                if(contiene(aux)){
                    res.agrega(aux);
                }
            }
        }
        else{
            res=null;
        }
        return res;
    }

    public void diferencia(ConjuntoADT<T> conj2) {
        Iterator<T> it;
        T elem;
        
        if(conj2!=null){
            it=conj2.iterator();
            
            while(it.hasNext()){
                elem=it.next();
                if(contiene(elem)){
                    quita(elem);
                }
            }
        }
    }
    
    public ConjuntoADT<T> dif(ConjuntoADT<T> conj2){
        ConjuntoADT<T> diferencia= new ConjuntoA();
        
        if(conj2!=null){
            Iterator<T> it= iterator();
            T aux;
            int j=0;
            
            while(it.hasNext()){
                aux=it.next();
                if(!conj2.contiene(aux)){
                    diferencia.agrega(aux);
                }
            }
        }
        return diferencia;
    }
    
    public ConjuntoADT<T> union(ConjuntoADT<T> c2){
        ConjuntoADT<T> res= new ConjuntoA();
        
        if(c2!=null){
            Iterator<T> cont2= c2.iterator();
            Iterator<T> cont1=iterator();
            return union(cont1,cont2,res);
        }
        
        return res;
    }
    
    private ConjuntoADT<T> union (Iterator<T> cont1,Iterator<T> cont2, ConjuntoADT<T> res){
        if(cont1.hasNext()){
            res.agrega(cont1.next());
            return union(cont1,cont2,res);
        }
        else{
            if(cont2.hasNext()){
                res.agrega(cont2.next());
                return union(cont1,cont2,res);
            }
            return res;
        }
    }
    
    public ConjuntoADT<T> inters(ConjuntoADT<T> c2){
        ConjuntoADT<T> res= new ConjuntoA();
        
        if(c2!=null){
            Iterator<T> conj1= iterator();
            return inters(conj1,c2,res);
        }
        
        return res;
    }
    
    private ConjuntoADT<T> inters(Iterator<T> conj1, ConjuntoADT<T> c2, ConjuntoADT<T> res){
        if(conj1.hasNext()){
            T aux= conj1.next();
            Iterator<T> conj2= c2.iterator();
            if(esta(aux,conj2)){
                res.agrega(aux);
            }
            return inters(conj1,c2,res);
        }
        else{
            return res;
        }
    }
    
    private boolean esta(T aux, Iterator<T> conj2){
        if(conj2.hasNext()){
            if(aux.equals(conj2.next())){
                return true;
            }
            return esta(aux,conj2);
        }
        else{
            return false;
        }
    }
    
    public ConjuntoADT<T> difer(ConjuntoADT<T> c2){
        ConjuntoADT<T> res= new ConjuntoA();
        
        if(c2!=null){
            Iterator<T> conj1= iterator();
            return difer(conj1,c2,res);
        }
        
        return res;
    }
    
    private ConjuntoADT<T> difer(Iterator<T> conj1,ConjuntoADT<T> c2,ConjuntoADT<T> res){
        if(conj1.hasNext()){
            T aux=conj1.next();
            Iterator<T> conj2=c2.iterator();        
            if(!esta(aux,conj2)){
                res.agrega(aux);
            }
            return difer(conj1,c2,res);
        }
        else{
            return res;
        }
        
    }
    
    public String toString(){
        StringBuilder cad= new StringBuilder();
        
        for(int i=0; i<cardinalidad; i++){
            cad.append(conjunto[i].toString());
            cad.append("\n");
        }
        
        return cad.toString();
    }
    
}
