const jwt = require("jsonwebtoken");
const fs = require("fs");
const path = require("path");
let pkey = fs.readFileSync(path.join(__dirname, "privatekey.pem"));

const data = {
  iss: "7a92e684-50e7-4154-a7ea-9b0895903ee0",
  sub: "7a92e684-50e7-4154-a7ea-9b0895903ee0",
  aud: "https://fhir.epic.com/interconnect-fhir-oauth/oauth2/token",
  jti: "aysydhf-asdfas-vjvyvyv",
  exp: Math.floor(Date.now() / 1000) + 60 * 4,
  nbf: Math.floor(Date.now() / 1000),
  iat: Math.floor(Date.now() / 1000),
};

const token = jwt.sign(data, pkey, { algorithm: "RS384" });
console.log(token);
