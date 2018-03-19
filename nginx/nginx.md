# Nginx as proxy to run locally

If you want to develop on front-end and test locally you need of course the backend too. Normally the warmupbutlerweb is packaged within the warmupbutlerapp and hosted by the spring boot embedded tomcat. But this is very slow when making a change to the front end.



Running the API only and hosting the front-end through nginx speeds things up.

1. Start the WarmupbutlerapiApplication in intellij or as a stand allone spring boot application
2. open a command prompt and go to the nginx folder within the workspace
3. Run command `start nginx`
4. open your browser and go to http://localhost:9080

Calls to / will be served from the warmupbutlerweb folder

Calls to /warmupbutler/api will be serve by tomcat on port 8080 context root /api/.

**Note:** to stop nginx run `nginx - s stop` 
