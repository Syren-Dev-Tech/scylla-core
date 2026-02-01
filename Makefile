FORGE_GRADLE_VERSION=8.8
FABRIC_GRADLE_VERSION=8.8

build: build-forge build-neoforge
publish: publish-forge publish-neoforge
publish-local: publish-forge-local publish-neoforge-local

build-forge: SHELL := /bin/bash
build-forge:
	source "${HOME}/.sdkman/bin/sdkman-init.sh" && \
	sdk use gradle ${FORGE_GRADLE_VERSION} && \
	gradle clean build -Pforge=true && \
	mkdir -p ./dist/forge && \
	cp build/libs/*.jar ./dist/forge/

build-fabric: SHELL := /bin/bash
build-fabric:
	source "${HOME}/.sdkman/bin/sdkman-init.sh" && \
	sdk use gradle ${FABRIC_GRADLE_VERSION} && \
	gradle clean build -Pfabric=true && \
	mkdir -p ./dist/fabric && \
	cp build/libs/*.jar ./dist/fabric/

build-neoforge: SHELL := /bin/bash
build-neoforge:
	source "${HOME}/.sdkman/bin/sdkman-init.sh" && \
	sdk use gradle ${FORGE_GRADLE_VERSION} && \
	gradle clean build -Pneoforge=true && \
	mkdir -p ./dist/neoforge && \
	cp build/libs/*.jar ./dist/neoforge/

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

publish-forge-local: SHELL := /bin/bash
publish-forge-local:
	source "${HOME}/.sdkman/bin/sdkman-init.sh" && \
	sdk use gradle ${FORGE_GRADLE_VERSION} && \
	gradle publishToMavenLocal -Pforge=true

publish-fabric-local: SHELL := /bin/bash
publish-fabric-local:
	source "${HOME}/.sdkman/bin/sdkman-init.sh" && \
	sdk use gradle ${FABRIC_GRADLE_VERSION} && \
	gradle publishToMavenLocal -Pfabric=true

publish-neoforge-local: SHELL := /bin/bash
publish-neoforge-local:
	source "${HOME}/.sdkman/bin/sdkman-init.sh" && \
	sdk use gradle ${FORGE_GRADLE_VERSION} && \
	gradle publishToMavenLocal -Pneoforge=true