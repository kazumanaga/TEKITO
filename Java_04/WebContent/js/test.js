//-----------------------------------------------------------
//	[test.js]	KAZUMA NAGAOKA
//
//-----------------------------------------------------------
//##グローバル変数
var g_CurrentNum = -1;
var g_UpdataCurrentNum = -1;
var g_SelectAction = false;
var g_tableNum = -1;
var g_readFile = 0;
var hiduke = new Date();

// 配列
var OrgArray = [];
var OrgDeleteFileArray = [];


//-----------------------------------------------------------
//	ロード実行
//	--スクリプトが実行できる状態になったら--
//-----------------------------------------------------------
$(document).ready( function() {


	//DataBase更新
	$("#list").jqGrid({
		    url: 'ajax/DBConection',
		    datatype: 'json',
		   mtype: 'GET',
		   colNames:['No', 'ユーザID', 'パスワード',"添付<br>書類", '表示名'],
		   colModel :[
		     {name:'id', width:95,editable:true},
		     {name:'userId', width:90},
		     {name:'userPass', width:90},
		     {index:'rb', name:'rb', align:'center',width:90,height:55, formatter:rbtnFmatter},
		     {name:'displayName', width:150},
		   ],
           cellEdit: false,                // false: セルの直接編集はしな
		   rowNum:100,
		   rowList:[10,20,30],
		   sortname: 'no',
		   sortorder: "asc",
		   scroll: true,
		   viewrecords: true,
		   caption: 'ログイン',

		   gridComplete : function(){
			   SelectRow();
			   SelectRowUpdata();
			   ReadUploadFile();
		   }
		});

	//DataBase更新
	$("#list2").jqGrid({
		    url: 'ajax/DBConection',
		    datatype: 'json',
		   mtype: 'GET',
		   colNames:['No', 'ユーザID', 'パスワード', '表示名'],
		   colModel :[
		     {name:'id', width:95,editable:true},
		     {name:'userId', width:90},
		     {name:'userPass', width:90},
		     {name:'displayName', width:150},
		   ],
           cellEdit: false,                // false: セルの直接編集はしな
		   rowNum:100,
		   rowList:[10,20,30],
		   sortname: 'no',
		   sortorder: "asc",
		   scroll: true,
		   viewrecords: true,
		   caption: 'ログイン',

		   gridComplete : function(){
			   SelectRow();
		   }
		});

	//
	function rbtnFmatter(cellvalue, options, rowObject)
	{
	    //input タグをリターンする
	    var rbtn = '<input type="button" value="添付" name="rbtn" id="rbtn' + options['rowId'] + '" ' +
	               'onclick="dialogOpenUpdate()"/>';

	    return rbtn;
	}

	$('#list').click(function(){

		SelectRowUpdata();
		});
	$("#button3").click(function ()
	{
		var year = hiduke.getFullYear();
		var month = hiduke.getMonth()+1;
		var day = hiduke.getDate();
		var hour = hiduke.getHours();
		var minute = hiduke.getMinutes();
		var second = hiduke.getSeconds();
		var huzake = document.getElementById("datetime1");
		huzake.value = year+"-"+month+"-"+day+"T"+hour+":"+minute+":"+second;
	});
	$( "#dialog" ).dialog({
		autoOpen: false,
		width: 680,
		height:320,
		buttons: []
	});

	$( "#dialogU" ).dialog({
		autoOpen: false,
		width: 680,
		height:320,
		buttons: []
	});

	// ファイル選択クリックに処理
	$("#fileselect1").click(function ()
	{
		UploadClear1();
	});
	// ファイル選択クリックに処理
	$("#fileselect2").click(function ()
	{
		UploadClear2();
	});

	$("#dialogU").dialog({
	    close: function() {
	    	UploadClear();
	    	SelectRowUpdata();

	    }
	});

	// ファイル選択後に処理
	$("#fileselect1").change(function () {
		// 現在のファイル数
		var files= document.getElementsByName("fileOs")[0].files;
		var slotNum = 0;
		slotNum = OrgArray.length;

		if(files.length == 0)
		{
			files= document.getElementsByName("fileOs")[1].files;
			// アップロード予定用
			for (var i = 0; i < files.length; i++){
				// ファイル数分スロット作成
				addfile2(i+slotNum,files[i].name);
				OrgArrayAdd(i);
			}
		}
		else
		{
			// アップロード予定用
			for (var i = 0; i < files.length; i++){
				// ファイル数分スロット作成
				addfile1(i+slotNum,files[i].name);
				OrgArrayAdd(i);
			}
		}

	  });
	// ファイル選択後に処理
	$("#fileselect2").change(function () {
		// 現在のファイル数
		var files= document.getElementsByName("fileOs")[0].files;
		if(files.length == 0)
		{
			files= document.getElementsByName("fileOs")[1].files;
			// アップロード予定用
			for (var i = 0; i < files.length; i++){
				// ファイル数分スロット作成
				addfile2(i+g_readFile,files[i].name);
				OrgArrayAdd(i);
				OrgDeleteFileArrayAdd(files[i].name);
			}
		}
		else
		{
			// アップロード予定用
			for (var i = 0; i < files.length; i++){
				// ファイル数分スロット作成
				addfile1(i+g_readFile,files[i].name);
				OrgArrayAdd(i);
			}
		}

	  });



});
//-----------------------------------------------------------
//	重複チェック
//	--ユーザID パスワード--	return bool
//-----------------------------------------------------------
function OverlapCheck(chId1,chPass1,chId2,chPass2)
{
	if(chId1 == chId2 && chPass1 == chPass2)
	{
		return true;
	}
	return false;
}
//-----------------------------------------------------------
//	ダイアログボックスverADD open
//	--jQuery--
//-----------------------------------------------------------
function dialogOpenAdd()
{
	$( "#dialogU" ).dialog( "close" );
	var element0 = $("#userName");
	element0.focus();
	var element4 = document.getElementById("addId");
    // 現在の最大のID番号取得
    var arrrows = $("#list").getRowData();
    var max = 0;
    for (i = 0; i < arrrows.length; i++)
    {
        var cur = parseInt(arrrows[i].id);
        if (max < cur)
        {
            max = cur;
        }
    }

    // 現在の最大ID割り当て
    element4.innerHTML = max+1;

	$( "#dialog" ).dialog( "open" );
}
//-----------------------------------------------------------
//ダイアログボックスverUPDATE open
//--jQuery--
//-----------------------------------------------------------
function dialogOpenUpdate()
{
	$( "#dialog" ).dialog( "close" );
    // 現在の選択されている行を取得
	var CurrentIdList = $("#list").getGridParam("selrow");

	// 登録日時はファイルを作り以降更新
	var year = hiduke.getFullYear();
	var month = hiduke.getMonth()+1;
	var day = hiduke.getDate();
	var hour = hiduke.getHours();
	var minute = hiduke.getMinutes();
	var second = hiduke.getSeconds();
	var timer2 = document.getElementById('datetime2');
	if(timer2)
	{
		timer2.value = year+"-"+month+"-"+day+"T"+hour+":"+minute+":"+second;
	}
	else
	{
		alert("エラー");
	}

	if(CurrentIdList)
	{
		$( "#dialogU" ).dialog( "open" );
	}
	else
	{
		alert("選択されてません");
	}
}
//-----------------------------------------------------------
//	データベース	追加
//
//-----------------------------------------------------------
function addRow()
{
	var element0 = $("#userId");
	var element1 = $("#userPassword");
	var element2 = $("#userName");


    // 現在の最大のID番号取得
    var arrrows = $("#list").getRowData();
    var max = 0;

    for (i = 0; i < arrrows.length; i++)
    {
        var cur = parseInt(arrrows[i].id);
        if (max < cur)
        {
            max = cur;
            //console.log(max);
        }
    }
    var tmpData = {
    		no: max + 1,
    };
    var str1=element0.val();
    var str2=element1.val();
    var str3=element2.val();

    var data = {"UserNo" : tmpData.no,
			"UserName" : str1,
			"UserPass" : str2,
			"DispName" : str3,
		};

    // rowId取得(#list)
	var arrayData =[]; // 配列の初期化
	var overFlag = false;

	if(!str1=="" && !str2=="" && !str3=="")
	{

		for(var i = 0;i<=arrrows.length;i++)
		{
			arrayData[i] = $('#list').jqGrid('getRowData', i+1);
			if(OverlapCheck(arrayData[i].userId,arrayData[i].userPass,data.UserName,data.UserPass))
			{
				alert("入力されたユーザー名とパスワードは\nすでに登録されているため\n追加登録できません");
				overFlag = true;
				break;
			}
		}

		if(!overFlag)
		{
			FileUpload1();

			 $.ajax({
		         type: "POST",
		         url: 'DataBase/DataBaseAdd',
		         data:data,
		         dataType: "json",
		         async: false,
		         success: function(){
				g_CurrentNum = 0;
				//最大行番号数選択
				//DataBase更新
				DataBaseUpdata();

					}
		     });
			 $( "#dialog" ).dialog( "close" );
		}


	}
	else
	{
		alert("未記入項目があります");
		if(str1=="")
		{
			element0.focus();
			element0.setSelectionRange(0,0);
		}
		else if(str2=="")
		{
			element1.focus();
			element1.setSelectionRange(0,0);
		}
		else if(str3=="")
		{
			element2.focus();
			element2.setSelectionRange(0,0);
		}

	}



}
//-----------------------------------------------------------
//データベース	削除
//
//-----------------------------------------------------------
function deleteRow()
{
	$( "#dialog" ).dialog( "close" );
	$( "#dialogU" ).dialog( "close" );
    // 選択されている行番号を取得
    var sel_id = $("#list").getGridParam("selrow");
    var index = $("#list").jqGrid('getInd',sel_id); // counting from 1

    // 現在の選択されている行を取得
	var CurrentIdList = $("#list").getGridParam("selrow");
    // rowId取得(#list)
    var rowIdList = $("#list").jqGrid('getDataIDs');
    // 選択されている行のﾃﾞｰﾀ取得
    var listData = $('#list').jqGrid('getRowData', CurrentIdList);
	var data = {"UserNo" : listData.id};
	var filedata = {"m_Num" : listData.id};
	if(CurrentIdList)
	{

		if(window.confirm('削除しますか？'))
		{


			$.ajax({
		        type: "POST",
		        url: 'uplo/DeleteDirectory',
		        data:filedata,
		        dataType: "json",
		        async: false,
		        success: function(){
					}
		    });
			$.ajax({
		         type: "POST",
		         url: 'DataBase/DataBaseDelete',
		         data:data,
		         dataType: "json",
		         async: false,
		         success: function(){
				//DataBase更新
				DataBaseUpdata();
				g_CurrentNum = index;


					}
		     });

		}

	}
	else
	{
		alert("選択されてません");
	}

	rowIdList = $("#list").jqGrid('getDataIDs');


    // 行選択(rowId指定)
	//$("#list").setSelection(rowIdList[1],true);
	// $("#list").setSelection(idyhoo[8],false); 反応なし
	// $("#list").resetSelection 選択解除
}
//-----------------------------------------------------------
//データベース	更新
//
//-----------------------------------------------------------
function updataRow()
{
    // 選択されている行番号を取得
    var sel_id = $("#list").getGridParam("selrow");
    var index = $("#list").jqGrid('getInd',sel_id); // counting from 1
    // 現在の選択されている行を取得
	var CurrentIdList = $("#list").getGridParam("selrow");
    // rowId取得(#list)
    var rowIdList = $("#list").jqGrid('getDataIDs');
    var listData = $('#list').jqGrid('getRowData', CurrentIdList);

    // 現在の最大のID番号取得
    var arrrows = $("#list").getRowData();

 // ID取得(キャレット変更用) ダイアログVer
	var element0 = $("#userId2");
	var element1 = $("#userPassword2");
	var element2 = $("#userName2");

    var str1=element0.val();
    var str2=element1.val();
    var str3=element2.val();

	var data = {"UserNo" : listData.id,
			"UserName" : str1,
			"UserPass" : str2,
			"DispName" : str3,
		};

    // rowId取得(#list)
	var arrayData =[]; // 配列の初期化
	var overFlag = false;

	if(CurrentIdList)
	{/*
		for(var i = 0;i<=arrrows.length;i++)
		{
			arrayData[i] = $('#list').jqGrid('getRowData', i);

			if(OverlapCheck(arrayData[i].userId,arrayData[i].userPass,data.UserName,data.UserPass))
			{
				alert("入力されたユーザー名とパスワードは\nすでに登録されているため\n更新できません");
				overFlag = true;
				break;
			}
		}*/
		if(!overFlag)
		{
			FileUpload2();
			$.ajax({
	         type: "POST",
	         url: 'DataBase/DataBaseEdit',
	         data:data,
	         dataType: "json",
	         async: false,
	         success: function(){
			//DataBase更新
			DataBaseUpdata();
			g_UpdataCurrentNum = index;
			$( "#dialogU" ).dialog( "close" );
				}
	     });

		}

	}
	else
	{
		alert("選択されてません");
	}


}
//-----------------------------------------------------------
// データベース更新
//
//-----------------------------------------------------------
function DataBaseUpdata()
{

	$("#list").trigger("reloadGrid");
	return;

	//DataBase更新
	$("#list").jqGrid({
		    url: 'ajax/DBConection',
		    datatype: 'json',
		   mtype: 'GET',
		   colNames:['No', 'ユーザID', 'パスワード', '表示名'],
		   colModel :[
		     {name:'id', width:95},
		     {name:'userId', width:90},
		     {name:'userPass', width:90},
		     {name:'displayName', width:150},
		   ],
		   rowNum:100,
		   rowList:[10,20,30],
		   sortname: 'no',
		   sortorder: "asc",
		   scroll: true,
		   viewrecords: true,
		   caption: 'ログイン',
		});

}
//-----------------------------------------------------------
// JQgrid 行選択
//
//-----------------------------------------------------------
function SelectRow()
{
	var rowMax = $("#list").getGridParam("records");
	var rowIdList = $("#list").jqGrid('getDataIDs');

	if(g_CurrentNum != -1)
	{
		if(g_CurrentNum == 0)
		{
			g_CurrentNum = rowMax;
		}
		if(g_CurrentNum == rowMax+1)
		{
			g_CurrentNum = rowMax;
		}
		g_CurrentNum--;
		$("#list").setSelection(rowIdList[g_CurrentNum],true);
		g_CurrentNum = -1;
	}
	if(g_UpdataCurrentNum != -1)
	{
		g_UpdataCurrentNum--;
		$("#list").setSelection(rowIdList[g_UpdataCurrentNum],true);
		g_UpdataCurrentNum = -1;
	}
}
//-----------------------------------------------------------
//
//
//-----------------------------------------------------------
function SelectRowUpdata()
{
	g_readFile = 0;
	UploadClear();
    // 現在の選択されている行を取得
	var CurrentIdList = $("#list").getGridParam("selrow");
    // rowId取得(#list)
    var rowIdList = $("#list").jqGrid('getDataIDs');
    var listData = $('#list').jqGrid('getRowData', CurrentIdList);

    // IDをもとにImageフォルダにアクセス
	// ajax通信 Actionへデータ(型:Integer) 送信と受信
	var data = {"m_Num" : listData.id};
	var fileNum=0;
	$.ajax({
        type: "GET",
        url: 'uplo/GetImageFileNum',
        data:data,
        dataType: "json",
        async: false,
        success: function(json){
        	fileNum = json;
			}
    });
	$.ajax({
        type: "GET",
        url: 'uplo/GetImageFileName',
        data:data,
        dataType: "json",
        async: false,
        success: function(fileData){
    		for (var i = 0; i < fileNum; i++){
    			// ファイル数分スロット作成
    			addfile2(i,fileData[i].m_filename);
    			OrgArrayAdd(i);
    			OrgDeleteFileArrayAdd(fileData[i].m_filename);
    		}
    		g_readFile = fileNum;
		}
    });
	// ID取得(キャレット変更用)
	var element0 = document.getElementById("userId2");
	var element1 = document.getElementById("userPassword2");
	var element2 = document.getElementById("userName2");
	var element3 = document.getElementById("userNum");
	var element4 = document.getElementById("uid");

	// 選択されている行データ取得
	var selectRow = $("#list").getGridParam('selrow');
	 var rowdata = $('#list').jqGrid('getRowData', selectRow);

	 if(selectRow)
	 {
		 // 要素に値渡し
			element0.value = rowdata.userId;
			element1.value = rowdata.userPass;
			element2.value = rowdata.displayName;
			element3.value = rowdata.id;
			element4.innerHTML = rowdata.id;
	 }
	 else
	 {
		 if(element0 && element0 && element0)
		{
		 // 要素空
			element0.value = '';
			element1.value = '';
			element2.value = '';
			element3.value = '';
			element4.value = '';
		}
	 }


}
function O()
{
	$.ajax({
        type: "POST",
        url: 'DataBase/DataBaseDelete',
        data:data,
        dataType: "json",
        async: false,
        success: function(){
		//DataBase更新
		T();

			}
    });

}
//-----------------------------------------------------------
//アップロード(予定/後)画像 スロット追加
// 強制的にスロット追加
//-----------------------------------------------------------
function AddFile()
{
	for(var i=0;i<5;i++)
	{
		addfile(i);
	}
}
//-----------------------------------------------------------
//アップロード(予定/後)画像 スロット追加
// 引数:(int num)
//-----------------------------------------------------------
function addfile(num)
{
	var count = num;
	var con = $(files);
	con.append("<input type='text' name='xxx' class='tekito"+count+"' /><button class='dlg-btn"+count+"'style='width:32px;height:32px;'onclick='deleteSlot("+num+")'></button>");
	$('.dlg-btn'+num).button({
		icons: { primary: "ui-icon-closethick" },
	});
}
//-----------------------------------------------------------
//アップロード(予定/後)画像 スロット追加
//引数:(int num,String name)
//-----------------------------------------------------------
function addfile1(num,name)
{
	var count = num;
	var filename = name;
	var con = $("#fileso1");
	con.append("<input type='text' name='xxx' value='"+filename+"' class='tekito"+count+"' /><button class='dlg-btn"+count+"'style='width:32px;height:32px;'onclick='deleteSlot("+num+")'></button>");
	$('.dlg-btn'+num).button({
		icons: { primary: "ui-icon-closethick" },
	});
}
//-----------------------------------------------------------
//アップロード(予定/後)画像 スロット追加
// 引数:(int num,String name)
//-----------------------------------------------------------
function addfile2(num,name)
{
	var count = num;
	var filename = name;
	var con = $("#fileso2");
	con.append("<input type='text' name='xxx"+num+"' value='"+filename+"' class='tekito"+count+"' /><button class='dlg-btn"+count+"'style='width:32px;height:32px;'onclick='deleteSlot("+num+")'></button>");
	$('.dlg-btn'+num).button({
		icons: { primary: "ui-icon-closethick" },
	});
}

