/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serializableejemplo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 */

public class Library implements java.io.Serializable {
    
    private List<Book> library = new ArrayList<>();

    /**
     * @return the library
     */
    public List<Book> getLibrary() {
        return library;
    }

    /**
     * @param library the library to set
     */
    public void setLibrary(List<Book> library) {
        this.library = library;
    }

}
