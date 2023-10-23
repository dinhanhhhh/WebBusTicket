package com.dtk.pojo;

import com.dtk.pojo.Feedback;
import com.dtk.pojo.Ticket;
import com.dtk.pojo.Trip;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-22T16:17:51")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Date> birthday;
    public static volatile SingularAttribute<User, String> address;
    public static volatile SingularAttribute<User, String> gender;
    public static volatile SetAttribute<User, Feedback> feedbackSet;
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile SetAttribute<User, Trip> tripSet;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SetAttribute<User, Ticket> ticketSet1;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SetAttribute<User, Ticket> ticketSet;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> fullname;
    public static volatile SingularAttribute<User, String> userRole;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}