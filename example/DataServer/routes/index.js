var express = require('express');
var router = express.Router();

/* GET home page. */
router.post('/', function(req, res, next) {
	console.log("요청할 때 전달한 데이터 : " + Object.keys(req.body));
	var data = { 'name' : '이순신', 'school' : '조선고등학교' };
  	res.json(data); 
});

router.get('/', function(req, res, next) {
	res.render('index', { title: 'Express' });
});


module.exports = router;
