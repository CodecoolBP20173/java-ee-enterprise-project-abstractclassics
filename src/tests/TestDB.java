import com.codecool.labourent.model.UserAccount;
import com.codecool.labourent.model.Gender;
import com.codecool.labourent.model.UserDetail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDB {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("labourentPU");
        EntityManager em = emf.createEntityManager();

        populateDb(em);

        em.close();
        emf.close();
    }

    private static void populateDb(EntityManager em) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = Calendar.getInstance().getTime();

        UserAccount userAccount = new UserAccount();
        userAccount.setEmail("sss");
        userAccount.setPassword("sss");
        userAccount.setUserName("sssssss");

        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName("Aladar");
        userDetail.setLastName("Kósa");
        userDetail.setDateOfBirth(birthDate);
        userDetail.setIntroductionText("Hello World!!!!");
        userDetail.setGender(Gender.MALE);
        userDetail.setCity("Budapest");
        userDetail.setPhoneNumber("65454546554");
        userDetail.setImgUrl("httpdasdasdsad");
        userDetail.setUserAccount(userAccount);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(userAccount);
        em.persist(userDetail);
        transaction.commit();
        System.out.println("Aladar saved.");

    }
}
