<?php
   header('Content-Type:text/plain; charset=utf-8');

    $no = $_POST['no'];
    $nick = $_POST['nick'];
    $password = $_POST['password'];

    // $file = $FILES['img'];
    // $srcName= $file['name']; //원본파일명
    // $tmpName= $file['tmp_name']; //임시저장소 경로/파일명

    // $dstName= "./image/" . date('YmdHis') . $srcName;
    // move_uploaded_file($tmpName, $dstName);
    
    //특수문자 - SQL에서 오동작 방지
    $password = addslashes($password);

    //MySQL DB
    $db = mysqli_connect('localhost', 'testhue96', 't1e2s3t4!', 'testhue96');
    mysqli_query($db, 'set names utf8');

    $sql = "UPDATE account_email SET nick = '$nick', password = '$password' WHERE no = '$no'";
    $result = mysqli_query($db, $sql);

    if($result) echo "계정 수정 성공";
    else echo "계정 수정 실패.";

    mysqli_close($db);
?>