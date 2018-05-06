var mongoose = require('mongoose');

var orderSchema = new mongoose.Schema({
  foodName : String,
  price : Number,
  quantity: Number,
  currentUser: String,
  cell: Number,
  location: String,
  completed: Boolean,
  time: Date
});

var order = mongoose.model ('order',orderSchema);
module.exports = order;
