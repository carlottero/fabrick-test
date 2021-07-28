# fabrick-test
TESTO ESERCIZIO:

Realizzare un controller Rest che permetta di gestire le seguenti operazioni sul conto:
    • Lettura saldo;
    • Lista di transazioni
    • Bonifico;

L’applicativo dovrà essere sviluppato utilizzando le API esposte da Fabrick
https://developers.fabrick.com/hc/it

Per la fase di sviluppo e test sarà sufficiente l’utilizzo della versione SANDBOX della API.

Non è necessario lo sviluppo di un interfaccia utente;

Facoltativo:
Verrà valutato positivamente, ma non obbligatorio al fine del test, la scrittura su DB e storicizzazione dei movimenti ottenuti con la lista delle transazioni.
Credenziali e input
L’esercizio dovrà essere svolto usando l’ambiente di Sandbox con le seguenti credenziali:

BaseUrl: 		https://sandbox.platfr.io
Auth-Schema: 	S2S
Api-Key: 		FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP
accountId: 		14537780
Proprietà/Costanti applicativo
    • {accountId} : Long, è il numero conto di riferimento, nelle API è sempre indicato come {accountId}, usare valore 14537780

Operazione: Lettura saldo
	API: https://docs.fabrick.com/platform/apis/gbs-banking-account-cash-v4.0
        ◦ Input: {accountId}:Long è un parametro dell’applicazione;
Output: Visualizzare il saldo

Operazione: Bonifico
	API: https://docs.fabrick.com/platform/apis/gbs-banking-payments-moneyTransfers-v4.0
Input:
        ◦ {accountId}:Long è un parametro dell’applicazione;
        ◦ {receiverName}:String, è il beneficiario del bonifico;
        ◦ {description}: String, descrizione del bonifico
        ◦ {currency}:String
        ◦ {amount}:String 
        ◦ {executionDate}:String YYYY-MM-DD

Output: Stato dell’operazione, il bonifico avrà esito KO per una limitazione del conto di prova. L’output atteso dovrà essere:

"code": "API000",
"description": "Errore tecnico  La condizione BP049 non e' prevista per il conto id 14537780"

Operazione: Lettura Transazioni
	API: https://docs.fabrick.com/platform/apis/gbs-banking-account-cash-v4.0
Input:
        ◦ {accountId}:Long è un parametro dell’applicazione;
        ◦ {fromAccountingDate}=2019-01-01
        ◦ {toAccountingDate}=2019-12-01
Output:  Lista delle transazioni, nelle date suggerite su esempio sono presenti movimenti.

Artefatto
Il codice sorgente dell’artefatto prodotto dovrà essere pubblicato e condiviso tramite:

    • GitHub;
    • Bitbucket;
    • Altri strumenti similari di version control;


COMMENTI SVOLGIMENTO esercizio di test:

Per comodità, riporto gli url delle chiamate:
GET Lettura saldo: 
http://localhost:8050/api/operazioni/saldo/14537780
GET Lista transazioni: 
http://localhost:8050/api/operazioni/transazioni/14537780?fromAccountingDate=2019-01-01&toAccountingDate=2019-12-01
POST Bonifico: 
http://localhost:8050/api/operazioni/bonifico/14537780

- Lettura transazioni: Ho notato che la formattazione delle date e dei numeri a virgola mobile, correttamente restistuiti dall'api esterna, vengono restituiti dalla mia api in modo corretto ma non formattato. Ho deciso di lasciarli così in quanto come da prassi la formattazione dovrebbe avvenire lato FE.
- N.B. il controller dovrebbe limitarsi a presentare i dati, e delegare lo strato di servizio ad eseguire la business logic. Io, visto che il codice era poco, ho preferito bypassare questo aspetto.
