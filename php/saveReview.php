<?php
   header('Content-Type:text/plain; charset=utf-8');

    // $dstName0 = null;
    // $dstName1 = null;
    // $dstName2 = null;
    // $dstName3 = null;
    // $dstName4 = null;
   
    $accountNo = $_POST['accountNo'];
    $shopId = $_POST['shopId'];
    $nick = $_POST['nick'];
    $text = $_POST['text'];
    

    //$fileSize = $_POST['fileSize'];

    // for($i = 0; $i < $fileSize; $i++) {
    //     $file = $FILES['img'.$i];
    //     $srcName = $file['name']; //원본파일명
    //     $tmpName= $file['tmp_name']; //임시저장소 경로/파일명

    //     $dstName.$i = './image/' . date('YmdHis') . $srcName;
    //     move_uploaded_file($tmpName, $dstName);
    // }
    
    //특수문자 - SQL에서 오동작 방지
    $nick = addslashes($nick);
    $text = addslashes($text);

    //MySQL DB
    $db = mysqli_connect('localhost', 'testhue96', 't1e2s3t4!', 'testhue96');
    mysqli_query($db, 'set names utf8');

    $sql = "INSERT INTO shop_reviews(accountNo,shopId,nick,text,imgPath1,imgPath2,imgPath3,imgPath4,imgPath5) 
    VALUES('$accountNo','$shopId','$nick','$text','$dstName0','$dstName1','$dstName2','$dstName3','$dstName4')";
    $result = mysqli_query($db, $sql);

    if($result) echo "게시글 등록 성공";
    else echo "게시글 등록 실패";

    mysqli_close($db);
?>