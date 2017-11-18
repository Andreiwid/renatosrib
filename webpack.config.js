var HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = {
    context: __dirname + "/webapp",
  
    entry: "./App.js",
  
    output: {
      filename: "app.js",
      path: __dirname + "/src/main/resources/static",
    },
    resolve: {
        extensions: ['.js', '.jsx', '.json']
      },
      module: {        
        loaders: [
          {
            test: /\.js?$/,
            exclude: /node_modules/,
            loader: "babel-loader",
            query: {
                plugins: ['transform-decorators-legacy'],
                presets: ['es2016', 'stage-0', 'react'],
              },
          },
          { test: /\.css$/, loader: 'style-loader!css-loader' },
        ]
      },      
      plugins: [
        new HtmlWebpackPlugin({
          title: 'Sistema de busca de arquivos',
          template: 'index.html',
        }),
        new CopyWebpackPlugin([          
          { from: __dirname + '/webapp/assets' },
        ]),
      ]
  };