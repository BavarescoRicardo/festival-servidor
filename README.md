## Setup
Instale o openjdk 17 https://jdk.java.net/archive/

Execute `docker-compose up -d` na raiz do projeto para subir o banco Postgres


# festival-servidor
Servidor de dados API para aplicativo do festival

API REST desenvolvida com o framework Spring-Boot, conta atualmente com requisições HTTP para o gerenciamento de festivais e eventos.
Interage com o banco de dados Postgres, onde utiliza a database de nome 'festivalr'.
Conexões JPA hibernate já estabelicidas com sucesso.
Utiliza segurança spring security e Tokens JWT para garantir autenticidade do 


## Disponível docker container para execução
### Primeiro deve se rodar o maven para criar o jar do aplicativo
  sudo chmod +x mvnw
  ./ mvnw install -DskipTests
   comandos para executar container
     docker-compose pull
     docker-compose down
     docker-compose build
     docker-compose up

## End points do docker
http://localhost:3034
    /api/evento/auth/register  --- {   "email": "ricardo.com",   "senha": "asd" }
    /api/evento/auth/login  --- {   "email": "ricardo.com",   "senha": "asd" }

## End points SEM docker
http://localhost:3033
    /api/evento/auth/register  --- {   "email": "ricardo.com",   "senha": "asd" }
    /api/evento/auth/login  --- {   "email": "ricardo.com",   "senha": "asd" }    


## gerar o certificado
sudo certbot certonly --standalone -d festival-evento.online --email ricardo.bav17@gmail.com --agree-tos --non-interactive
/etc/letsencrypt/live/festival-evento.online

# Converter .pem para .p12 para usar no sprint properties
sudo -i

sudo openssl pkcs12 -export -in /etc/letsencrypt/live/festival-evento.online/cert.pem -inkey /etc/letsencrypt/live/festival-evento.online/privkey.pem -out /etc/letsencrypt/live/festival-evento.online/certificate.p12 -name "certificate" -CAfile /etc/letsencrypt/live/festival-evento.online/chain.pem -caname "Let's Encrypt Authority X3"
