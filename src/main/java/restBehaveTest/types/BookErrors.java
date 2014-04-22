package restBehaveTest.types;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "bookerrors")
public class BookErrors {
    private List<String> errors = new ArrayList<String>();

    public BookErrors() {}

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @XmlElement
    public List<String> getErrors() {
        return errors;
    }
}
