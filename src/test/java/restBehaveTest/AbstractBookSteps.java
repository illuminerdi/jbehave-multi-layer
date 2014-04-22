package restBehaveTest;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import restBehaveTest.types.Book;

/**
 * Created by jgclingenpeel on 4/21/14.
 */
public class AbstractBookSteps {

    protected Book book;

    @Given("a book with no title or author")
    public void theBookIsCreated() {
        book = new Book();
    }

    @Given("a book: $title by $author")
    public void theBookIsCreated(String title, String author) {
        book = new Book(title, author);
    }

    @When("the title is $title")
    public void theTitleIs(String title) {
        this.book.setTitle(title);
    }

    @When("the author is $author")
    public void theAuthorIs(String author) {
        this.book.setAuthor(author);
    }

}
