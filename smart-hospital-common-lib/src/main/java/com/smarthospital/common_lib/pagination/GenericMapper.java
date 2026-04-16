package com.smarthospital.common_lib.pagination;

public interface GenericMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
}
