<?php
   header('Content-Type:text/plain; charset=utf-8');

    $postTag = $_POST['postTag'];
    $title = $_POST['title'];
    $text = $_POST['text'];
    $imgPath = $_POST['imgPath'];
    
    //특수문자 - SQL에서 오동작 방지
    $title = addslashes($title);
    $text = addslashes($text);
    $imgPath = addslashes($imgPath);

    //MySQL DB
    $db = mysqli_connect('localhost', 'testhue96', 't1e2s3t4!', 'testhue96');
    mysqli_query($db, 'set names utf8');

    $sql = "INSERT INTO community_post(postTag,title,text,imgPath) VALUES('$postTag','$title','$text','$imgPath')";
    $result = mysqli_query($db, $sql);

    if($result) echo "게시글 등록 성공";
    else echo "게시글 등록 실패.";

    mysqli_close($db);
?>