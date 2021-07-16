package it.fabrick.testapp.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Saldo implements Serializable
{
	private static final long serialVersionUID = -3208333782864833973L;
	
	private String accountId;
    private String iban;
    private String abiCode;
    private String cabCode;
    private String countryCode;
    private String internationalCin;
    private String nationalCin;
    private String account;
    private String alias;
    private String productName;
    private String holderName;
    private Date activatedDate;
    private String currency;
}
