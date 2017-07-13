<?php
 require_once('conn.php');

 
 $sql = "select name,score from highscores ";


 $res = mysqli_query($con,$sql);

while($item = mysqli_fetch_array($res))
 {
  $newcart = array();
 $result = array();
 $totals = array();

 array_push($result,array('name' => $item['name'] , 'score' =>$item['score']));


foreach ($result as $row) {
  $product = $row['name'];
 	
  $quantity = $row['score'];

  $totals[$product] += $quantity;




foreach ($totals as $product => $quantity) {
  //$newcart[] = array('name' => $product, 'score' => $quantity);
  array_push($newcart,array('name' => $product , 'score' =>$quantity));
}  
 }
 //array_push($result,array('name' => $item['name'] , 'score' =>$item['score']));
}
 echo json_encode(array("result"=>$newcart));
 

 ?>