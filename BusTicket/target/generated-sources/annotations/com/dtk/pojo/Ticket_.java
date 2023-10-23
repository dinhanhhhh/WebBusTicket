package com.dtk.pojo;

import com.dtk.pojo.TicketDetail;
import com.dtk.pojo.Trip;
import com.dtk.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-22T16:17:51")
@StaticMetamodel(Ticket.class)
public class Ticket_ { 

    public static volatile SingularAttribute<Ticket, Date> bookDate;
    public static volatile SingularAttribute<Ticket, Trip> idTrip;
    public static volatile SingularAttribute<Ticket, String> paymentMethod;
    public static volatile SingularAttribute<Ticket, String> statusTicket;
    public static volatile SingularAttribute<Ticket, Integer> id;
    public static volatile SingularAttribute<Ticket, User> idCustomerNew;
    public static volatile SingularAttribute<Ticket, Date> paymentDate;
    public static volatile SingularAttribute<Ticket, String> paymentContent;
    public static volatile SingularAttribute<Ticket, User> idUserLogin;
    public static volatile SetAttribute<Ticket, TicketDetail> ticketDetailSet;

}