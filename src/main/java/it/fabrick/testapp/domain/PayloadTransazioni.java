package it.fabrick.testapp.domain;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class PayloadTransazioni implements Serializable
{

	private static final long serialVersionUID = -960787139841366642L;

	private Set<Transazione> list;
}
