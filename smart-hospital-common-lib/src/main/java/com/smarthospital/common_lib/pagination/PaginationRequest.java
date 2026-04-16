package com.smarthospital.common_lib.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Sort;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequest {

    @Builder.Default
    private Integer page=1;

    @Builder.Default
    private Integer size=10;

    private String sortField;

    @Builder.Default
    private Sort.Direction direction = Sort.Direction.DESC;
}
