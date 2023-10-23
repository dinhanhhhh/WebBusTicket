package com.dtk.pojo;

import com.dtk.pojo.Seat;
import com.dtk.pojo.Trip;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-22T16:17:51")
@StaticMetamodel(Coach.class)
public class Coach_ { 

    public static volatile SingularAttribute<Coach, String> name;
    public static volatile SingularAttribute<Coach, String> licensePlates;
    public static volatile SetAttribute<Coach, Trip> tripSet;
    public static volatile SingularAttribute<Coach, Integer> id;
    public static volatile SingularAttribute<Coach, Integer> totalseat;
    public static volatile SetAttribute<Coach, Seat> seatSet;

}