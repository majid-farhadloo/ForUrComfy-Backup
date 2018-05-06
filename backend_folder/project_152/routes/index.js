var express = require('express');
var router = express.Router();
var User = require('../lib/User');
var md5= require('md5');
var mongoose = require('mongoose');
var ExpressBrute = require ('express-brute');
var nodemailer = require('nodemailer');
var jade = require('jade');
const filePath = __dirname + '/find_pwd.jade';

var store = new ExpressBrute.MemoryStore();
var bruteforce = new ExpressBrute(store,{
	freeRetries: 2,
	minWait: 1000 * 60, //1 minute
	maxWait: 10*60*1000, //10 minutes
	failCallback: ExpressBrute.FailTooManyRequests
});


//CONNECT TO DATABASE
mongoose.connect("mongodb://heroku_vtkwsl62:1234qwer@ds235778.mlab.com:35778/heroku_vtkwsl61");
var db = mongoose.connection;
db.once("open",function() {
	console.log("DB connected!");
});
db.on("error", function (err) {
	console.log("DB ERROR :", err);
});


/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});


//POST REQUEST FOR SAVING USER TO DB
router.post('/register',function(req,res)
{
    if(!req.body.email) return res.json({"result":false, "message":"Email required"});
    if(!req.body.password) return res.json({"result":false, "message":"Password required"});
    if(!req.body.firstname) return res.json({"result":false, "message":"Firstname required"});
    if(!req.body.lastname) return res.json({"result":false, "message":"Lastname required"});
    if(!req.body.cell) return res.json({"result":false, "message":"Phone required"});

    var email = req.body.email;
    var password = md5(req.body.password);
    var firstname = req.body.firstname;
    var lastname = req.body.lastname;
    var cell = req.body.cell;

    var newUser = new User();
    newUser.email = email;
    newUser.password = password;
    newUser.firstname = firstname;
    newUser.lastname = lastname;
    newUser.cell = cell;

    User.findOne({email:email},function(err,user){
        if(err) {
            console.log(err);
            res.json(err);
        } else {
            if(user==null) {
                newUser.save(function(err,savedUser){
                    if(err){
                        console.log(err);
                        return res.json({"result":false, "message":"Failed creating an account"});
                    }
                    return res.json({"result":true, "message":"Account Registered!"});
                });
            }else{
                console.log(user);
                res.json({"result":false, "message":"Email already exists"});
            }
        }
    });

});

//POST REQUEST FOR FINDING USER
router.post('/login',bruteforce.prevent,function(req,res,next)
{
  if(!req.body.email) return res.json({"result":false, "message":"Email required"});
  if(!req.body.password) return res.json({"result":false, "message":"Password required"});
  var email = req.body.email;
  var password = md5(req.body.password);

  User.findOne({email: email, password: password},function(err,user){
    if(err) {
        next(err);
      //return res.json({"result":false, "message":"Login Failed"});
    } else {
        //if user exists
        console.log(user);
        if(user) {
						req.session.user = user;
            return res.json({
                "result": true,
                "message": "Login success",
                "data": {
                    "firstname": user['firstname'],
                    "lastname": user['lastname'],
                    "cell": user['cell']
                }
          });
        }
        // if user doesn't exist
        res.json({
            "result": false,
            "message": "Login Failed"
        });
    }
  })
});

router.get('/check_session',function(req,res){
	if(!req.session.user){
		return res.json({
			"result": false,
			"message": "User not logged in"
		});}
		else{
		return res.json({
			"result":true,
			"message": "Session Created",
			"data": {
					"firstname": req.session.user['firstname'],
					"lastname": req.session.user['lastname'],
					"cell": req.session.user['cell']
			}
		});
	}
});

router.get('/logout',function(req,res)
{
	req.session.destroy();
	return res.json({
		"result":true,
		"message":"User logged out"
	});
})

router.post('/finding_pwd', function(req, res)
{
    var user_email = req.body.user_email;

    var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
    var string_length = 8;
    var randomstring = '';
    for (var i=0; i<string_length; i++) {
        var rnum = Math.floor(Math.random() * chars.length);
        randomstring += chars.substring(rnum,rnum+1);
    }

    var data = {randomstring: randomstring};
    var query = { email : user_email};
    var newVal = { $set: {password: md5(randomstring)} };

    User.findOne({email:user_email},function(err,user){
        if(err) {
            console.log(err);
            res.json(err);
        } else {
            if(user==null) {
                console.log("Email doesn't exist");
                res.json({"result":false, "message":"Email doesn't exist"});
            }else{
                User.updateOne(query, newVal, function(err, res) {
                    if(err) {
                        throw err;
                    } else {
                        console.log("password updated");
                    }
                });
            }
        }
    });


    var transporter = nodemailer.createTransport({
      service: 'gmail',
      auth: {
        user: 'forurcomfy.help@gmail.com',
        pass: 'rnrmfrnrmf!@#'
      }
    });

    var mailOptions = {
      from: 'forurcomfy.help@gmail.com',
      to: user_email,
      subject: 'Temporary password from ForUrComfy',
      // text: 'Your temporary password is ' + randomstring +'.'
      html: jade.renderFile(filePath, data)
    };

    transporter.sendMail(mailOptions, function(error, info){
        if (error) {
            console.log(error);
        } else {
            console.log('Email sent: ' + info.response);
            return res.json({"result":true, "message":"Email sent to " + user_email});
        }
    });
        
});

module.exports = router;
