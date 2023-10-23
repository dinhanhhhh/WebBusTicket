package com.dtk.pojo;

import com.dtk.pojo.Coach;
import com.dtk.pojo.TicketDetail;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-22T16:17:51")
@StaticMetamodel(Seat.class)
public class Seat_ { 

    public static volatile SingularAttribute<Seat, String> name;
    public static volatile SingularAttribute<Seat, Coach> idCoach;
    public static volatile SingularAttribute<Seat, Integer> id;
    public static volatile SetAttribute<Seat, TicketDetail> ticketDetailSet;

}