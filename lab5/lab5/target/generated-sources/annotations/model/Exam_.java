package model;

import abstraction.AbstractEntity_;
import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Student;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-10-28T03:02:01")
@StaticMetamodel(Exam.class)
public class Exam_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Exam, Integer> duration;
    public static volatile SingularAttribute<Exam, LocalTime> startingTime;
    public static volatile SingularAttribute<Exam, String> name;
    public static volatile ListAttribute<Exam, Student> students;

}