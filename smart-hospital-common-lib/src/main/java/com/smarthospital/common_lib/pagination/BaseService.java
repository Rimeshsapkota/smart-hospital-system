package com.smarthospital.common_lib.pagination;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseService<E, D, ID> {

    protected final JpaRepository<E, ID> repository;
    protected final GenericMapper<E, D> mapper;

    protected BaseService(JpaRepository<E, ID> repository,
                          GenericMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PageResult<D> findAll(PaginationRequest request) {
        Pageable pageable = PaginationUtils.getPageable(request);
        Page<E> entities = repository.findAll(pageable);
        List<D> dtoList = entities
                .stream()
                .map(mapper::toDto)
                .toList();
        return new PageResult<>(
                dtoList,
                entities.getTotalElements(),
                entities.getTotalPages(),
                entities.getSize(),
                entities.getNumber(),
                entities.isEmpty()
        );
    }
}