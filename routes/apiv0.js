'use strict'
var router = require('koa-router')()
const db = require('../mongo.js')

router.get('/', function *(next){
    this.redirect('http://htmlpreview.github.io/?https://github.com/Beim/save/blob/master/html/API_v0.html')
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
            bookId: '1'
        }
        let response = yield db.insert['book'](bookData)
        this.body = 'Hello World@ connected : ' + response
    }
    else if (this.params.name === 'user') {
        let query = this.query
        let limit = {}
        for (let i in query) {
            limit[i] = new RegExp(query[i], 'gi')
        }
        let skip = {
            _id: 0,
            __v: 0
        }
        let response = yield db.search['user'](limit, skip)
        let length = response.length
        let success = length > 0
        this.body = {
            success: success,
            length: length,
            response: response
        }
    }
    else if (this.params.name === 'book') {
        let query = this.query
        let limit = {}
        let idx = {}
        // if (query._limit && parseInt(query._limit) > 0) {
        //     idx.limit = parseInt(query._limit)
        //     delete query._limit
        // }
        // if (query._skip && parseInt(query._skip) > 0) {
        //     idx.skip = parseInt(query._skip)
        //     delete query._skip
        // }
        if (query._page) {
            let arr = query._page.split(',')
            idx.limit = parseInt(arr[1])
            idx.skip = (parseInt(arr[0]) - 1) * idx.limit
            delete query._page
        }
        for (let i in query) {
            limit[i] = new RegExp(query[i], 'gi')
        }
        let skip = {
            _id: 0,
            __v: 0
        }
        let response = yield db.search['book'](limit, skip, idx)
        let length = response.length
        let success = length > 0
        this.body = {
            success: success,
            length: length,
            response: response
        }

    }

})

module.exports = router