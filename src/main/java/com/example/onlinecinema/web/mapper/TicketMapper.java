package com.example.onlinecinema.web.mapper;

import com.example.onlinecinema.domain.ticket.Ticket;
import com.example.onlinecinema.web.DTO.TicketDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TicketMapper extends Mappable<Ticket, TicketDTO> {
}
