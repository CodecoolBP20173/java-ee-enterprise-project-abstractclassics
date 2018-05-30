import com.codecool.labourent.model.UserDetail;

import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailTest {

    @Test
    void getAge() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = Calendar.getInstance().getTime();
        int expectedAge = 20; //"2018-05-29"

        try {
            birthDate = sdf.parse("1997-07-21");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        UserDetail userDetail = new UserDetail();
        userDetail.setDateOfBirth(birthDate);

        assertEquals(expectedAge, userDetail.getAge());
    }

}