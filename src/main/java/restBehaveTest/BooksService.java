package restBehaveTest;

import restBehaveTest.types.Book;
import restBehaveTest.types.BookStore;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/books")
public class BooksService {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Book[] getBooks() {
        return BookStore.getAll();
    }
}
