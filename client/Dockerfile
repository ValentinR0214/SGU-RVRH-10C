#Etapa 1: Recursos
FROM node:20 AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

ARG API_HOST
ARG API_PORT
ARG API_BASE
#para definir HTTP o HTTPS
ARG API_PROTOCOL

ENV VITE_API_HOST=$API_HOST
ENV VITE_API_PORT=$API_PORT
ENV VITE_API_BASE=$API_BASE
#para https o http dinamicos (configuracion en docker-compose)
ENV VITE_API_PROTOCOL=$API_PROTOCOL

RUN npm run build

#Etapa2: Imagen
FROM nginx:alpine

COPY --from=build /app/dist/ /usr/share/nginx/html

#nginx configuration para https, si utilizamos http se puede eliminar esta linea y el archivo nginx.conf
COPY ./nginx.conf /etc/nginx/nginx.conf 

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

