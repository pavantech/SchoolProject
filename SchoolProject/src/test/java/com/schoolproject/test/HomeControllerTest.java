package com.schoolproject.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.schooleducation.DAO.RegisterDAO;
import com.schooleducation.controller.MainController;
import com.schooleducation.model.Register;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/mvc-dispatcher-servlet.xml","file:WebContent/WEB-INF/db-conf.xml" })
public class HomeControllerTest {
	private MockMvc mockMvc;
	@InjectMocks
    private MainController mainController;
	@Mock
    private RegisterDAO redo;
	 @Before
	    public void setup() {
	 
	        // Process mock annotations
	        MockitoAnnotations.initMocks(this);
	 
	        // Setup Spring test in standalone mode
	        this.mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
	 
	    }

    @Test
    public void testHome() throws Exception {

        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("index"));
            //.andExpect(redirectedUrl("index.jsp"));

    }
    @Test
    public void testLogin() throws Exception {

        mockMvc.perform(get("/login"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("sign-in"));
            //.andExpect(redirectedUrl("index.jsp"));

    }
    @Test
    public void testRegister() throws Exception {

        mockMvc.perform(get("/register"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("Sign-up"));
            //.andExpect(redirectedUrl("index.jsp"));

    }
    @Test
    public void testLoginForm() throws Exception {
    	// when(redo.registerUser(any(Register.class)))
         //.thenThrow(new Exception("For Testing"));

        this.mockMvc.perform(post("/request").param("username", "pavan")
            .param("password", "pavan"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("index"));
            //.andExpect(redirectedUrl("index.jsp"));

    }
    @Test
    public void testcreateRegister() throws Exception {
    	// when(redo.registerUser(any(Register.class)))
         //.thenThrow(new Exception("For Testing"));

        this.mockMvc.perform(post("/addStudent").param("username", "pavan")
        		.param("firstname", "pavan")
        		.param("lastname", "pavan")
        		.param("username", "pavan")
            .param("email", "pavan")
            .param("address", "pavan")
            .param("phone", "pavan"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("index"));
            //.andExpect(redirectedUrl("index.jsp"));

    }
}
