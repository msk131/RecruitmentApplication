# RecruitmentApplication
This project was bootstrapped with Create React App.<br /><br />

Configure<br /><br />
The app requires only one configuration attribute, which is API server url and port. Specify it in config.js. Default is serverUrl = "http://localhost:9080", which is configured for local development.<br /><br />

Steps to Build and run:  <br />
Git clone https://github.com/msk131/RecruitmentApplication.git <br />
cd RecruitmentApplication  <br />
mvnw run:spring-boot <br />
cd recruitment_front_end_react<br />
npm install <br />
npm start <br />
This will run application in develop mode with auto reload feature at http://localhost:3000. <br /><br />

Distribute<br />
To create production build run:<br />

npm run build<br />
This will create static files in folder build ready to be deployed on http server. E.g. you may serve them using simple nodejs http server:
<br /><br />
npm install -g serve<br />
serve -s build<br />
