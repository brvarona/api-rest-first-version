package com.cashonline.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cashonline.entity.Loan;
import com.cashonline.exception.ResourceNotFoundException;
import com.cashonline.repository.LoanRepository;
import com.cashonline.repository.UserRepository;
import com.cashonline.service.LoanService;

/**
 * LoanService implementation
 * 
 * @author Braian Varona
 *
 */

@Service
public class LoanServiceImpl implements LoanService {

	private Logger LOG = LoggerFactory.getLogger(LoanServiceImpl.class);

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
     * Gets loans by user id.
     *
     * @param paging with page and size 
     * @param userId the user id

     * @return the list of loans
	 * @throws ResourceNotFoundException 
     */
	public List<Loan> getLoans(Pageable paging, Long userId) throws ResourceNotFoundException {
        LOG.info("Getting the loans with user id:" + userId);
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
