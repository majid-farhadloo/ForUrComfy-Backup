var mongoose = require('mongoose');

var userSchema = new mongoose.Schema({
  email : {type:String , unique : true},
  password : {type:String},
  firstname: String,
  lastname: String,
  cell: String
});

var User = mongoose.model ('user',userSchema);
module.exports = User;
