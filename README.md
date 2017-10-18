Para compilar e rodar esse código é necessário ter instalado Java 8 e Maven.

Antes de rodar aplicação utilize o comando mvn clean install para baixar as dependências do projeto.

Para rodar a aplicação é preciso executar o comando mvn wildfly-swarm:run e acessar através da url localhost:8080.

Gostaria de destacar que o código foi desenvolvido de acordo com as práticas de clean code, foi utilizado o Java 8 e o Wildfly Swarm.

Para melhorar o código poderiamos ter mais testes unitários e de integração. Também poderia ter sido utilizado um framework front-end, como Angular ou React, 
consumindo uma API Rest.