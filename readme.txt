In the application I use the following technologies: Spring Boot, JUnit, Maven, H2, Mockito.
The application uses h2 database in memory.

Endpoints:

1. Creating client:
POST
http://localhost:8080/clients
{"firstName": "Artem",
"lastName": "Kostenko",
"birthday": "1990-01-03",
"gender": "MALE",
"phoneNumbers": [{"number": "380951234567"}]}

2. Storing information about a client's call:
POST
http://localhost:8080/clients/{clientID}/calls
{"callersPhoneNumber": "380951234567",
"recipientsPhoneNumber": "380957654321",
"date": "2019-06-07",
"duration": 600,
"city": "Dnipro"}

3. Getting information on a number of calls:
GET
http://localhost:8080/calls

4. Getting information about the longest call
GET
http://localhost:8080/clients/{clientId}/longest_call?start=2019-06-06&end=2019-06-07
