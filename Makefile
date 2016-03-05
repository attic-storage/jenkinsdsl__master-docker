start:
	docker run --name jenkinsdsl__master -p 8080:8080 -p 50000:50000 \
		-v $(shell pwd)/.config:/var/jenkins_home --rm jenkinsdsl/master-docker

build:
	docker build -t jenkinsdsl/master-docker master-docker
