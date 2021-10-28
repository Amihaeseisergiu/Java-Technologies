package tests;

import java.time.LocalTime;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import org.junit.Rule;
import org.junit.Test;
import model.Exam;
import static org.junit.Assert.assertEquals;

public class ExamTest {
    
    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.withUnit("integration-test");
    
    @Test
    public void testCRUD() {
        this.provider.begin();
        this.provider.em().persist(new Exam("Foo", LocalTime.now(), 10));
        this.provider.em().persist(new Exam("Bar", LocalTime.now(), 20));
        this.provider.em().persist(new Exam("Baz", LocalTime.now(), 20));

        List<Exam> resultList = this.provider.em().createQuery("SELECT e FROM Exam e", Exam.class).getResultList();

        assertEquals(3, resultList.size());

        for (Exam resultExam : resultList) {
            assertNotNull(resultExam.getId());
        }
        
        resultList.get(0).setName("Foo2");
        
        resultList = this.provider.em().createQuery("SELECT e FROM Exam e", Exam.class).getResultList();
        assertEquals(resultList.get(0).getName(), "Foo2");
        
        this.provider.em().remove(resultList.get(0));
        resultList = this.provider.em().createQuery("SELECT e FROM Exam e", Exam.class).getResultList();
        
        assertEquals(2, resultList.size());

        this.provider.commit();
    }
}
