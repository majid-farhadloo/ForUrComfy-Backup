var express = require('express');
var router = express.Router();
var User = require('../lib/User');
var md5= require('md5');
var mongoose = require('mongoose');
var app = express();
var session = require('express-session');
