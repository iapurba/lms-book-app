package com.iapurba.bookapp.mapper;

public interface ResponseMapper<E, D> {
    D toResponseDto (E entity);
}
