build: build-forge build-neoforge
publish: publish-forge publish-neoforge

build-forge: SHELL := /bin/bash
build-forge:
	gradle clean build -Pforge=true && \
	mkdir -p ./dist && \
	cp build/libs/*.jar ./dist/scylla-forge.jar

build-fabric: SHELL := /bin/bash
build-fabric:
	gradle clean build -Pfabric=true && \
	mkdir -p ./dist && \
	cp build/libs/*.jar ./dist/scylla-fabric.jar

build-neoforge: SHELL := /bin/bash
build-neoforge:
	gradle clean build -Pneoforge=true && \
	mkdir -p ./dist && \
	cp build/libs/*.jar ./dist/scylla-neoforge.jar

publish-forge: SHELL := /bin/bash
publish-forge:
	gradle publish -Pforge=true

publish-fabric: SHELL := /bin/bash
publish-fabric:
	gradle publish -Pfabric=true

publish-neoforge: SHELL := /bin/bash
publish-neoforge:
	gradle publish -Pneoforge=true