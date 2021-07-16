package it.fabrick.testapp.exception;

public class DateFilterNotAllowedException extends Exception
{

	private static final long serialVersionUID = 3323838991271453565L;

	private String messaggio;
	
	public DateFilterNotAllowedException()
	{
		super();
	}

	public DateFilterNotAllowedException(String Messaggio)
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
