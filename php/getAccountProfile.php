<?php
    header('Content-Type:application/json; charset=utf-8');

    $no = $_GET['no'];

    //MySQL DB [account_email]
    $db = mysqli_connect('localhost', 'testhue96', 't1e2s3t4!', 'testhue96');
    mysqli_query($db, 'set names utf8');

    $sql = "SELECT nick,email,password FROM account_email WHERE no = '$no'";
    $result = mysqli_query($db,$sql);

    //레코드 수
    $rowNum = mysqli_num_rows($result);

    $rows = array(); 
    for($i = 0; $i < $rowNum; $i++) {
        $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
        $rows[$i] = $row;
    }

    echo json_encode($rows);

    mysqli_close($db);
?>