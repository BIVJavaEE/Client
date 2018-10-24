var MiniCssExtractPlugin = require("mini-css-extract-plugin");
var path = require("path");

module.exports = {

    entry: {
        "predicates-list": "./src/predicates-list/index.tsx"
    },

    output: {
        filename: "[name].js",
        path: path.join(__dirname, '../src/main/webapp/bundles/')
    },

    devtool: "source-map",

    mode: 'development',

    resolve: {
        extensions: [".ts", ".tsx", ".js", ".json"]
    },

    module: {
        rules: [
            {
                test: /\.tsx?$/,
                loader: "awesome-typescript-loader"
            },
            {
                enforce: "pre",
                test: /\.js$/,
                loader: "source-map-loader"
            },
            {
                test: /\.scss$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    "css-loader",
                    "sass-loader"
                ]
            }
        ]
    },

    externals: {
        "react": "React",
        "react-dom": "ReactDOM"
    },

    plugins: [
        new MiniCssExtractPlugin({
            filename: "[name].css",
            chunkFilename: "[id].css"
        })
    ]
};