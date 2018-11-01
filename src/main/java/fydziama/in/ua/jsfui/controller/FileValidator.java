package fydziama.in.ua.jsfui.controller;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.util.Scanner;

@FacesValidator(value="FileValidator")
public class FileValidator implements Validator{
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Part file = (Part) value;
        String text = "";

        try {
            InputStream is = file.getInputStream();
            text = new Scanner(is).useDelimiter("\\A").next();
            // Do not accept an upload unless it contains the string
            // JSR-344
        } catch (Exception ex) {
            throw new ValidatorException(new FacesMessage("Invalid file"), ex);
        }
        if (!text.contains("JSR-344")) {
            throw new ValidatorException(new FacesMessage("Invalid file.  File must contain special string"));
        }

    }
}
