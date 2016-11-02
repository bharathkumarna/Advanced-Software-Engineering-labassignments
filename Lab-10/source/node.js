
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var bodyParser = require("body-parser");
var express = require('express');
var ObjectId = require('mongodb').ObjectID;
var cors = require('cors');
var app = express();
var result={'body': []};
var url = 'mongodb://ase:xyz123@ds135797.mlab.com:35797/demo';
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.post('/register', function (req, res) {
    MongoClient.connect(url, function(err, db) {
        if(err)
        {  res.write("Connecting to Database Failed");
            res.end();  }
        insertDocument(db, req.body, function() {
            res.write("Successfully Registered");
            console.log("Successfully Registered ")
            res.end();       });    });
    var insertDocument = function(db, data, callback) {
        db.collection('ase').insertOne( data, function(err, result) {
            if(err)           {
                res.write("Registration Failed");
                res.end();           }
            console.log("Registration Successful");

            callback();        });    };})
app.post('/disp',function (req,res) {
    MongoClient.connect(url, function(err, db) {
        assert.equal(null, err);
        findUser(db, function() {
            db.close();        });    });
    var findUser = function(db, callback) {
        var cursor =db.collection('ase').find();
        cursor.toArray(function(err, doc) {
            assert.equal(err, null);
                j=doc;
                JSON.stringify(j);
                doc1=j;
            for (var i=0;i<doc.length;i++) {
                result.body.push({"ID":doc[i]._id,"Firstname": doc[i].Firstname,"Lastname": doc[i].LastName,"Email": doc[i].Email,"Phone":doc[i].Phone});
            }console.log(result);
            res.contentType('application/json');
            res.write(JSON.stringify(j));
            res.end();        });    };})
app.post('/update',function (req,res) {
    MongoClient.connect(url, function(err, db) {
        if(err)        {
            res.write("Connecting to Database Failed");
            res.end();        }
        updateDocument(db, req.body, function() {
            res.write("Updated Record");
            res.end();        });    });
    var id=req.body.id2;
    var item={FirstName:req.body.fn,LastName:req.body.ln,Email:req.body.e1,Phone:req.body.p1};
    var updateDocument = function(db, data, callback) {
        db.collection('ase').updateOne({"_id":ObjectId(id)},{$set:item}, function(err, result) {
            if(err)            {
                res.write("Registration Failed");
                res.end();            }
            console.log("Updated Record");
            callback();        });    };})
app.post('/delete', function(req, res) {
    var id = req.body.id1;
    MongoClient.connect(url, function(err, db) {
        if(err)        {
            res.write("Registration Failed");
            res.end();        }
        db.collection('ase').deleteOne({"_id": ObjectId(id)}, function(err, result) {
            res.write("Item Deleted");
            res.end();
            console.log('Item deleted');
        });});})
app.post('/login', function(req, res) {
    var first = req.body.fname;
    var pass=req.body.pwd;
    MongoClient.connect(url, function(err, db) {
        if(err)        {
            res.write("Registration Failed");
            res.end();        }
        db.collection('ase').find({"FirstName":ObjectId(first) ,"Password":ObjectId(pass)}, function(err, result) {
            res.write("Sign in Successfull");
            res.end();
            console.log('Signin');
        });});})
;
var server = app.listen(8081, function () {
    var host = server.address().address
    var port = server.address().port
    console.log("Application Running at http://%s:%s", host, port) })