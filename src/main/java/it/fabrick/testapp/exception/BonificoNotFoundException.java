package it.fabrick.testapp.exception;

public class BonificoNotFoundException  extends Exception
{
 
	private static final long serialVersionUID = -5630320396706560376L;
	
	private String messaggio;
	
	public BonificoNotFoundException()
	{
		super();
	}

	public BonificoNotFoundException(String Messaggio)
	{
		super(Messaggio);
		this.messaggio = Messaggio;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	
	

}
