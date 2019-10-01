/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializableejemplo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 */
public class SerializableManager {
    
    /**
     * Método que serializará myLibrary para guardarla en el archivo f.
     * @param myLibrary objeto Library que es un arraylist de libros.
     * @param f archivo donde se guardarán los datos.
     * @throws FileNotFoundException 
     * @throws IOException
     */
    public static void serialize(Library myLibrary, File f) 
            throws FileNotFoundException, IOException{
        ObjectOutputStream oos =
            new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(myLibrary);
        oos.close();
    }
    
    /**
     * Método con la misma función que el anterior, pero recibe un arraylist
     * de libros en vez de un objeto Library.
     * @param library arraylist de libros.
     * @param f archivo donde se guardarán los datos.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void serialize(List<Book> library, File f) 
            throws FileNotFoundException, IOException{
        Library myLibrary = new Library();
        myLibrary.setLibrary(library);
        serialize(myLibrary, f);
    }
    
    /**
     * Método que leerá el fichero para ir leyendo los objetos.
     * @param f archivo donde están guardados los datos.
     * @return devuelve un objeto Library.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Library unserialize(File f) 
            throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream ois =
            new ObjectInputStream(new FileInputStream(f));
        Library myLibrary = (Library)ois.readObject();
        ois.close();
        return myLibrary;
    }
    
    /**
     * Método con la misma función que el anterior, pero devuelve un arraylist
     * de libros en vez de un objeto Library.
     * @param f archivo donde están guardados los datos.
     * @return devuelve un arraylist de libros.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static List<Book> unserializeList(File f)
            throws IOException, ClassNotFoundException{
        return unserialize(f).getLibrary();
    }
}







