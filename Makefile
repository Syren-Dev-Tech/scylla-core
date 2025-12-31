FORGE_GRADLE_VERSION=8.8
FABRIC_GRADLE_VERSION=8.8

build: build-forge build-neoforge
publish: publish-forge publish-neoforge

build-forge: SHELL := /bin/bash
build-forge:
	source "${HOME}/.sdkman/bin/sdkman-init.sh" && \
	sdk use gradle ${FORGE_GRADLE_VERSION} && \
	gradle clean build -Pforge=true && \
	mkdir -p ./dist && \
	cp build/libs/*.jar ./dist/scylla-forge.jar

build-fabric: SHELL := /bin/bash
build-fabric:
	source "${HOME}/.sdkman/bin/sdkman-init.sh" && \
	sdk use gradle ${FABRIC_GRADLE_VERSION} && \
	gradle clean build -Pfabric=true && \
	mkdir -p ./dist && \
	cp build/libs/*.jar ./dist/scylla-fabric.jar

build-neoforge: SHELL := /bin/bash
build-neoforge:
	source "${HOME}/.sdkman/bin/sdkman-init.sh" && \
	sdk use gradle ${FORGE_GRADLE_VERSION} && \
	gradle clean build -Pneoforge=true && \
	mkdir -p ./dist && \
	cp build/libs/*.jar ./dist/scylla-neoforge.jar

publish-forge: SHELL := /bin/bash
publish-forge:
	source "${HOME}/.sdkman/bin/sdkman-init.sh" && \
	sdk use gradle ${FORGE_GRADLE_VERSION} && \
	gradle publish -Pforge=true

publish-fabric: SHELL := /bin/bash
publish-fabric:
	source "${HOME}/.sdkman/bin/sdkman-init.sh" && \
	sdk use gradle ${FABRIC_GRADLE_VERSION} && \
	gradle publish -Pfabric=true

publish-neoforge: SHELL := /bin/bash
publish-neoforge:
	source "${HOME}/.sdkman/bin/sdkman-init.sh" && \
	sdk use gradle ${FORGE_GRADLE_VERSION} && \
	gradle publish -Pneoforge=true