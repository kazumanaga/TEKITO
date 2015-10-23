<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="header.jsp"/>
<script type="text/javascript" src="js/test.js">
</script>
<script src="js/jquery.jqGrid.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" href="css/jquery-ui.structure.min.css" />
<link rel="stylesheet" type="text/css" href="css/ui.jqgrid.css" />

<!-- カーソル -->
<body onLoad="document.LoginCheck.userId.focus()">
<!-- ログイン -->
<s:form action="LoginCheck">
    <s:if test="state == null">
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
    </s:if>
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
<table id = "jquery-tablehover" class="tablesorter">
 <thead>
    <tr>
      <th>ユーザID</th>
      <th>ユーザ名</th>
      <th>パスワード</th>
      <th>表示名</th>
    </tr>
  </thead>
  <tbody >
    <tr>
    	<td>001</td>
    	<td>AAA AAA</td>
    	<td>2000/01/01</td>
    	<td>AAA@example.com</td>
    </tr>
    <tr>
    	<td>002</td>
    	<td>BBB BBB</td>
    	<td>2000/05/10</td>
    	<td>BBB@example.com</td>
    </tr>
    <tr>
    	<td>002</td>
    	<td>CCC CCC</td>
    	<td>2000/11/07</td>
    	<td>CCC@example.com</td>
    </tr>
    <tr>
    	<td>003</td>
    	<td>DDD DDD</td>
    	<td>2000/06/11</td>
    	<td>DDD@example.com</td>
    </tr>
    <tr>
    	<td>004</td>
    	<td>EEE EEE</td>
    	<td>2000/04/23</td>
    	<td>EEE@example.com</td>
    </tr>
    <tr>
    	<td>005</td>
    	<td>FFF FFF</td>
    	<td>2000/10/30</td>
    	<td>FFF@example.com</td>
    </tr>
  </tbody>
</table>