let extend = function(data) {

    let hash = {}
    let len = data.length

    for (let i = 0; i < len; i++) {
        let bookId = data[i].bookId.split(': ')[1]
        let amount = data[i].amount.split(': ')[1]
        if (hash[bookId]) {
            hash[bookId].amount -= 1
            if (hash[bookId].amount < 0) {
                emptyCar(false)
                return {
                    success: false,
                    message: '购买失败 数量不足' // 数量不足
                }
            }
        } else {
            hash[bookId] = {
                bookId: bookId,
                amount: amount - 1
            }
        }
    }
    let promises = []
    for (let i in hash) {
        let promise = new Promise(function(res, rej) {
            updateBookInfo(hash[i].bookId, hash[i].amount, function(response, target) {
                if (response.success) {
                    res(response)
                } else {
                    rej(response)
                }
            })
        })
        promises.push(promise)
    }
    Promise.all(promises).then(function(responses) {
        emptyCar(true)
        return({
            success: true,
            message: '购买成功'
        })
    }).catch(function(response) {
        emptyCar(false)
        return({
            success: false,
            message: '购买失败 网络错误'
        })
    })

}

let updateBookInfo = function(bookId, amount, callback) {
    let data = {
        type: 'put',
        data: {
            // 限制条件，使用bookId
            condition: {
                bookId: bookId
            },
            // 更新信息 (不可修改bookId，只建议修改amount)
            update: {
                amount: amount
            }
        }
    }
    let url = 'http://beim.site:3333/apiv0/book'
    let xhr = new XMLHttpRequest()
    xhr.responseType = 'json'
    xhr.open('POST', url)
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8')
    xhr.onload = (e) => {
        let response = e.target.response
            //Do something...
        callback(response, e.target)
    }
    xhr.send(JSON.stringify(data))
}