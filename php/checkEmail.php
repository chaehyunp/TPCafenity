<?php
    header('Content-Type:text/plain; charset=utf-8');

    $email = $_POST['email'];

    //특수문자 - SQL에서 오동작 방지
    $email = addslashes($email);

    //MySQL DB [account_email]
    $db = mysqli_connect('localhost', 'testhue96', 't1e2s3t4!', 'testhue96');
    mysqli_query($db, 'set names utf8');

    $sql = "SELECT count(*) as total FROM account_email WHERE email ='$email'";
    $result = mysqli_query($db,$sql);

    $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
    echo $row['total'];

    mysqli_close($db);
?>