const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const appPath = path.join(__dirname, './client');
const HtmlWebpackPluginConfig = new HtmlWebpackPlugin({
    template: __dirname + '/client/index.html',
    filename: 'index.html',
    inject: 'body'
});

module.exports = {
    entry: [
        './client/index.js'
    ],
    output: {
        path: __dirname + '/src/main/java/webapp',
        filename: "bundle.js"
    },
    module: {
        preLoaders: [
            {
                test: /\.(js|jsx)$/,
                include: appPath,
                loader: 'eslint-loader'
            }
        ],
        loaders: [
            {
                test: /\.css$/,
                loader: 'style-loader!css-loader'
            }, {
                test: /\.less/,
                loader: 'style-loader!css-loader!less-loader'
            }, {
                test: /\.(png|jpg|gif)$/,
                loader: 'url-loader?limit=10000'
            }, {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                loader: "babel-loader"
            }, {
                test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                loader: "url-loader?limit=10000&minetype=application/font-woff"
            }, { test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                loader: "file-loader"
            }
        ],
    },
    plugins: [HtmlWebpackPluginConfig]
};
