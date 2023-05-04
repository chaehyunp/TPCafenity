<?php
   header('Content-Type:text/plain; charset=utf-8');

    $nick = $_POST['nick'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    
    //특수문자 - SQL에서 오동작 방지
    $email = addslashes($email);
    $password = addslashes($password);

    //MySQL DB [account_email]
    $db = mysqli_connect('localhost', 'testhue96', 't1e2s3t4!', 'testhue96');
    mysqli_query($db, 'set names utf8');

    $sql = "INSERT INTO account_email(nick,email,password) VALUES('$nick','$email','$password')";
    $result = mysqli_query($db, $sql);

    if($result) echo "회원가입 되셨습니다";
    else echo "회원 등록 실패.";

    mysqli_close($db);
?>