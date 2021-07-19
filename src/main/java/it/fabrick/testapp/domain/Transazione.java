package it.fabrick.testapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "TRANSAZIONI")
public class Transazione implements Serializable
{

	private static final long serialVersionUID = 2501752132814579851L;
	
	@Id
	@Basic(optional = false)
	@Column
	private Long transactionId;
	
	@Column
	private String operationId;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date accountingDate;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date valueDate;
	
	@ManyToOne
	//@JoinColumn(referencedColumnName = "id")
	private TipoTransazione type;
	
	@Column
	private Double amount;
	
	@Column
    private String currency;
	
	@Column
    private String description;
}