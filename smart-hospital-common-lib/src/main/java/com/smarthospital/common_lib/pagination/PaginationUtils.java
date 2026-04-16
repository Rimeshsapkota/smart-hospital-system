package com.smarthospital.common_lib.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * Page Number (number of page to retrieve data)
 * Page Size (size of data that will be in a page)
 * Sorting Direction (ordering data as DESCENDING or ASCENDING)
 * Sorting Field (sorting data based on a field of database)
 */
@Component
public class PaginationUtils {
    public static Pageable getPageable(PaginationRequest request) {
        return PageRequest.of(
                request.getPage() - 1, // Spring is 0-based
                request.getSize(),
                Sort.by(request.getDirection(), request.getSortField())
        );
    }
}
