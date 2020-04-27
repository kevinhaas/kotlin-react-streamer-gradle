config.resolve.modules.push("build/js/node_modules/@jetbrains")

config.devServer = config.devServer || {}
config.devServer.watchOptions = {
    "aggregateTimeout": 5000,
    "poll": 1000
}