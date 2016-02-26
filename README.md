Apache CXF and Dynamic Features
==============

- Non-Validating API: 500 Internal Server Error  

        curl http://localhost:8080/rest/api/v1/people?count=-1 -i

- Validating API: 400 Bad Request

        curl http://localhost:8080/rest/api/v2/people?count=100 -i

