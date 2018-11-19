# RecruitmentApplication
This project was bootstrapped with Create React App.

Configure
The app requires only one configuration attribute, which is API server url and port. Specify it in config.js. Default is serverUrl = "http://localhost:9080", which is configured for local development.

Steps to Build and run: /n
Git clone https://github.com/msk131/RecruitmentApplication.git/n
cd ecruitmentApplicat/nion/n 
mvnw run:spring-boot/n
cd recruitment_front_end_react/n
npm install/n
npm start/n
This will run application in develop mode with auto reload feature at http://localhost:3000./n/n

Distribute
To create production build run:

npm run build
This will create static files in folder build ready to be deployed on http server. E.g. you may serve them using simple nodejs http server:

npm install -g serve
serve -s build
