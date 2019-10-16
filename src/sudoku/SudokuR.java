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
public class SudokuR {
     private int[][] s; //Matriz 9x9 representa el sudoku
     private ConjuntoADT<Integer>[][] c;   //Matriz con conjuntos: guardará los números introducidos por cada región 3x3
     private ConjuntoADT<Integer>[] fila;  //Arreglos con conjuntos: guardarán los números introducidos en cada fila y columna
     private ConjuntoADT<Integer>[] columna;
     private final int NUM=9;
     
    public SudokuR() {
        s= new int[NUM][NUM];
        c= new ConjuntoA[3][3];
        fila=new ConjuntoA[9];
        columna=new ConjuntoA[9];
        rellenaMatCon(0,0);
        rellenaMatriz(0,0);
        rellenaFila(0);
        rellenaCol(0);
    }
    /**<pre>
     * Rellena Fila
     * Declará una serie de conjuntos que contendrán los números guardados en cada fila, correspondiente a su posición en el arreglo
     * 
     * @param i sirve de auxiliar para recorrer todos las celdas del arreglo
     * </pre>
     */
    private void rellenaFila(int i){
       if(i<NUM){
           fila[i]=new ConjuntoA(); //Declara un conjunto por celda del arreglo
           rellenaFila(i+1);
       }
    }
    
    /**<pre>
     * Rellena Columna
     * Declará una serie de conjuntos que contendrán los números guardados en cada columna, correspondiente a su posición en el arreglo
     * 
     * @param i sirve de auxiliar para recorrer todos las celdas del arreglo
     * </pre>
     */
    private void rellenaCol(int i){
        if(i<NUM){
            columna[i]=new ConjuntoA(); //Declara un conjunto por celda del arreglo
            rellenaCol(i+1);
        }
    }
    
    /**<pre>
     * Rellena Matriz con Conjuntos
     * Declará una serie de conjuntos que contendrán los números guardados por cada región 3x3
     * 
     * @param i sirve de auxiliar para recorrer los renglones de la matriz
     * @param j sirve de auxiliar para recorrer las columnas de la matriz
     * </pre>
     */
    private void rellenaMatCon(int i, int j){
        if(i<3){
            if(j<3){
                c[i][j]=new ConjuntoA();    //En una matriz de 3x3 crea en cada celda un conjunto que simulará las regiones 3x3
                rellenaMatCon(i,j+1);
            }
            else{
                rellenaMatCon(i+1,0);
            }
        }
    }
    
    /**<pre>
     * Recorre todas las celdas del sudoku y agrega 0
     * 
     * @param i
     * @param j 
     * </pre>
     */
    private void rellenaMatriz(int i, int j){
        if(i<NUM){
            if(j<NUM){
                s[i][j]=0;   //A cada celda donde ni halla un número le pondrá un 0 con el fin de evitar errores de preguntar por una celda vacía
                rellenaMatriz(i,j+1);
            }
            else{
                rellenaMatriz(i+1,0);
            }
        }
    }
    
    /**<pre>
     * Revisa si el número dado no se encuentra dentro de la misma fila, columna o región 3x3
     * 
     * @param i Permite moverse por renglones
     * @param j Permite moverse por columnas
     * @param num Número que se someterá a revisión
     * </pre>
     * @return <ul>
     * <li> true: Si el número puede ser agregado</li>
     * <li> false: Si el número incumple con alguna condición</li>
     * </ul>
     */
    public boolean revisa(int i, int j, int num){
        if(c[i/3][j/3].contiene(num)){  //Al dividir las posiciones en que se encuentra devolverá la posición de la región 3x3, y se asegurará el número no esté en el conjunto
            return false;
        }
        else{
            if(fila[i].contiene(num)){  //Checa que el número no halla sido insertado ya en la fila
                return false;
            }
            else{
                return !columna[j].contiene(num); //Checa que el número no halla sido insertado ya en la columna
            }
        }
    }
    

