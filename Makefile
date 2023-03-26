postgres:
	docker run -p 5432:5432 \
		-d \
 		--rm \
	 	--name postgresdb \
	 	--network green-shop-net \
	 	--env-file ./config/development.env \
	 	-v ./postgres-data:/var/lib/postgresql/data \
	 	postgres

backend:
	docker run -p 8080:8080 \
		--rm \
		-d \
		--name green-shop-backend \
		--network green-shop-net \
		-v ./backend/target:/usr/local/tomcat/webapps \
		--env-file ./config/development.env \
		green-shop-backend

frontend:
	docker run -p 3000:3000 \
		-d \
		--rm \
		--name green-shop-frontend \
		-v ./frontend/src:/app/src \
		green-shop-frontend

stop:
	docker stop postgresdb green-shop-frontend green-shop-backend

dev:
	docker-compose -f docker-compose.yml up -d

build:
	docker-compose -f docker-compose.production.yml up -d

down:
	docker-compose down

# SSH

SSH_STRING:=root@31.184.254.152

ssh:
	ssh $(SSH_STRING)

copy-files:
	scp -r ./* $(SSH_STRING):/root/app