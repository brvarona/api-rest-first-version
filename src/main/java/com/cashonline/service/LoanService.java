package com.cashonline.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.cashonline.entity.Loan;
import com.cashonline.exception.ResourceNotFoundException;

/**
 * Interface LoanService
 * 
 * @author Braian Varona
 *
 */

public interface LoanService {

	public List<Loan> getLoans(Pageable paging, Long userId) throws ResourceNotFoundException ;

}
