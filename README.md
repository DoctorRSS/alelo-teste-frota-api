# alelo-teste-frota-api
Parte Backend do teste para a entrevista da Alelo

Link para execução do projeto:

http://localhost:8080/vehicle - Pesquisa todos os Veiculos (GET)

http://localhost:8080/vehicle?page=0&size=3 - Pesquisa com paginação (GET)

http://localhost:8080/vehicle?plate=&status= - Pesquisa com Filtro (GET)

http://localhost:8080/vehicle?{id} - Atualiza um veículo por Id (PUT)

http://localhost:8080/vehicle?{id} - Remove um veiculo por Id (Delete)

http://localhost:8080/vehicle - Cria um novo cadastro de veiculo (POST)

O projeto contêm:

Spring Boot, Spring Data JPA, Swagger, Webservice Rest , Teste Unitário JUNIT, Acesso ao Banco de Dados MySQL e migração com Flyway.
