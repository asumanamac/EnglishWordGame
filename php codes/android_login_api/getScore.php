<?php
 require_once('conn.php');

 
 $sql = "select DISTINCT name,score,level from highscores ";
 


$sql1="SELECT DISTINCT a.name, a.score, a.level
FROM highscores a
INNER JOIN highscores b ON a.name != b.name
";

 $res = mysqli_query($con,$sql1);
 
 $result = array();
 $sum=0;
 
 while($row = mysqli_fetch_array($res)){
 	//$sum+=$row['score'];
 array_push($result,array('name' => $row['name'] , 'score' =>$sum, 'level' =>$row['level']));

 
 }
 
 echo json_encode(array("result"=>$result));
  
 mysqli_close($con);