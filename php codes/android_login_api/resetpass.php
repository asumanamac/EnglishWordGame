<?php

require_once('conn.php');
require_once('generatepass.php');

if($_POST)
{   $hata = false;
    $sonucmesaji = "";
    $email=$_POST['email'];


    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) 
    {
        $sonucmesaji =  "Invalid email address please type a valid email!!";
        $hata=true;
    }
   if(!$hata)
    {

        
        $query = "SELECT email,id FROM users where email='$email'";
        $sonuc = mysqli_query($con,$query);
        $Results = mysqli_fetch_array($sonuc);
 
        if(count($Results)>=1)
        {

            $encrypt = md5(1290*3+$Results['id']);
            $message = "Your password reset link send to your email address.";
            $to=$email;
            $subject="Forget Password";
            $from = 'info@asumanamac.com';
            $body='Hi, Your Membership ID is  Dear user, If this e-mail does not apply to you please ignore it.It appears that you have requested a password.Your password is  Thank you.The Administration.Solve your problems.';
            $headers = "From: " . strip_tags($from);
          
            mail($to,$subject,$body,$headers);

            $sonucmesaji="Mail is sent successfully.Please check your email";
          

           
            $cevap = array('sonuc' => "1", 'sonucmesaji' => $sonucmesaji);
        }
        else
        {
            $hata=true;
            $sonucmesaji = "Account not found please signup now!!";
            $cevap = array('sonuc' => "0", 'sonucmesaji' =>  $sonucmesaji);
        }
          

    }

     header('Content-Type: application/json');
        echo json_encode($cevap);
    
}


?>