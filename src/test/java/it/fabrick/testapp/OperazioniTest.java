package it.fabrick.testapp;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FabrickTestApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class OperazioniTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Before
	public void setup()
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void A_testLetturaSaldo() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/operazioni/saldo/14537780")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	public void B_testListaTransazioni() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/operazioni/transazioni/14537780?fromAccountingDate=2019-01-01&toAccountingDate=2019-12-01")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	public void C_testBonifico() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/api/operazioni/bonifico/14537780")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());
	}
	
	String jsonData = ""
			+ "{"
			+ "    \"creditor\": {"
			+ "    \"name\": \"John Doe\","
			+ "    \"account\": {"
			+ "      \"accountCode\": \"IT23A0336844430152923804660\","
			+ "      \"bicCode\": \"SELBIT2BXXX\""
			+ "    },"
			+ "    \"address\": {"
			+ "      \"address\": null,"
			+ "      \"city\": null,"
			+ "      \"countryCode\": null"
			+ "    }"
			+ "  },"
			+ "  \"executionDate\": \"2019-04-01\","
			+ "  \"uri\": \"REMITTANCE_INFORMATION\","
			+ "  \"description\": \"Payment invoice 75/2017\","
			+ "  \"amount\": 800,"
			+ "  \"currency\": \"EUR\","
			+ "  \"isUrgent\": false,"
			+ "  \"isInstant\": false,"
			+ "  \"feeType\": \"SHA\","
			+ "  \"feeAccountId\": \"14537780\","
			+ "  \"taxRelief\": {"
			+ "    \"taxReliefId\": \"L449\","
			+ "    \"isCondoUpgrade\": false,"
			+ "    \"creditorFiscalCode\": \"56258745832\","
			+ "    \"beneficiaryType\": \"NATURAL_PERSON\","
			+ "    \"naturalPersonBeneficiary\": {"
			+ "      \"fiscalCode1\": \"MRLFNC81L04A859L\","
			+ "      \"fiscalCode2\": null,"
			+ "      \"fiscalCode3\": null,"
			+ "      \"fiscalCode4\": null,"
			+ "      \"fiscalCode5\": null"
			+ "    },"
			+ "    \"legalPersonBeneficiary\": {"
			+ "      \"fiscalCode\": null,"
			+ "      \"legalRepresentativeFiscalCode\": null"
			+ "    }"
			+ "  }"
			+ "}";

}
