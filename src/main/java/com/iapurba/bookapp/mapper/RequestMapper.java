package com.iapurba.bookapp.mapper;

public interface RequestMapper<E, D> {
    D toRequestDto(E entity);
}
