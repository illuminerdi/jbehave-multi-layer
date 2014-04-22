package restBehaveTest;

import restBehaveTest.types.Book;
import restBehaveTest.types.BookErrors;
import restBehaveTest.types.BookException;
import restBehaveTest.types.BookStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/book")
public class BookService {
    private static final Logger logger = Logger.getLogger(BookService.class.getCanonicalName());

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Book getBook(@QueryParam("title") String title) {
        return BookStore.get(title);
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createBook(@FormParam("title") String title, @FormParam("author") String author)
            throws BookException {
        logger.warning(title + ":" + author);
        Book newBook = new Book(title, author);
        System.out.println(newBook);
        if(!newBook.isValid()) {
            BookErrors be = new BookErrors();
            be.setErrors(newBook.errorMessages());
            throw new BookException(be);
        }
        BookStore.add(newBook);
        return Response.status(Response.Status.OK).entity(newBook).build();
    }
}
