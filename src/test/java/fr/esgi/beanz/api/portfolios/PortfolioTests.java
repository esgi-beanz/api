package fr.esgi.beanz.api.portfolios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PortfolioTests {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void fetchPortfolioWithoutAuthShouldThrow403Error() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/portoflios/0")).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isForbidden());
  }

  @Test
  void fetchPortfoliosWithoutAuthShouldThrow403Error() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/portoflios")).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isForbidden());
  }

}
