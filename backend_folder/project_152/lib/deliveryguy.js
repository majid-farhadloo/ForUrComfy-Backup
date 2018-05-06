var mongoose = require('mongoose');

var deliveryguySchema = new mongoose.Schema({
  deliveryguy_name: String,
});

var deliveryguy = mongoose.model ('deliveryguy',deliveryguySchema);
module.exports = deliveryguy;
