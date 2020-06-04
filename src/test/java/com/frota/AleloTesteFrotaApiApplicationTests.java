package com.frota;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frota.entity.Vehicle;
import com.frota.resource.VehicleResource;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive")
@AutoConfigureWebTestClient
@AutoConfigureMockMvc
class AleloTesteFrotaApiApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private VehicleResource vehicleResource;

	/*@Test
	void contextLoads() {
	}*/
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(vehicleResource).build();
	}
	
	//@Test
	public void criarTest() throws Exception {
		this.mockMvc.perform(post("/vehicle"))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print())
		.andReturn();
	}
	
	//@Test
		public void payloadOkTest() throws Exception{
			String payload = construirPayload();
			this.mockMvc.perform(post("/vehicle")
					.contentType(MediaType.APPLICATION_JSON)
					.content(payload))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			//.andExpect(content().string("merchantRequestId"))
			//.andExpect(content().string("wfqMessageId"))
			.andDo(print())
			.andReturn();
		}
		
		@Test
		public void payloadErrorTest() throws Exception{
			String payload = construirPayload();
			this.mockMvc.perform(post("/vehicle")
					.contentType(MediaType.APPLICATION_JSON)
					.content(payload))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andReturn();
		}


		private String construirPayload() throws IOException  {
			ObjectMapper mapper = new ObjectMapper();
			
			File json = new File(
			        getClass().getClassLoader().getResource("payload_atualizar.json").getFile());
			Vehicle vehicle = mapper.readValue(json, Vehicle.class);
			return mapper.writeValueAsString(vehicle);
		}

}
