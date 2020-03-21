/***
 * IT18004182
 * H C S PERERA
 */

"use strict";

const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");
const bodyParser = require("body-parser");
const app = express();

mongoose.Promise = global.Promise;

mongoose.connect("mongodb://localhost:27017/nodejs", err => {
  if (err) {
    console.log(err);
    process.exit(1);
  }
});

const UserModel = require("./model/User");

app.use(express.json());
app.use(express.static(__dirname));
app.use(bodyParser.json());
app.use(cors());
app.use(
  bodyParser.urlencoded({
    extended: false
  })
);
app.use(express.static("public"));

app.get("/", (req, res, next) => {
  res.sendFile("index.html");
});

// Users array
const users = [];

// Get all users
app.get("/users", (req, res) => {
  UserModel.find()
    .exec()
    .then(users => {
      res.json(users);
    })
    .catch(err => {
      console.log(err);
      res.sendStatus(500);
    });
});

// Get user by id
app.get("/users/:id", (req, res) => {
  UserModel.findById(req.params.id)
    .exec()
    .then(user => {
      res.json(user || {});
    })
    .catch(err => {
      console.error(err);
      res.sendStatus(500);
    });
});

// create new users
app.post("/users", (req, res, next) => {
  const user = new UserModel(req.body);
  user
    .save()
    .then(user => {
      res.json(user);
    })
    .catch(err => {
      console.error(err);
      res.sendStatus(500);
    });
});

// update user
app.put("/users/:id", (req, res) => {
  const user = new UserModel(req.body);
  user
    .update()
    .then(() => {
      res.sendStatus(200);
    })
    .catch(err => {
      console.error(err);
      res.sendStatus(500);
    });
});

// delete user
app.delete("/users/:id", (req, res) => {
  UserModel.remove(req.params.id)
    .then(() => {
      res.sendStatus(200);
    })
    .catch(err => {
      console.error(err);
      res.sendStatus(500);
    });
});

app.listen(3000, err => {
  if (err) {
    console.error(err);
    return;
  }

  console.log("App listening on port 3000");
});
