## Setup
Instale o openjdk 17 https://jdk.java.net/archive/

Execute `docker-compose up -d` na raiz do projeto para subir o banco Postgres


# festival-servidor
Servidor de dados API para aplicativo do festival

API REST desenvolvida com o framework Spring-Boot, conta atualmente com requisições HTTP para o gerenciamento de festivais e eventos.
Interage com o banco de dados Postgres, onde utiliza a database de nome 'festivalr'.
Conexões JPA hibernate já estabelicidas com sucesso.
Utiliza segurança spring security e Tokens JWT para garantir autenticidade do 
