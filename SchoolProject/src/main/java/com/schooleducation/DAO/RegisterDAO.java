package com.schooleducation.DAO;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.schooleducation.model.Register;

@Repository
@Transactional
public class RegisterDAO {
	@PersistenceContext 
	private EntityManager em;
    public String registerUser(Register register) {
	        //em.getTransaction().commit();
	        List<Register> check= validateLogin(register.getUsername(),register.getPassword());
	        if(check.size()==1)
	        {
	        	System.out.println("User details registered");
	        	return "Already Register";
	        }
	        else
	        {
	        	em.persist(register);
	        	return "Successfully register";
	        	
	        }
	    } 
	    // Retrieves all the guests:
	    public List<Register> getAllGuests() {
	        TypedQuery<Register> query = em.createQuery(
	            "from Register", Register.class);
	        return query.getResultList();
	    }
	    public List<Register> validateLogin(String username, String password)
	    {
	    	Query query = em.createQuery(
		            "FROM Register where username=:username and password=:password");
	    	query.setParameter("username", username);
	    	query.setParameter("password", password);	
		        return query.getResultList();
	    
	    }
}
