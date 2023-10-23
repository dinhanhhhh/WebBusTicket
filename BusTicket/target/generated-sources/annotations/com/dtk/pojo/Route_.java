package com.dtk.pojo;

import com.dtk.pojo.Trip;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-22T16:17:51")
@StaticMetamodel(Route.class)
public class Route_ { 

    public static volatile SingularAttribute<Route, String> start;
    public static volatile SingularAttribute<Route, String> end;
    public static volatile SetAttribute<Route, Trip> tripSet;
    public static volatile SingularAttribute<Route, Integer> id;

}