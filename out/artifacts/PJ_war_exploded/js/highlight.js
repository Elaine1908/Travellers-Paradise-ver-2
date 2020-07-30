var pattern1 = /index/;
var pattern2 = /Browser/;
var pattern3 = /Search/;
var pattern4 = /upload/;
var pattern5 = /photo/;
var pattern6 = /favorites/;
var pattern7 = /friend/;

window.onload=function () {
    if(pattern1.test(location.pathname)){document.getElementById('home').style.color='lightgray'}
    else if(pattern2.test(location.pathname)){document.getElementById('browser').style.color='lightgray'}
    else if(pattern3.test(location.pathname)){document.getElementById('search').style.color='lightgray'}
    else if(pattern4.test(location.pathname)){document.getElementById('upload').style.color='lightgray'}
    else if(pattern5.test(location.pathname)){document.getElementById('myPhoto').style.color='lightgray'}
    else if(pattern6.test(location.pathname)){document.getElementById('myFavor').style.color='lightgray'}
    else if(pattern7.test(location.pathname)){document.getElementById('myFriend').style.color='lightgray'}
};

