package it.fabrick.testapp.exception;

import lombok.Data;

@Data
public class ErrorResponse 
{
	private int codice;
	private String messaggio;
	
}
