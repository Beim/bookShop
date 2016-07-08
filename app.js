'use strict'
var app = require('koa')()
var koaRouter = require('koa-router')()
var parser = require('koa-bodyparser')
var json = require('koa-json')
var path = require('path')
var session = require('koa-session')
var koaStatic = require('koa-static')
var db = require('./mongo.js')
    // var views = require('koa-views')
    // var onerror = require('koa-onerror')
var fs = require('fs')

/* routes*/
var index = require('./routes/index')
var users = require('./routes/users')
var apiv0 = require('./routes/apiv0')

/*
	middleware
*/
app.keys = ['scret', 'keys'] //	key of session
const opts = {
        'maxAge': 60 * 60 * 1000
    } //	maxAge of session
app.use(session(app, opts))
app.use(parser({
    // detectJSON: function (ctx) {
    //     return /\.json$/i.test(ctx.path);
    // },
    extendTypes: {
        json: ['application/x-javascript'] // will parse application/x-javascript type body as a JSON string
    }
}))
app.use(json())
// app.use(logger())

app.use(function * (next) {
    if (this.method === 'POST') {
        let header = JSON.stringify(this.header)
        let body = JSON.stringify(this.request.body)
        let ws = fs.createWriteStream('./server.log', {
            flags: 'a+'
        })
        let username = 'haha'
        let bookname = 'haha'
        if (this.request.body.data && this.request.body.data.username) username = Buffer.from(this.request.body.data.username)
        if (this.request.body.data && this.request.body.data.bookname) bookname = Buffer.from(this.request.body.data.bookname)
        let str = `
            date: ${new Date()}
            ip: ${this.ip}
            host: ${this.host}
            method: ${this.method}
            header: ${header}
            body: ${body}
            username: ${username.toString('hex')}
            bookname: ${bookname.toString('hex')}
        `
        ws.write(str)
    }
    yield next
})

app.use(function * (next) {
    // var start = new Date()

    this.set('Access-Control-Allow-Origin', '*')
    this.set('Access-Control-Allow-Methods', 'GET,POST,OPTIONS')
    this.set('Access-Control-Allow-Headers', 'X-Requested-With,content-type')
    this.set('Access-Control-Allow-Credentials', true)
    if (this.method == 'OPTIONS') {
        this.status = 200
    }
    yield next
    // var ms = new Date() - start
    // console.log('%s%s - %s', this.method, this.url, ms)
})

app.use(koaStatic(path.join(__dirname, '/public')))

koaRouter.use('/', index.routes(), index.allowedMethods())
koaRouter.use('/users', users.routes(), users.allowedMethods())
koaRouter.use('/apiv0', apiv0.routes(), apiv0.allowedMethods())

app.use(koaRouter.routes())

// app.on('error', function (err, ctx) {
//   logger.error('server error', err, ctx)
// })

module.exports = app