<?php
    header('Content-Type:application/json; charset=utf-8');

    $email = $_POST['email'];
    $password = $_POST['password'];

    //특수문자 - SQL에서 오동작 방지
    $email = addslashes($email);
    $password = addslashes($password);

    //MySQL DB [account_email]
    $db = mysqli_connect('localhost', 'testhue96', 't1e2s3t4!', 'testhue96');
    mysqli_query($db, 'set names utf8');

    $sql = "SELECT no,nick FROM account_email WHERE email ='$email' AND password = '$password'";
    $result = mysqli_query($db,$sql);

    //결과의 총 레코드 수
    $rowNum=mysqli_num_rows($result);

    $rows = array();
    for($i=0; $i<$rowNum; $i++) {
        $row=mysqli_fetch_array($result, MYSQLI_ASSOC);
        $rows[$i]=$row;
    }

    //숫자도 문자열
    echo json_encode($rows);

    mysqli_close($db);
?>