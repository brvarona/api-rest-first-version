package com.braianvarona.service;

import java.util.List;
import org.springframework.data.domain.Pageable;

import com.braianvarona.entity.Loan;

/**
 * Interface LoanService
 * 
 * @author Braian Varona
 *
 */

public interface LoanService {

	public List<Loan> getLoans(Pageable paging, Long userId);

}
