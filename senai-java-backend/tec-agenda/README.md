# Rodando a aplicação

Entrar na pasta target, abrir o prompt de comando e rodar o comando:

## java -jar tec-agenda-0.0.1-SNAPSHOT.jar

A aplicação estará rodando.
http://localhost:8080/

# Modelos

### GET /api/contatos

```json
[
    {
        "idContato": 1,
        "nome": "Nome A",
        "telefone": "12345678",
        "endereco": "Endereço A",
        "email": "a@a.a",
        "linkFacebook": "http://www.facebook.com/a",
        "foto": null,
        "ratingBar": 5
    },
    {
        "idContato": 2,
        "nome": "Nome B",
        "telefone": "12345678",
        "endereco": "Endereço B",
        "email": "b@b.b",
        "linkFacebook": "http://www.facebook.com/b",
        "foto": null,
        "ratingBar": 3
    }
]
```

### GET /api/contatos/1

```json
{
    "idContato": 1,
    "nome": "Nome A",
    "telefone": "12345678",
    "endereco": "Endereço A",
    "email": "a@a.a",
    "linkFacebook": "http://www.facebook.com/a",
    "foto": null,
    "ratingBar": 5
}
```

### POST /api/contatos

```json
{
    "nome": "Nome A",
    "telefone": "12345678",
    "endereco": "Endereço A",
    "email": "a@a.a",
    "linkFacebook": "http://www.facebook.com/a",
    "foto": null,
    "ratingBar": 5
}
```

### DELETE /api/contatos/1

### PUT /api/contatos/1

```json
{
    "nome": "Nome A",
    "telefone": "12345678",
    "endereco": "Endereço A",
    "email": "a@a.a",
    "linkFacebook": "http://www.facebook.com/a",
    "foto": null,
    "ratingBar": 5
}
```

### CTRL+C para parar a aplicação.