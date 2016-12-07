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
        './client/index.jsx'
    ],
    output: {
        path: __dirname + '/src/main/webapp',
        filename: "bundle.js",
        sourceMapFilename: "bundle.map"
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
                test: /\.(png|jpe?g|gif)$/,
                loader: 'file-loader?name=/img/[name].[ext]'
            }, {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                loader: "babel-loader"
            }, {
                test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                loader: "url-loader?limit=10000&minetype=application/font-woff&name=/fonts/[name].[ext]"
            }, {
                test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                loader: "file-loader"
            }, {
                test: /\.[ot]tf$/,
                loader: 'url?limit=65000&mimetype=application/octet-stream&name=/fonts/[name].[ext]'
            }
        ],
    },
    plugins: [HtmlWebpackPluginConfig]
};
