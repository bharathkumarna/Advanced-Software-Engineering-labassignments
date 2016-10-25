/**
 * Created by bn4n5 on 10/24/2016.
 */

var express = require('express');
var app = express();
var request = require('request');
app.get('/getdata/:curr/:amount', function (req, res) {
    var result={
        'exchange': []
    };

    request('http://api.fixer.io/latest?base='+req.params.curr+'&symbols=USD', function (error,response,body) {
        if (error) {
            return console.log('Error:', error);
        }

        if (response.statusCode !== 200) {
            return console.log('Invalid Status Code Returned:', response.statusCode);
        }
        body = JSON.parse(body);
        var curr = body;

        request('http://www.apilayer.net/api/live?access_key=163f2c37bd4eaab1e38f8735759542ea&currencies=xau,xag', function (error,response,body1) {
            if (error) {
                return console.log('Error:', error);
            }

            if (response.statusCode !== 200) {
                return console.log('Invalid Status Code Returned:', response.statusCode);
            }
            body1 = JSON.parse(body1);
            var rate = body1;
        result.exchange.push({"Goldrate": (req.params.amount*curr.rates.USD*rate.quotes.USDXAU).toFixed(2),"Silverrate":(req.params.amount*curr.rates.USD*rate.quotes.USDXAG).toFixed(2)});
        res.contentType('application/json');
        res.write(JSON.stringify(result));
        res.end();
    });

    });
    console.log(result);
})
var server = app.listen(8081, function () {
    var host = server.address().address
    var port = server.address().port

})
