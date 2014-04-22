package restBehaveTest;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.testng.Assert;
import restBehaveTest.types.Book;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by jgclingenpeel on 4/20/14.
 */
public class BookFunctionalSteps {

    private final String DOMAIN = "http://localhost:9998/book";
    private final String USER_AGENT = "Mozilla/5.0";

    public BookFunctionalSteps() {

    }

    public String createBook(String title, String author) throws Exception {
        URL obj = new URL(DOMAIN);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        StringBuilder paramBuilder = new StringBuilder();
        if(title != null && !title.equals("")) {
            paramBuilder = paramBuilder.append("title=").append(title);
        }
        if(author != null && !author.equals("")) {
            if(paramBuilder.length() > 0) {
                paramBuilder = paramBuilder.append("&");
            }
            paramBuilder = paramBuilder.append("author=").append(author);
        }

        String urlParameters = paramBuilder.toString();

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public String getBook(String title) throws Exception {
        String url = DOMAIN + "?title=" + title;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private void createShouldThrowException() throws Exception {
        try {
            serverResponse = createBook(book.getTitle(), book.getAuthor());
            Assert.fail("Expected to get a 400 message back from the server.");
        } catch (IOException ioException) {
            Assert.assertTrue(ioException.getMessage().contains("400"), "Expected to get a 400 message back from the server.");
        }
    }

    private String serverResponse;
    private Book book;

    @Given("a book with no title or author")
    public void theBookIsCreated() {
        book = new Book();
    }

    @Given("a book: $title by $author")
    public void theBookIsCreated(String title, String author) {
        book = new Book(title, author);
    }

    @When("the title is null")
    public void theTitleIsNull() {
        book.setTitle("");
    }

    @When("the author is null")
    public void theAuthorIsNull() {
        book.setAuthor("");
    }

    @When("the title is $title")
    public void theTitleIs(String title) {
        this.book.setTitle(title);
    }

    @When("the author is $author")
    public void theAuthorIs(String author) {
        this.book.setAuthor(author);
    }

    @Then("the book has this error: $errors")
    public void theBookHasTheseErrors(String errors) throws Exception {
        createShouldThrowException();
    }

    @Then("the book is not valid")
    public void theBookIsNotValid() throws Exception {
        createShouldThrowException();
    }

    @Then("the book is valid")
    public void theBookIsValid() throws Exception {
        serverResponse = createBook(book.getTitle(), book.getAuthor());
        assertThat(serverResponse,
                equalTo("{\"author\":\"" + book.getAuthor() + "\",\"title\":\"" + book.getTitle() + "\"}"));
    }
}
