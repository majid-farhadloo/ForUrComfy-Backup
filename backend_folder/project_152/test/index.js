const assert = require('chai').assert;
const expect = require('chai').expect;
const request = require('mocha').request;
const app = require('../app');
const baseUrl = "https://node-practice0208.herokuapp.com";

describe('first test',function(){
  it ('should pass the test',function(){
    expect(true).to.be.true;
  })
})

describe('return account registered',function(){
  it('should return account registered message',function(done){
    request.get({ url:baseUrl+'/delivery/request'},
      function(error,response,body){
        expect(response.statusCode).to.equal(200);
        console.log(body);
        done();
      });
  });
});
