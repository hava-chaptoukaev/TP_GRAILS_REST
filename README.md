GET:
curl -i http://localhost:8091/api/annonce/2 -X GET -H 'Accept: application/json'


PATCH:
curl -i http://localhost:8091/api/annonce/2 -H 'Content-Type: application/json' -X PATCH 

curl -i http://localhost:8091/api/annonce/2 -H 'Content-Type: application/json' -X PATCH -d '{"title":"test"}'

curl -i http://localhost:8091/api/annonce/2 -H 'Content-Type: application/json' -X PATCH -d '{"title":"test", "author":{"id":2}}'

curl -i http://localhost:8091/api/annonce/2 -X GET -H 'Accept: application/json'


DELETE:
curl -i http://localhost:8091/api/annonce/2 -X DELETE 
curl -i http://localhost:8091/api/annonce/2 -X GET -H 'Accept: application/json'


GET:
curl -i http://localhost:8091/api/annonces/ -X GET -H 'Accept: application/json'


POST:
curl -i -X POST -H 'Content-Type: application/json' http://localhost:8091/api/annonces -d '{"title":"title2","dateCreated":"2019-10-04T11:23:53Z","validTill":"2019-10-04T11:23:53Z","illustrations":[{"id":5},{"id":7},{"id":6}],"description":"description","state":true,"author":{"id":1}}'

curl -i -X POST -H 'Content-Type: application/json' http://localhost:8091/api/annonces -d '{"title":"title2","dateCreated":"2019-10-04T11:23:53Z","validTill":"2019-10-04T11:23:53Z","illustrations":[{"id":5},{"id":7},{"id":6}],"description":"description","state":true,"author":{"id":7}}'

curl -i -X POST -H 'Content-Type: application/json' http://localhost:8091/api/annonces -d '{"dateCreated":"2019-10-04T11:23:53Z","validTill":"2019-10-04T11:23:53Z","illustrations":[{"id":5},{"id":7},{"id":6}],"description":"description","state":true,"author":{"id":1}}'


curl -i http://localhost:8091/api/annonce/2 -H 'Content-Type: application/json' -X PATCH -d ‘{title":"Test"}'
 
