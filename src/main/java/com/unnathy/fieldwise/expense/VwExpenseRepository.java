package com.unnathy.fieldwise.expense;

import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VwExpenseRepository extends ListPagingAndSortingRepository<VwExpense, Long> {
}
