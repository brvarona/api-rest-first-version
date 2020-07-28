package com.cashonline.restapi.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.cashonline.entity.User;
import com.cashonline.repository.UserRepository;

/**
 * 
 * @author Braian Varona
 *
 */

@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
    private TestEntityManager testEntityManager;
	
	@Autowired
	private UserRepository userRepository;

	@Test
	public void whenInitialized_thenFindsById() {
		User user = new User("juanperez@gmail.com", "Juan", "Perez");
        user = testEntityManager.persist(user);
        
		User item = userRepository.findById(user.getId()).get();		
		assertEquals("Juan", item.getFirstName());
		assertEquals("Perez", item.getLastName());
		assertEquals("juanperez@gmail.com", item.getEmail());

	}

}
