FROM node:latest
EXPOSE 9000
COPY . app/
RUN cd app; npm install
ENTRYPOINT [ "/bin/sh", "-c", "cd app; npm run start-docker" ]
