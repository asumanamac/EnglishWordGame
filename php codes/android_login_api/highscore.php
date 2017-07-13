<?php
 require_once('conn.php');
 
 $sql = "SELECT id,name,score,level,date FROM highscores";
 
 $res = mysqli_query($con,$sql);
 
 $result = array();
 
 while($row = mysqli_fetch_array($res)){
 array_push($result,array('id' => $row['id'] , 'name' =>$row['name'] , 'score' =>$row['score'] , 'level' =>$row['level'] , 'date' =>$row['date']));

 
 }
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);