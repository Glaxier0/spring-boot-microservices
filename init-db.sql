CREATE DATABASE e_commerce;
CREATE USER glaxier WITH ENCRYPTED PASSWORD 'glaxierpassword';
ALTER DATABASE e_commerce OWNER TO glaxier;

CREATE DATABASE keycloak;
ALTER DATABASE keycloak OWNER TO glaxier;