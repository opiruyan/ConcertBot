# Deploy
Bot is deployed to Railway.app

## Build

To extract and inspect jar - jar xf build/libs/FindYourConcert.jar META-INF/MANIFEST.MF

**JAR** - https://docs.oracle.com/javase/tutorial/deployment/jar/index.html

## Enviroment
`.env` is avalable only locally and not added to the repo hence is omitted in the deployments.

Therefore production ENVs set in the Railway(needs to be changed) and dotenv package is disabled - https://github.com/cdimascio/dotenv-java#optional-ignoreifmissing