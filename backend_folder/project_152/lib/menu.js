var mongoose = require('mongoose');

var menuSchema = new mongoose.Schema({
  itemId : Number,
  itemName : String,
  category: String,
  description: String,
  sortPosition : Number,
  price: Number,
  image: String
});

var menu = mongoose.model ('menu',menuSchema);
module.exports = menu;