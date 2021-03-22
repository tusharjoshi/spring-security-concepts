# Basic API with Security

Generated with command

```bash
spring init -dweb,security \
  --build maven \
  -g com.company \
  -a bookapi c02-secured-api
```

* Run the project
* Copy the password on console
* access URL http://localhost:8080
* use username: user and password copied from logs
* Now the following URLs will be accessible
    * http://localhost:8080/api/v1/books/1
    * http://localhost:8080/api/v1/books/2
    * http://localhost:8080/api/v1/books/3
* Open Inspect Panel
* Check Network Tab
* Observe login request
* Observe user and password in plain text