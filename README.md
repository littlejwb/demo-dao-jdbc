# Projeto de Estudo sobre DAO (Data Acces Object) utilizando JDBC e MySQL

Este é um projeto de estudo sobre o padrão DAO (Data Access Object) utilizando JDBC e MySQL.
O objetivo é demonstrar como criar uma aplicação Java que interage com um banco de dados através de um DAO, realizando as operações CRUD (Create, Read, Update e Delete)
nas tabelas `product` e `supplier`.

## Banco de Dados
Para este projeto foi criado um banco de dados fictício no MySQL com duas tabelas: `product` e `supplier`. 

A tabela `product` contém as seguintes colunas:
- id (chave primária)
- name
- section
- price
- supplier_id (chave estrangeira)

A tabela `supplier` contém as seguintes colunas:
- id (chave primária)
- name

## Aplicação
A aplicação utiliza o padrão DAO para realizar as operações CRUD nas tabelas. 
A aplicação também utiliza a associação de objetos para lidar com a relação entre elas. Por exemplo, cada objeto Product contém um objeto Supplier correspondente.

A aplicação possui duas classes DAO: ProductDAO e SupplierDAO. Para cada entidade, há um objeto responsável por fazer acesso a dados relacionado a esta
entidade.

As classes DAO utilizam a classe DaoFactory para estabelecer a conexão com o banco de dados. 

A aplicação também possui tratamento de exceção personalizado. Foram criadas classes de exceção personalizadas para lidar com exceções específicas do projeto.

Fonte: https://www.udemy.com/course/java-curso-completo/
