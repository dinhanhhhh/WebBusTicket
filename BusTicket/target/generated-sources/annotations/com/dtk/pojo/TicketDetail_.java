package com.dtk.pojo;

import com.dtk.pojo.Seat;
import com.dtk.pojo.Ticket;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-10-22T16:17:51")
@StaticMetamodel(TicketDetail.class)
public class TicketDetail_ { 

    public static volatile SingularAttribute<TicketDetail, Boolean> statusSeat;
    public static volatile SingularAttribute<TicketDetail, Ticket> idTicket;
    public static volatile SingularAttribute<TicketDetail, Long> priceSeat;
    public static volatile SingularAttribute<TicketDetail, Seat> idSeat;
    public static volatile SingularAttribute<TicketDetail, Integer> idTicketDetail;

}