package mx.unam.ciencias.icc;

/**
 * <p>Clase para listas doblemente ligadas de cadenas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas tienen un iterador para poder recorrerlas.</p>
 */
public class ListaCadena {

    /* Clase Nodo privada para uso interno de la clase ListaCadena. */
    private class Nodo {
        public String elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(String elemento) {
            this.elemento = elemento;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Nodo iterador. */
    private Nodo iterador;
    /* Número de elementos en la lista. */
    private int longitud;
    
    /**
     * Busca si existe un nodo con el parametro de entrada de tipo cadena.
     * Regresa el nodo que contiene el elemento.
     * @return el nodo en caso de que exista y <code>null</code> en caso de que no.
     */
    private Nodo buscaCadena( String elemento ){
        Nodo n = cabeza;
        while( n != null ){
            if( n.elemento.equals(elemento) )
                return n;
            n = n.siguiente;
        }
        return null;
    }

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        return longitud == 0;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último. Después de llamar este
     * método, el iterador apunta a la cabeza de la lista.
     * @param elemento el elemento a agregar.
     */
    public void agregaFinal(String elemento) {
        Nodo ultimo = new Nodo( elemento );
        if( longitud == 0 )
            cabeza = rabo = ultimo;
        else{
            ultimo.anterior = rabo;
            rabo.siguiente = ultimo;
            rabo = ultimo;
        }
        longitud++;
        iterador = cabeza;
        // Aquí va su código.
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último. Después de llamar este
     * método, el iterador apunta a la cabeza de la lista.
     * @param elemento el elemento a agregar.
     */
    public void agregaInicio(String elemento) {
        Nodo primero = new Nodo( elemento );
        if( longitud == 0 )
            cabeza = rabo = primero;
        else{
            cabeza.anterior = primero;
            primero.siguiente = cabeza;
            cabeza = primero;
        }
        longitud++;
        iterador = cabeza;
        // Aquí va su código.
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica. Si un elemento de la lista es
     * modificado, el iterador se mueve al primer elemento.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(String elemento) {
        Nodo n = cabeza;
        while( n != null ){
            if( n.elemento.equals(elemento) ){
                if( n.anterior == null )
                    eliminaPrimero();
                else if( n.siguiente == null )
                    eliminaUltimo();
                else{
                    n.siguiente.anterior = n.anterior;
                    n.anterior.siguiente = n.siguiente;
                    longitud--;
                }   
                iterador = cabeza;
            }
            n = n.siguiente;
        }   
        // Aquí va su código.
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista está vacía.
     */
    public String eliminaPrimero() {
        String e = ( cabeza != null ) ? cabeza.elemento : null;
        if( longitud != 0 ){
            if( cabeza != rabo ){
                cabeza = cabeza.siguiente;
                cabeza.anterior = null;
            }
            else
                cabeza = rabo = null;
            longitud--;
        }
        return e;
        // Aquí va su código.
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista está vacía.
     */
    public String eliminaUltimo() {
        String e = ( rabo != null ) ? rabo.elemento : null;
        if( longitud != 0 ){
            if( rabo.equals(cabeza) )
                rabo = cabeza = null;
            else{
                rabo = rabo.anterior;
                rabo.siguiente = null;
            }
            longitud--;
        }
        return e;
        // Aquí va su código.
    }

    /**
     * Nos dice si un elemento está en la lista. El iterador no se mueve.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(String elemento) {
        return buscaCadena( elemento ) != null;
        // Aquí va su código.
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public ListaCadena reversa() {
        Nodo n = rabo;
        ListaCadena reversa = new ListaCadena();
        while( n != null ){
            reversa.agregaFinal( n.elemento );
            n = n.anterior;
        }
        return reversa;
        // Aquí va su código.
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public ListaCadena copia() {
        Nodo n = cabeza;
        ListaCadena copia = new ListaCadena();
        while( n != null ){
            copia.agregaFinal( n.elemento );
            n = n.siguiente;
            copia.longitud++;
        }
        return copia;
        // Aquí va su código.
    }

    /**
     * Limpia la lista de elementos. El llamar este método es equivalente a
     * eliminar todos los elementos de la lista.
     */
    public void limpia() {
        cabeza = rabo = null;
        longitud = 0;
        // Aquí va su código.
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public String getPrimero() {
        return ( cabeza != null ) ? cabeza.elemento : null;
        // Aquí va su código.
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public String getUltimo() {
        return ( rabo != null ) ? rabo.elemento : null;
        // Aquí va su código.
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista. Si el índice es menor
     * que cero o mayor o igual que el número de elementos de la lista, el
     * método regresa <tt>null</tt>.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, si <em>i</em> es mayor
     *         o igual que cero y menor que el número de elementos en la lista;
     *         <tt>null</tt> en otro caso.
     */
    public String get(int i) {
        Nodo n = cabeza;
        String e = null;
        if( i>=0 && i< longitud ){
            for( int a = i; a>0; a-- )
                n = n.siguiente;
            e = n.elemento;
        }
        return e;
        // Aquí va su código.
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(String elemento) {
        Nodo n = cabeza;
        int indice = 0;
        if( buscaCadena( elemento ) != null && longitud != 0 ){
            while( !n.elemento.equals( elemento ) ){
                indice++;
                n = n.siguiente;
            }
        }
        else 
            indice = -1;
        return indice;
        // Aquí va su código.
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    public String toString() {
        Nodo n = cabeza;
        String lista = "[";
        while( n != rabo ){
            lista += String.format( "%s, " , n.elemento );
            n = n.siguiente;
        }
        lista+= String.format( "%s]" , n.elemento );
        return lista;
        // Aquí va su código.
    }

    /**
     * Mueve el iterador de la lista a su primer elemento.
     */
    public void primero() {
        iterador = cabeza;
        // Aquí va su código.
    }

    /**
     * Mueve el iterador de la lista a su último elemento.
     */
    public void ultimo() {
        iterador = rabo;
        // Aquí va su código.
    }

    /**
     * Mueve el iterador al siguiente elemento.
     */
    public void siguiente() {
        iterador = iterador.siguiente;
        // Aquí va su código.
    }

    /**
     * Mueve el iterador al elemento anterior.
     */
    public void anterior() {
        iterador = iterador.anterior;
        // Aquí va su código.
    }

    /**
     * Regresa el elemento al que el iterador apunta.
     * @return el elemento al que el iterador apunta, o <tt>null</tt> si el
     *         iterador es inválido.
     */
    public String get() {
        return (( iterador != null ) ? iterador.elemento : null);
        // Aquí va su código.
    }

    /**
     * Nos dice si el iterador es válido.
     * @return <tt>true</tt> si el iterador es válido; <tt>false</tt> en otro
     *         caso.
     */
    public boolean iteradorValido() {
        return iterador != null;
        // Aquí va su código.
    }
}
