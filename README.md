# duonduo_backend

## 1. API Doc
### Auth
| URL          | Method | Parameter      | Return    | Description                  |
|--------------|--------|----------------|-----------|------------------------------|
| /auth/signup | POST   | email password |           | signup                       |
| /auth/login  | POST   | email password | JWT token | return JWT token after login |
| /auth/kakao  | GET    |                |           |  kakao OAuth                 |
| /auth/naver  | GET    |                |           |  naver OAuth                 |
| /auth/token  | POST   |     token      |           |    verify token              |
| /auth/resign | DELETE |     token      |           |    delete token              |
| /auth/id     | PUT    | password       | UserDto   | modify password              |

### User
| URL          | Method | Parameter      | Return    | Description                  |
|--------------|--------|----------------|-----------|------------------------------|
| /user        | GET    |                | UserDto   | return all users             |
| /user/id     | GET    |                | UserDto   | return user                  |


### Info
| URL          | Method | Parameter      | Return        | Description                  |
|--------------|--------|----------------|---------------|------------------------------|
| /info        | GET    |   token        | NicknameDto   | return user's info           |
| /info        | PATCH  |   token, info  | NicknameDto   | modify user's info           |
| /info        | POST   |   token, info  | NicknameDto   | add user's info              |

## 2. Docker Command
### build
```
docker build -t duonduo:version .
```
### run
```
docker run -d --name duonduo -p HOSTPORT(8080):CONTAINERPORT(8080) duonduo:version
```
### running container
```
docker ps
```
### logs
```
docker logs -f duonduo
```
