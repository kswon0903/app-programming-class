var express = require('express');
var router = express.Router();

/* GET home page. */

router.post('/', function(req, res, next) {

	var data = { 'name' : '이순신 post', 'school' : '조선고등학교' };
  //res.render('index', { title: 'Express' });
  	res.json(data);
});

router.get('/', function(req, res, next) {

	var data = { 'name' : '이순신 get', 'school' : '조선고등학교' };
  //res.render('index', { title: 'Express' });
  	res.json(data);
});


module.exports = router;
