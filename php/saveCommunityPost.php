<?php
   header('Content-Type:text/plain; charset=utf-8');

    $postTag = $_POST['postTag'];
    $title = $_POST['title'];
    $nick = $_POST['nick'];
    $text = $_POST['text'];
    $accountNo = $_POST['accountNo'];

    //특수문자 - SQL에서 오동작 방지
    $title = addslashes($title);
    $nick = addslashes($nick);
    $text = addslashes($text);

    //이미지 파일
    $image0 = $_FILES['image0'];
    echo "1.$image0 ";
    $srcName0 = $image0['name']; //원본파일명
    echo "2.$srcName0 ";
    $tmpName0 = $image0['tmp_name']; //임시저장소 경로/파일명
    echo "3.$tmpName0 ";

    $image1 = $_FILES['image1'];
    echo "1.$image1 ";
    $srcName1 = $image1['name']; //원본파일명
    echo "2.$srcName1 ";
    $tmpName1 = $image1['tmp_name']; //임시저장소 경로/파일명
    echo "3.$tmpName1 ";

    $image2 = $_FILES['image2'];
    echo "1.$image2 ";
    $srcName2 = $image2['name']; //원본파일명
    echo "2.$srcName2 ";
    $tmpName2 = $image2['tmp_name']; //임시저장소 경로/파일명
    echo "3.$tmpName2 ";

    $image3 = $_FILES['image3'];
    echo "1.$image3 ";
    $srcName3 = $image3['name']; //원본파일명
    echo "2.$srcName3 ";
    $tmpName3 = $image3['tmp_name']; //임시저장소 경로/파일명
    echo "3.$tmpName3 ";
    
    $image4 = $_FILES['image4'];
    echo "1.$image4 ";
    $srcName4 = $image4['name']; //원본파일명
    echo "2.$srcName4 ";
    $tmpName4 = $image4['tmp_name']; //임시저장소 경로/파일명
    echo "3.$tmpName4 ";

    //이미지파일 저장
    if($image0 != "") {
        $dstName0 = "./image/".date('YmdHis').$srcName0;
        echo "4.$dstName0 ";
        move_uploaded_file($tmpName0, $dstName0);
    } else { $dstName0 = ""; }
    
    if($image1 != "") {
        $dstName1 = "./image/".date('YmdHis').$srcName1;
        echo "4.$dstName1 ";
        move_uploaded_file($tmpName1, $dstName1);
    } else { $dstName1 = ""; }

    if($image2 != "") {
        $dstName2 = "./image/".date('YmdHis').$srcName2;
        echo "4.$dstName2 ";
        move_uploaded_file($tmpName2, $dstName2);
    } else { $dstName2 = ""; }

    if($image3 != "") {
        $dstName3 = "./image/".date('YmdHis').$srcName3;
        echo "4.$dstName0 ";
        move_uploaded_file($tmpName3, $dstName3);
    } else { $dstName3 = ""; }

    if($image4 != "") {
        $dstName4 = "./image/".date('YmdHis').$srcName4;
        echo "4.$dstName4 ";
        move_uploaded_file($tmpName4, $dstName4);
    } else { $dstName4 = ""; }

    //MySQL DB
    $db = mysqli_connect('localhost', 'testhue96', 't1e2s3t4!', 'testhue96');
    mysqli_query($db, 'set names utf8');

    $sql = "INSERT INTO community_post(accountNo,postTag,title,nick,text,image0,image1,image2,image3,image4) 
    VALUES('$accountNo','$postTag','$title','$nick','$text','$dstName0','$dstName1','$dstName2','$dstName3','$dstName4')";

    $result = mysqli_query($db, $sql);

    if($result) echo "게시글 등록 성공";
    else echo "게시글 등록 실패";

    mysqli_close($db);
?>