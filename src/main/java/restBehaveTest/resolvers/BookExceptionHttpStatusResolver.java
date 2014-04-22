package restBehaveTest.resolvers;

import restBehaveTest.types.BookException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BookExceptionHttpStatusResolver implements ExceptionMapper<BookException> {

    @Override
    public Response toResponse(BookException be) {
        Response.Status httpStatus = Response.Status.BAD_REQUEST;

        return Response.status(httpStatus).entity(be.getBookErrors())
                .build();
    }
}
