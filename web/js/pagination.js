let totalCount;
let totalPage;
let pageSize = 6;
//let currentPage;

function showPage(arr,currentPage) {
for(let i=(currentPage-1)*6;i<currentPage*6;i++){
return "<div class=box>\n" +
    "        <a href=details.php?id=" + arr[i].ImageID + "><img src=../../travel-images/large/" + arr[i].PATH + " alt=image height=150 width=200></a>\n" +
    "        <div class=words>\n" +
    "        <h4>"+arr[i].Title+"</h4>\n" +
    "        <p>" +arr[i].Description+"</p>\n" +
    "        </div>\n" +
    "        </div>";
}

}

//加载数据，这个函数作用：1、页面初始化渲染，2、点击分页时调用。现在你可以理解为页面初始化用的。
// currentId 当前第几页
function loadData(currentId) {
    $.ajax({
        url: "{:url('../Search')}?currentId=" + currentId,
        type: "get",
        dataType: 'json',
        success: function (res) {
//paginatFactory函数是渲染页面的核心，稍后会做介绍。
            paginatFactory(res, currentId);
        }, error: function (res) {
            console.log(res);
        }
    })
}

function paginatFactory(res, currentId) {
    var html = "";    //html变量存储要展示的内容，下面循环不在赘述。
    for (var i = 0; i < res.data.length; i++) {
        var category = res.data[i].category === 0 ? "设计文章" : res.data[i].category === 1 ? "前端文章" : "旅游杂计";
        html += " <tr>" +
            "                    <td>" + res.data[i].title + "</td>" +
            "                    <td>" + res.data[i].content + "</td>" +
            "                    <td>" + category +
            "                    </td>" +
            "                    <td>" +
            "                        <a class=\"layui-btn layui-btn-normal\" href=\"{:url('articlelist/edit')}?id=" + res.data[i].id + "\">编辑</a>" +
            "                        <div class=\"layui-btn layui-btn-warm removeArticle\" data-id=\"" + res.data[i].id + "\">删除</div>" +
            "                    </td>" +
            "                </tr>";
    }
    $('#articlelist').html(html);    //请在body中加一个id为//articlelist的table标签来查看效果。

    //这里就是分页核心了，为了更好查看效果，请在刚才id为articlelist表格后创建一个class为paginate的ul标签。
   //disabled设置页码不能点击，如果当前页是第一页，«按钮不能点
    var pageHtml = "<li " + (res.previou ? "" : "disabled") + " data-pageid='" + res.previou + "'>«</li>";
//pageTotal来设置分页显示数量，如果>=6的话，最多显示6条，否则你懂得
    var pageTotal = res.total >= 6 ? 6 : res.total;
    //由于是两种样式，所以根据当前页来更改
    if (currentId < 6) {
        for (var j = 1; j <= pageTotal; j++) {
            pageHtml += "            <li " + (currentId === j ? "disabled" : "") + " data-pageid='" + j + "'>" + j + "</li>";
        }
    } else {
        //如果大于6，会加上...并且1、2页都在左侧留着
        for (var j = 1; j <= pageTotal; j++) {
            //小于3的话 1、2页码一直存在
            if (j < 3) {
                pageHtml += "            <li " + (currentId === j ? "disabled" : "") + " data-pageid='" + j + "'>" + j + "</li>";
            }
            //等于3，加上...
            if (j === 3) {
                pageHtml += "<li disabled class='jump'>...</li>";
            }
            //如果当前页大于三。页码需要根据当前页来渲染。
            if (currentId > 3) {
                //当前页-1，如果当前页是7，左边显示7-1=6
                if (j === 4) {
                    if (currentId === res.total) {
                        pageHtml += "<li data-pageid='" + (currentId - 2) + "'>" + (currentId - 2) + "</li>";
                    }
                    pageHtml += "<li data-pageid='" + (currentId - 1) + "'>" + (currentId - 1) + "</li>";
                }
                //当前页，注意disabled，不能在点击，
                if (j === 5) {
                    pageHtml += "<li disabled data-pageid='" + currentId + "'>" + currentId + "</li>";
                }
                //这里当前页+1，你应该懂了。
                if (j === 6 && currentId < res.total) {
                    pageHtml += "<li data-pageid='" + (currentId + 1) + "'>" + (currentId + 1) + "</li>";
                }
            }
            //这里如果当前页小于总页数的倒数第二页，...就显示。否则后面每页数了就不显示。
            if (j === 6 && currentId < (res.total - 1)) {
                pageHtml += "<li disabled class='jump'>...</li>";
            }
        }
    }
    //这里判断是不是最后一页，是就disabled
    pageHtml += "            <li " + (currentId >= res.total ? "disabled" : "") + " data-pageid='" + res.next + "'>»</li>";
    //这里就是页面直接跳转了。
    pageHtml += "<li class='jump' disabled>共" + res.total + "页, 到第<input class='entrance' value='" + currentId + "' type='text'>页</li><li data-total='" + res.total + "' class='confirm'>确定</li>"
    //最后渲染
    $('.paginate').html(pageHtml);
}

