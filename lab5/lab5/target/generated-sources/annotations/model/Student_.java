package model;

import abstraction.AbstractEntity_;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Exam;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-10-28T03:02:01")
@StaticMetamodel(Student.class)
public class Student_ extends AbstractEntity_ {

    public static volatile ListAttribute<Student, Exam> exams;
    public static volatile SingularAttribute<Student, String> name;

}