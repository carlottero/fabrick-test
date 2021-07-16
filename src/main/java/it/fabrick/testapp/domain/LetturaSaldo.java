package it.fabrick.testapp.domain;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class LetturaSaldo implements Serializable
{
	private static final long serialVersionUID = 5365322020073321643L;

	private String status;
	private Set<String> error;
	private Saldo payload;
}
