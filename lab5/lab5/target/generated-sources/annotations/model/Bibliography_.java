package model;

import abstraction.AbstractEntity_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.WrittenTest;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-10-28T23:10:02")
@StaticMetamodel(Bibliography.class)
public class Bibliography_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Bibliography, WrittenTest> writtenTest;
    public static volatile SingularAttribute<Bibliography, String> name;

}