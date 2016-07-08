let search = function() {
    let searchText = document.getElementById('searchText')
    let text = searchText.value
    let pathname = location.pathname
    pathname = pathname.replace(/index.html/, 'showlist.html')
    location.href = pathname + '#bookname=' + text
    console.log(pathname)
    let nbtn = document.getElementById('notExistBtn')
    if (nbtn) {
        nbtn.click()
    }
   $("#showlist").css('display','block');
   $("#shopCar").css('display','none');
}