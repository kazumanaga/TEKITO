<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Java_03</title>
<link rel="stylesheet" type="text/css" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"/>
<link rel="stylesheet" href="css/tablesorter/themes/blue/style.css" type="text/css" />
<style>
div.container{
    max-width:800px;
}
h6.err{
    color:red;
}
div.result dd{
    margin-bottom:32px;
}
</style>
<style type="text/css">
<!--
#jquery-tablehover {
    width: 500px;
     background-color: #FFF;
}
#jquery-tablehover th{
     background-color: #dddd00;
}
#jquery-tablehover td {
    border: 2px solid gray;
    text-align: center;
}


#jquery-tablehover tr.tablehover-click {
    background-color: #000000;
}
table.tablesorter tbody td {
	color: #3D3Dff;
	padding: 4px;
	//background-color: #FFF;
	vertical-align: top;
}
-->
</style>

<!-- JQUERY  -->
<script
  type='text/javascript'
  src="js/jquery.js"></script>
<script
  type='text/javascript'
  src="js/jquery-ui.min.js"></script>
<script
  type='text/javascript'
  src="js/jquery-latest.js"></script>
<script
  type='text/javascript'
  src="js/jquery.tablesorter.min.js"></script>
  <script
  type='text/javascript'
  src="js/jquery.tablehover.min.js"></script>


</head>