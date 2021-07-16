package it.fabrick.testapp.domain;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class LetturaListaTransazioni implements Serializable
{
	private static final long serialVersionUID = -4504155689221878260L;

	private String status;
	private Set<String> error;
	private PayloadTransazioni payload;
}
