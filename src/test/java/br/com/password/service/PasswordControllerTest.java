package br.com.password.service;

import br.com.password.service.api.request.PasswordRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PasswordControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void valid() throws Exception {

		PasswordRequest request = new PasswordRequest();
		request.setPassword("AbTp9!fok");

		mockMvc.perform(
				post("/password")
						.content(new ObjectMapper().writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("true"));

	}

	@Test
	public void notValid() throws Exception {

		PasswordRequest request = new PasswordRequest();
		request.setPassword("AbTp9 fok");

		mockMvc.perform(
				post("/password")
						.content(new ObjectMapper().writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("false"));

	}

	@Test
	public void notValidNullRequest() throws Exception {

		mockMvc.perform(
				post("/password")
						.content(new ObjectMapper().writeValueAsString(null))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andExpect(status().is4xxClientError());

	}

	@Test
	public void notValidNullPasswordRequest() throws Exception {

		mockMvc.perform(
				post("/password")
						.content(new ObjectMapper().writeValueAsString(new PasswordRequest()))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("false"));

	}

	@Test
	public void notValidEmptyPasswordRequest() throws Exception {

		PasswordRequest request = new PasswordRequest();
		request.setPassword("");

		mockMvc.perform(
				post("/password")
						.content(new ObjectMapper().writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("false"));

	}

	@Test
	public void notValidSpacePasswordRequest() throws Exception {

		PasswordRequest request = new PasswordRequest();
		request.setPassword(" ");

		mockMvc.perform(
				post("/password")
						.content(new ObjectMapper().writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("false"));

	}

	@Test
	public void notValidCharDuplicated() throws Exception {

		PasswordRequest request = new PasswordRequest();
		request.setPassword("AbTp9!foA");

		mockMvc.perform(
				post("/password")
						.content(new ObjectMapper().writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("false"));

	}

}
