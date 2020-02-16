// Classes and Objects

// Declaring a variable
var a;
let b;

// Global variable
c;

// functions

// annoymous function
// function (){}

// named function
function fun_name() {}

// variable expressions
let fun1 = function d() {};

// JSON Object
let Person = {
  firstName: "Joe",
  lastName: "Bill",
  age: 28,
  // Get name via function
  fullName: function getName() {
    return Person.name;
  }
};

// JS also support Static functions


// 
function Person(first, last) {
    this.firstName = first,
    this.lastName = last;
}

let Student = new Person('Saman', 'Kumara');


 