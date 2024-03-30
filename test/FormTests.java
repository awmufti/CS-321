package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import Shared.Address;
import Shared.Form;
import java.util.Date;
/*
 * Below are all the possible assertions needed for each form field, and will likewise be used during the testing phase
 */
public class FormTest {
    /*
     * This assert is to make sure the form field is an int
     */
    @Test
    public void isIntegerValidation(String value) {
        assertEquals(true,value.matches("-?\\d+(\\.\\d+)?"));
    }
    /*
     * This assert is to make sure that the int field is positive for age and other fields
     */
    @Test
    public void isPositiveValidation(int value) {
        assertEquals(true, value>0);
    }

    /*
     * This assert is to make sure that the email String field is in email format
     */
    @Test
    public void isNotEmptyValidation(String value) {
        assertEquals(true, value.matches("^[^@]+@[^@]+\\.[^@]+$"));
    }

    /*
     * This assert is to make sure that the birthday is not over 150 years ago
     */
    @Test
    public void birthdateValidation(Date birthdate) {
        Date date = new Date();
        date.setYear(1874);
        assertEquals(true, birthdate.compareTo(date)>0);
    }


}