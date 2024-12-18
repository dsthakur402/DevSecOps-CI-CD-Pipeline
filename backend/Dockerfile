FROM node:23.3.0-bookworm-slim

WORKDIR /app/backend

COPY . .

RUN npm i

EXPOSE 5000

CMD [ "npm","start" ]