"use strict";
const mongoose = require("mongoose");

const UserSchema = new mongoose.Schema({
  firstName: String,
  lastName: String,
  birthday: Date
});

const User = mongoose.model("User", UserSchema);
module.exports = User;
