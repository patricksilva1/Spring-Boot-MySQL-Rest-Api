package dev.patricksilva.crud.models.utils;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.patricksilva.crud.models.entities.Order;
import dev.patricksilva.crud.models.entities.Role;
import dev.patricksilva.crud.models.shared.OrderDTO;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO toDto(Order order);

    Order toEntity(OrderDTO orderDTO);

    default Set<Role> map(Set<String> value) {
        return value.stream().map(roleName -> {
            Role role = new Role();
            role.setName(roleName);
            return role;
        }).collect(Collectors.toSet());
    }
}