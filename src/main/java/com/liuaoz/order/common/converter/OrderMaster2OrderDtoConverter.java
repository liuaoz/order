package com.liuaoz.order.common.converter;

import com.liuaoz.order.dto.OrderDto;
import com.liuaoz.order.model.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description : TODO
 * @Author : matrix
 * @Date : created in 2018/1/6 23:54
 */
public final class OrderMaster2OrderDtoConverter {

    public static OrderDto convert(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasters) {

        return orderMasters.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