//-----------------------------------------------------------
//アップロード(予定/後)画像 指定削除
//
//-----------------------------------------------------------
function deleteSlot(i)
{
	var ova = "";
	// ajax通信 Actionへデータ(型:Integer) 送信と受信
	var data = {"m_Num" : i,"param" : "asd"};
	/*$.ajax({
        type: "GET",
        url: 'uplo/GetImageFileNum',
        data:data,
        dataType: "json",
        async: false,
        success: function(json){


			}
    });
	$.ajax({
        type: "GET",
        url: 'uplo/GetImageFileName',
        dataType: "json",
        async: false,
        success: function(data){
        	alert(data[0].m_filename);
        	ova = data[0].m_filename;
        	alert(ova);
			}
    });*/
	$("#fileso1").find('.dlg-btn'+i).remove();
	$("#fileso1").find('.tekito'+i).remove();
	$("#fileso2").find('.dlg-btn'+i).remove();
	$("#fileso2").find('.tekito'+i).remove();
	OrgArraySelectAdd(i,-1);

}
//-----------------------------------------------------------
// アップロード(予定/後)画像クリア
//
//-----------------------------------------------------------
function UploadClear1()
{
	var aNode ="";
	aNode = document.getElementById("fileso1");
	for (var i =aNode.childNodes.length-1; i>0; i--) {
		aNode.removeChild(aNode.childNodes[i]);
	}
	document.getElementById("fileselect1").files = "";
	document.getElementById("fileselect1").value = "";
	OrgArrayClear();
	OrgDeleteFileArrayClear();

}
//-----------------------------------------------------------
//アップロード(予定/後)画像クリア
//
//-----------------------------------------------------------
function UploadClear2()
{
	var aNode ="";
	var files= document.getElementsByName("fileOs")[1].files;
	// 子ノード解析
	aNode = document.getElementById("fileso2");
	for (var i =aNode.childNodes.length-1; i>g_readFile*2; i--) {
		aNode.removeChild(aNode.childNodes[i]);
	}
	document.getElementsByName("fileOs")[1].files = "";
	document.getElementById("fileselect2").value = "";
}
//-----------------------------------------------------------
//アップロード(予定/後)画像クリア
//
//-----------------------------------------------------------
function UploadClear()
{
	var aNode ="";
	aNode = document.getElementById("fileso2");
	for (var i =aNode.childNodes.length-1; i>0; i--) {
		aNode.removeChild(aNode.childNodes[i]);
	}
	document.getElementById("fileselect2").files = "";
	document.getElementById("fileselect2").value = "";
	OrgArrayClear();
	OrgDeleteFileArrayClear();

}
//-----------------------------------------------------------
// アップロード画像存在表示
// 現在のあるすべての行に対して画像ある場合 色変え
//-----------------------------------------------------------
function dis(id)
{
	var Id = "#rbtn"+(id);
	$(Id).css('background', '#f3f365');
}
function FileUpload1()
{
	var formData = new FormData();
    files = $("#fileselect1").prop("files");
    // 現在の最大のID番号取得
    var arrrows = $("#list").getRowData();
    var max = 0;
    for (i = 0; i < arrrows.length; i++)
    {
        var cur = parseInt(arrrows[i].id);
        if (max < cur)
        {
            max = cur;
        }
    }


	for(var i=0, l=files.length; i<l; i+=1)
	{
		// 削除されていないファイルだけを送信するように整形
		if(OrgArray[i] != -1)
		{
			formData.append("fileOs", files[i]);
	}
  }
	formData.append("m_fileNum", max+1);
	$.ajax({
        type: "POST",
        url: 'uplo/workTest',
        data:formData,
        dataType: "json",
        async: false,
        processData: false,
        contentType: false,
        success: function(){

			}
    });

}
function FileUpload2()
{
    // 現在の選択されている行を取得
	var CurrentIdList = $("#list").getGridParam("selrow");
    var listData = $('#list').jqGrid('getRowData', CurrentIdList);

	var formData = new FormData();
    files = $("#fileselect2").prop("files");
    // 現在の最大のID番号取得
    var arrrows = $("#list").getRowData();
    var max = 0;
    for (i = 0; i < arrrows.length; i++)
    {
        var cur = parseInt(arrrows[i].id);
        if (max < cur)
        {
            max = cur;
        }
    }


	for(var i=0, l=files.length; i<l; i+=1)
	{
		// 削除されていないファイルだけを送信するように整形
		if(OrgArray[i+g_readFile] != -1)
		{
			formData.append("fileOs", files[i]);
		}
	}
	formData.append("m_fileNum", listData.id);
	$.ajax({
        type: "POST",
        url: 'uplo/workTest',
        data:formData,
        dataType: "json",
        async: false,
        processData: false,
        contentType: false,
        success: function(){

			}
    });

	for(var i=0, l=OrgArray.length; i<l; i+=1)
	{
		// 削除するファイルだけを送信するように整形
		if(OrgArray[i] == -1)
		{
			var data = {"m_Num" : listData.id,"param" : OrgDeleteFileArray[i]};
			$.ajax({
		        type: "POST",
		        url: 'uplo/DeleteFile',
		        data:data,
		        dataType: "json",
		        async: false,
		        success: function(){
					}
		    });

		}
	}

}
//-----------------------------------------------------------
// 配列要素追加
//
//-----------------------------------------------------------
function OrgArrayAdd(value)
{
	// 要素 挿入
	OrgArray.push(value);
}
//-----------------------------------------------------------
// 配列要素指定追加
//
//-----------------------------------------------------------
function OrgArraySelectAdd(i,value)
{
	// 要素 指定追加
	OrgArray[i] = value;
}
//-----------------------------------------------------------
// 配列要素全削除
//
//-----------------------------------------------------------
function OrgArrayClear()
{
	// 配列要素全削除
	OrgArray.length = 0;
}
function OrgDeleteFileArrayAdd(filename)
{
	OrgDeleteFileArray.push(filename);
}
function OrgDeleteFileArrayClear()
{
	// 配列要素全削除
	OrgDeleteFileArray.length = 0;
}
//-----------------------------------------------------------
// アップロード画像読み込み
//
//-----------------------------------------------------------
function ReadUploadFile()
{
    // リスト化されている行から読み込み
	// ID取得
    var arrrows = $("#list").getRowData();
    for (i = 0; i < arrrows.length; i++)
    {
        // IDをもとにImageフォルダにアクセス
    	// ajax通信 Actionへデータ(型:Integer) 送信と受信
    	var data = {"m_Num" : arrrows[i].id};
    	var fileNum=0;
    	$.ajax({
            type: "GET",
            url: 'uplo/GetImageFileNum',
            data:data,
            dataType: "json",
            async: false,
            success: function(json){
            	fileNum = json;
    			}
        });

        // 画像あったらアップロード画像存在表示
    	if(fileNum > 0)
    	{
        	dis(arrrows[i].id);
    	}


    }
}

function close1()
{
	$( "#dialog" ).dialog( "close" );
}
function close2()
{
	$( "#dialogU" ).dialog( "close" );
}