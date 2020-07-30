<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Insert title here</title>
    <link href="default.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="js/ajaxfileupload.js"></script>

    <script type="text/javascript">
        var currentPage;

        $(document).ready(function(){
            createPage(1,5,20);
            currentPage = 1;
            createbutton(5);
        });
        function createPage(currPage, limit, total){
            var html = [], showNum = limit;
            if (total - (currPage * limit) < 0) showNum = total - ((currPage - 1) * limit);
            html.push(' <table class="table table-hover table-bordered" cellspacing="0" width="100%">');
            html.push(' <thead><tr><th style="font-size: 20px">ID</th><th style="font-size: 20px">标题</th></tr></thead><tbody>');
            console.log("ajax");

            $.ajax({
                url : "${pageContext.request.contextPath}/JsonTest",
                dataTpye : "json",
                data : {
                    "currpage":currPage,
                    "limit":limit
                },
                type : "post",
                success : function(msg) {
                    $("#showjson").html(msg);
                    var data = jQuery.parseJSON(msg);
                    if (data.length>0) {
                        for (var i = 0; i < showNum; i++) {
                            html.push('<tr><td>' + data[i].id + '</td>');
                            html.push('<td>' + data[i].name + '</td>');
                            html.push('</tr>');
                        }
                    }
                    html.push('</tbody></table>');
                    var mainObj = $('#list');
                    mainObj.empty();
                    mainObj.html(html.join(''));

                    /* for(x in parsedJson){
                        $("#show").html(parsedJson[x].id+"----"+parsedJson[x].name);
                    } */
                },
                error:function(){  //请求失败的回调方法
                    alert("请求失败，请重试");
                }
            });
        }
        function createbutton(total) {
            var inp;
            for (var i = 0; i <= total; i++) {
                if (i == total) {
                    inp = document.createElement("input");
                    inp.type = "button";
                    inp.value = "下一页";
                    inp.id = "nextpage";
                    inp.onclick = function() {
                        if (Number(currentPage) != total - 1) {
                            createPage(Number(currentPage) + 1, 5, 20);
                            currentPage = Number(currentPage) + 1;
                            console.log(currentPage);
                        }

                    };
                } else if (i == 0) {
                    inp = document.createElement("input");
                    inp.type = "button";
                    inp.value = "上一页";
                    inp.id = "nextpage";
                    inp.onclick = function() {
                        if (Number(currentPage) != 1) {
                            createPage(Number(currentPage) - 1, 5, 20);
                            currentPage = Number(currentPage) - 1;
                            console.log(currentPage);
                        }

                    };
                } else {
                    inp = document.createElement("input");
                    inp.type = "button";
                    inp.value = i;
                    inp.id = "page" + i;
                    inp.onclick = function() {
                        console.log($(this).val());
                        currentPage = $(this).val();
                        createPage($(this).val(), 5, 20);
                        console.log(currentPage);
                    };
                }

                document.getElementById("pages").appendChild(inp);
            }


        }


    </script>
</head>
<body>

<p id="showjson"></p>
<hr>
<p id="show"></p>


<div id="list">

</div>
<hr>
<br><br><br><br><br>
<div style="float:left;height:30;width: 20%" id="pages">
</div>



</body>
</html>

