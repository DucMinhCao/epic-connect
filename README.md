# epic-connect

## How To Use It

Go to jwt folder

```Javascript
node index.js
```

You will get a jwt code. Use this to post to epic oauth server

```sh
POST https://fhir.epic.com/interconnect-fhir-oauth/oauth2/token HTTP/1.1
Content-Type: application/x-www-form-urlencoded

grant_type=client_credentials&client_assertion_type=urn%3Aietf%3Aparams%3Aoauth%3Aclient-assertion-type%3Ajwt-bearer&client_assertion=<YOUR_JWT_HERE>
```

Replace your access_key you receive from epic to hapi-client, and then run
