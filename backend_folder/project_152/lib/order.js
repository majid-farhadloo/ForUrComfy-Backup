var mongoose = require('mongoose');

var Food = new mongoose.Schema({
  foodName: String,
  price: String,
  quantity:String
});

var orderSchema = new mongoose.Schema({
  food : [Food],
  name : String,
  cell: Number,
  location: String,
  completed: Boolean,
  time: Date
});


var order = mongoose.model ('order',orderSchema);
module.exports = order;
