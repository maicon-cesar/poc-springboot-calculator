package br.com.calculator.javaspringcalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import br.com.calculator.javaspringcalculator.identities.Operation;
import br.com.calculator.javaspringcalculator.services.CalculatorServices;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class JavaSpringCalculatorApplicationTests {

	@TestConfiguration
	static class CalculatorTestConfigurarion {
		@Bean
		public Calculator calc() {
			return new Calculator();
		}	
	}
	
	@Autowired
	Calculator calc;

	@Test
	public void calculatorTest() {
		Double result;
		
		result = calc.add(5.0, 7.0);
		Assertions.assertEquals(result, 12.0);
	
		result = calc.sub(13.0, 9.0);
		Assertions.assertEquals(result, 4.0);
		
		result = calc.mul(3.0, 9.0);
		Assertions.assertEquals(result, 27.0);
		
		result = calc.div(9.0, 3.0);
		Assertions.assertEquals(result, 3.0);

		result = calc.div(5.0, 0.0);
		Assertions.assertEquals(result, 0.0);
	}
	
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private CalculatorServices calculatorServices;
  
    @Test
    void Calculator1TestFail() throws Exception {  	//Whithout parameters
	    mockMvc.perform(get("/calculator1"))
	            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
    @Test
    public void Calculator1TestAddCalc() throws Exception {   	
    	Double result = 10.0;
        Mockito.when(calculatorServices.executeCalculation(2.0, 8.0, "add")).thenReturn(result);
        
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("num1", "2.0");
		requestParams.add("num2", "8.0");
		requestParams.add("oper", "add");
        
   		mockMvc.perform(get("/calculator1").params(requestParams))
        		.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("2.0 add 8.0 = 10.0")));
    }
    
    @Test
    public void Calculator1TestSubCalc() throws Exception {   	
    	Double result = 10.0;
        Mockito.when(calculatorServices.executeCalculation(15.0, 5.0, "sub")).thenReturn(result);
        
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("num1", "15.0");
		requestParams.add("num2", "5.0");
		requestParams.add("oper", "sub");
        
   		mockMvc.perform(get("/calculator1").params(requestParams))
        		.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("15.0 sub 5.0 = 10.0")));
    }    
    
    @Test
    public void Calculator1TestMulCalc() throws Exception {   	
    	Double result = 21.0;
        Mockito.when(calculatorServices.executeCalculation(3.0, 7.0, "mul")).thenReturn(result);
        
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("num1", "3.0");
		requestParams.add("num2", "7.0");
		requestParams.add("oper", "mul");
        
   		mockMvc.perform(get("/calculator1").params(requestParams))
        		.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("3.0 mul 7.0 = 21.0")));
    }
    
    @Test
    public void Calculator1TestDivCalc() throws Exception {   	
    	Double result = 25.0;
        Mockito.when(calculatorServices.executeCalculation(50.0, 2.0, "div")).thenReturn(result);
        
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("num1", "50.0");
		requestParams.add("num2", "2.0");
		requestParams.add("oper", "div");
        
   		mockMvc.perform(get("/calculator1").params(requestParams))
        		.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("50.0 div 2.0 = 25.0")));
    }
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void Calculator2AddTest() throws Exception {
    	Operation oper = new Operation(3.0, 2.0, "add", 5.0);
    	
    	Double result = 5.0;
        Mockito.when(calculatorServices.executeCalculation(3.0, 2.0, "add")).thenReturn(result);
    	
	    mockMvc.perform(post("/calculator2")
	            .contentType("application/json")
	            .content(objectMapper.writeValueAsString(oper)))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("output").value("5.0"));
    }
   
    @Test
    void Calculator2SubTest() throws Exception {
    	Operation oper = new Operation(8.0, 5.0, "sub", 3.0);
    	
    	Double result = 3.0;
        Mockito.when(calculatorServices.executeCalculation(8.0, 5.0, "sub")).thenReturn(result);
    	
	    mockMvc.perform(post("/calculator2")
	            .contentType("application/json")
	            .content(objectMapper.writeValueAsString(oper)))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("output").value("3.0"));
    }
    
    @Test
    void Calculator2MulTest() throws Exception {
    	Operation oper = new Operation(3.0, 2.0, "mul", 6.0);
    	
    	Double result = 6.0;
        Mockito.when(calculatorServices.executeCalculation(3.0, 2.0, "mul")).thenReturn(result);
    	
	    mockMvc.perform(post("/calculator2")
	            .contentType("application/json")
	            .content(objectMapper.writeValueAsString(oper)))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("output").value("6.0"));
    }
    
    @Test
    void Calculator2DivTest() throws Exception {
    	Operation oper = new Operation(5.0, 2.0, "div", 2.5);
    	
    	Double result = 2.5;
        Mockito.when(calculatorServices.executeCalculation(5.0, 2.0, "div")).thenReturn(result);
    	
	    mockMvc.perform(post("/calculator2")
	            .contentType("application/json")
	            .content(objectMapper.writeValueAsString(oper)))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("output").value("2.5"));
    }
	
    @Test
    void CalculatorTestHistoric() throws Exception {
	    mockMvc.perform(get("/historic")
	            .contentType("application/json"))
	            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    void TestMongoDB(@Autowired MongoTemplate mongoTemplate) {
        DBObject objectToSave = BasicDBObjectBuilder.start()
            .add("key", "value")
            .get();

        mongoTemplate.save(objectToSave, "collection_test");

        assertThat(mongoTemplate.findAll(DBObject.class, "collection_test")).extracting("key")
            .containsOnly("value");
    }    
}
