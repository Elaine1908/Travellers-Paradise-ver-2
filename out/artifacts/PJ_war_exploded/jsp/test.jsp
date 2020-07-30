<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        #div1{
            top:100px;
            width: 100px;
            height: 450px;
            background: rgba(173, 168, 170, 0.19);
            position: absolute;
            left: -100px;
        }
        #div1 span{
            width: 20px;
            height: 80px;
            position: absolute;
            background: #5c3fb9;
            top: 80px;
            right: -20px;
            color: white;
            writing-mode: vertical-lr;/*从左向右 从右向左是 writing-mode: vertical-rl;*/
        }

    </style>
    <script>
        window.onload=function () {
            var oDiv = document.getElementById("div1");
            oDiv.onmouseover=function () {
                move(0,10);
            }
            oDiv.onmouseout=function () {
                move(-100,-10);
            }
        }
        var timer=null;
        function  move(target,speed) {
            var oDiv = document.getElementById("div1");
            clearInterval(timer);
            timer=setInterval(function () {
                if(oDiv.offsetLeft==target)
                {
                    clearInterval(timer);
                }
                else
                {
                    oDiv.style.left=oDiv.offsetLeft+speed+'px';
                }
            },30)
        }
    </script>
</head>
<body>

</body>
</html>

