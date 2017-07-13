<?php
 require_once('conn.php');
 
 $sql = "select word,image_name,image_path from vehicles";
 
 $res = mysqli_query($con,$sql);
 
 $result = array();
 
 while($row = mysqli_fetch_array($res)){
 array_push($result,array('word' => $row['word'] , 'name' =>$row['image_name'] , 'image_path' =>$row['image_path']));

 
 }
 
 echo json_encode(array("result_vehicles"=>$result));
 
 mysqli_close($con);