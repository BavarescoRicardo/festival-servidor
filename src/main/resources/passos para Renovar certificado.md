# Renovar certificado

* 1 sudo certbot renew

* 2 para converter os arquivos gerados em um certificado pkcs12
    sudo openssl pkcs12 -export -in /etc/letsencrypt/live/festival-evento.online/fullchain.pem -inkey etc/letsencrypt/live/festival-evento.online/privkey.pem -out etc/letsencrypt/live/festival-evento.online/keystore.p12 -name tomcat -CAfile etc/letsencrypt/live/festival-evento.online/chain.pem -caname root


* 3 mover o certificado para a pasta do projeto

sudo mv /etc/letsencrypt/live/festival-evento.online/keystore.p12 /home/rba/Desktop/Desenvolvimento

sudo chown rba /home/rba/Desktop/Desenvolvimento/keystore.p12


* Rebuild project
sudo mvn clean package
sudo mvn package

* executando servico
pasta target do servidor-festival
sudo java -jar festival...

   password do certificado: keyraiz



