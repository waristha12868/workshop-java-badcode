package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterBusinessTest {

    @Test
    @DisplayName("First name ของ speaker มีค่าเป็น null")
    public void case01() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Exception exception = assertThrows(ArgumentNullException.class, () ->
                registerBusiness.register(null, new Speaker())
        );
        assertEquals("First name is required.", exception.getMessage());
    }

    @Test
    @DisplayName("Last name ของ speaker มีค่าเป็น null")
    public void case02() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("FName");
        Exception exception = assertThrows(ArgumentNullException.class, () ->
                registerBusiness.register(null, speaker)
        );
        assertEquals("Last name is required.", exception.getMessage());
    }

    @Test
    @DisplayName("Email ของ speaker มีค่าเป็น null")
    public void case03() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("FName");
        speaker.setLastName("LName");
        Exception exception = assertThrows(ArgumentNullException.class, () ->
                registerBusiness.register(null, speaker)
        );
        assertEquals("Email is required.", exception.getMessage());
    }

    @Test
    @DisplayName("Domain ของ email ไม่ถูกต้อง")
    public void case04() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("FName");
        speaker.setLastName("LName");
        speaker.setEmail("FName.gmail.com");
        Exception exception = assertThrows(DomainEmailInvalidException.class, () ->
                registerBusiness.register(null, speaker)
        );
        assertNull(exception.getMessage());
    }

    @Test
    @DisplayName("Domain ของ email ไม่ support")
    public void case05() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("FName");
        speaker.setLastName("LName");
        speaker.setEmail("FName@fail.com");
        Exception exception = assertThrows(SpeakerDoesntMeetRequirementsException.class, () ->
                registerBusiness.register(null, speaker)
        );
        assertEquals("Speaker doesn't meet our standard rules.", exception.getMessage());
    }
    
    @Test
    @DisplayName("ไม่สามารถบันทึก speaker ลง database ได้")
    public void case06() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("FName");
        speaker.setLastName("LName");
        speaker.setEmail("FName@gmail.com");
        Exception exception = assertThrows(SaveSpeakerException.class, () ->
                registerBusiness.register(null, speaker)
        );
        assertEquals("Can't save a speaker.", exception.getMessage());
    }
}
