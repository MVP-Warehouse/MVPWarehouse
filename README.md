# Setup steps for the project
- Install Open JDK 25
- Install docker
- Run this command:
```
  sudo docker run --name mvpwarehouse-pg -p 5432:5432 \
    -e POSTGRES_DB=mvpwarehouse \
    -e POSTGRES_USER=mvpuser \
    -e POSTGRES_PASSWORD=mvppass \
    -v mvpwarehouse-pgdata:/var/lib/postgresql/data \
    -d postgres:16
  ```
- Start database docker container with this command:
```
  sudo docker start mvpwarehouse-pg
```
- Run this project with IntelliJ, it will suggest to download the dependencies automatically.