    /**<pre>
     * Alta de un número
     * Inserta el número en su correspondiente sección de la matriz del sudoku, 
     * además lo agrega a los conjuntos correspondientes de fila, columna y región.
     * 
     * @param num Número que se añadirá
     * @param i Renglon donde se coloca
     * @param j Columna donde se inserta
     * </pre>
     */
    public void altaNum(int num, int i,int j){
        s[i][j]=num; //Agrega el número en la posición correspondiente a la matriz sudoku, además lo agrega a su conjunto en fila, columna y región
        c[i/3][j/3].agrega(num);    //Toma en cuenta que previamente a agregarlo se revisó fuera posible insertarlo
        fila[i].agrega(num);
        columna[j].agrega(num);
    }
   
      /**<pre>
     * Devuelve el número solicitado en su correspondiente posición
     * 
     * @param i Renglón 
     * @param j Columna
     * @return Devuelve el número insertado en dicha posición
     * </pre>
     */
    public int getNum(int i,int j){
        return s[i][j];
    }
   
    /**
     * <pre>
     * Solucionador
     * 
     * Método que llama el usuario para resolver el sudoku
     * </pre>
     * @return  <ul>
     *  <li>true: si el sudoku tiene solución </li>
     *  <li>false: si el sudoku no tiene solución </li>
     * </ul>
     */
    public boolean solucionador(){
        int i=0;
        int j=0;
        int num=1;
        
        return solucionador(i,j,num); //Llama al método recursivo, parte desde la celda en la posición (0,0) e intenta primero insertar el número 1
    }
    
    /**<Pre>
     * Solucionador (Recursivo)
     * Recorre de celda en celda, cuando encuentra una casilla vacía, 
     * llama al método asigna número para sustituir el 0 por un número en dicha posición
     * En caso de que la solución no sea la deseada, se regresa y prueba con el siguiente número
     * </pre>
     */
    private boolean solucionador(int i, int j,int num){
        boolean aux=false;
        if(i<NUM){  //En ambos if se asegura no se salga del rango de la matriz
            if(j<NUM){   
                if(s[i][j]==0){ //Evita caer en celdas con números que hayan sido insertados por el usuario
                    asignaNumero(i,j,num); //Llama a buscar si se puede insertar algún número 
                        if(s[i][j]>0){  //De llegar a 0 significa que ningún número pudo ser insertado, true permite avanzar a la siguiente celda, de lo contrario debe regresarse
                            aux=true;
                        }
                        if(aux){
                            aux=solucionador(i,j+1,1);
                        }
                        if(!aux&&s[i][j]>0){ //Si el número no satisfació a la solución se regresa a la celda anterior, remueve ese número de los conjuntos y prueba con el siguiente
                            num=s[i][j];    //Se agrega la condición que no sea 0 para evitar caer en un ciclo infinito intentando poner un número imposible
                            s[i][j]=0;  
                            c[i/3][j/3].quita(num);
                            fila[i].quita(num);
                            columna[j].quita(num);
                            aux=solucionador(i,j,num+1);
                        }
                }
                else{
                    aux=solucionador(i,j+1,1);
                }
            }
            else{
                aux=solucionador(i+1,0,1);
            }
        }
        else{
            aux= true;
        }
        return aux; //De haber llegado al final la variable que devolverá será true, de no encontrar solución habrá regresado al inicio de la matriz y devolverá false
    }
    
    /**<pre>
     * Metodo que prueba en una celda los números del 1 al 9, poniendo el primero que cumpla con las condiciones
     * de no repetirse en la fila, columna o región; para ello se apoya del método revisar
     * </pre>
     */
    private void asignaNumero(int i,int j,int num){
        if(num>0&&num<10){ 
            if(revisa(i,j,num)){
                altaNum(num,i,j);
            }
            else{
                asignaNumero(i,j,num+1); //Va probando con cada número del 1 al 9 hasta que alguno pueda ser colocado de acuerdo al método revisa
            }
        }
        else{
            s[i][j]=0;
        }
    }
    
     /**<pre>
     * Método de apoyo que imprime la matriz del sudoku
     * </pre>
     */
    public String toString(){
        int i=0;
        int j=0;
        StringBuilder cad=new StringBuilder();
        
        return toString(i,j,cad);
    }
    
    private String toString(int i, int j,StringBuilder cad){
        if(i<NUM){
            if(j<NUM){
                cad.append("\t" + s[i][j]);
                return toString(i,j+1,cad);
            }
            else{
                j=0;
                cad.append("\n");
                return toString(i+1,j,cad);
            }
        }
        return cad.toString();
    }
}
