$.ajax({
        type:'POST',
        url:'php/showFav.php',
        async: false,
        success:function (data) {
            console.log(1);
            console.log(data);
            $('#collection').html(data);
        }
    })

/*window.onload=function loadXMLDoc()
{
    var xmlhttp;
    if (window.XMLHttpRequest)
    {
        // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp=new XMLHttpRequest();
    }
    else
    {
        // IE6, IE5 浏览器执行代码
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("collection").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET","localhost:63342/PJ2/src/php/showFav.php",true);
    xmlhttp.send();
}*/