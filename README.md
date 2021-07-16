# fabrick-test
esercizio di test

Per comodità, riporto gli url delle chiamate:
GET Lettura saldo: 
http://localhost:8050/api/operazioni/saldo/14537780
GET Lista transazioni: 
http://localhost:8050/api/operazioni/transazioni/14537780?fromAccountingDate=2019-01-01&toAccountingDate=2019-12-01
POST Bonifico: 
http://localhost:8050/api/operazioni/bonifico/14537780

- Lettura transazioni: Ho notato che la formattazione delle date e dei numeri a virgola mobile, correttamente restistuiti dall'api esterna, vengono restituiti dalla mia api in modo corretto ma non formattato. Ho deciso di lasciarli così in quanto come da prassi la formattazione dovrebbe avvenire lato FE.
