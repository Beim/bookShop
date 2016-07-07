let rce = React.createElement.bind()
let rcc = React.createClass.bind()

let total = rcc({
    getInitialState: function() {
        return {
            page: '1',
            pageNum: '5',
            listData: [],
            bookname: ''
        }
    },
    componentDidMount: function() {
        let hash = window.location.hash.split('=')
        let bookname = hash[1]
        let _this = this
        let limit = {
            _page: this.state.page + ',' + this.state.pageNum,
            bookname: bookname
        }
        let callback = function(response) {
            if (response.success) {
                let listData = response.response
                _this.setState({
                    listData: listData,
                    bookname: bookname
                })
            }
        }
        this.getBook(limit, callback)
        let searchText = document.getElementById('searchText')
        if (bookname) {
            searchText.value = bookname
        }
    },
    getBook: function(limit, callback) {
        let queryStr = ''
        for (let i in limit) {
            queryStr += (i + '=' + limit[i] + '&')
        }
        let url = 'http://beim.site:3333/apiv0/book?' + queryStr
        let xhr = new XMLHttpRequest()
        xhr.responseType = 'json'
        xhr.open('GET', url)
        xhr.onload = (e) => {
            let response = e.target.response
                //Do something...
            callback(response)
        }
        xhr.send()
    },
    btnHandler: function(e) {
        let text = e.target.textContent
        let flag = 0
        if (text === '上一页') {
            flag = -1
        } else {
            flag = 1
        }
        let page = parseInt(this.state.page) + flag
        let pageNum = this.state.pageNum
        if (page > 0) {
            let _this = this
            let limit = {
                _page: page + ',' + pageNum,
                bookname: this.state.bookname
            }
            let callback = function(response) {
                if (response.success) {
                    let listData = response.response
                    _this.setState({
                        listData: listData,
                        page: '' + page,
                        pageNum: pageNum
                    })
                } else {
                    alert('没有更多')
                }
            }
            this.getBook(limit, callback)
        } else {
            alert('已到达第一页')
        }

    },
    hashChangeHandler: function() {
        let bookname = window.location.hash.split('=')[1]
        let _this = this
        let limit = {
            _page: this.state.page + ',' + this.state.pageNum,
            bookname: bookname
        }
        let callback = function(response) {
            if (response.success) {
                let listData = response.response
                _this.setState({
                    listData: listData,
                    bookname: bookname
                })
            } else {
                _this.setState({
                    listData: [],
                    bookname, bookname
                })
            }
        }
        this.getBook(limit, callback)
        let searchText = document.getElementById('searchText')
        searchText.value = bookname
    },
    render: function() {
        let listData = this.state.listData
        let list = listData.map((value, index) => {
            return (
                rce('div', {
                        'className': 'item',
                        'key': 'listData' + index
                    },
                    rce('div', {
                            'className': 'bookImage'
                        },
                        rce('img', {
                            'src': value.image
                        })
                    ),
                    rce('div', {
                        'className': 'bookname'
                    }, '书名： ' + value.bookname),
                    rce('div', {
                        'className': 'writer'
                    }, '作者: ' + value.writer),
                    rce('div', {
                        'className': 'publishTime'
                    }, '出版时间: ' + value.publishTime),
                    rce('div', {
                        'className': 'publisher'
                    }, '出版社: ' + value.publisher),
                    rce('div', {
                        'className': 'amount',
                        'data-amount': value.amount
                    }, '数量: ' + value.amount),
                    rce('div', {
                        'className': 'price'
                    }, '定价: 未知'),
                    rce('div', {
                        'className': 'bookId'
                    }, 'ID: ' + value.bookId),
                    rce('div', {
                            'className': 'collect btn btn-success',
                            'onClick': addToCar
                        },
                        
                        rce('a', null,
                            rce('img', {
                            'className': 'bookCar',
                            'src': './img/icon/bookcar.png'

                            }),
                         '收藏')
                        
                    )

                )
            )
        })
        let btnStyle = list.length > 0 ? {} : {'display': 'none'}
        return rce('div', null,
            rce('div', {
                    'className': 'list'
                },
                list,
                rce('button', {
                    'id': 'notExistBtn',
                    'onClick': this.hashChangeHandler
                })
            ),
            rce('div', {
                    'className': 'btnBar',
                    'style': btnStyle
                },
                rce('div', {
                        'className': 'prevBtn btn btn-info'
                    },
                    rce('a', {
                            'dataMyType': 'prev',
                            'onClick': this.btnHandler
                        },
                        '上一页'
                    )
                ),
                rce('div', {
                        'className': 'nextBtn btn btn-info'
                    },
                    rce('a', {
                            'dataMyType': 'next',
                            'onClick': this.btnHandler
                        },
                        '下一页'
                    )
                )
            )
        )
    }
})

ReactDOM.render(rce(total, null), document.getElementById('reactDiv'))