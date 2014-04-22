package restBehaveTest.types;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "book")
public class Book {
    private String title;
    private String author;

    public Book() {}

    public Book(String title, String author) {

        this.title = title;
        this.author = author;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isValid() {
        return getAuthor() != null && getTitle() != null;
    }

    public String[] errors() {
        List<String> errorMessages = generateErrors();
        return errorMessages.toArray(new String[errorMessages.size()]);
    }

    public List<String> errorMessages() {
        return generateErrors();
    }

    private List<String> generateErrors() {
        List<String> errorMessages = new ArrayList<String>();
        if(getAuthor() == null) {
            errorMessages.add("Author must not be null.");
        }
        if(getTitle() == null) {
            errorMessages.add("Title must not be null.");
        }
        return errorMessages;
    }

    @Override
    public String toString() {
        return "Book: {title: " + getTitle() + "; author: " + getAuthor() + "}";
    }
}
