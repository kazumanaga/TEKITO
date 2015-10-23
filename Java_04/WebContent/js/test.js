//グローバル
var g_CurrentNum = -1;
var g_UpdataCurrentNum = -1;
var g_SelectAction = false;
var g_tableNum = -1;
// メソッド
$(document).ready( function() {

	$('#button1').click(function(){
		  $.ajax({
		    url: "testjson.json",
		    type: "GET",
		    dataType: "json",
		    cache: false,
		    success: function(data){
		    	var message = jsonParser(data);
		      $('#div1').html(message);
		    },
		    error: function(xhr, textStatus, errorThrown){
		      alert('Error! ' + textStatus + ' ' + errorThrown);
		    }
		  });
		});
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

		   gridComplete : function(){
			   SelectRow();
			   SelectRowUpdata();
		   }
		});

	$('#list').click(function(){

		SelectRowUpdata();
		});

});
function OverlapCheck(chId1,chPass1,chId2,chPass2)
{
	if(chId1 == chId2 && chPass1 == chPass2)
	{
		return true;
	}
	return false;
}
function jsonParser(data) {
    var message = "";
    var dataArray = data.error;
    for(var count in dataArray){
        message = message + dataArray[count].errorCode;
        message = message + ' ： ';
        message = message + dataArray[count].errorMessage;
        message = message + '<br/>';
    }
    return message;
}
$(function()
{
	var element0 = document.getElementById("userId");
	var element1 = document.getElementById("userPassword");
	var element2 = document.getElementById("userName");


	  // 行選択処理
	  $("tr").click( function(){
		  var CurrentIdList = $("#list").getGridParam("selrow");

		});
});
function addRow()
{
	// ID取得(キャレット変更用)
	var element0 = document.getElementById("userId");
	var element1 = document.getElementById("userPassword");
	var element2 = document.getElementById("userName");

    // 現在の最大のID番号取得
    var arrrows = $("#list").getRowData();
    var max = 0;

    for (i = 0; i < arrrows.length; i++)
    {
        var cur = parseInt(arrrows[i].id);
        if (max < cur)
        {
            max = cur;
            console.log(max);
        }
    }
    var tmpData = {
    		no: max + 1,
    };
    var str1=document.js.userid.value;
    var str2=document.js.pass.value;
    var str3=document.js.display.value;

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
				alert("同じID又は同じﾊﾟｽﾜｰﾄﾞがあります");
				overFlag = true;
				break;
			}
		}

		if(!overFlag)
		{
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

function deleteRow()
{
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

	if(CurrentIdList)
	{

		if(window.confirm('削除しますか？'))
		{
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

	// ID取得(キャレット変更用)
	var element0 = document.getElementById("userId");
	var element1 = document.getElementById("userPassword");
	var element2 = document.getElementById("userName");

	var data = {"UserNo" : listData.id,
			"UserName" : element0.value,
			"UserPass" : element1.value,
			"DispName" : element2.value,
		};

    // rowId取得(#list)
	var arrayData =[]; // 配列の初期化
	var overFlag = false;

	if(CurrentIdList)
	{
		for(var i = 0;i<=arrrows.length;i++)
		{
			if(i !=index-1)
			{
				arrayData[i] = $('#list').jqGrid('getRowData', i+1);
				if(OverlapCheck(arrayData[i].userId,arrayData[i].userPass,data.UserName,data.UserPass))
				{
					alert("同じID又は同じﾊﾟｽﾜｰﾄﾞがあります");
					overFlag = true;
					break;
				}
			}
		}
		if(!overFlag)
		{
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
				}
	     });
		}

	}
	else
	{
		alert("選択されてません");
	}


}
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
function SelectRowUpdata()
{
	// ID取得(キャレット変更用)
	var element0 = document.getElementById("userId");
	var element1 = document.getElementById("userPassword");
	var element2 = document.getElementById("userName");

	// 選択されている行データ取得
	var selectRow = $("#list").getGridParam('selrow');
	 var rowdata = $('#list').jqGrid('getRowData', selectRow);

	 if(selectRow)
	 {
		 // 要素に値渡し
			element0.value = rowdata.userId;
			element1.value = rowdata.userPass;
			element2.value = rowdata.displayName;
	 }
	 else
	 {
		 if(element0 && element0 && element0)
		{
		 // 要素空
			element0.value = '';
			element1.value = '';
			element2.value = '';
		}
	 }


}

