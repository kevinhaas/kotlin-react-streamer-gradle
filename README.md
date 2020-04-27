## kotlin-react-streamer-gradle

#### Explore IP radio stations and listen in your browser  

An experiment using Kotlin to target the browser. Transpiles Kotlin to JS, and includes  
the [`kotlin-react` wrappers](https://github.com/JetBrains/kotlin-wrappers/tree/master/kotlin-react).

`npm start` for webpack-dev-server  
`npm run build` for prod bundle

## Gradle vs npm
Started out trying to only use the packages JetBrains provides for Kotlin on npm. The standard   
library is there along with other things, but some are still not published, like `kotlinx.serialization`.  

Gradle can resolve these dependencies outside of npm, and is configured to support npm itself. It will  
update the `package.json` automatically, and `npm install` occurs during the gradle build.
