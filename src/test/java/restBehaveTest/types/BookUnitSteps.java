package restBehaveTest.types;


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.testng.Assert;
import restBehaveTest.AbstractBookSteps;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BookUnitSteps extends AbstractBookSteps {

    @Then("the book has this error: $errors")
    public void theBookHasTheseErrors(String errors) {
        assertThat(Arrays.toString(book.errors()), equalTo(errors));
    }

    @When("the title is null")
    public void theTitleIsNull() {
        book.setTitle(null);
    }

    @When("the author is null")
    public void theAuthorIsNull() {
        book.setAuthor(null);
    }

    @Then("the book is not valid")
    public void theBookIsNotValid() {
        Assert.assertFalse(book.isValid(), "Expected book to be invalid");
    }

    @Then("the book is valid")
    public void theBookIsValid() {
        Assert.assertTrue(book.isValid(), "Expected book to be valid");
    }
}
