<?php
   header('Content-Type:text/plain; charset=utf-8');

    $accountNo = $_POST['accountNo'];
    $id = $_POST['id'];
    $place_name = $_POST['place_name'];
    $phone = $_POST['phone'];
    $address_name = $_POST['address_name'];
    $road_address_name = $_POST['road_address_name'];
    $x = $_POST['x'];
    $y = $_POST['y'];
    $place_url = $_POST['place_url'];
    $distance = $_POST['distance'];
    
    //특수문자 - SQL에서 오동작 방지
    $accountNo = addslashes($accountNo);
    $id = addslashes($id);
    $place_name = addslashes($place_name);
    $phone = addslashes($phone);
    $address_name = addslashes($address_name);
    $road_address_name = addslashes($road_address_name);
    $x = addslashes($x);
    $y = addslashes($y);
    $place_url = addslashes($place_url);
    $distance = addslashes($distance);

    //MySQL DB
    $db = mysqli_connect('localhost', 'testhue96', 't1e2s3t4!', 'testhue96');
    mysqli_query($db, 'set names utf8');

    $sql = "INSERT INTO marked_shop (accountNo,id,place_name,phone,address_name,road_address_name,x,y,place_url,distance) 
    VALUES ('$accountNo','$id','$place_name','$phone','$address_name','$road_address_name','$x','$y','$place_url','$distance')";
    $result = mysqli_query($db, $sql);

    if($result) echo "즐겨찾기 등록 성공";
    else echo "즐겨찾기 등록 실패.";

    mysqli_close($db);
?>