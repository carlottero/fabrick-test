package it.fabrick.testapp.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Transazione implements Serializable
{

	private static final long serialVersionUID = 2501752132814579851L;

	private String transactionId;
	private String operationId;
	private Date accountingDate;
	private Date valueDate;
	private TipoTransazione type;
	private Double amount;
    private String currency;
    private String description;
}