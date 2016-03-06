start:
	docker run --name jenkinsdsl__master \
	    -p 8080:8080 \
	    -p 16042:16042 \
	    -p 50000:50000 \
	    -v $(shell pwd)/.config:/var/jenkins_home \
	    -v ~/.ssh:/var/jenkins_publickeys \
	    --rm jenkinsdsl/master-docker

build:
	docker build -t jenkinsdsl/master-docker .
