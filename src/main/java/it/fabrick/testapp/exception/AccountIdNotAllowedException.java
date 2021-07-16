package it.fabrick.testapp.exception;

public class AccountIdNotAllowedException extends Exception
{
	private static final long serialVersionUID = -7232894415872416325L;
	
	private String messaggio;
	
	public AccountIdNotAllowedException()
	{
		super();
	}

	public AccountIdNotAllowedException(String Messaggio)
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
