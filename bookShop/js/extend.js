let extend = function(data) {
    let hash = {}
    let len = data.length
    for (let i = 0; i < len; i++) {
        if (hash[data[i].bookId]) {
            hash[data[i].bookId].amount -= 1
            if (hash[data[i].bookId] < 0) {
                return {
                    success: false,
                    message: '购买失败' // 数量不足
                }
            }
        } else {
            hash[data[i].bookId] = data[i]
        }
    }
    console.info(hash)
}