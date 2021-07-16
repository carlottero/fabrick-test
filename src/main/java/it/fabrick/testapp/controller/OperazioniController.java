package it.fabrick.testapp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import it.fabrick.testapp.domain.LetturaListaTransazioni;
import it.fabrick.testapp.domain.LetturaSaldo;
import it.fabrick.testapp.domain.Saldo;
import it.fabrick.testapp.domain.Transazione;
import it.fabrick.testapp.exception.AccountIdNotAllowedException;
import it.fabrick.testapp.exception.BonificoNotFoundException;
import it.fabrick.testapp.exception.DateFilterNotAllowedException;

@RestController
@RequestMapping(value="api/operazioni")
public class OperazioniController 
{
	private static final Logger logger = LoggerFactory.getLogger(OperazioniController.class);
	
	@PostMapping(value="/bonifico/{accountId}")
	public ResponseEntity<String> bonifico(@PathVariable("accountId") String AccountId) throws AccountIdNotAllowedException, BonificoNotFoundException
	{
		if(!"14537780".equals(AccountId))
			throw new AccountIdNotAllowedException("AccountId non valido.");
		
		final String uri ="https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/" + AccountId + "/payments/money-transfers";
		
		HttpHeaders headers = this.setHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
	    
	    try {
			ResponseEntity<String> response;
				response = restTemplate.exchange(
				    uri, HttpMethod.POST, entity, String.class);
			return response;
		} catch (RestClientException e) {
			//e.printStackTrace();
			throw new BonificoNotFoundException("Bonifico non disponibile.");
		}
	}
	
	@GetMapping(value="/saldo/{accountId}", produces="application/json")
	public ResponseEntity<LetturaSaldo> letturaSaldo(@PathVariable("accountId") String AccountId) throws AccountIdNotAllowedException
	{
		if(!"14537780".equals(AccountId))
			throw new AccountIdNotAllowedException("AccountId non valido.");
		
		final String uri = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/" + AccountId;

	    HttpEntity entity = new HttpEntity(this.setHeaders());
	    
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<LetturaSaldo> response = restTemplate.exchange(
	        uri, HttpMethod.GET, entity, LetturaSaldo.class);
	    
	    // recupero i dati del Saldo per una eventuale gestione
	    Saldo saldo = response.getBody().getPayload();
	    
	    return response;
	}
	
	@GetMapping(value="/transazioni/{accountId}", produces="application/json")
	public ResponseEntity<LetturaListaTransazioni> listaTransazioni(@PathVariable("accountId") String AccountId, 
			@RequestParam("fromAccountingDate") String FromAccountingDate, @RequestParam("toAccountingDate") String ToAccountingDate) throws AccountIdNotAllowedException, DateFilterNotAllowedException
	{
		//final String uri = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/transactions?fromAccountingDate=2019-01-01&toAccountingDate=2019-12-01";

	    HttpEntity entity = new HttpEntity(this.setHeaders());
	    
	    if(!"14537780".equals(AccountId))
			throw new AccountIdNotAllowedException("AccountId non valido.");
	    if(!"2019-01-01".equals(FromAccountingDate) || !"2019-12-01".equals(ToAccountingDate))
	    	throw new DateFilterNotAllowedException("Range di ricerca non consentito.");
	    
	    final String uri = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/transactions";
	    
	    Map<String, String> urlParams = new HashMap<>();
	    urlParams.put("accountId", AccountId);
	    
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri)
	            .queryParam("fromAccountingDate", FromAccountingDate)
	            .queryParam("toAccountingDate", ToAccountingDate);
	    
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<LetturaListaTransazioni> response = restTemplate.exchange(
	    		builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, entity, LetturaListaTransazioni.class);
	    
	    // recupero la lista transazioni per una eventuale gestione
	    Set<Transazione> listaTransazioni = response.getBody().getPayload().getList();
	    
	    return response;
	}
	
	private HttpHeaders setHeaders()
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Auth-Schema", "S2S");
	    headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
	    return headers;
	}
	
	String requestBody = ""
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
