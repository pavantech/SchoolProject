package com.schooleducation.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.schooleducation.model.Register;

@Repository
@Transactional
public class RegisterDAO {
	@PersistenceContext 
	private EntityManager em;
    public void persist(Register register) {
	        em.persist(register);
	        //em.getTransaction().commit();
	    }
	 
	    // Retrieves all the guests:
	    public List<Register> getAllGuests() {
	        TypedQuery<Register> query = em.createQuery(
	            "SELECT * FROM user", Register.class);
	        return query.getResultList();
	    }
}
