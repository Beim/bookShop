'use strict'
var router = require('koa-router')()

router.get('/', function *(next){
	this.body = 'Hello World@index'
})

module.exports = router
