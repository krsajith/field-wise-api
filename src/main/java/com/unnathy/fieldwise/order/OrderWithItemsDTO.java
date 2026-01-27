package com.unnathy.fieldwise.order;

import com.unnathy.fieldwise.orderitem.OrderItemDTO;
import java.util.List;
import lombok.Data;

@Data
public class OrderWithItemsDTO {
    private OrderDTO order;
    private List<OrderItemDTO> items;
}
