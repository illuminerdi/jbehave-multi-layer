package restBehaveTest.types;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

public class BookException extends Exception {
    private static final long serialVersionUID = 1L;

    private BookErrors bookErrors;

    public BookException() {}

    public BookException(BookErrors errors) {
        super();
        this.bookErrors = errors;
    }

    public BookErrors getBookErrors() {
        return this.bookErrors;
    }
}
