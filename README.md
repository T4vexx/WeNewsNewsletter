# WeNews
Projeto em Java que contém todos os principais pilares da programação orientada a objeto, com visual feito em JavaFX

## Dependências
Para o programa funcionar primeiramente você deve ter uma banco de dados PostgreSQL rodando na sua máquina
Quando a DB estiver rodando você deve editar a linha 37 do código com suas credenciais

```
public sistema() { con = DBFun.connectToDb("Nome da database","postgres","Senha da db")}
```
A versão do Java usada para o projeto foi JAVA 20

## Funcionalidades
Essa aplicação foi pensada para funcionar como um portal de notícias online
+ Registar e cadastrar novas contas
+ Ver informações do usuário
+ Ler parcialmente uma notícia caso o usuário não seja um inscrito pago
+ Ler a notícia completa caso o usuário seja uma inscrito
+ Comentar na notícia
+ Jornalistas conseguem escrever notícias
+ Escritores conseguem escrever Artigos
+ Editores conseguem editar qualquer artigo ou notícia
+ Usuário consegue comprar uma inscrição
+ Usuário consegue renovar uma inscrição

## Visual
<div style="align: center" width="600em">
  <img height="500em" src="https://github.com/T4vexx/WeNewsNewsletter/assets/68335367/273ed1f1-cf40-4140-a10a-2fbf86b4206b" />
  <img height="500em" src="https://github.com/T4vexx/WeNewsNewsletter/assets/68335367/2a922c89-a4c3-46ed-8730-9d5d4c290367" />
</div>
<div style="align: center">
  <img height="500em" src="https://github.com/T4vexx/WeNewsNewsletter/assets/68335367/e09776c6-c805-4f30-b907-fbe441ac5460" />
</div>
