package com.braianvarona.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.braianvarona.entity.Loan;
import com.braianvarona.exception.ResourceNotFoundException;
import com.braianvarona.repository.LoanRepository;
import com.braianvarona.repository.UserRepository;
import com.braianvarona.service.LoanService;

/**
 * LoanService implementation
 * 
 * @author Braian Varona
 *
 */

@Service
public class LoanServiceImpl implements LoanService {

	private Logger log = LoggerFactory.getLogger(LoanServiceImpl.class);

    private LoanRepository loanRepository;
    private UserRepository userRepository;

    @Autowired
    public void setLoanRepository(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	    
	/**
     * Get loans by user id.
     *
     * @param paging with page and size 
     * @param userId the user id

     * @return the list of loans
	 * @throws ResourceNotFoundException 
     */
    @Override
	public List<Loan> getLoans(Pageable paging, Long userId) {
		log.info("Getting the loans with user id:" + userId);
        Page<Loan> pagedResult = null;

		if (userId != null) {     
			userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Loan", "user_id", userId));
			pagedResult = loanRepository.findByUserId(userId, paging);
		} else
			pagedResult = loanRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Loan>();
        }
	}

}
