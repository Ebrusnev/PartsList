package com.partsList.repository;

import com.partsList.model.CompPart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompPartRepository extends PagingAndSortingRepository<CompPart, Integer> {

    Page<CompPart> findByNameContaining(String nameForSearch, Pageable pageRequest);

    Page<CompPart> findByIsMustHaveAndNameContaining(Boolean required, String nameForSearch, Pageable pageRequest);

}
