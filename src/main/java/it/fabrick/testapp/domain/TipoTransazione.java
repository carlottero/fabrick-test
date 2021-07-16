package it.fabrick.testapp.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class TipoTransazione implements Serializable
{
	private static final long serialVersionUID = 954255921012990659L;
	
	private String enumeration;
	private String value;
}
