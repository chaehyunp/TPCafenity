<?php
    header('Content-Type:application/json; charset=utf-8');

    //Delete 할 아이템의 no 정보
    $accountNo = $_GET['accountNo'];
    $id = $_GET['id'];

    $db = mysqli_connect('localhost','testhue96','t1e2s3t4!','testhue96');
    mysqli_query($db, 'set names utf8');

    // DB 실제 경로 폴더 내 이미지 삭제 준비
    $sql = "SELECT * FROM marked_shop WHERE accountNo = '$accountNo' AND id = '$id'";
    $result = mysqli_query($db, $sql);

    $row = mysqli_fetch_array($result, MYSQLI_ASSOC);

    //DB 테이블에서 삭제
    $sql = "DELETE FROM marked_shop WHERE accountNo = '$accountNo' AND id = '$id'";
    $result = mysqli_query($db, $sql);

    if($result) echo "아이템 삭제";
    else echo "아이템 삭제 실패";

    mysqli_close($db);
?>