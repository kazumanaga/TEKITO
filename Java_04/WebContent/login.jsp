<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="header.jsp"/>
<script type="text/javascript" src="js/test.js"></script>

<!-- カーソル -->
<body>
<!-- ログイン -->
<s:form action="LoginCheck">
    <s:if test="state == null">
	    <div>
	        <div>
	            ユーザー名:<s:textfield name="userId0" />
	        </div>
	    </div>
	    <div>
	        <div>
	            パスワード:<s:textfield name="password" type="password" />
	        </div>
	    </div>
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
	<input type='button' id='button3' onClick="dialogOpenAdd()" value='追加'>
	<input type='button' id='button2' onClick="dialogOpenUpdate()"  value='更新'>
	<input type='button' id='button0' onClick="deleteRow()" value='削除'>
	</p>

<!-- ダイアログ -->
	<div id="dialog" title="新規追加">
	登録日時：<input type="datetime-local" step=1 id="datetime1">
	<br><br>
	<b>ID: </b><b id ="addId"></b>
	<p>ユーザ名:<input type="text" name="userid" id = "userId" /></p>
	<p>パスワード:<input type="text" name="pass" id = "userPassword" /></p>
	<p>表示名:<input type="text" name="display" id = "userName" /></p>
	<s:form action="Upload" enctype="multipart/form-data">
		<input id="fileselect1" name="fileOs" type="file" multiple="multiple"/>
	</s:form>
			<button class="dlg-btn" onClick="addRow()" style="position: absolute; top: 10px; left:400px;">OK</button>
			<button class="dlg-btn" onClick="close1()"style="position: absolute; top: 10px; left:500px;">Cancel</button>
		<div id="fileso1" style = "position: absolute;top: 50px; left:400px;">
		</div>
	</div>
	<script>
	$('.dlg-btn').button({
		icons: { primary: "ui-icon-check" },
	});
	</script>

	</form>

	</div>

<!-- ダイアログ -->
	<div id="dialogU" title="更新">
		<s:form action="Upload" enctype="multipart/form-data">
		登録日時：<input type="datetime-local" step=1 id="datetime2">
		<br><br>
		<b>ID: </b><b id ="uid"></b><input type="hidden" name="uid" id = "userNum" />
		<p>ユーザ名:<input type="text" name="userid" id = "userId2" /></p>
		<p>パスワード:<input type="text" name="pass" id = "userPassword2" /></p>
		<p>表示名:<input type="text" name="display" id = "userName2" /></p>

		<div id = "mass">
		</div>
		<input id="fileselect2" name="fileOs" type="file" multiple="multiple"/>
		</s:form>
			<button class="dlg-btn" onClick="updataRow()" style="position: absolute; top: 10px; left:400px;">OK</button>
			<button class="dlg-btn" onClick="close2()"style="position: absolute; top: 10px; left:500px;">Cancel</button>
		<div id="fileso2" style = "position: absolute;top: 50px; left:400px;">
		</div>
	</div>
	<script>
	$('.dlg-btn').button({
		icons: { primary: "ui-icon-check" },
	});
	</script>


</s:if>

<br>
<!-- テーブル -->
<s:if test="state == 'in'">
<table id="list"></table>
</s:if>
<s:else>
<table id="list2"></table>
</s:else>