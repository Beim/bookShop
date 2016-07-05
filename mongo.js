'use strict'
var mongoose = require('mongoose')
var db = mongoose.connect("mongodb://127.0.0.1:27017/bookShop")
db.connection.on("error",function(error){
	console.log("connection error : " + error)
});
db.connection.on("open",function(){
	console.log("------- 数据库连接成功！-------")
});

var userSchema = new mongoose.Schema({
	username: String,
	password: String
})

var userModel = mongoose.model('users', userSchema)

var bookSchema = new mongoose.Schema({
	bookname: String,
	writer: String,
	publishTime: String,
	publisher: String,
	condition: String,
	amount: String,
	image: String,
	bookId: String
})

var bookModel = mongoose.model('books', bookSchema)

exports.insert = {
	user: (data) => {
		return new Promise((res, rej) => {
			userModel.create(data, (err, doc) => {
				if (err) {
					console.log('create err : ' + err)
					res(0)
				}
				else {
					res(1)
				}
			})
		})
	},
	book: (data) => {
		return new Promise((res, rej) => {
			bookModel.create(data, (err, doc) => {
				if (err) {
					console.log('create err : ' + err)
					res(0)
				}
				else {
					res(1)
				}
			})
		})
	}
}

exports.search = {
	user: (limit, skip = null, idx = null) => {
		return new Promise((res, rej) => {
			userModel.find(limit, skip, idx,(err, doc) => {
				if(err){
					console.log('find err : ' + err)
					res(0)
				}
				else{
					res(doc)
				}
			})

		})
	},
	book: (limit, skip = null, idx = null) => {
		return new Promise((res, rej) => {
			bookModel.find(limit, skip, idx,(err, doc) => {
				if(err){
					console.log('find err : ' + err)
					res(0)
				}
				else{
					res(doc)
				}
			})

		})
	}
}

// var testSchema = new mongoose.Schema({
// 	name : String
// })


// var testModel = db.model('collections', testSchema)

