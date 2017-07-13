<?php

require_once('conn.php');
require_once('generatepass.php');


if($_POST)
{  
    $email=$_POST['email'];
   
   
    


     $hata = false;
    $sonucmesaji = "";
    if (!filter_var($email, FILTER_VALIDATE_EMAIL))
    {
        $sonucmesaji =  "Invalid email address please type a valid email!!";
        $hata=true;
    }
    if(!$hata){

    $check = mysql_query("SELECT * FROM users WHERE email = '$email'")or die(mysql_error());
$check2 = mysql_num_rows($check);

   if($check!=0)
    {

        $sifre = md5(generatepass());
        
        $query = "UPDATE users SET encrypted_password='123459877774hfjffkfk' WHERE email =$email";
        $sonuc = mysqli_query($con,$query);
        $Results = mysqli_fetch_array($sonuc);
        if(count($Results)>=1)
        {

         

            $sonucmesaji="Mail is sent successfully.Please check your email";
          

           
            $sonuc = array('sonuc' => "1", 'sonucmesaji' => $sonucmesaji);
        }
    }
        else
        {
            $hata=true;
            $sonucmesaji= "Account not found please signup now!!";
            $sonuc = array('sonuc' => "0", 'sonucmesaji' =>  $sonucmesaji);
        }
          

    }
        echo json_encode($sonuc);
    
}


?>