'use strict'
var router = require('koa-router')()
const db = require('../mongo.js')

router.get('/', function *(next){
	this.body = 'Hello World@api'
})


router.get('/:name', function *(next) {
    if (this.params.name === 'test') {
        let userData = {
            username: 'test',
            password: 'test'
        }
        let bookData = {
            bookname: '工数',
            writer: 'writer',
            publishTime: '20160704',
            publisher: 'HIT',
            condition: '9',
            amount: '100',
            image: 'tudou.png',
            bookId: 1
        }
        let response = yield db.insert['book'](bookData)
        this.body = 'Hello World@ connected : ' + response    
    }
    else if (this.params.name === 'user') {
        let limit = {}
        let skip = {
            _id: 0,
            __v: 0
        }
        let response = yield db.search['user'](limit, skip)
        this.body = response   
    }
    else if (this.params.name === 'book') {
        let limit = {
        }
        let skip = {
            _id: 0,
            __v: 0
        }
        let response = yield db.search['book'](limit, skip)
        this.body = response
    }
    
})

module.exports = router