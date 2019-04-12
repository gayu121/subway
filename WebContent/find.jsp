<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>可控制图片放大缩小还原移动效果的JS网页图片查看器</title>
<meta http-equiv="imagetoolbar" content="no">
<style type="text/css">
    body
    {
        font-family: "Verdana" , "Arial" , "Helvetica" , "sans-serif";
        font-size: 12px;
        line-height: 180%;
    }
    td
    {
        font-size: 12px;
        line-height: 150%;
    }
</style>
<script language="JavaScript">
    drag = 0
    move = 0
 
    // 拖拽对象
    // 参见：http://blog.sina.com.cn/u/4702ecbe010007pe
    var ie = document.all;
    var nn6 = document.getElementById && !document.all;
    var isdrag = false;
    var y, x;
    var oDragObj;
 
    function moveMouse(e) {
        if (isdrag) {
            oDragObj.style.top = (nn6 ? nTY + e.clientY - y : nTY + event.clientY - y) + "px";
            oDragObj.style.left = (nn6 ? nTX + e.clientX - x : nTX + event.clientX - x) + "px";
            return false;
        }
    }
 
    function initDrag(e) {
        var oDragHandle = nn6 ? e.target : event.srcElement;
        var topElement = "HTML";
        while (oDragHandle.tagName != topElement && oDragHandle.className != "dragAble") {
            oDragHandle = nn6 ? oDragHandle.parentNode : oDragHandle.parentElement;
        }
        if (oDragHandle.className == "dragAble") {
            isdrag = true;
            oDragObj = oDragHandle;
            nTY = parseInt(oDragObj.style.top + 0);
            y = nn6 ? e.clientY : event.clientY;
            nTX = parseInt(oDragObj.style.left + 0);
            x = nn6 ? e.clientX : event.clientX;
            document.onmousemove = moveMouse;
            return false;
        }
    }
    document.onmousedown = initDrag;
    document.onmouseup = new Function("isdrag=false");
 
    
 
    function smallit() {
        var height1 = images1.height;
        var width1 = images1.width;
        images1.height = height1 / 1.2;
        images1.width = width1 / 1.2;
    }
 
    function bigit() {
        var height1 = images1.height;
        var width1 = images1.width;
        images1.height = height1 * 1.2;
        images1.width = width1 * 1.2;
    }
    function realsize() {
        images1.height = images2.height / 1.6; 
        images1.width = images2.width / 1.6;
        block1.style.left = 0;
        block1.style.top = 0;
 
    }
 
    window.onload = function () {
        var height1 = document.body.clientHeight;
        var width1 = document.body.clientWidth;
        images1.height = height1 ;
        images1.width = width1;
    }
 
 
 
</script>

<style type="text/css">

td, a { font-size:12px; color:#000000 }
#Layer1 { position:absolute; z-index:100; top: 10px;right: 5px;}
#Layer2 { position:absolute; z-index:1; }

.find{position: absolute;z-index: 10;font-size: 20;float:left}
.show{position: absolute;z-index: 20;font-size: 20;float:left;top:200px}

</style>
</head>

<body rightmargin="100" topmargin="0" marginwidth="0" marginheight="0" oncontextmenu="return false"
    ondragstart="return false" onselectstart="return false" onselect="document.selection.empty()"
    oncopy="document.selection.empty()" onbeforecopy="return false" onmouseup="document.selection.empty()"
    style="overflow-y: hidden; overflow-x: hidden;">
    <form action="${pageContext.request.contextPath}/Servlet" method="post">
    <div id="Layer1">

        <table border="0" cellspacing="2" cellpadding="5" >
           
            <tr>
                <td>
                </td>
                <td>
                    <img src="image/real.png" width="30" height="30" style="cursor: hand" onclick="realsize();"
                        title="还原" alert="还原">
                </td>
               
            </tr>
            
            <tr>
                <td>
                </td>
                <td>
                    <img src="image/big.png" width="30" height="30" style="cursor: hand" onclick="bigit();"
                        title="放大">
                </td>
                <td>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <img src="image/small.png" width="30" height="30" style="cursor: hand" onclick="smallit();"
                        title="缩小">
                </td>
                <td>
                </td>
            </tr>
        </table>
    </div>
    <div id='hiddenPic' style='position: absolute; left: 0px; top: 0px; width: 0px; height: 0px;
        z-index: 1; visibility: hidden;'>
        <img name='images2' src='image/1.jpg' border='0'  />
    </div>
    <div id='block1' onmouseout='drag=0' onmouseover='dragObj=block1; drag=1;' style='z-index: 10;
        height: 0; left: 0px; position: absolute; top: 0px; width: 0' class="dragAble">
        <img name='images1' src='image/1.jpg' border='0' />
    </div>
    
    <div class="find">
            <h2 color="pink">
                换乘查询
            </h2>
            起始站：<input type="text" style="cursor: hand" name="start" ><br><br>
            终点站：<input type="text" style="cursor: hand" name="end" ><br><br>
            &nbsp&nbsp<input type="submit" value="提交" style="cursor: hand" margin="center">
            &nbsp&nbsp <input type="reset" value="重置">
        </div>
       
    <div class="show">
           <table>
    		<c:forEach items="${MinShort}" var="item">
				<tr>
					<td>${item.sname}</td>
					<td>${item.change}</td>					
					
				</tr>
			</c:forEach>
			</table>
    </div>
</form>
</body>

</html>
