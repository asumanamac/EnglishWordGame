<?php
require_once("conn.php");
header('Content-Type: application/json; charset=utf-8'); 
 
if($_POST){

    $name = $_POST["name"];
    $score= $_POST["score"];
    $level = $_POST["level"];
    $email = $_POST["email"];
    
    $error = false;
    $resultmessage = "";
   
    if(!$error){
    
      $sql = "SELECT name,email FROM users WHERE email = '$email'";
      $result = mysqli_query($con,$sql);

     

      if(mysqli_num_rows($result)>0)
{

      $sql="SELECT name,level FROM highscores WHERE name='$name' AND level = '$level'";
        $result3 = mysqli_query($con,$sql);

    if(mysqli_num_rows($result3)>0)
{


   $sql = "UPDATE highscores SET score = '$score' WHERE name = '$name' AND '$score' > score AND level ='$level'";
}

else
       {
     $sql ="INSERT INTO highscores (name, score,level, date)
            VALUES
        ('$name','$score','$level',NOW())";
       }
}
     else
       {
     $sql ="INSERT INTO highscores (name, score,level, date)
            VALUES
        ('$name','$score','$level',NOW())";
       }
        /* $sql.="SELECT name,level FROM highscores WHERE name ='$name' AND level !='$level'";
          $result2 = mysqli_query($con,$sql2);
    
        $sql.="SELECT name,level FROM highscores WHERE name='$name' AND level = '$level'";
        $result3 = mysqli_query($con,$sqll);

       
       if (mysqli_num_rows($result) > 0){
        //if ($score > $row['score']){
      
     
      if (mysqli_num_rows($result3) > 0){
        $sql = "UPDATE highscores SET score = '$score' WHERE name = '$name' AND '$score' > score AND level ='$level'";

      }

      if(mysqli_num_rows($result2) > 0){
      $sql ="INSERT INTO highscores (name, score,level, date)
            VALUES
        ('$name','$score','$level',NOW())";

      }**/
      
         
     
       
    

       /*  if (mysqli_num_rows($result3) > 0){
            //while($row = mysqli_fetch_row($result)){
//if($row['level']==$level){
         
 $sql = "UPDATE highscores SET score = '$score' WHERE name = '$name' AND '$score' > score AND level ='$level'";
        
            if(mysqli_num_rows($result2) > 0)
             {

             $sql ="INSERT INTO highscores (name, score,level, date)
            VALUES
           ('$name','$score','$level',NOW())";
            }

       
        //}
           /**if($row['level']!=$level) 
           {
             $sql ="INSERT INTO highscores (name, score,level, date)
            VALUES
        ('$name','$score','$level',NOW())";
          $resultmessage = "New one is added";
            $answer = array('result' => "1", 'resultmessage' => $resultmessage);
        }    } **/

 
       

 


  
  

 
 
       

        if (!mysqli_query($con,$sql)){
        
            $resultmessage = mysqli_error($con); 
            $answer = array('result' => "0", 'resultmessage' => $resultmessage);
        }else{
            $resultmessage = "Score saving is successful";
            $answer = array('result' => "1", 'resultmessage' => $resultmessage);
        }
    
        mysqli_close($con);

    }else{
        $answer = array('result' => "0",'resultmessage' => $resultmessage); 
    }
    echo json_encode($answer , JSON_UNESCAPED_UNICODE);
}else{
    echo "Score saving Failed";
}

?>