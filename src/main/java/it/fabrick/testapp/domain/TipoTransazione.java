package it.fabrick.testapp.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "TIPOTRANSAZIONE")
public class TipoTransazione implements Serializable
{
	private static final long serialVersionUID = 954255921012990659L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private String enumeration;
	
	@Column
	private String value;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
	@JsonBackReference
	private Set<Transazione> transazioni = new HashSet<>();
}
