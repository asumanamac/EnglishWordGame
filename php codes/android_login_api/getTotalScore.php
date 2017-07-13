<?php
 require_once('conn.php');
 
 $sql = "select name,score from highscores order by score desc ";

 $res = mysqli_query($con,$sql); 
 
 
if (mysqli_num_rows($res) > 0) {
$result = array();
while($item = mysqli_fetch_array($res)){

	$result[]=$item;


$total=array();
foreach ($result as $row) { 

  $name = $row['name'];
  $score = $row['score'];
$total[$name] = ifexistsidx($total,$name);

  $total[$name] += $score;

}
$newArray = array();
foreach ($total as $name => $score) {

  array_push($newArray,array('name' => $name, 'score' =>$score));

} 
}  array_multisort($newArray, SORT_DESC, $total);
echo json_encode(array("result"=>$newArray));
}

function ifexistsidx($var,$index)
{
  return(isset($var[$index])?$var[$index]:null);
}
?>
 
 