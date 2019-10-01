/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializableejemplo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 */
public class SerializableEjemplo {

    /**
     * @param args the command line arguments
     */
    
    static final String BBDDFILE = "bbdd.bin";
    static Library myLibrary = new Library();
    static Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) {
        // TODO code application logic here
        int option = 0;
        loadBBDDXML();
        do {
            printMenu();
            try {
                option = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número como opción");
                option = 0;
            } catch (NoSuchElementException e) {
                System.out.println("Error al leer teclado");
                option = 0;
            } catch (IllegalStateException e) {
                System.out.println("Error en flujo de datos. Iniciando.");
                teclado = new Scanner(System.in);
                option = 0;
            }
            doTasks(option);
        } while (option != 5);

    }

    static void loadBBDDXML() {
        System.out.println("Cargando base de datos");
        try {
            myLibrary = SerializableManager.unserialize(new File(BBDDFILE));
            System.out.println("Base de datos cargada");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al serializar el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Un error ocurrió: " + e.getMessage());
        }
    }

    static void printMenu() {
        System.out.println("--------Menu-------");
        System.out.println("1. Para listar");
        System.out.println("2. Para insertar libro");
        System.out.println("3. Para borrar un libro");
        System.out.println("4. Para guardar la BBDD");
        System.out.println("5. Para salir");
        System.out.print("Elija una opción: ");
    }

    static void doTasks(int option) {
        switch (option) {
            case 0:
                break;
            case 1:
                listBooks();
                break;
            case 2:
                insertBook();
                break;
            case 3:
                delBook();
                break;
            case 4:
                saveBBDD();
                break;
            case 5:
                System.out.println("Saliendo de la aplicación");
                System.out.println("Guardar la base de datos antes de "
                        + "salir: 1) SI , cualquier otra tecla) No");
                try {
                    option = teclado.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Introduzca valor correcto");
                    option=5;
                }
                if (option == 1) {
                    saveBBDD();
                    option=5;
                }
                break;
            default:
                System.out.println("Elija una opción válida");
        }
    }

    static void listBooks() {
        System.out.println("-------- BOOKS --------");
        for (Book b : myLibrary.getLibrary()) {
            System.out.println(b);
        }
        System.out.println("------------------------");
    }

    static void insertBook() {
        try {
            teclado.nextLine();
            System.out.print("Inserte el título: ");
            String title = teclado.nextLine();
            System.out.print("Inserte autor: ");
            String author = teclado.nextLine();
            System.out.print("Inserte editorial: ");
            String publisher = teclado.nextLine();
            System.out.print("Inserte isbn: ");
            String isbn = teclado.nextLine();
            Book b = new Book();
            b.setAuthor(author);
            b.setName(title);
            b.setPublisher(publisher);
            b.setIsbn(isbn);
            ArrayList<Book> library = (ArrayList< Book>) myLibrary.getLibrary();
            library.add(b);
            myLibrary.setLibrary(library);
        } catch (InputMismatchException e) {
            System.out.println("Introduzca valor correcto");
        } catch (NoSuchElementException e) {
            System.out.println("Error al leer teclado");
        } catch (IllegalStateException e) {
            System.out.println("Error en flujo de datos. Iniciando.");
            teclado = new Scanner(System.in);
        }

    }

    static void delBook() {
        try {
            teclado.nextLine();
            System.out.print("Inserte el isbn del libro a borrar: ");
            String isbn = teclado.nextLine();
            ArrayList<Book> library = (ArrayList< Book>) myLibrary.getLibrary();
            int index = -1, i = -1;
            for (Book b : library) {
                i++;
                if (b.getIsbn().equals(isbn)) {
                    index = i;
                }
            }
            if (index == -1) {
                System.out.println("ISBN no encontrado");
            } else {
                library.remove(index);
                myLibrary.setLibrary(library);
            }
        } catch (InputMismatchException e) {
            System.out.println("Introduzca valor correcto");
        } catch (NoSuchElementException e) {
            System.out.println("Error al leer teclado");
        } catch (IllegalStateException e) {
            System.out.println("Error en flujo de datos. Iniciando.");
            teclado = new Scanner(System.in);
        }

    }
    

    static void saveBBDD() {
        try {
            SerializableManager.serialize(myLibrary, new File(BBDDFILE));
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }

    public static void toPrint(){
        
    }

}






