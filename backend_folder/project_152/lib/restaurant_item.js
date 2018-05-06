var mongoose = require('mongoose');

var restaurant_schema = new mongoose.Schema({
  restaurant_name : String,
  restaurant_image: String
});

var restaurant_item = mongoose.model ('restaurant_item',restaurant_schema);
module.exports = restaurant_item;

