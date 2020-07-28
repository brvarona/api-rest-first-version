package com.cashonline.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cashonline.entity.Loan;
import com.cashonline.exception.ResourceNotFoundException;
import com.cashonline.service.LoanService;
import com.cashonline.service.impl.UserServiceImpl;

/**
 * LoanRestController implementation
 * 
 * @author Braian Varona
 *
 */

@RestController
public class LoanRestController {	
	
    private Logger log = LoggerFactory.getLogger(LoanRestController.class);

	@Autowired
	private LoanService loanService;
	
	/**
     * Get loans by user id.
     *
     * @param paging with page and size 
     * @param id the user id
     *
     * @return response that contains a list of loans and paging results
	 * @throws ResourceNotFoundException 
     */
	@GetMapping("/loans")
	public ResponseEntity<List<Loan>> getLoans(Pageable paging, @RequestParam(name = "user_id", required = false) Long userId) throws ResourceNotFoundException { 
		log.info("GET - /loans?&page=" + paging.getPageNumber() + "&size=" + paging.getPageSize() + "userId=" + userId);
		List<Loan> list = loanService.getLoans(paging, userId);		
        return new ResponseEntity<List<Loan>>(list, new HttpHeaders(), HttpStatus.OK); 
    }

}
