<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="header.jsp"/>


<!-- カーソル -->
<body onLoad="document.LoginCheck.userId.focus()">
<span class="ui-icon ui-icon-arrowthick-2-se-nw"></span>a
<!-- ログイン -->
<s:form action="LoginCheck">
    <div>
        <div>
            ユーザー名:<s:textfield name="userId" />
        </div>
    </div>
    <div>
        <div>
            パスワード:<s:textfield name="password" type="password" />
        </div>
    </div>
            <s:if test="state == null">

            <s:submit value="ログイン"/>
            <s:if test="pattern == 'err'">
            <font size="3" color="red"><s:property value="errmsg" /></font>
			</s:if>
		    <s:if test="pattern == 'out'">
            <font size="3" color="black"><s:property value="errmsg" /></font>
			</s:if>

			</s:if>

            <s:elseif test="state != in">
            <s:submit value="値がおかしい"/>
            <s:if test="pattern == 'err'">
            <font size="3" color="red"><s:property value="errmsg" /></font>
			</s:if>
		    <s:if test="pattern == 'out'">
            <font size="3" color="black"><s:property value="errmsg" /></font>
			</s:if>
			</s:elseif>

</s:form>
<!-- ユーザマスタ -->
<s:if test="state == 'in'">
	<div id="disp">
			<s:form action="LoginOut">
            <s:submit value="ログアウト"/>
            <s:if test="pattern == 'ok'">
            <font size="3" color="black"><s:property value="errmsg" /></font>
			</s:if>
			</s:form>
	<br>
	<div style="border:#aa66aa solid 1px;width:800px;position: relative;font-size: 18px;font-weight: 700;color: #aa66aa;">
	<span style="position:absolute; top:-10px; left:30px; background-color: #fff;">ユーザーマスター編集</span>
	</div>
<br>

	<form name="js">
	<p>
	<input type='button' id='button3' onClick="addRow()" value='追加'>
	<input type='button' id='button2' onClick="updataRow()"  value='更新'>
	<input type='button' id='button0' onClick="deleteRow()" value='削除'>
	</p>

	<ul>
	<li style="display: inline;margin-right: 120px;top:10px;">ユーザ名</li>
	<li style="display: inline;margin-right: 120px;">パスワード</li>
	<li style="display: inline;">表示名</li>
	</ul>
	<p>
	<input type="text" name="userid" id = "userId" />
	<input type="text" name="pass" id = "userPassword" />
	<input type="text" name="display" id = "userName" />
	</p>
	</form>
	</div>

</s:if>

<br>
<!-- テーブル -->
<table id="list"></table>

<div style="color:#a5a5ff; font-size:20pt; font-weight:bold;">ログイン</div>

</body>
<div id="dialog" title="新規追加">

</div>