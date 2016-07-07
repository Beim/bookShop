var shopCar = function() {
    $("#showlist").css('display', 'none');
    $("#shopCar").css('display', 'block')
}
var data = []
var addToCar = function(e) {
    let target = e.target
    let tagname = target.tagName
    while (tagname !== 'DIV' && tagname !== 'div') {
        target = target.parentNode
        tagname = target.tagName
    }
    let outDiv = target.parentNode
    var bookname = outDiv.getElementsByClassName('bookname')[0].innerHTML;
    var writer = outDiv.getElementsByClassName('writer')[0].innerHTML;
    var bookId = outDiv.getElementsByClassName('bookId')[0].innerHTML;
    var publishTime = outDiv.getElementsByClassName('publishTime')[0].innerHTML;
    var publisher = outDiv.getElementsByClassName('publisher')[0].innerHTML;
    var amount = outDiv.getElementsByClassName('amount')[0].innerHTML;
    var price = outDiv.getElementsByClassName('price')[0].innerHTML;
    var pic = outDiv.getElementsByClassName('bookImage')[0].innerHTML;
    data.push({
        bookname: bookname,
        writer: writer,
        publisher: publisher,
        bookId: bookId,
        amount: amount
    })
    var car_item = "<div class='car_item'></div>";
    $('.showCar_content_right').append(car_item);
    // var carItem = $('.showCar_content_right:last-child');
    var carItem = document.getElementsByClassName('showCar_content_right')[0].lastChild
    console.log('caritem: ' + carItem)
    var car_pic = "<img class='car_bookImage'> " + pic;
    $(carItem).append(car_pic);
    var car_bookname = "<div class='car_bookname'>" + bookname + "</div>";
    $(carItem).append(car_bookname);
    var car_writer = "<div class='car_writer'>" + writer + "</div>";
    $(carItem).append(car_writer);
    var car_bookId = "<div class='car_bookId' >" + bookId + "</div>";
    $(carItem).append(car_bookId);
    var car_publishTime = "<div class='car_publishTime'> " + publishTime + "</div>";
    $(carItem).append(car_publishTime);
    var car_publisher = "<div class='car_publisher' >" + publisher + "</div>";
    $(carItem).append(car_publisher);
    var car_amount = "<div class='car_amount' >数量：1</div>";
    $(carItem).append(car_amount);
    var car_price = "<div class='car_price' >" + price + "</div>";
    $(carItem).append(car_price);
    alert("添加成功");
}

var buy = function() {
    // console.info('im buy')
    // let url = 'http://192.168.191.9:8080/bookShop/buyBook'
    // let buy = new XMLHttpRequest()
    // buy.responseType = 'json'
    // buy.open('post', url)
    // buy.setRequestHeader('Content-Type', 'application/x-javascript;charset=UTF-8')
    // buy.onload = (e) => {
    //     let buy_response = e.target.response
    //     console.log(JSON.stringify(buy_response))
    //     //Do something...
    //     if (buy_response[0].success == 'true') {
    //         $(".showCar_content_right").empty();
    //         data = [];
    //         alert("购买成功");
    //     } else {
    //         alert("购买失败");
    //     }
    // }
    // buy.send(JSON.stringify(data))


    extend(data)
}

let emptyCar = function(isSuccess) {
    if (isSuccess) {
        $(".showCar_content_right").empty();
        data = [];
        alert('购买成功')
    } else {
    	alert('购买失败')
    }
}