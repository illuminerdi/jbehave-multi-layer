package restBehaveTest.types;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgclingenpeel on 4/20/14.
 */
public class BookStore {
    private final static List<Book> ALL_BOOKS = new ArrayList<Book>();

    public static Book[] getAll() {
        return ALL_BOOKS.toArray(new Book[ALL_BOOKS.size()]);
    }


    public static Book get(String title) {
        Book found = new Book();
        for(Book b : ALL_BOOKS) {
            if(b.getTitle().equals(title)) {
                found = b;
                break;
            }
        }
        return found;
    }

    public static void add(Book newBook) {
        ALL_BOOKS.add(newBook);
    }
}
