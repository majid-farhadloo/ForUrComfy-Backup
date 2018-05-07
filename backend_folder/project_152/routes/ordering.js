var express = require('express');
var router = express.Router();
var order = require('../lib/order')
// var md5= require('md5');
var mongoose = require('mongoose');
var moment = require('moment');



//CONNECT TO DATABASE
mongoose.connect("mongodb://heroku_vtkwsl62:1234qwer@ds235778.mlab.com:35778/heroku_vtkwsl61");
var db = mongoose.connection;
db.once("open",function() {
    console.log("DB connected!");
});
db.on("error", function (err) {
    console.log("DB ERROR :", err);
});

/* GET users listing. */
router.post('/', function(req, res) {
    // res.send('respond with a resource');
    // var user  = req.session.user;
    var newOrder = new order();
    // var food = new order.food();
    var food = JSON.parse(req.body.food);
    // var price = req.body.price;
    // var quantity = req.body.quantity;
    var name = req.body.name;
    var location = req.body.location;
    var cell = req.body.cell;
    console.log("console log : " + food);
    /*
    var jsonStr = '{"theTeam":[{"teamId":"1","status":"pending"},{"teamId":"2","status":"member"},{"teamId":"3","status":"member"}]}';

var obj = JSON.parse(jsonStr);
obj['theTeam'].push({"teamId":"4","status":"pending"});
jsonStr = JSON.stringify(obj);
    */
    console.log("type = " + typeof(food));
    newOrder.name = name;
    newOrder.time = Date.now();
    newOrder.completed = false;
    newOrder.location = location;
    newOrder.cell = cell;
    console.log(newOrder);
    newOrder.save(function(err,savedOrder){
        // if(!req.session.user)
        // {
        //   return res.json({"result":false, "message":"User not logged in"});
        // }
        if(err){
            console.log(err);
            return res.status(400).json({"result":false, "message":"Failed to save order"});
        }
        return res.status(200).json({
                          "message":"Order is being processed!"
                          });
    });

});

module.exports = router;
