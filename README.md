# Rolehierachy DEMO

## Goal
Show usage of *Rolehierachy* in Spring Security 4 and 5. In the app are three roles defined:
1. admin
2. editor
3. user

The hierachy is **admin > editor > user**.

## Public Endpoint
No authorization needed:  
```
http localhost:9012/public
http localhost:9012/public -a user:password
http localhost:9012/public -a editor:password
http localhost:9012/public -a admin:password
```

Response:

```
HTTP/1.1 200 
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Content-Length: 13
Content-Type: text/plain;charset=UTF-8
Date: Sat, 30 Mar 2019 09:18:59 GMT
Expires: 0
Pragma: no-cache
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block

Hello public!
```

## User Endpoint

Any authorization is valid  

```
http localhost:9012/public -a user:password
http localhost:9012/public -a editor:password
http localhost:9012/public -a admin:password
```

Response:
```
HTTP/1.1 200 
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Content-Length: 13
Content-Type: text/plain;charset=UTF-8
Date: Sat, 30 Mar 2019 09:18:59 GMT
Expires: 0
Pragma: no-cache
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block

Hello user!
```

## Editor Endpoint

Editor and admin authorization is valid  

```
http localhost:9012/public -a editor:password
http localhost:9012/public -a admin:password
```

Response:
```
HTTP/1.1 200 
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Content-Length: 13
Content-Type: text/plain;charset=UTF-8
Date: Sat, 30 Mar 2019 09:18:59 GMT
Expires: 0
Pragma: no-cache
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block

Hello editor!
```


## Editor Endpoint

Only admin authorization is valid  

```
http localhost:9012/public -a admin:password
```

Response:
```
HTTP/1.1 200 
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Content-Length: 13
Content-Type: text/plain;charset=UTF-8
Date: Sat, 30 Mar 2019 09:18:59 GMT
Expires: 0
Pragma: no-cache
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block

Hello admin!
```

